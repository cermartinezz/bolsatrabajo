/**
 * Redirige despues de el tiempo asignado o el click del usuario segun la url asignada
 * @param title
 * @param text
 * @param type
 * @param timer
 * @param url
 */
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

/**
 * SOlo muestar el mensaje
 * @param title
 * @param text
 * @param type
 * @param timer
 */
function showMessageTimer(title, text, type, timer){
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

/**
 * Muestra un mensaje y redirije
 * @param title
 * @param text
 * @param type
 * @param url
 */
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

/**
 *
 * @param title
 * @param text
 * @param type
 * @param confirmText
 * @param url
 * @param requestType
 * @param data
 */
function showMessageConfirmation(title,text,type,confirmText,url,requestType,data= {}){
    swal({
        title: title,
        text: text,
        type: type,
        showCancelButton: true,
        confirmButtonColor: "#75bfd6",
        cancelButtonColor: "#dd5f58",
        confirmButtonText: confirmText,
    }).then(()=> {
        axios[requestType](url,data)
            .then(response => {
                swal("Accion realizada",response.headers.message,"success");
            })
    })
}

/**
 * le da formato a los numero de 1 digito
 * @param number
 * @returns {string}
 */
function formatNumber(number){

    let formattedNumber = ("0" + number).slice(-2);

    return formattedNumber
}

/**
 * @Formato yyyy-MM-dd
 * @param date
 * @returns {string|*}
 */
function dateName(date){

    date = date.split("-");

    let day = date[2];

    let month = date[1];

    let year = date[0];

    month = monthName(formatNumber(month));

    day = formatNumber(day);

    date_string = "" + day + ", " + month + " "+ year;

    return date_string
}

function monthName(month){
    switch (month){
        case "01":
            return "Enero";
            break;
        case "02":
            return "Febrero";
            break;
        case "03":
            return "Marzo";
            break;
        case "04":
            return "Abril";
            break;
        case "05":
            return "Mayo";
            break;
        case "06":
            return "Junio";
            break;
        case "07":
            return "Julio";
            break;
        case "08":
            return "Agosto";
            break;
        case "09":
            return "Septiembre";
            break;
        case "10":
            return "Octubre";
            break;
        case "11":
            return "Noviembre";
            break;
        case "12":
            return "Diciembre";
            break;

    }
}

/**
 * Funcion general para borrar un registro
 * recibe como parametro el id del registro y
 * la ruta del mapping del controlador rest
 * Ejemplo de llamada a la funcion desde thymeleaf
 * th:onclick="'eliminarRegistro(\'' + ${award.id} + '\',\'award\')'"
 * ormerino
 */
function eliminarReg(id,ruta){
    swal({
      title: 'Eliminar',
      text: "Esta seguro que desea eliminar este elemento?",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Si, eliminar!',
    }).then(function () {
        axios.delete("/"+ruta+"/"+id)
            .then(response => {
                })
      swal(
        'Borrado!',
        'El registro ha sido eliminado.',
        'success'
      ).then(function () {
            location.reload();
        })
    })
}