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
        id: "",
        description: "",
        name: "",
        minAge: "",
        maxAge: "",
        company: "",
        stateOfEducation: "",

        yearOfExperience: "",
        jobCatPuesto : "",
        jobCatId: "",
        jobs: [],
        workExperience: [],

        titles: titles,
        academicTitleId: "",
        academicTitleName: "",
        academicExperience: [],

        skillCategoryId: "",
        skillId: "",
        skillName: "",
        categories: [],
        skills: [],
        jobProfileSkills: [],

        levels: levels,
        languages: languages,
        languageId: "",
        levelId: "",
        jobProfilesLanguages: [],

        mostrarFormulario: false,
        mostrarCargo: false,
        mostrarTitulo: false,
        mostrarHablidad: false,
        listErrors: new Errors()

    },
    methods: {
        cambiarEstado(){
            this.mostrarFormulario = !this.mostrarFormulario;
        },
        actualizarDatos(description,name,minAge,maxAge){
            //este metodo se llama en el actualizar
            this.jobProfile.name = name;
            this.jobProfile.description = description;
            this.jobProfile.minAge = minAge;
            this.jobProfile.maxAge = maxAge;
        },
        actualizar(){
            //Actualiza la informacion general del perfil
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
        cambiarHabilidades(){
           axios.get("/skills/categoria/"+this.skillCategoryId)
                .then(response => {
                    this.skills = response.data;
                    this.skillId = "";
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
        },
        crearExperienciaAcademica(){
            let academicTitleCat={
                id: this.academicTitleId,
                titulo: this.academicTitleName
            };
            let academicExperienceProfile = {
                academicTitleCat: academicTitleCat
            };
            axios.post("/api/perfil/"+this.id+"/academico",academicExperienceProfile)
                .then(response => {
                    showMessageTimer("Guardado",response.headers.message,"success",2500);
                    this.mostrarTitulo = false;
                    this.agregarTitulo();
                })
                .catch(error => {
                    if(error.response.status >= 400 && error.response.status <= 499){
                        showMessageTimer("Error",error.response.headers.message,"error",2500);
                    }
                })
        },
        agregarHabilidades(){
            let skillCategory = {
                id : this.skillCategoryId
            };
            let skill={
                id: this.skillId,
                titulo: this.skillName,
                skillCategory: skillCategory
            };
            let jobProfileSkill = {
                skill: skill
            };
            axios.post("/api/perfil/"+this.id+"/hablidades",jobProfileSkill)
                .then(response => {
                    showMessageTimer("Guardado",response.headers.message,"success",2500);
                    this.mostrarHablidad = false;
                    this.agregarHabilidad();
                })
                .catch(error => {
                    if(error.response.status >= 400 && error.response.status <= 499){
                        showMessageTimer("Error",error.response.headers.message,"error",2500);
                    }
                })
        },
        crearIdiomas(){
            let languageLevel = {
                id: this.levelId
            };
            let language = {
                id: this.languageId
            };
            let jobProfileLanguage = {
                languageLevel: languageLevel,
                language: language
            };
            axios.post("/api/perfil/"+this.id+"/languajes",jobProfileLanguage)
                .then(response => {
                    showMessageTimer("Guardado",response.headers.message,"success",2500);
                    this.languageId = "";
                    this.languageLevel = "";
                    this.agregarLenguaje();
                })
                .catch(error => {
                    if(error.response.status >= 400 && error.response.status <= 499){
                        showMessageTimer("Error",error.response.headers.message,"error",2500);
                    }
                })
        },
        agregarCargo(){
            axios.get("/empresa/"+username+"/perfiles/"+this.id)
                .then(response => {
                    this.profile = response.data;
                    this.workExperience = this.profile.workExperienceProfile
                })
        },
        agregarTitulo(){
            axios.get("/empresa/"+username+"/perfiles/"+this.id)
                .then(response => {
                    this.profile = response.data;
                    this.academicExperience = this.profile.academicExperienceProfile;
                })
        },
        agregarHabilidad(){
            axios.get("/empresa/"+username+"/perfiles/"+this.id)
                .then(response => {
                    this.profile = response.data;
                    this.jobProfileSkills = this.profile.skills;
                })
        },
        agregarLenguaje(){
            axios.get("/empresa/"+username+"/perfiles/"+this.id)
                .then(response => {
                    this.profile = response.data;
                    this.jobProfilesLanguages = this.profile.languages;
                })
        },
        eliminarTitulo(titulo,index){
            axios.delete("/api/perfil/"+this.id+"/titulo/"+titulo.academicTitle.id+"/eliminar")
                .then(response  => {
                    showMessageTimer("Eliminada","Se elimino correctamente","info",3000);
                })
        },
        eliminarhabilidad(habilidad,index){
            axios.delete("/api/perfil/"+this.id+"/hablidades/"+habilidad.skill.id+"/eliminar")
                .then(response  => {
                    showMessageTimer("Eliminada","Se elimino correctamente","info",3000);
                    this.$delete(this.jobProfileSkills,index);
                })
        },
        eliminarTrabajo(trabajo, index){
            axios.delete("/api/perfil/"+this.id+"/cargo/"+trabajo.jobCat.id+"/eliminar")
                .then(response  => {
                    showMessageTimer("Eliminada","Se elimino correctamente","info",3000);
                    this.$delete(this.workExperience,index);
                })
        },
        eliminarIdioma(idioma,index){
            axios.delete("/api/perfil/"+this.id+"/languajes/"+idioma.language.id+"/nivel/"+idioma.languageLevel.id+"eliminar")
                .then(response  => {
                    showMessageTimer("Eliminada","Se elimino correctamente","info",3000);
                    this.$delete(this.jobProfilesLanguages,index);
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
        },
        mostrarTitulo: function (mostrarTitulo){
            if(mostrarTitulo == false){
                this.academicTitleName = "";
                this.academicTitleId = "";
            }
            if(mostrarTitulo == true){
                this.academicTitleName = "";
                this.academicTitleId = 0;
            }
        },
        mostrarHablidad: function (mostrarHablidad){
            if(mostrarHablidad == false){
                this.skillName= "";
                this.skillId= "";
            }
            if(mostrarHablidad == true){
                this.skillName= "";
                this.skillId= 0;
            }
        }
    },
    computed: {
      educMin: function(){
          if(this.stateOfEducation == "BACHILLER"){
              return "Bachiller";
          }if(this.stateOfEducation == "UNIVERSITARIO_1"){
              return "1er año de universidad";
          }if(this.stateOfEducation == "UNIVERSITARIO_2"){
              return "2do año de universidad";
          }if(this.stateOfEducation == "UNIVERSITARIO_3"){
              return "3ro año de universidad";
          }
          if(this.stateOfEducation == "UNIVERSITARIO_4"){
              return "4to año de universidad";
          }if(this.stateOfEducation == "UNIVERSITARIO_5"){
              return "5to año de universidad";
          }if(this.stateOfEducation == "EGRESADO"){
              return "Egresado";
          }if(this.stateOfEducation == "GRADUADO"){
              return "Graduado";
          }
      }
    },
    mounted(){
        axios.get("/api/cargos")
            .then(response => {
                this.jobs = response.data;
            });

        this.id = this.jobProfile.id;
        this.description = this.jobProfile.description;
        this.name = this.jobProfile.name;
        this.minAge = this.jobProfile.minAge;
        this.maxAge = this.jobProfile.maxAge;
        this.company = this.jobProfile.company.id;
        this.stateOfEducation = this.jobProfile.stateOfEducation;
        this.workExperience = profile.workExperienceProfile;
        this.academicExperience = profile.academicExperienceProfile;
        this.jobProfileSkills = profile.skills;
        this.jobProfilesLanguages = profile.languages;
        this.categories = categories;
    }
});