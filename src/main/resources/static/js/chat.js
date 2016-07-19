$(document).on('click', '.chat_header', function() {
    $('.chat_box').toggleClass('active');
    $('#chat_conversation').scrollTop($('#chat_conversation')[0].scrollHeight);
});
$(document).on('submit', '#create_messge_form', function(){
	var user = $('.navbar-text').text().split(' ')[1];
    var data = $('#message').val();
    $('#message').val('');
    $('#message').attr('disabled','disabled');
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