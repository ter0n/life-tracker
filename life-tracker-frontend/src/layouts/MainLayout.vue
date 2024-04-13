<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title>
          Quasar App
        </q-toolbar-title>

        <q-btn label="Выход" icon="logout" @click="logout" flat/>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
    >
      <q-list>
        <q-item-label
          header
        >
          Essential Links
        </q-item-label>

        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { defineComponent, ref } from 'vue';
import EssentialLink from 'components/EssentialLink.vue';
import router from "src/router";

const linksList = [
  {
    title: 'Main',
    caption: 'Go to main page',
    icon: 'school',
    link: '/'
  },
  {
    title: 'Go Test Tree',
    caption: 'Try D3 Tree',
    icon: 'favorite',
    link: '/test'
  },
  {
    title: 'Go Official Tree',
    caption: 'Official D3 Tree',
    icon: 'favorite',
    link: '/official-tree'
  },
  {
    title: 'Example Pack',
    caption: 'Test Pack',
    icon: 'favorite',
    link: '/pack-from-inet'
  }
];

export default defineComponent({
  name: 'MainLayout',

  components: {
    EssentialLink
  },

  setup () {
    const leftDrawerOpen = ref(false)

    return {
      essentialLinks: linksList,
      leftDrawerOpen,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  },

  methods: {
    logout() {
      // удаление токена из локального хранилища
      delete localStorage['jwt'];
      // удаление заголовка
      delete api.defaults.headers.common['Authorization'];
      // перенаправляем на начальную страницу авторизации
      this.$router.push('/auth/sign-in');
    }
  }
})
</script>
