import {defineStore} from "pinia";
import {api} from "boot/axios";


export const useWorkSkillStore = defineStore("workSkill", {
  state: () => ({
    showAddSkillDialog: false,
    showChangeSkillDialog: false,
    showDeleteSkillDialog: false,
    workSkills: null,
    skillForCommentEdit: null,
    skillsIsReady: false
  }),
  getters: {},
  actions: {
    loadAllWorkSkills() {
      // api.get("/work-skills/get-all").then(response => {
      //   console.log("response: ", response);
      //   if (response.status === 200) {
      //     this.workSkills = response.data;
      //     this.skillsIsReady = true;
      //   }
      // });
      api.get("/work-skills/get-user-work-skills").then(response => {
        console.log("get-user-work-skills response: ", response);
        if (response.status === 200) {
          this.workSkills = response.data;
          this.skillsIsReady = true;
        }
      });
    },

    createNewWorkSkill(newSkill) {
      api.post("/work-skills/create-skill", newSkill).then(response => {
        if (response.status === 200) {
          this.workSkills = response.data;
        }
      })
    },

    updateWorkSkill(editSkill) {
      api.post("/work-skills/edit-skill", editSkill).then(response => {
        if (response.status === 200) {
          if (!!response.data) {
            this.workSkills = response.data;
          }
        }
      })
    },

    deleteSkill(skill) {
      api.delete("/work-skills/delete-skill", {
        headers: { },
        data: skill
      }).then(response => {
        if (response.status === 200) {
          console.log("Skill was deleted!!!");
          this.workSkills = response.data;
        }
      })
    },

    clearStore() {
      this.workSkills = null;
      this.skillComment = null;
      this.skillsIsReady = false;
    }
  }
})
