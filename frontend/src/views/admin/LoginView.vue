<template>

  <div class="page-title light-background">
    <div class="container">
      <h1 class="mb-0">Admin Login</h1>
    </div>
  </div>

  <section class="section">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-5">

          <div class="card border-0 shadow-sm">
            <div class="card-body p-4">

              <h2 class="h4 mb-3">관리자 로그인</h2>

              <input
                  v-model="username"
                  class="form-control mb-2"
                  placeholder="아이디"
              >

              <input
                  v-model="password"
                  type="password"
                  class="form-control mb-3"
                  placeholder="비밀번호"
              >

              <button
                  class="btn btn-primary w-100"
                  type="button"
                  @click="login"
              >
                로그인
              </button>

            </div>
          </div>

        </div>
      </div>
    </div>
  </section>

</template>

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

    const response = await api.post('/api/auth/login', {
      username: username.value,
      password: password.value
    })

    authStore.login(response.data.token)
    router.push('/admin')

  } catch {

    alert('로그인 실패')
  }
}

</script>
