<template>
  <q-editor
    v-model="editorText"
    :definitions="{
        bold: {label: 'Bold', icon: null, tip: 'My bold tooltip'}
      }"
  />
</template>

<script setup>

import {computed, ref, watchEffect} from "vue";
import {useWorkSkillStore} from "stores/WorkSkill";

const workSkillStore = useWorkSkillStore();

const editorText = ref("");

const skillForTextEditor = computed(() => {
  return workSkillStore.skillForCommentEdit;
});

watchEffect(() => {
  console.log("skillForTextEditor.value.comment: ", skillForTextEditor);
  if (!!skillForTextEditor.value && !!skillForTextEditor.value.comment) {
    editorText.value = skillForTextEditor.value.comment;
  } else {
    editorText.value = "";
  }
});

</script>

<style scoped>

</style>
