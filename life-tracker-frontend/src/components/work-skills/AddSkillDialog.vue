<template>
  <q-dialog v-model="workSkillStore.showAddSkillDialog">
    <q-card style="width: 25rem" class="q-pa-md">
      <q-card-section>
        <div class="text-h6">Добавление нового элемента</div>

        <q-input class="vertical-margin" outlined v-model="newSkill.name" label="Имя"/>

        <q-input outlined
                 class="vertical-margin"
                 v-model="newSkill.value"
                 type="number"
                 label="Значение"/>

        <q-select standout="bg-teal text-white"
                  class="vertical-margin"
                  v-model="newSkill.parentId"
                  :options="allWorkSkills"
                  option-value="id"
                  option-label="name"
                  emit-value
                  map-options
                  label="Родительский элемент"
                  clearable
        />

        <q-input outlined
                 class="vertical-margin"
                 v-model="newSkill.comment"
                 type="textarea"
                 label="Комментарий"/>

      </q-card-section>

      <q-card-actions class="row">
        <q-btn class="col" color="primary" @click="submit"> Добавить </q-btn>
        <q-btn class="col" color="red" @click="cancel"> Отменить </q-btn>
      </q-card-actions>

    </q-card>
  </q-dialog>
</template>

<script setup>

import {useWorkSkillStore} from "src/stores/WorkSkill"
import {computed, onMounted, ref} from "vue";

const workSkillStore = useWorkSkillStore();

const newSkill = ref();

function initNewSkill() {
  newSkill.value = {
    id: null,
    name: "",
    value: 0,
    comment: "",
    parentId: null,
    // childrens: [],
  };
}

initNewSkill();

const allWorkSkills = computed(() => workSkillStore.workSkills);

onMounted(() => {
  // allWorkSkills.value = workSkillStore.workSkills;
  console.log("allWorkSkills: ", allWorkSkills.value);
});

function submit() {
  workSkillStore.createNewWorkSkill(newSkill.value);
  initNewSkill();
  workSkillStore.showAddSkillDialog = false;
}

function cancel() {
  initNewSkill();
  workSkillStore.showAddSkillDialog = false;
}


</script>

<style scoped>

.vertical-margin {
  margin-bottom: 1rem;
}

</style>
