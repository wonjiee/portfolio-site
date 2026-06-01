<template>

  <div class="container mt-4">

    <h2>관리자 페이지</h2>

    <div class="d-flex gap-2 mt-3">

      <router-link
          to="/admin/projects"
          class="btn btn-primary me-2"
      >
        프로젝트 관리
      </router-link>

      <router-link
          to="/admin/posts"
          class="btn btn-secondary me-2"
      >
        블로그 관리
      </router-link>

      <button
          class="btn btn-danger"
          @click="logout"
      >
        로그아웃
      </button>

    </div>

  </div>

</template>

<script setup>

import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const authStore = useAuthStore()

function logout() {

  authStore.logout()

  router.push('/')
}

</script>