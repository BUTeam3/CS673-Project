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
            $('#create_issue_modal').modal('hide');
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
        }
    });
}

function set_csrf(xhr){
    return xhr.setRequestHeader('X-CSRF-Token', $('meta[name="_csrf"]').attr('content'))
}