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
    el: '#app',
    data: {
        institution: "",
        certification: "",
        expirationDate: "",
        code: "",
        listErrors: new Errors(),
        postulant: postulant,
        certifications_list: []
    },
    methods: {
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
        onSubmit(){
            axios.post("/api/postulante/" + this.postulant + "/certificaciones",this.$data)
                .then(response => {
                    console.log(response);
                    showMessageTimerRedirect("Guardado",response.headers.message,"success",5000,response.headers.location);
                })
                .catch(error => {
                    console.log(error);
                    //showMessageTimer("Error",error.headers.message,"error",5000);
                })
        }
    },
})