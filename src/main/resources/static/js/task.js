//Opens and closes task sidebar menu
$(document).on('click', '#chat_button', function() {
    var screen_top = $(window).scrollTop();
    $('#chat_menu').css('top', screen_top);
    $('html').toggleClass('chat_menu_open');
});
//header of issue chat opens and closes chat
//options in sidebar menu just changes name of chat
$(document).on('click', '.chat_option, .task_header', function() {
	var idNum=$(this).find('.chat_name').attr('id');
	if(!($('.task_box').hasClass('active'))&&$(this).attr('class')=='chat_option'){
		$('.task_box').toggleClass('active');
	}else if($(this).attr('class')=='task_header'){
		$('.task_box').toggleClass('active');
	}
	//function to change the name of task chat name
	if ( $.isNumeric(idNum) && idNum!=$('.task_header').text().split(' ')[1]){
		$('.task_header').text('Issue '+idNum);		
		$('#task_conversation').empty();		
	    $.ajax({
			type: "POST",
			url: "/issue/readmsg",
			beforeSend: function (xhr) {
				xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
			},
			data: {
				id: idNum
			},
			success: function (data) {
				$('#task_conversation').append(data);
			}
		});
	}
    $('#task_conversation').scrollTop($('#task_conversation')[0].scrollHeight);
});
/**
 * Updates the channel table with a new relation between messages and record table
 * 
 * param: message that user sent 
 */
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
/**
 * Updates the record table with channelid that was just created from update_channel_table function
 * 
 * param: message that user sent 
 */
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
	var channelid = $('.task_header').text().split(' ')[1];
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
			channelId: parseInt(channelid),
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
//refreshes chat to update from other users
setInterval(function() {
    var id=$('.task_header').text().split(' ')[1];
	if ($.isNumeric(id)){
    id = $('#task_conversation li').length ? -1 : $('.task_header').text().split(' ')[1];
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
				$('#task_conversation').scrollTop($('#task_conversation')[0].scrollHeight);
			}
		});
	}
}, 1000);