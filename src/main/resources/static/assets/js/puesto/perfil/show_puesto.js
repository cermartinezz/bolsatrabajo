var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

new Vue({
    el: '#app',
    data: {
        job_id: job_id,
        nombreJ: "",
        salarioJ: "",
        descripcionJ: "",
        category: "",
        company: "",
        department: "",
        userPostulant: userPostulant,
    },
    methods:{
        aplicar: function (userPostulant) {
            axios.get("/api/postulante/"+this.userPostulant.username+"/puesto/"+this.job_id)
                .then(response =>{
                      console.log(response);
                    showMessageTimerRedirect("Guardado", response.headers.message, "success", 5000, response.headers.location);
                })
                .catch(error => {
                    console.log(error);
                    showMessageTimer("Error",error.response.headers.message,"error",5000);

                })
        }
    },
    mounted(){
        axios.get('/job/'+this.job_id+'/ver')
            .then(response =>{
                console.log(response.data);
                this.nombreJ = response.data.nombreJ;
                this.salarioJ = response.data.salarioJ;
                this.descripcionJ = response.data.descripcionJ;
                this.category = response.data.category;
                this.company = response.data.company.nombreC;
                this.department = response.data.department.nombre;
            })
    }
})