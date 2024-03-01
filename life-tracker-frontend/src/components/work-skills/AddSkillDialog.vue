<template>
  <q-dialog v-model="workSkillStore.showAddSkillDialog">
    <q-card style="width: 300px" class="q-px-sm q-pb-md">
      <q-card-section>
        <div class="text-h6">Volumes</div>
      </q-card-section>

    </q-card>
  </q-dialog>
</template>

<script setup>

import {useWorkSkillStore} from "src/stores/WorkSkill"

const workSkillStore = useWorkSkillStore();


</script>

<style scoped>

</style>
