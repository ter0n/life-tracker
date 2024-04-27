<template>
  <q-dialog v-model="workSkillStore.showChangeSkillDialog">
    <q-card style="width: 25rem" class="q-px-sm q-pb-md">
      <q-card-section>
        <div class="text-h6">Редактирование элемента</div>
      </q-card-section>
      <q-card-section>
        <q-select standout="bg-teal text-white"
                  v-model="editSkill"
                  :options="allWorkSkills"
                  option-label="name"
                  label="Выберите элемент"
                  clearable/>
      </q-card-section>

      <q-card-section class="q-pa-md">
        <q-input class="vertical-margin" outlined v-model="editSkill.name" label="Имя"/>

        <q-input outlined
                 class="vertical-margin"
                 v-model="editSkill.value"
                 type="number"
                 label="Значение"/>

        <q-select standout="bg-teal text-white"
                  class="vertical-margin"
                  v-model="editSkill.parentId"
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
                 v-model="editSkill.comment"
                 type="textarea"
                 label="Комментарий"/>
      </q-card-section>

      <q-card-actions class="row">
        <q-btn class="col" color="primary" @click="submit"> Удалить </q-btn>
        <q-btn class="col" color="red" @click="cancel"> Отменить </q-btn>
      </q-card-actions>

    </q-card>
  </q-dialog>
</template>

<script setup>

import {useWorkSkillStore} from "src/stores/WorkSkill"
import {computed, onMounted, ref} from "vue";

const workSkillStore = useWorkSkillStore();

const workSkill = ref(null);

const allWorkSkills = computed(() => workSkillStore.workSkills);

const editSkill = ref({
  id: null,
  name: "",
  value: 0,
  comment: "",
  parentId: null,
  // childrens: [],
});

onMounted(() => {
  console.log("allWorkSkills: ", allWorkSkills.value);
});

function submit() {
  workSkillStore.deleteSkill(workSkill.value);
  workSkillStore.showChangeSkillDialog = false;
}

function cancel() {
  workSkillStore.showChangeSkillDialog = false;
}

</script>

<style scoped>

</style>
