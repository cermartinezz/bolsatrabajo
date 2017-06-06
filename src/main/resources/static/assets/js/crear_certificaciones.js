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
        certificationTitle: "",
        institution: "",
        mostrar: false,
        listErrors: new Errors()
    },
    watch: {
        mostrar: function (mostrar) {
            if(mostrar == false){
                this.institution = "";
            }
            if(mostrar == true){
                this.institution = {
                    institutionName: "",
                    institutionType: "",
                };
            }
        }
    },
    methods: {
        onSubmit: function(){
            axios.post("/api/certificaciones", this.$data)
                .then(response => {
                    console.log(response);
                    showMessageTimerRedirect("Guardado","El registro fue guardado con exito",'success',2500,response.headers.location);
                    this.clearData();
                })
                .catch(error => {
                    console.log(error.response);
                    if(error.response.status >= 400 && error.response.status <= 499){
                        showMessageTimer("Error",error.response.headers.message,"error",2500);
                    }
                })
        },
        clearData(){
            this.certificationTitle = "";
            this.institution = "";
            this.listErrors = new Errors();
        }
    }
})


