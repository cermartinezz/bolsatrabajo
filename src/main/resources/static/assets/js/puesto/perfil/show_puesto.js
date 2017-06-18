var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

new Vue({
    el: '#app',
    data: {
        job_id: job_id,
        nombreJ: "",
        salarioJ: "",
        descripcionJ: "",
        category: "",
        company: "",
        department: "",
        idiomas: [],
        estadoEducacion: "",
        expAcademica: [],
        edadMin: "",
        edadMax: "",
        expLaboral: [],
        habilidades: [],
        userPostulant: userPostulant,
    },
    methods:{
        aplicar: function (userPostulant) {
            axios.get("/api/postulante/"+this.userPostulant.username+"/puesto/"+this.job_id)
                .then(response =>{
                      console.log(response);
                    showMessageTimerRedirect("Guardado", response.headers.message, "success", 5000, response.headers.location);
                })
                .catch(error => {
                    console.log(error);
                    showMessageTimer("Error",error.response.headers.message,"error",5000);

                })
        }
    },
    mounted(){
        axios.get('/job/'+this.job_id+'/ver')
            .then(response =>{
                console.log(response.data);
                this.nombreJ = response.data.nombreJ;
                this.salarioJ = response.data.salarioJ;
                this.descripcionJ = response.data.descripcionJ;
                this.category = response.data.category;
                this.company = response.data.company.nombreC;
                this.department = response.data.department.nombre;
                this.idiomas = response.data.jobProfile.languages;
                this.expAcademica = response.data.jobProfile.academicExperienceProfile;
                this.estadoEducacion = response.data.jobProfile.stateOfEducation;
                this.expLaboral = response.data.jobProfile.workExperienceProfile;
                this.edadMin = response.data.jobProfile.minAge;
                this.edadMax = response.data.jobProfile.maxAge;
                this.habilidades = response.data.jobProfile.skills;
            })
    }
})