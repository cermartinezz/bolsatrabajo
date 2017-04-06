new Vue({
    el: "#app",
    data: {
        institutionCode: "",
        institutionName: "",
        institutionType: "",
        error: false,
        success: false,
        message: "",
    },
    methods:{
        onSubmit: function(){
            axios.post("/instituciones", this.$data)
                .then(response => {
                    this.message = ""
                    this.success = true;
                    this.message = "La institucion fue creada con exito";
                    this.error=false;
                })
                .catch(error => {
                    this.message = "";
                    this.error = true;
                    this.message = error.response.data.errorMessage
                })
        }
    }

})