<template>

  <header
      id="header"
      class="header d-flex align-items-center fixed-top"
  >

    <div
        class="container-fluid position-relative d-flex align-items-center justify-content-between"
    >

      <router-link
          to="/"
          class="logo d-flex align-items-center"
          @click="closeMobileNav"
      >
        <h1 class="sitename mb-0">Jiwon</h1>
      </router-link>

      <nav
          id="navmenu"
          class="navmenu"
      >

        <div class="profile-img">
          <img
              src="/style/img/profile/profile2.jpeg"
              alt="Profile"
              class="img-fluid rounded-circle"
          >
        </div>

        <router-link
            to="/"
            class="logo d-flex align-items-center justify-content-center"
            :class="{ active: route.path === '/' }"
            @click="closeMobileNav"
        >
          <h1 class="sitename">Jiwon Eom</h1>
        </router-link>

        <div class="social-links text-center">
          <a
              href="https://github.com/wonjiee"
              target="_blank"
              rel="noopener"
              class="github"
          >
            <i class="bi bi-github"></i>
          </a>
        </div>

        <ul>
          <li>
            <router-link
                to="/"
                :class="{ active: route.path === '/' }"
                @click="closeMobileNav"
            >
              Home
            </router-link>
          </li>
          <li>
            <router-link
                to="/projects"
                :class="{ active: route.path.startsWith('/projects') }"
                @click="closeMobileNav"
            >
              Projects
            </router-link>
          </li>
          <li>
            <router-link
                to="/posts"
                :class="{ active: route.path.startsWith('/posts') }"
                @click="closeMobileNav"
            >
              Blog
            </router-link>
          </li>
          <li>
            <a
                href="/#contact"
                @click="goContact"
            >
              Contact
            </a>
          </li>
          <li v-if="!authStore.isAuthenticated">
            <router-link
                to="/login"
                @click="closeMobileNav"
            >
              Admin
            </router-link>
          </li>
          <li v-else>
            <router-link
                to="/admin"
                @click="closeMobileNav"
            >
              Dashboard
            </router-link>
          </li>
        </ul>

        <i
            class="mobile-nav-toggle d-xl-none bi"
            :class="menuOpen ? 'bi-x' : 'bi-list'"
            role="button"
            aria-label="Toggle navigation"
            @click="toggleMobileNav"
        ></i>

      </nav>

    </div>

  </header>

</template>

<script setup>

import { ref, onMounted, onUnmounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { initAos } from '@/composables/useTemplateEffects'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()

const menuOpen = ref(false)

function toggleMobileNav() {

  menuOpen.value = !menuOpen.value
  document.body.classList.toggle(
      'mobile-nav-active',
      menuOpen.value
  )
}

function closeMobileNav() {

  menuOpen.value = false
  document.body.classList.remove('mobile-nav-active')
}

function goContact(event) {

  closeMobileNav()

  if (route.path === '/') {

    event.preventDefault()
    document.querySelector('#contact')?.scrollIntoView({
      behavior: 'smooth'
    })
    return
  }

  router.push('/#contact')
}

onMounted(() => {

  closeMobileNav()
  initAos()
})

onUnmounted(() => {

  closeMobileNav()
})

</script>
