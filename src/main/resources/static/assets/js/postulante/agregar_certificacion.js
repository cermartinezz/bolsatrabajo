new Vue({
    el: '#app',
    data: {
        institution: "",
        certification: "",
        certification_code: "",
        certifications_list: []
    },
    methods: {
        getCertifications(){
            this.certification =  "";
            axios.get("/api/certificaciones/institucion/" + this.institution )
                .then(response => {
                    console.log(response.data);
                    this.certifications_list = response.data;
                })
                .catch(error => {
                    console.log(error);
                })
        }
    },
})