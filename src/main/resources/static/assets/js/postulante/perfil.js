var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

new Vue({
    el: "#app",
    data: {
        recommendations: [],
        username: username,
    },
    methods: {
        redirectRecomendaciones(id){
            window.location.href = "/postulante/"+this.username+"/recomendaciones/"+id+"/editar";
        },
        deleteRecomendation: function(recomendationIndex,id){
            this.$delete(this.recommendations,recomendationIndex);
            axios.delete("/api/recomendaciones/"+username+"/recomendacion/"+id);
        }
    },
    mounted(){
        axios.get('/api/recomendaciones/'+this.username)
            .then(response => {
                console.log(response);
                this.recommendations = response.data;
            })
            .catch(error => {
                console.log(error);
            })
    }
})