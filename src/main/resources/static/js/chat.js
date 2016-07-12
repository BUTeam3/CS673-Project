$(document).on('click', '#chat_button', function() {
    var screen_top = $(window).scrollTop();
    $('#chat_menu').css('top', screen_top);
    $('html').toggleClass('chat_menu_open');
});

$(document).on('click', '.chat_header, .chat_option', function() {
    $('.chat_box').toggleClass('active');
});
$(document).on('submit', '#create_messge_form', function(){
    var data = $('#message').val();
    $('#message').val('');
    $('#message').attr('disabled','disabled');
	var user = $('#message').data('user');
    $theForm = $(this);
    // send xhr request
    $.ajax({
        type: "post",
        url: "/chat_msg/new",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
			channelId: '0',
			username: user,
            data: data
        },
        success: function (data) {
            $('#chat_conversation').append(data);
            $('#chat_conversation').scrollTop($('#chat_conversation')[0].scrollHeight);
            $('#message').removeAttr('disabled');
        }
    });
	
	
    // prevent submitting again
    return false;
});

$(function(){
    $('#chat_conversation').scrollTop($('#chat_conversation')[0].scrollHeight);
});


setInterval(function() {
    var mid = $('.chat_msg').length ? $('.chat_msg:last').data('message-id') : 0;
    $.ajax({
        type: "POST",
        url: "/chat_msg/read",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
            mid: mid,
        },
        success: function (data) {
            $('#chat_conversation').append(data);
        }
    });
}, 1000);