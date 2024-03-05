<template>
  <q-dialog v-model="workSkillStore.showDeleteSkillDialog">
    <q-card style="width: 300px" class="q-px-sm q-pb-md">
      <q-card-section>
        <div class="text-h6">Удаление элемента</div>

        <q-select standout="bg-teal text-white"
                  v-model="workSkill"
                  :options="allWorkSkills"
                  option-label="name"
                  label="Выберите элемент"
                  clearable
        />

        <q-btn color="primary" @click="submit"> Удалить </q-btn>
        <q-btn color="red" @click="cancel"> Отменить </q-btn>
      </q-card-section>

    </q-card>
  </q-dialog>
</template>

<script setup>

import {useWorkSkillStore} from "src/stores/WorkSkill"
import {computed, onMounted, ref} from "vue";

const workSkillStore = useWorkSkillStore();

const workSkill = ref(null);

const allWorkSkills = computed(() => workSkillStore.workSkills);

onMounted(() => {
  console.log("allWorkSkills: ", allWorkSkills.value);
});

function submit() {
  workSkillStore.deleteSkill(workSkill.value);
  workSkillStore.showDeleteSkillDialog = false;
}

function cancel() {
  workSkillStore.showDeleteSkillDialog = false;
}


</script>

<style scoped>

</style>
