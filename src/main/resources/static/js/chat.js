$(document).on('click', '#chat_button', function() {
    var screen_top = $(window).scrollTop();
    $('#chat_menu').css('top', screen_top);
    $('html').toggleClass('chat_menu_open');
});

$(document).on('click', '.chat_header, .chat_option', function() {
    $('.chat_box').toggleClass('active');
});
$(document).on('submit', '#create_messge_form', function(){
	
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
            data: $('#message').val()
        },
        success: function (data) {
            $('#create_messge_form input').val('');
        }
    });
	
	
    // prevent submitting again
    return false;
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