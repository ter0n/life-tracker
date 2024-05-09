<template>
  <q-editor
    v-model="editorText"
    min-height="75vh"
    max-height="75vh"
    :definitions="{
      save: {
        tip: 'Сохранить изменения',
        icon: 'save',
        handler: saveComment
      },
      skillName: {label: getSkillName()}
    }"
    :toolbar="[
        [
          {
            label: $q.lang.editor.align,
            icon: $q.iconSet.editor.align,
            fixedLabel: true,
            list: 'only-icons',
            options: ['left', 'center', 'right', 'justify']
          }
        ],
        ['bold', 'italic', 'strike', 'underline', 'subscript', 'superscript'],
        ['token', 'hr', 'link', 'custom_btn'],
        ['print', 'fullscreen'],
        [
          {
            label: $q.lang.editor.formatting,
            icon: $q.iconSet.editor.formatting,
            list: 'no-icons',
            options: [
              'p',
              'h1',
              'h2',
              'h3',
              'h4',
              'h5',
              'h6',
              'code'
            ]
          },
          {
            label: $q.lang.editor.fontSize,
            icon: $q.iconSet.editor.fontSize,
            fixedLabel: true,
            fixedIcon: true,
            list: 'no-icons',
            options: [
              'size-1',
              'size-2',
              'size-3',
              'size-4',
              'size-5',
              'size-6',
              'size-7'
            ]
          },
          {
            label: $q.lang.editor.defaultFont,
            icon: $q.iconSet.editor.font,
            fixedIcon: true,
            list: 'no-icons',
            options: [
              'default_font',
              'arial',
              'arial_black',
              'comic_sans',
              'courier_new',
              'impact',
              'lucida_grande',
              'times_new_roman',
              'verdana'
            ]
          },
          'removeFormat'
        ],
        ['quote', 'unordered', 'ordered', 'outdent', 'indent'],

        ['undo', 'redo'],
        ['viewsource'],
        ['save', 'skillName']
      ]"
    :fonts="{
        arial: 'Arial',
        arial_black: 'Arial Black',
        comic_sans: 'Comic Sans MS',
        courier_new: 'Courier New',
        impact: 'Impact',
        lucida_grande: 'Lucida Grande',
        times_new_roman: 'Times New Roman',
        verdana: 'Verdana'
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
  if (!!skillForTextEditor.value) {
    skillForTextEditor.value.comment = editorText.value;
    workSkillStore.updateWorkSkill(skillForTextEditor.value);
  }
}

</script>

<style scoped>

</style>
