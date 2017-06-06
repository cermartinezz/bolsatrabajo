var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

class Errors{
    constructor (){
        this.errors = {};
    }
    record(listErrors){
        this.errors = listErrors;
    }
    get(field){
        if(this.errors[0]){
            var data = $.grep(this.errors,function(obj){
                return obj.field === field;
            });
            if(data.length!==0){
                return data[0].defaultMessage
            }
        }
    }
    has(field){
        if(this.errors[0]){
            var data = $.grep(this.errors,function(obj){
                return obj.field === field;
            });

            return (data.length !== 0)

        }
    }
}
new Vue({
    el: "#app",
    data: {
        id: "",
        codigo: "",
        titulo: "",
        editorial: "",
        tipo: "",
        listErrors: new Errors()
    },
    methods: {
        onSubmit: function(){
            axios.put("/publications/"+code, this.$data)
                .then(response => {
                console.log(response);
            showMessageTimerRedirect("Actualizado","El registro fue actualizado con exito",'success',2500,response.headers.location);
        })
            .catch(error => {
                console.log(error);
            this.listErrors.record(error.response.data.errors);
        })
        }
    },
    mounted(){
        axios.get("/publications/"+code)
            .then(response =>{
            console.log(response.data);
        this.id = response.data.id;
        this.codigo = response.data.codigo;
        this.titulo = response.data.titulo;
        this.editorial = response.data.editorial;
    })
    .catch(error => error.log(error))
    }
})