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
        name: "",
        description: "",
        minAge: "",
        maxAge: "",
        company: id,
        stateOfEducation: "",
        mostrarPrincipal: true,
        listErrors: new Errors()
    },
    methods: {
        atras(){
            window.history.back();
        },
        onSubmitPrincipal(){
            axios.post("/empresa/"+username+"/perfiles",this.$data)
                .then(response => {
                    console.log(response);
                    id = response.data.id;
                    url = "/empresa/"+username+"/perfiles/codigo/"+id;
                    showMessageTimerRedirect("Creado",response.headers.message,"success",2500,url);
                }).catch(error => {
                    if(error.response.status >= 400 && error.response.status <= 499){
                        showMessageTimer("Error",error.response.headers.message,"error",2500);
                    }
            });
        }
    }
})