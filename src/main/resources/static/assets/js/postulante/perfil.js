var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

new Vue({
    el: "#app",
    data: {
        recommendations: [],
        certifications: [],
        skills: [],
        postulante: {},
        postulantPublications: [],
        postulantLanguages: [],
        username: username,
    },
    methods: {
        redirectRecomendaciones(id){
            window.location.href = "/postulante/"+this.username+"/recomendaciones/"+id+"/editar";
        },
        deleteRecomendation: function(recomendationIndex,id){
            this.$delete(this.recommendations,recomendationIndex);
            axios.delete("/api/recomendaciones/"+username+"/recomendacion/"+id);
        },
        redirectCertificaciones(certification){
            window.location.href = "/postulante/"+this.username+"/certificaciones/"+certification.code+ "/"+certification.primaryKey.certification.certificationId+"/editar";
        },
        deleteCertificacion: function(index,certification){
            this.$delete(this.certifications,index);
            url= "/api/postulante/"+this.username+"/certificaciones/codigo/"+certification.code+ "/certificacion/"+certification.primaryKey.certification.certificationId+"/eliminar"
            showMessageConfirmation("Eliminar",
                                    "Se eliminara la certificacion",
                                    "warning",
                                    "Desea eliminar la certificacion?",url,"delete")
        },
        deleteSkill: function(index,skill){
            this.$delete(this.skills,index);
            console.log(skill);
            url= "/api/postulante/"+this.username+"/habilidades/"+skill.skill.codigo+"/eliminar";
            showMessageConfirmation("Eliminar",
                                    "Se eliminara la habilidad",
                                    "warning",
                                    "Desea eliminar la habilidad?",url,"delete")
        },
        deletePublication: function(index,publication){
            this.$delete(this.postulantPublications,index);
            console.log(publication);
            url= "/api/postulante/"+this.username+"/publicaciones/"+publication.publication.codigo+"/eliminar";
            showMessageConfirmation("Eliminar",
                "Se eliminara la publicacion",
                "warning",
                "Desea eliminar la publicacion?",url,"delete")
        },
        deleteLanguage: function(index,language){
            this.$delete(this.postulantLanguages,index);
            console.log(language);
            url= "/api/postulante/"+this.username+"/idiomas/"+language.language.codigo+"/eliminar";
            showMessageConfirmation("Eliminar",
                "Se eliminara el idioma",
                "warning",
                "Desea eliminar este idioma?",url,"delete")
        },
        dateName(date){
            return dateName(date);
        }
    },
    mounted()
    {
        axios.get('/api/postulant/'+this.username)
            .then(response => {
                console.log(response.data);
                this.postulante = response.data;
                this.certifications = response.data.certifications;
                this.skills = response.data.skills;
                this.recommendations = response.data.recommendations;
                this.postulantPublications = response.data.postulantPublications;
                this.postulantLanguages = response.data.postulantLanguages;
            })
            .catch(error => {
                console.log(error);
            })
    }
})