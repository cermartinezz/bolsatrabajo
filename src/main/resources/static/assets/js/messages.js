function showMessageTimer(title,text,type,timer,url){
    swal({
        title: title,
        text: text,
        type: type,
        timer: timer
    }).then(
        function () {
            window.location.href = url
        }
    )
}

function showMessage(title,text,type,url){
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
