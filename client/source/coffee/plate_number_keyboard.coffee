$(document).on 'touchstart','dd.key', (e) ->
  e.preventDefault()
  me = $(this)
  if not me.hasClass('disable')
    me.addClass('active')

console.log 'localStorage :', localStorage
$(document).on 'touchend','dd.key.delete_key', (e) ->
  e.preventDefault()
  $input = $('.license_plate_input')
  input_before = $input.text()
  before_len = input_before.length
  spacing = input_before.endsWith ' '

  if spacing
  	input_after = input_before.substring 0, before_len - 2
  else	
  	input_after = input_before.substring 0, before_len - 1

  after_len = input_after.length
  if after_len is 0
  	$('.pn_wapper').removeClass 's2'

  $input.text input_after


$(document).on 'touchend','dd.key', (e) ->
  e.preventDefault()
  me = $(this)
  $input = $('.license_plate_input')
  if not me.hasClass('disable')
    me.removeClass('active')
    val = me.attr('value')

    num_key = $('.pn_wapper .pn_2 dd').filter('.num_key')
    letter_key = $('.pn_wapper .pn_2 dd').filter('.letter_key')
    last_key = $('.pn_wapper .pn_2 dd').filter('.last_key')

    input_before = $input.text()
    before_len = input_before.length

    # 7位车牌号
    if before_len < 8
    	$input.append(val)
	    input_after = $input.text()
	    after_len = input_after.length
	    last_key.addClass('disable')

	    if after_len is 1
	        $('.pn_wapper').addClass('s2')
	        num_key.addClass('disable')
	    if after_len > 1
	    	num_key.removeClass('disable')
	    if after_len is 2
	    	$input.append(' ')
	    if after_len is 7
	    	last_key.removeClass('disable')