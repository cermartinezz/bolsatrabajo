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
        name: "",
        lastName: "",
        dui: "",
        nit: "",
        username: "",
        listErrors: new Errors()
    },
    methods: {
        onSubmit(){
            axios.put('/api/postulant/'+ username +'/actualizar',this.$data)
                .then(response => {
                    console.log(response);
                    showMessageTimer("Actualizado","Informacion Actualizada","success",5000,response.headers.location);
                })
                .catch(error => {
                    console.log(error)
                })
        }
    },
    mounted(){
        axios.get('/api/postulant/'+username)
            .then(response => {
                console.log(response);
                this.name = response.data.name;
                this.lastName = response.data.lastName;
                this.nit = response.data.nit;
                this.dui = response.data.dui;
                this.username = response.data.username;
            })
            .catch(error => {
                console.log(error);
            })
    }
})

