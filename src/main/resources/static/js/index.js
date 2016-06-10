dragula([
    document.getElementById('done_board'),
    document.getElementById('current_board'),
    document.getElementById('backlog_board'),
    document.getElementById('icebox_board')
]).on('drop', function (el) {
    console.log(el);
});