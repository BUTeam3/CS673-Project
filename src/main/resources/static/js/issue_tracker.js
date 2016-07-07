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
/*
$(document).on('click', '.task_difficulty input:checked', function(){
	update_difficulty($(this).val(),$(this).parent().parent().data('task-id'),1);
});
*/
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
/*
function update_difficulty(difficulty){
    $.ajax({
        type: "post",
        url: "/task/update",
        beforeSend: function(xhr){
            xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
        },
        data: {
            difficulty: difficulty
        },
        success: function(data) {
            $('#issue_tracker').html(data);
            drag_and_drop();
            $.jGrowl({ title: "Success!", message: "Task difficulty updated" });
        }
    });
<<<<<<< HEAD
}
*/
function set_csrf(xhr){
    return xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
}