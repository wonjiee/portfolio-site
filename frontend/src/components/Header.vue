<template>

  <nav
      class="navbar navbar-expand-lg bg-white border-bottom"
  >

    <div class="container">

      <router-link
          to="/"
          class="navbar-brand fw-bold"
      >
        Jiwon Portfolio
      </router-link>

      <div class="navbar-nav ms-auto align-items-center">

        <router-link
            to="/projects"
            class="nav-link"
        >
          Projects
        </router-link>

        <router-link
            to="/posts"
            class="nav-link"
        >
          Blog
        </router-link>

        <router-link
            v-if="!authStore.isAuthenticated"
            to="/login"
            class="btn btn-outline-dark ms-3"
        >
          Admin
        </router-link>

        <router-link
            v-else
            to="/admin"
            class="btn btn-dark ms-3"
        >
          Dashboard
        </router-link>

      </div>

    </div>

  </nav>

</template>

<script setup>

import { useAuthStore } from '@/stores/auth'

const authStore = useAuthStore()

</script>