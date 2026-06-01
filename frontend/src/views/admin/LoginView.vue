<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import api from '@/api/axios'
import { useAuthStore } from '@/stores/auth'

const username = ref('')
const password = ref('')

const router = useRouter()
const authStore = useAuthStore()

async function login() {

  try {

    const response =
        await api.post(
            '/api/auth/login',
            {
              username: username.value,
              password: password.value
            }
        )

    authStore.login(
        response.data.token
    )

    router.push('/')

  } catch (error) {

    alert('로그인 실패')
  }
}
</script>

<template>

  <div class="container mt-5">

    <h2>관리자 로그인</h2>

    <input
        v-model="username"
        class="form-control mb-2"
        placeholder="아이디"
    >

    <input
        v-model="password"
        type="password"
        class="form-control mb-2"
        placeholder="비밀번호"
    >

    <button @click="login">
      로그인
    </button>

  </div>

</template>