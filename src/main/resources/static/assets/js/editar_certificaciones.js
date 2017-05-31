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
        certificationId: "",
        certificationCode: "",
        certificationTitle: "",
        institution: "",
        listErrors: new Errors()
    },
    methods: {
        onSubmit: function(){
            axios.put("/api/certificaciones/"+code, this.$data)
                .then(response => {
                    console.log(response);
                    showMessageTimerRedirect("Actualizado","El registro fue actualizado con exito",'success',2500,response.headers.location);
                })
                .catch(error => {
                    console.log(error);
                    this.listErrors.record(error.response.data.errors);
                })
        }
    },
    mounted(){
        axios.get("/api/certificaciones/"+code)
            .then(response =>{
                console.log(response.data);
                this.certificationId = response.data.certificationId;
                this.certificationCode = response.data.certificationCode;
                this.certificationTitle = response.data.certificationTitle;
                this.institution = response.data.institution.id;

            })
            .catch(error => error.log(error))
    }
})