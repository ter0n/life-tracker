import {defineStore} from "pinia";
import {api} from "boot/axios";


export const useWorkSkillStore = defineStore("workSkill", {
  state: () => ({
    showAddSkillDialog: false,
    workSkills: null
  }),
  getters: {},
  actions: {
    loadAllWorkSkills() {
      api.get("/work-skills/get-all").then(response => {
        console.log("response: ", response);
        if (response.status === 200) {
          this.workSkills = response.data;
          console.log("data: ", this.workSkills);
        }
      });
    },

    createNewWorkSkill(newSkill) {
      api.post("/work-skills/create-skill", newSkill).then(response => {
        if (response.status === 200) {
          console.log("New skill was created!!!");
          this.workSkills = response.data;
        }
      })
    }
  }
})
