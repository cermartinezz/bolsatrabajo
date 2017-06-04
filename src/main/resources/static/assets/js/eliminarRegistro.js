var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

function eliminarRegistro(id,ruta){
    eliminarReg(id,ruta);
}