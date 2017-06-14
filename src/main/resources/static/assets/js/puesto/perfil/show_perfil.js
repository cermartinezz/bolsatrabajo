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
        username: username,
        listErrors: new Errors(),
        id: "",
        description: "",
        name: "",
        minAge: "",
        maxAge: "",
        company: "",
        yearOfExperience: "",
        jobCatPuesto : "",
        jobCatId: "",
        jobs: [],
        mostrarFormulario: false,
        workExperience: [],
        mostrarCargo: false
    },
    methods: {
        cambiarEstado(){
            this.mostrarFormulario = !this.mostrarFormulario;
        },
        agregarCargo(){
          axios.get("/empresa/"+username+"/perfiles/"+this.id)
              .then(response => {
                  this.profile = response.data;
                  this.workExperience = this.profile.workExperienceProfile
              })
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
        },
        crearExperiencia(){
            let jobCat={
                id : this.jobCatId,
                puesto: this.jobCatPuesto
            };
            let workExperienceProfile = {
                yearOfExperience: this.yearOfExperience,
                jobCat: jobCat
            }
            axios.post("/api/perfil/"+this.id,workExperienceProfile)
                .then(response => {
                    showMessageTimer("Guardado",response.headers.message,"success",2000);
                    this.agregarCargo()
                })
                .catch(error => {
                    if(error.response.status >= 400 && error.response.status <= 499){
                        showMessageTimer("Error",error.response.headers.message,"error",2500);
                    }
            })
        }
    },
    watch: {
        mostrarCargo: function (mostrarCargo){
            if(mostrarCargo == false){
                this.jobCatPuesto = "";
                this.jobCatId = "";
            }
            if(mostrarCargo == true){
                this.jobCatPuesto = "";
                this.jobCatId = 0;
            }
        }
    },
    mounted(){
        axios.get("/api/jobs")
            .then(response => {
                console.log(response);
                this.jobs = response.data;
            })
            .catch(error => {
                console.log(error.response);
                if(error.response.status >= 400 && error.response.status <= 499){
                    console.log("error");
                }
            })

        this.id = this.jobProfile.id;
        this.description = this.jobProfile.description;
        this.name = this.jobProfile.name;
        this.minAge = this.jobProfile.minAge;
        this.maxAge = this.jobProfile.maxAge;
        this.company = this.jobProfile.company.id;
        this.workExperience = profile.workExperienceProfile;
    }
})