package fr.eseo.atribus.beans;

/*
 * Copyright (c) 2011, Jade Cheng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met: * Redistributions of source code must retain the
 * above copyright notice, this list of conditions and the following disclaimer. * Redistributions
 * in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution.
 * * Neither the name of the University of Hawaii nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY Jade Cheng ''AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL Jade Cheng BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RechercheGoogle {
  private static final Logger LOGGER = Logger.getLogger(RechercheGoogle.class.getName());

  public RechercheGoogle() {
    super();
  }

  public String getRequete(final String[] args) {
    if (args.length == 0) {
      return null;
    }

    try {
      final URL url =new URL("https://openweathermap.org/city/3037656");
      final String html = downloadString(url);
      RechercheGoogle.LOGGER.log(Level.INFO, html,html);
      return html;

    } catch (final IOException ioe) {
      RechercheGoogle.LOGGER.log(Level.INFO, "Exception", ioe);
    }
    return null;
  }

  /**
   * Reads all contents from an input stream and returns a string from the data.
   * 
   * @param stream The input stream to read.
   * 
   * @return A string built from the contents of the input stream.
   * 
   * @throws IOException Thrown if there is an error reading the stream.
   */
  private static String downloadString(final InputStream stream) throws IOException {
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    int ch;
    while (-1 != (ch = stream.read()))
      out.write(ch);
    return out.toString();
  }

  /**
   * Downloads the contents of a URL as a String. This method alters the User-Agent of the HTTP
   * request header so that Google does not return Error 403 Forbidden.
   * 
   * @param url The URL to download.
   * 
   * @return The content downloaded from the URL as a string.
   * 
   * @throws IOException Thrown if there is an error downloading the content.
   */
  private static String downloadString(final URL url) throws IOException {
    final String agent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US)";
    final URLConnection connection = url.openConnection();
    connection.setRequestProperty("User-Agent", agent);
    final InputStream stream = connection.getInputStream();
    return downloadString(stream);
  }

  /**
   * Encodes a string of arguments as a URL for a Google search query.
   * 
   * @param args The array of arguments to pass to Google's search engine.
   * 
   * @return A URL for a Google search query based on the arguments.
   */
  private static URL encodeGoogleQuery(final String[] args) {
    try {
      final StringBuilder localAddress = new StringBuilder();
      localAddress.append("/search?q=");
      for (int i = 0; i < args.length; i++) {
        final String encoding = URLEncoder.encode(args[i], "UTF-8");
        localAddress.append(encoding);
        if (i + 1 < args.length)
          localAddress.append("+");
      }
      return new URL("https", "www.google.com", localAddress.toString());
    } catch (final IOException ioe) {
      RechercheGoogle.LOGGER.log(Level.INFO, "Exception", ioe);
    }
    return null;
  }


}
