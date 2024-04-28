<template>
  <q-dialog v-model="workSkillStore.showChangeSkillDialog">
    <q-card style="width: 25rem" class="q-px-sm q-pa-md">
      <q-card-section>
        <div class="text-h6">Редактирование элемента</div>
      </q-card-section>
      <q-card-section class="q-pa-md">
        <q-select standout="bg-teal text-white"
                  class="vertical-margin"
                  v-model="selectedSkill"
                  :options="allWorkSkills"
                  option-label="name"
                  label="Выберите элемент"
                  @update:model-value="initEditSkill"
                  clearable/>

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
        <q-btn class="col" color="primary" @click="submit"> Сохранить </q-btn>
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

const selectedSkill = ref(null);

const editSkill = ref();

function initEditSkill(newVal) {
  if (!!newVal) {
    editSkill.value = {
      id: newVal.id,
      name: newVal.name,
      value: newVal.value,
      comment: newVal.comment,
      parentId: newVal.parentId,
    };
  } else {
    editSkill.value = {
      id: null,
      name: "",
      value: 0,
      comment: "",
      parentId: null,
    };
  }
}

initEditSkill(null);

onMounted(() => {
  console.log("allWorkSkills: ", allWorkSkills.value);
});

function submit() {
  workSkillStore.updateWorkSkill(editSkill.value);
  initEditSkill(null);
  selectedSkill.value = null;
  workSkillStore.showChangeSkillDialog = false;
}

function cancel() {
  initEditSkill(null);
  selectedSkill.value = null;
  workSkillStore.showChangeSkillDialog = false;
}



</script>

<style scoped>

.vertical-margin {
  margin-bottom: 1rem;
}

</style>
