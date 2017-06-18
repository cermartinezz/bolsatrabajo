var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

new Vue({
    el:'#app',
    data: {
        username: username,
        jobs: jobs,
        job_list: [],
        candidateInfo: [],
    }
})