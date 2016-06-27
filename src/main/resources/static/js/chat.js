$(document).on('click', '#chat_button', function() {
    var screen_top = $(window).scrollTop();
    $('#chat_menu').css('top', screen_top);
    $('html').toggleClass('chat_menu_open');
});

$(document).on('click', '.chat_header, .chat_option', function() {
    $('.chat_box').toggleClass('active');
});
$(document).on('submit', '#create_messge_form', function(){
    $theForm = $(this);

    // send xhr request
    $.ajax({
        type: "post",
        url: "/chat_msg/new",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
			channel: '0',
			username: 'Jerry',
            data: $('#message').val()
        },
        success: function(data) {
            $('.chat_box').html(data);
            $.jGrowl({ title: "Success!", message: "Message added" });
        }
    });

    // prevent submitting again
    return false;
});

function set_csrf(xhr){
    return xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
}