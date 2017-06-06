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
        language: "",
        languageLevel: "",
        postulant: postulant
    },
    methods: {
        onSubmit(){
            axios.post("/api/postulante/" + this.postulant + "/idiomas",this.$data)
                .then(response => {
                    console.log(response);
                    if(response.status >= 200 && response.status <= 299) {
                        showMessageTimerRedirect("Guardado", response.headers.message, "success", 5000, response.headers.location);
                    }else{
                        showMessageTimer("Error",response.headers.message,"danger",2000);
                        this.skill = "";
                    }
                })
                .catch(error => {
                    console.log(error);
                    showMessageTimer("Error",error.response.headers.message,"error",5000);

                })
        }
    },
})