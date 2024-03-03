<template>
  <div>
    <h2>Pack here!</h2>
    <div class="row" style="margin-bottom: 2rem">

      <q-btn @click="touchBackend">
        Test
      </q-btn>

      <q-btn style="margin-left: 1rem"
             @click="showDialog">
        Добавить элемент
      </q-btn>
    </div>
    <div class="row">
      <PackChart/>
    </div>
  </div>
  <AddSkillDialog/>
</template>

<script setup>
import jsonData from '../assets/json/flare.json'
import {api} from "boot/axios";
import PackChart from "components/work-skills/PackChart.vue";
import AddSkillDialog from "components/work-skills/AddSkillDialog.vue";
import {useWorkSkillStore} from "src/stores/WorkSkill"
import {onMounted} from "vue";


const workSkillStore = useWorkSkillStore();

onMounted(() => {
  workSkillStore.loadAllWorkSkills();
});

function touchBackend() {
  api.get("/work-skills/get-all").then(response => {
    console.log("response: ", response);
    if (response.status === 200) {
      const data = response.data;
      console.log("data: ", data);
    }
  });
}

function showDialog() {
  workSkillStore.showAddSkillDialog = !workSkillStore.showAddSkillDialog;
}

// export default {
//   name: "PackChartTest",
//   components: {AddSkillDialog, PackChart},
//   data() {
//     return {
//       showAddSkillDialog: false
//     }
//   },
//   methods: {
//     touchBackend() {
//       api.get("/work-skills/get-all").then(response => {
//         console.log("response: ", response);
//         if (response.status === 200) {
//           const data = response.data;
//           console.log("data: ", data);
//         }
//       });
//     },
//     showDialog() {
//       this.showAddSkillDialog = !this.showAddSkillDialog;
//     }
//   },
//   mounted() {
//     console.log("Mounted!");
//   }
//
// }


</script>

<style>

</style>
