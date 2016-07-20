function drag_and_drop(){
    dragula([
        document.getElementById('done_board'),
        document.getElementById('current_board'),
        document.getElementById('backlog_board'),
        document.getElementById('icebox_board')
    ]).on('drop', function (el) {
        var id = $(el).data('task-id');
        var state = $(el).closest('.board').data('state');
        update_state(id, state);
    });
}

$(function(){
    drag_and_drop();
});
$(document).on('click', '.task_difficulty input:checked', function(){
	var id=$(this).parent().parent().data('task-id');
	update_state(id,1);
	update_difficulty(id,$(this).val());
});
$(document).on('click', 'input[value=Start]', function(){
	var id=$(this).data('task-id');
	update_state(id,2);
});
$(document).on('click', '.task_done', function(){
	$('.AcceptReject_'+this.id).show();
	$(this).hide();
});
$(document).on('click', '.task_accept', function(){
	var id=$(this).parent().parent().data('task-id');
	update_state(id,3);
});
$(document).on('click', '.task_reject', function(){
	var id=$(this).parent().parent().data('task-id');
	update_state(id,1);
});

$(document).on('submit', '#create_issue_form', function(){

    $theForm = $(this);

    // send xhr request
    $.ajax({
        type: "post",
        url: "/task/new",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
            data: $('#data').val()
        },
        success: function(data) {
            $('#issue_tracker').html(data);
            drag_and_drop();
			update_channel_table($('#data').val());
            $('#create_issue_modal').modal('hide');
            $.jGrowl({ title: "Success!", message: "Task added" });
        }
    });

    // prevent submitting again
    return false;
});

function update_state(id, state){
    $.ajax({
        type: "post",
        url: "/task/update",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
            id: id,
            state: state
        },
        success: function(data) {
            $('#issue_tracker').html(data);
            drag_and_drop();
            $.jGrowl({ title: "Success!", message: "Task state updated" });
        }
    });
}
function update_difficulty(id, difficulty){
    $.ajax({
        type: "post",
        url: "/task/difficulty",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
            id: id,
            difficulty: difficulty
        },
        success: function(data) {
            $('#issue_tracker').html(data);
            drag_and_drop();
            $.jGrowl({ title: "Success!", message: "Task Difficulty updated" });
        }
    });
}
function set_csrf(xhr){
    return xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
}