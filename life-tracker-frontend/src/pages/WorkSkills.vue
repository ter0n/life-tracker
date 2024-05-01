<template>
  <div>
    <q-card class="q-ma-md">
      <q-card-section class="row" style="margin-bottom: 2rem">

        <q-btn style="margin-left: 1rem"
               color="primary"
               @click="showAddDialog">
          Добавить элемент
        </q-btn>

        <q-btn style="margin-left: 1rem"
               color="primary"
               @click="showChangeDialog">
          Изменить элемент
        </q-btn>

        <q-btn style="margin-left: 1rem"
               color="primary"
               @click="showDeleteDialog">
          Удалить элемент
        </q-btn>
      </q-card-section>
    </q-card>
    <div class="row">
      <div class="col-6">
      <PackChart/>
      </div>
      <q-card class="q-ma-md col-12 col-md-5">
        <SkillCommentEditor />
      </q-card>
    </div>
  </div>
  <AddSkillDialog/>
  <ChangeSkillDialog/>
  <DeleteSkillDialog/>
</template>

<script setup>
import PackChart from "components/work-skills/PackChart.vue";
import AddSkillDialog from "components/work-skills/AddSkillDialog.vue";
import {useWorkSkillStore} from "src/stores/WorkSkill"
import {computed, onMounted, ref} from "vue";
import DeleteSkillDialog from "components/work-skills/DeleteSkillDialog.vue";
import ChangeSkillDialog from "components/work-skills/ChangeSkillDialog.vue";
import SkillCommentEditor from "components/work-skills/SkillCommentEditor.vue";


const workSkillStore = useWorkSkillStore();

onMounted(() => {
  workSkillStore.loadAllWorkSkills();
});

function showAddDialog() {
  workSkillStore.showAddSkillDialog = !workSkillStore.showAddSkillDialog;
}

function showDeleteDialog() {
  workSkillStore.showDeleteSkillDialog = !workSkillStore.showDeleteSkillDialog;
}

function showChangeDialog() {
  workSkillStore.showChangeSkillDialog = !workSkillStore.showChangeSkillDialog;
}



</script>

<style>

</style>
