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
        id: id,
        name: "",
        phoneNumber: "",
        username: username,
        listErrors: new Errors()
    },
    methods: {
        onSubmit(){
            axios.put("/api/recomendaciones/"+username+"/actualizar/recomendacion/"+this.id,this.$data)
                .then(response => {
                    console.log(response);
                    showMessageTimerRedirect("Actualizado","Recomendacion Actualizada","success",2000,response.headers.location)
                })
                .catch(error => {
                    console.log(error);
                })
        }
    },
    mounted(){
        axios.get("/api/recomendaciones/"+this.username+'/'+this.id)
            .then(response => {
                console.log(response);
                this.name = response.data.name;
                this.phoneNumber = response.data.phoneNumber;
            })
            .catch(error => {
                console.log(error);
            })
    }
})