<template>
  <q-editor
    v-model="editorText"
    :definitions="{
      save: {
        tip: 'Сохранить изменения',
        icon: 'save',
        handler: saveComment
      },
      skillName: {label: getSkillName()}
    }"
    :toolbar="[
        [ 'left', 'center', 'right', 'justify' ],
        ['bold', 'italic', 'strike', 'underline'],
        ['undo', 'redo'],
        ['save', 'skillName']
      ]"
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
  if (!!skillForTextEditor.value && !!skillForTextEditor.value.comment) {
    editorText.value = skillForTextEditor.value.comment;
  } else {
    editorText.value = "";
  }
});

function getSkillName() {
  if (!!skillForTextEditor.value && !!skillForTextEditor.value.name) {
    return skillForTextEditor.value.name;
  } else {
    return "";
  }
}

function saveComment() {
  if (!!skillForTextEditor.value && !!skillForTextEditor.value.comment) {
    skillForTextEditor.value.comment = editorText.value;
    workSkillStore.updateWorkSkill(skillForTextEditor.value);
  }
}

</script>

<style scoped>

</style>
