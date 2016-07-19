$(document).on('click', '#chat_button', function() {
    var screen_top = $(window).scrollTop();
    $('#chat_menu').css('top', screen_top);
    $('html').toggleClass('chat_menu_open');
});

$(document).on('click', '.task_header,.chat_option', function() {
    $('.task_box').toggleClass('active');
	if ($.isNumeric($(this).find('.chat_name').attr('id'))){
		$('.task_header').text('Issue '+$(this).find('.chat_name').attr('id'));
	    $.ajax({
			type: "POST",
			url: "/issue/readmsg",
			beforeSend: function (xhr) {
				xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
			},
			data: {
				id: $(this).find('.chat_name').attr('id')
			},
			success: function (data) {
				$('#task_conversation').append(data);
			}
		});
	}
    $('#task_conversation').scrollTop($('#task_conversation')[0].scrollHeight);
});

function update_channel_table(data){
    $.ajax({
        type: "post",
        url: "/issue/updateChannel",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
			met:0,
            data: data
        },
        success: function() {
            drag_and_drop();
			update_record_channel(data);
        }
    });
}
function update_record_channel(data){
    $.ajax({
        type: "post",
        url: "/issue/updateChannel",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
			met:1,
            data: data
        },
        success: function() {
            drag_and_drop();
        }
    });
}
$(document).on('submit', '#create_chat_messge_form', function(){
	var user = $('.navbar-text').text().split(' ')[1];
	var task = $('.task_header').text().split(' ')[1];
    var data = $('#taskMessage').val();
    $('#taskMessage').val('');
    $('#taskMessage').attr('disabled','disabled');
    $theForm = $(this);
    // send xhr request
    $.ajax({
        type: "post",
        url: "/issue/new",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
			channelId: parseInt(task),
			username: user,
            data: data
        },
        success: function (data) {
            $('#task_conversation').append(data);
            $('#task_conversation').scrollTop($('#task_conversation')[0].scrollHeight);
            $('#taskMessage').removeAttr('disabled');
        }
    });
    // prevent submitting again
    return false;
});

setInterval(function() {
    var id = $('.chat_msg').length ? $('.chat_msg:last').data('message-id') : $('.task_header').text().split(' ')[1];
    $.ajax({
        type: "POST",
        url: "/issue/readmsg",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
            id: id,
        },
        success: function (data) {
            $('#task_conversation').append(data);
        }
    });
}, 1000);