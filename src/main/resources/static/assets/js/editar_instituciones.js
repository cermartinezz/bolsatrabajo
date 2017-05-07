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
        id: "",
        institutionCode: "",
        institutionName: "",
        institutionType: "",
        listErrors: new Errors()
    },
    methods: {
        onSubmit: function(){
            axios.put("/api/instituciones/"+code, this.$data)
                .then(response => {
                    console.log(response);
                    showMessageTimer("Actualizado","El registro fue actualizado con exito",'success',2500);
                })
                .catch(error => {
                    console.log(error);
                    this.listErrors.record(error.response.data.errors);
                })
        }
    },
    mounted(){
        axios.get("/api/instituciones/"+code)
            .then(response =>{
                console.log(response.data);
                this.id = response.data.id;
                this.institutionCode = response.data.institutionCode;
                this.institutionType = response.data.institutionType;
                this.institutionName = response.data.institutionName;

            })
            .catch(error => error.log(error))
    }
})