<template>
  <q-dialog v-model="workSkillStore.showAddSkillDialog">
    <q-card style="width: 300px" class="q-pa-md">
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
        <div class="row justify-around">
          <q-btn color="primary" @click="submit"> Добавить</q-btn>
          <q-btn color="red" @click="cancel"> Отменить</q-btn>
        </div>
      </q-card-section>

    </q-card>
  </q-dialog>
</template>

<script setup>

import {useWorkSkillStore} from "src/stores/WorkSkill"
import {computed, onMounted, ref} from "vue";

const workSkillStore = useWorkSkillStore();

const newSkill = ref({
  id: null,
  name: "",
  value: 0,
  comment: "",
  parentId: null,
  // childrens: [],
});

const allWorkSkills = computed(() => workSkillStore.workSkills);

onMounted(() => {
  // allWorkSkills.value = workSkillStore.workSkills;
  console.log("allWorkSkills: ", allWorkSkills.value);
});

function submit() {
  workSkillStore.createNewWorkSkill(newSkill.value);
  workSkillStore.showAddSkillDialog = false;
}

function cancel() {
  workSkillStore.showAddSkillDialog = false;
}


</script>

<style scoped>

.vertical-margin {
  margin-bottom: 1rem;
}

</style>
