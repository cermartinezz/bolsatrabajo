var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

function eliminarRegistro(id,ruta){
    eliminarReg(id,ruta);
}

function eliminarRegistroExpLabo(id1,id2,id3){
    eliminarRegExpLabo(id1,id2,id3);
}

function eliminarRegistroExpAcad(id1,id2){
    eliminarRegExpAcad(id1,id2);
}