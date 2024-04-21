<template>
  <div class="absolute-center">
    <q-card class="card">
      <q-card-section>
        <div class="text-h5 text-center">Добро пожаловать</div>
        <q-form
          @submit="tryLogin"
          @reset="onReset"
          class="q-gutter-md"
        >
          <q-input clearable
                   label="Логин:"
                   v-model="user.username"
                   :rules="[
                   val => val && val.length > 0 || 'Введите логин',
                   ]"
          />
          <q-input clearable
                   type="password"
                   label="Пароль:"
                   v-model="user.password"
                   :rules="[
                   val => val && val.length > 0 || 'Введите пароль',
                   ]"
          />

          <div class="col q-gutter-y-md">
            <div class="row justify-center">
              <q-btn label="Войти" type="submit" color="primary"/>
            </div>
<!--            <div class="row justify-center">-->
<!--              <q-btn label="Очистить" type="reset" color="primary" flat no-caps/>-->
<!--            </div>-->
            <div class="row justify-center">
              <q-btn color="primary" @click="moveToSignUp" flat no-caps>Перейти на страницу регистрации</q-btn>
            </div>
          </div>
        </q-form>
      </q-card-section>
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
      if (response.status === 200) {
        localStorage.setItem("jwt", response.data);
        api.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('jwt')}`;
        router.push('/');
      }
    });
}

function moveToSignUp() {
  router.push('/auth/sign-up');
}

function onReset() {
  user.value.username = '';
  user.value.password = '';
}

</script>


<style scoped>

.card {
  width: 25rem;
}

</style>
