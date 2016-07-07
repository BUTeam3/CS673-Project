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
    var scroll_top = $('#chat_conversation').scrollTop();
    $.ajax({
        type: "get",
        url: "/chat_msg/read",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        success: function (data) {
            $('#chat_conversation').replaceWith(data);
            var out = $('#chat_conversation');
            var isScrolledToBottom = out.scrollHeight - out.clientHeight <= out.scrollTop + 1;
            if(isScrolledToBottom){
                out.scrollTop = out.scrollHeight - out.clientHeight;
            }
        }
    });
}, 1000);