function showMessageTimer(title,text,type,timer){
    swal({
        title: title,
        text: text,
        type: type,
        timer: timer
    }).then(
        function () {},
        // handling the promise rejection
        function (dismiss) {

        }
    )
}
