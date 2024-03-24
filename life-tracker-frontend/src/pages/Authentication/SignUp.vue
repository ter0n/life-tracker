<template>
  <div class="absolute-center">
    <q-card class="card">
      <q-card-section>
        <div class="text-h5 text-center">Регистрация</div>
        <q-form
          @submit="tryRegister"
          @reset="onReset"
          class="q-gutter-md"
        >
          <q-input clearable
                   label="Логин:"
                   v-model="newUser.username"
                   :rules="[
                   val => val && val.length > 0 || 'Введите логин',
                   val => val.indexOf(' ') === -1 || 'Логин не должен содержать пробелы'
                   ]"
          />

          <q-input clearable
                   :type="isPwd ? 'password' : 'text'"
                   label="Пароль:"
                   v-model="newUser.password"
                   :rules="[
                   val => val && val.length > 0 || 'Введите пароль',
                   // val => val.length >= 6 || 'Длина пароля должна быть не меньше 6 символов'
                   ]"
          >
            <template v-slot:append>
              <q-icon
                :name="isPwd ? 'visibility_off' : 'visibility'"
                class="cursor-pointer"
                @click="isPwd = !isPwd"
              />
            </template>
          </q-input>

          <q-input clearable
                   :type="isPwdCheck ? 'password' : 'text'"
                   label="Повторите пароль:"
                   v-model="checkPassword"
                   :rules="[
                   val => val && val.length > 0 || 'Введите пароль еще раз',
                   val => val === newUser.password || 'Пароли не совпадают'
                   ]"
          >
            <template v-slot:append>
              <q-icon
                :name="isPwdCheck ? 'visibility_off' : 'visibility'"
                class="cursor-pointer"
                @click="isPwdCheck = !isPwdCheck"
              />
            </template>
          </q-input>

          <div class="col q-gutter-y-md">
            <div class="row justify-center">
              <q-btn label="Зарегистрироваться" type="submit" color="primary"/>
            </div>
<!--            <div class="row justify-center">-->
<!--              <q-btn label="Очистить" type="reset" color="primary" flat no-caps/>-->
<!--            </div>-->
            <div class="row justify-center">
              <q-btn color="primary" @click="moveToSignIn" flat no-caps>Перейти на страницу входа</q-btn>
            </div>
          </div>

        </q-form>

      </q-card-section>
      <!--      <q-card-actions class="justify-around">-->
      <!--        <q-btn color="primary" @click="tryRegister">Зарегистрироваться</q-btn>-->
      <!--        <q-btn color="primary" @click="moveToSignIn" flat>Вход</q-btn>-->
      <!--      </q-card-actions>-->
    </q-card>
  </div>
</template>

<script setup>

import {ref} from "vue";
import {api} from "boot/axios";
import {useRouter} from "vue-router";

const newUser = ref({
  username: "",
  password: "",
});

const checkPassword = ref("");
const isPwd = ref(true);
const isPwdCheck = ref(true);

const router = useRouter();

function tryRegister() {
  api.post(`/auth/sign-up`, newUser.value)
    .then(response => {
      if (response.status === 200) {
        router.push('/');
      }
    });
}

function onReset() {
  newUser.value.username = '';
  newUser.value.password = '';
  checkPassword.value = '';
}

function moveToSignIn() {
  router.push('/auth/sign-in');
}

</script>


<style scoped>

.card {
  width: 25rem;
}

</style>
