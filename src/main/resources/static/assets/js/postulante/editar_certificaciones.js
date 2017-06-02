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
let data;
new Vue({
    el: "#app",
    data: {
        institution: "",
        institutionName: "",
        certification: "",
        certificationTitle: "",
        expirationDate: "",
        code: "",
        postulant: postulant,
        username: username,
        listErrors: new Errors()
    },
    methods: {
        onSubmit(){
            axios.put("/api/postulante/"+username+"/certificaciones/codigo/"+code+"/certificacion/"+certificationId+"/actualizar",this.$data)
                .then(response => {
                    showMessageTimerRedirect("Actualizado","Certificacion actualizada con exito","success",2500,response.headers.location);
                })
                .catch(error => {

                })
        },
        getCertifications(){
            this.certification =  "";
            axios.get("/api/certificaciones/institucion/" + this.institution )
                .then(response => {
                    this.certifications_list = response.data;
                })
                .catch(error => {
                    console.log(error);
                })
        },
    },
    created(){
        axios.get("/api/postulante/"+username+"/certificaciones/codigo/"+code+"/certificacion/"+certificationId)
            .then(response => {
                this.institution = response.data.certification.institution.id;
                this.institutionName = response.data.certification.institution.institutionName;
                this.code = response.data.code;
                this.expirationDate = response.data.expirationDate;
                this.certification = response.data.certification.certificationId;
                this.certificationTitle = response.data.certification.certificationTitle;
            })
            .catch(error => {
                console.log(error)
            })
    },

});