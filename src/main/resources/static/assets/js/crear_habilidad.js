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
        titulo: "",
        codigo: "",
        skillCategory: "",
        listErrors: new Errors()
    },
    methods:{
        onSubmit: function(){
            axios.post("/skills/create", this.$data)
                .then(response => {
                console.log(response);
            showMessageTimerRedirect("Guardado","El registro fue guardado con exito",'success',2500,response.headers.location);
        })
            .catch(error => {
                console.log(error);
            this.listErrors.record(error.response.data.errors);
        })
        }
    }

})