var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

new Vue({
    el:'#app',
    data: {
        username: username,
        candidate: candidate,
        job_list: [],
        candidateInfo: [],
    },
    mounted(){
        axios.get('/api/postulant/'+this.username+'/applications')
            .then(response =>{
                console.log(response.data);
                this.candidateInfo = response.data;
                this.job_list = response.data.candidates;

            })
            .catch(error =>{
                console.log(error);
            })
    }
})