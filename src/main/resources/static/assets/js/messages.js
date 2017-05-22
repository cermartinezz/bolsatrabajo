function showMessageTimerRedirect(title, text, type, timer, url){
    swal({
        title: title,
        text: text,
        type: type,
        timer: timer
    }).then(
        function () {
            window.location.href = url
        },
        function (dismiss) {
            if (dismiss === 'timer') {
                window.location.href = url
            }
        }
    )
}

function showMessageTimer(title, text, type, timer){
    swal({
        title: title,
        text: text,
        type: type,
        timer: timer
    })
}

function showMessageRedirect(title,text,type,url){
    swal({
        title: title,
        text: text,
        type: type
    }).then(
        function () {
            window.location.href = url
        }
    )
}
