package fr.eseo.atribus.beans;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * La classe PasswordStorage.
 */
public class PasswordStorage {

  /** La constante PBKDF2_ALGORITHM. */
  public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(PasswordStorage.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La constante SALT_BYTE_SIZE. */
  // These constants may be changed without breaking existing hashes.
  public static final int SALT_BYTE_SIZE = 24;

  /** La constante HASH_BYTE_SIZE. */
  public static final int HASH_BYTE_SIZE = 64;

  /** La constante PBKDF2_ITERATIONS. */
  public static final int PBKDF2_ITERATIONS = 64000;

  /** La constante HASH_SECTIONS. */
  // These constants define the encoding and may not be changed.
  public static final int HASH_SECTIONS = 5;

  /** La constante HASH_ALGORITHM_INDEX. */
  public static final int HASH_ALGORITHM_INDEX = 0;

  /** La constante ITERATION_INDEX. */
  public static final int ITERATION_INDEX = 1;

  /** La constante HASH_SIZE_INDEX. */
  public static final int HASH_SIZE_INDEX = 2;

  /** La constante SALT_INDEX. */
  public static final int SALT_INDEX = 3;

  /** La constante PBKDF2_INDEX. */
  public static final int PBKDF2_INDEX = 4;

  /**
   * Instancie un nouveau PasswordStorage.
   */
  private PasswordStorage() {
    super();
  }

  /**
   * La Classe InvalidHashException.
   */
  public static class InvalidHashException extends Exception {

    /** La constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instancie un nouveau InvalidHashException.
     *
     * @param message le message renvoyé par l'exception
     */
    public InvalidHashException(String message) {
      super(message);
    }

  }

  /**
   * La Classe CannotPerformOperationException.
   */
  public static class CannotPerformOperationException extends Exception {

    /** La constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instancie un nouveau cannotPerformOperationException.
     *
     * @param message le message renvoyé par l'exception
     */
    public CannotPerformOperationException(String message) {
      super(message);
    }

  }

  /**
   * Fonction créant le hash.
   *
   * @param password le mot de passe
   * @return Le paramètre string
   * @throws CannotPerformOperationException de type CannotPerformOperationException
   */
  public static String createHash(String password) throws CannotPerformOperationException {
    return PasswordStorage.createHash(password.toCharArray());
  }

  /**
   * Fonction créant le hash.
   *
   * @param password le mot de passe
   * @return Le paramètre string
   * @throws CannotPerformOperationException de type CannotPerformOperationException
   */
  public static String createHash(char[] password) throws CannotPerformOperationException {
    // Generate a random salt
    final SecureRandom random = new SecureRandom();
    final byte[] salt = new byte[PasswordStorage.SALT_BYTE_SIZE];
    random.nextBytes(salt);

    // Hash the password
    final byte[] hash = PasswordStorage.pbkdf2(password, salt, PasswordStorage.PBKDF2_ITERATIONS,
        PasswordStorage.HASH_BYTE_SIZE);
    final int hashSize = hash.length;

    // format: algorithm:iterations:hashSize:salt:hash
    return "sha256:" + PasswordStorage.PBKDF2_ITERATIONS + ":" + hashSize + ":"
        + PasswordStorage.toBase64(salt) + ":" + PasswordStorage.toBase64(hash);
  }

  /**
   * Fonction verifiant le mot de passe.
   *
   * @param password le mot de passe
   * @param correctHash le hash correct
   * @return true, si réussi
   * @throws CannotPerformOperationException de type cannotPerformOperationException
   * @throws InvalidHashException de type invalidHashException
   */
  public static boolean verifyPassword(String password, String correctHash)
      throws CannotPerformOperationException, InvalidHashException {
    return PasswordStorage.verifyPassword(password.toCharArray(), correctHash);
  }

  /**
   * Fonction verifiant le mot de passe.
   *
   * @param password le mot de passe
   * @param correctHash le correctHash
   * @return true, si réussi
   * @throws CannotPerformOperationException de type cannotPerformOperationException
   * @throws InvalidHashException de type invalidHashException
   */
  public static boolean verifyPassword(char[] password, String correctHash)
      throws CannotPerformOperationException, InvalidHashException {
    // Decode the hash into its parameters
    final String[] params = correctHash.split(":");
    if (params.length != PasswordStorage.HASH_SECTIONS) {
      throw new InvalidHashException("Fields are missing from the password hash.");
    }

    // Currently, Java only supports SHA1.
    if (!"sha256".equals(params[PasswordStorage.HASH_ALGORITHM_INDEX])) {
      throw new CannotPerformOperationException("Unsupported hash type.");
    }

    int iterations = 0;
    try {
      iterations = Integer.parseInt(params[PasswordStorage.ITERATION_INDEX]);
    } catch (final NumberFormatException ex) {
      throw new InvalidHashException("Could not parse the iteration count as an integer.");
    }

    if (iterations < 1) {
      throw new InvalidHashException("Invalid number of iterations. Must be >= 1.");
    }

    byte[] salt = null;
    try {
      salt = PasswordStorage.fromBase64(params[PasswordStorage.SALT_INDEX]);
    } catch (final IllegalArgumentException ex) {
      PasswordStorage.LOGGER.log(Level.INFO, EXCEPTION, ex);
      throw new InvalidHashException("Base64 decoding of salt failed.");
    }

    byte[] hash = null;
    try {
      hash = PasswordStorage.fromBase64(params[PasswordStorage.PBKDF2_INDEX]);
    } catch (final IllegalArgumentException ex) {
      PasswordStorage.LOGGER.log(Level.INFO, EXCEPTION, ex);
      throw new InvalidHashException("Base64 decoding of pbkdf2 output failed.");
    }

    int storedHashSize = 0;
    try {
      storedHashSize = Integer.parseInt(params[PasswordStorage.HASH_SIZE_INDEX]);
    } catch (final NumberFormatException ex) {
      throw new InvalidHashException("Could not parse the hash size as an integer.");
    }

    if (storedHashSize != hash.length) {
      throw new InvalidHashException("Hash length doesn't match stored hash length.");
    }

    // Compute the hash of the provided password, using the same salt,
    // iteration count, and hash length
    final byte[] testHash = PasswordStorage.pbkdf2(password, salt, iterations, hash.length);
    // Compare the hashes in constant time. The password is correct if
    // both hashes match.
    return PasswordStorage.slowEquals(hash, testHash);
  }

  /**
   * Slow equals.
   *
   * @param aa le aa
   * @param bb le bb
   * @return true, si réussi
   */
  private static boolean slowEquals(byte[] aa, byte[] bb) {
    int diff = aa.length ^ bb.length;
    for (int i = 0; i < aa.length && i < bb.length; i++) {
      diff |= aa[i] ^ bb[i];
    }
    return diff == 0;
  }

  /**
   * Pbkdf2.
   *
   * @param password le mot de passe
   * @param salt le salt
   * @param iterations le iterations
   * @param bytes le bytes
   * @return Le paramètre byte[]
   * @throws CannotPerformOperationException de type cannotPerformOperationException
   */
  private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
      throws CannotPerformOperationException {
    try {
      final PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
      final SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
      return skf.generateSecret(spec).getEncoded();
    } catch (final NoSuchAlgorithmException ex) {
      PasswordStorage.LOGGER.log(Level.INFO, EXCEPTION, ex);
      throw new CannotPerformOperationException("Hash algorithm not supported.");
    } catch (final InvalidKeySpecException ex) {
      PasswordStorage.LOGGER.log(Level.INFO, EXCEPTION, ex);
      throw new CannotPerformOperationException("Invalid key spec.");
    }
  }

  /**
   * From base64.
   *
   * @param hex le hex
   * @return Le paramètre byte[]
   */
  private static byte[] fromBase64(String hex) {
    return DatatypeConverter.parseBase64Binary(hex);
  }

  /**
   * To base64.
   *
   * @param array le array
   * @return Le paramètre string
   */
  private static String toBase64(byte[] array) {
    return DatatypeConverter.printBase64Binary(array);
  }

}
