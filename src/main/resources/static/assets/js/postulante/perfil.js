var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

new Vue({
    el: "#app",
    data: {
        recommendations: [],
        certifications: [],
        skills: [],
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
        deleteSkill: function(index,certification){
            this.$delete(this.certifications,index);
            url= "/api/postulante/"+this.username+"/habilidades/"+certification.code+"/eliminar";
            showMessageConfirmation("Eliminar",
                                    "Se eliminara la habilidad",
                                    "warning",
                                    "Desea eliminar la certificacion?",url,"delete")
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
                this.certifications = response.data.certifications;
                this.skills = response.data.skills;
                this.recommendations = response.data.recommendations;
            })
            .catch(error => {
                console.log(error);
            })
    }
})