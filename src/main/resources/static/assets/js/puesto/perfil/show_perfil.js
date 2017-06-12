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
        jobProfile: profile,
        listErrors: new Errors(),
        id: "",
        description: "",
        name: "",
        minAge: "",
        maxAge: "",
        company: "",
        mostrarFormulario: false,
    },
    methods: {
        cambiarEstado(){
            this.mostrarFormulario = !this.mostrarFormulario;
        },
        actualizarDatos(description,name,minAge,maxAge){
            this.jobProfile.name = name;
            this.jobProfile.description = description;
            this.jobProfile.minAge = minAge;
            this.jobProfile.maxAge = maxAge;
        },
        actualizar(){
            profile = {
                id: this.id,
                name: this.name,
                description: this.description,
                company: this.company,
                minAge: this.minAge,
                maxAge: this.maxAge
            };
            axios.put("/empresa/"+this.jobProfile.company.username+"/perfiles/editar/"+this.id,profile)
                .then(response => {
                    console.log(response);
                    if(response.status >= 200 && response.status <= 299){
                        showMessageTimer("Se actualizo",response.headers.message,"success",2500);
                        this.actualizarDatos(profile.description,profile.name,profile.minAge,profile.maxAge);
                        this.cambiarEstado();
                    }
                })
                .catch(error => {
                    if(error.response.status >= 400 && error.response.status <= 499){
                        showMessageTimer("Error",error.response.headers.message,"error",2500);
                    }
                })
        }
    },
    mounted(){
        this.id = this.jobProfile.id;
        this.description = this.jobProfile.description;
        this.name = this.jobProfile.name;
        this.minAge = this.jobProfile.minAge;
        this.maxAge = this.jobProfile.maxAge;
        this.company = this.jobProfile.company.id;
    }
})