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
        category: "",
        skill: "",
        listErrors: new Errors(),
        postulant: postulant,
        skill_list: [],
        postulant_skill: {},
        postulant_skills: []
    },
    methods: {
        getSkills(){
            axios.get("/skills/categoria/"+this.category)
                .then(response => {
                    console.log(response.data);
                    this.skill_list = response.data;
                });
            this.skill = "";
        },
        onSubmit(){
            axios.post("/api/postulante/" + this.postulant + "/habilidades",this.$data)
                .then((response) => {
                    console.log(response);
                    if(response.status >= 200 && response.status <= 299){
                        showMessageTimer("Guardado",response.headers.message,"success",2000);
                        this.postulant_skill = response.data.skill;
                        this.postulant_skills.push(this.postulant_skill);
                    }else{
                        showMessageTimer("Error",response.headers.message,"danger",2000);
                        this.skill = "";
                    }
                    //showMessageTimerRedirect("Guardado",response.headers.message,"success",5000,response.headers.location);
                })
                .catch(error => {
                    console.log(error);
                    showMessageTimer("Error",error.response.headers.message,"error",5000);
                })
        }
    },
    mounted(){
        axios.get('/api/postulant/'+postulant)
            .then(response => {
                let skills = [];
                let items = response.data.skills;
                items.forEach((item)=>{
                    skills.push(item.skill)
                });
                this.postulant_skills = skills;
            })
            .catch(error => {
                console.log(error);
            })
    }
})