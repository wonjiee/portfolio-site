<template>

  <nav class="navbar navbar-expand-lg bg-light border-bottom">

    <div class="container">

      <div>

        <router-link
            to="/"
            class="btn btn-link"
        >
          홈
        </router-link>

        <router-link
            to="/projects"
            class="btn btn-link"
        >
          프로젝트
        </router-link>

        <router-link
            to="/posts"
            class="btn btn-link"
        >
          블로그
        </router-link>

      </div>

      <div>

        <router-link
            v-if="!authStore.isAuthenticated"
            to="/login"
            class="btn btn-primary"
        >
          관리자 로그인
        </router-link>

        <router-link
            v-else
            to="/admin"
            class="btn btn-dark"
        >
          관리자 페이지
        </router-link>

      </div>

    </div>

  </nav>

</template>

<script setup>
import { useAuthStore } from '@/stores/auth'

const authStore = useAuthStore()
</script>