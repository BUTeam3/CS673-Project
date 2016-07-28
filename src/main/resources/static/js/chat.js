//header of general chat opens and closes
$(document).on('click', '.chat_header', function() {
    $('.chat_box').toggleClass('active');
    $('#chat_conversation').scrollTop($('#chat_conversation')[0].scrollHeight);
});
//Submits message to general chat
$(document).on('submit', '#create_messge_form', function(){
	var user = $('.navbar-text').text().split(' ')[1];
    var data = $('#message').val();
    $('#message').val('');
    $('#message').attr('disabled','disabled');
    $theForm = $(this);
    $.ajax({
        type: "post",
        url: "/chat_msg/new",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
			//sends to general chat with user logged in and message
			channelId: '0',
			username: user,
            data: data
        },
		//displays message in chat, scrolls to bottom, enables input box
        success: function (data) {
            $('#chat_conversation').append(data);
            $('#chat_conversation').scrollTop($('#chat_conversation')[0].scrollHeight);
            $('#message').removeAttr('disabled');
        }
    });
    // prevent submitting again
    return false;
});
//refreshes chat to update from other users
setInterval(function() {
    var mid = $('#chat_conversation .chat_msg').length ? $('#chat_conversation .chat_msg:last').data('message-id') : 0;
	var id = 0;
    $.ajax({
        type: "POST",
        url: "/chat_msg/read",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
            mid: mid,
			id: id
        },
        success: function (data) {
            $('#chat_conversation').append(data);
            $('#chat_conversation').scrollTop($('#chat_conversation')[0].scrollHeight);
        }
    });
}, 1000);