validator.message['date'] = 'Date invalide';

$('form').on('blur', 'input[required], input.optional, select.required',
		validator.checkField).on('change', 'select.required',
		validator.checkField).on('keypress', 'input[required][pattern]',
		validator.keypress);

$('.multi.required').on('keyup blur', 'input', function() {
	validator.checkField.apply($(this).siblings().last()[0]);
});

$('form').submit(function(e) {
	e.preventDefault();
	var submit = true;
	// evaluate the form using generic validaing
	if (!validator.checkAll($(this))) {
		submit = false;
	}

	if (submit)
		this.submit();
	return false;
});

$('#vfields').change(function() {
	$('form').toggleClass('mode2');
}).prop('checked', false);

$('#alerts').change(function() {
	validator.defaults.alerts = (this.checked) ? false : true;
	if (this.checked)
		$('form .alert').remove();
}).prop('checked', false);
