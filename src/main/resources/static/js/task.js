$(document).on('click', '#chat_button', function() {
    var screen_top = $(window).scrollTop();
    $('#chat_menu').css('top', screen_top);
    $('html').toggleClass('chat_menu_open');
});

$(document).on('click', '.task_header,.chat_option', function() {
    $('.task_box').toggleClass('active');
	if ($.isNumeric($(this).find('.chat_name').attr('id'))){
		$('.task_header').text('Issue '+$(this).find('.chat_name').attr('id'));
	}
    $('#task_conversation').scrollTop($('#task_conversation')[0].scrollHeight);
});
$(document).on('submit', '#create_chat_messge_form', function(){
	var user = $('.navbar-text').text().split(' ')[1];
    var data = $('#taskMessage').val();
    $('#taskMessage').val('');
    $('#taskMessage').attr('disabled','disabled');
    $theForm = $(this);
    // send xhr request
    $.ajax({
        type: "post",
        url: "/chat_msg/new",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
		//	channelid: ,		figure this out to get the channelid using taskid
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
/*
setInterval(function() {
    var mid = $('.chat_msg').length ? $('.chat_msg:last').data('message-id') : 0;
    $.ajax({
        type: "POST",
        url: "/chat_msg/read",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
//            id: id,
        },
        success: function (data) {
            $('#task_conversation').append(data);
        }
    });
}, 1000);*/