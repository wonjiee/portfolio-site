<template>

  <header class="admin-bar">

    <div class="container-fluid d-flex align-items-center justify-content-between py-3">

      <router-link
          to="/"
          class="admin-bar-brand"
          @click="closePublicNavState"
      >
        <i class="bi bi-arrow-left me-2"></i>
        사이트로 돌아가기
      </router-link>

      <nav class="admin-bar-nav d-flex align-items-center gap-2">

        <router-link
            to="/admin"
            class="admin-nav-link"
            :class="{ active: route.path === '/admin' }"
        >
          <i class="bi bi-grid me-1"></i>
          Dashboard
        </router-link>

        <router-link
            to="/admin/projects"
            class="admin-nav-link"
            :class="{ active: route.path === '/admin/projects' }"
        >
          <i class="bi bi-folder me-1"></i>
          Projects
        </router-link>

        <router-link
            to="/admin/posts"
            class="admin-nav-link"
            :class="{ active: route.path === '/admin/posts' }"
        >
          <i class="bi bi-journal-text me-1"></i>
          Blog
        </router-link>

        <router-link
            to="/admin/contacts"
            class="admin-nav-link"
            :class="{ active: route.path === '/admin/contacts' }"
        >
          <i class="bi bi-envelope me-1"></i>
          Contact
        </router-link>

        <button
            class="btn btn-sm admin-logout-btn"
            type="button"
            @click="logout"
        >
          <i class="bi bi-box-arrow-right me-1"></i>
          Logout
        </button>

      </nav>

      <button
          class="admin-mobile-toggle d-lg-none btn"
          type="button"
          aria-label="Admin menu"
          @click="mobileOpen = !mobileOpen"
      >
        <i :class="mobileOpen ? 'bi bi-x-lg' : 'bi bi-list'"></i>
      </button>

    </div>

    <div
        v-show="mobileOpen"
        class="admin-mobile-menu d-lg-none border-top"
    >
      <router-link
          to="/admin"
          class="admin-mobile-link"
          @click="mobileOpen = false"
      >
        Dashboard
      </router-link>
      <router-link
          to="/admin/projects"
          class="admin-mobile-link"
          @click="mobileOpen = false"
      >
        Projects
      </router-link>
      <router-link
          to="/admin/posts"
          class="admin-mobile-link"
          @click="mobileOpen = false"
      >
        Blog
      </router-link>
      <router-link
          to="/admin/contacts"
          class="admin-mobile-link"
          @click="mobileOpen = false"
      >
        Contact
      </router-link>
      <button
          class="admin-mobile-link btn text-start w-100"
          type="button"
          @click="logout"
      >
        Logout
      </button>
    </div>

  </header>

</template>

<script setup>

import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()

const mobileOpen = ref(false)

function closePublicNavState() {

  document.body.classList.remove('mobile-nav-active')
}

function logout() {

  mobileOpen.value = false
  authStore.logout()
  router.push('/login')
}

</script>
