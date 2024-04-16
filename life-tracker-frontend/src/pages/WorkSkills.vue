<template>
  <div>
    <q-card class="q-ma-md">
      <q-card-section class="row" style="margin-bottom: 2rem">
        <q-btn @click="touchBackend">
          Test
        </q-btn>

        <q-btn style="margin-left: 1rem"
               @click="showAddDialog">
          Добавить элемент
        </q-btn>

        <q-btn style="margin-left: 1rem"
               @click="showDeleteDialog">
          Удалить элемент
        </q-btn>
      </q-card-section>
    </q-card>
    <div class="row">
      <PackChart/>
    </div>
  </div>
  <AddSkillDialog/>
  <DeleteSkillDialog/>
</template>

<script setup>
import jsonData from '../assets/json/flare.json'
import {api} from "boot/axios";
import PackChart from "components/work-skills/PackChart.vue";
import AddSkillDialog from "components/work-skills/AddSkillDialog.vue";
import {useWorkSkillStore} from "src/stores/WorkSkill"
import {onMounted} from "vue";
import DeleteSkillDialog from "components/work-skills/DeleteSkillDialog.vue";


const workSkillStore = useWorkSkillStore();

onMounted(() => {
  workSkillStore.loadAllWorkSkills();
});

function touchBackend() {
  api.get("/work-skills/get-all").then(response => {
    if (response.status === 200) {
      const data = response.data;
    }
  });
}

function showAddDialog() {
  workSkillStore.showAddSkillDialog = !workSkillStore.showAddSkillDialog;
}

function showDeleteDialog() {
  workSkillStore.showDeleteSkillDialog = !workSkillStore.showDeleteSkillDialog;
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
