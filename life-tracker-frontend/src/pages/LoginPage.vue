<template>
  <div class="absolute-center">
    <q-card class="card">
      <q-card-section>
        <div class="text-h5 text-center">Вход</div>
        <q-input clearable
                 label="Логин:"
                 v-model="user.username"/>
        <q-input clearable
                 type="password"
                 label="Пароль:"
                 v-model="user.password"/>
      </q-card-section>
      <q-card-actions class="justify-around">
        <q-btn color="primary" @click="tryLogin">Войти</q-btn>
      </q-card-actions>
    </q-card>
  </div>
</template>

<script setup>

import {ref} from "vue";
import {api} from "boot/axios";
import {useRouter} from "vue-router";

const router = useRouter();

const user = ref({
  username: "",
  password: "",
});

function tryLogin() {
  api.post(`/auth/sign-in`, user.value)
    .then(response => {
      console.log("Log in!!!!!")
      if (response.status === 200) {
        localStorage.setItem("jwt", response.data);
        router.push('/');
      }
    });
}

</script>


<style scoped>

.card{
  width: 25rem;
}

</style>
