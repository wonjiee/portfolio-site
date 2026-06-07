<template>

  <header
      id="header"
      class="header d-flex align-items-center fixed-top"
  >

    <div
        class="container-fluid position-relative d-flex align-items-center justify-content-between"
    >

      <a
          href="/"
          class="logo d-flex align-items-center"
          @click="navigateFromMenu('/', $event)"
      >
        <h1 class="sitename mb-0">Jiwon</h1>
      </a>

      <button
          type="button"
          class="mobile-nav-toggle"
          aria-label="Open navigation"
          :aria-expanded="menuOpen"
          @click="toggleMobileNav"
      >
        <i class="bi bi-list"></i>
      </button>

    </div>

  </header>

  <Teleport to="body">

    <nav
        id="navmenu"
        class="navmenu"
        :class="{ 'navmenu-open': menuOpen }"
        @click.stop
    >

      <button
          type="button"
          class="mobile-nav-close"
          aria-label="Close navigation"
          @click="closeMobileNav"
      >
        <i class="bi bi-x-lg"></i>
      </button>

      <div class="profile-img">
        <img
            src="/style/img/profile/profile2.jpeg"
            alt="Profile"
            class="img-fluid rounded-circle"
        >
      </div>

      <a
          href="/"
          class="logo d-flex align-items-center justify-content-center"
          :class="{ active: route.path === '/' }"
          @click="navigateFromMenu('/', $event)"
      >
        <h1 class="sitename">Jiwon Eom</h1>
      </a>

      <div class="social-links text-center">
        <a
            href="https://github.com/wonjiee"
            target="_blank"
            rel="noopener"
            class="github"
            @click="closeMobileNav"
        >
          <i class="bi bi-github"></i>
        </a>
      </div>

      <ul>
        <li>
          <a
              href="/"
              :class="{ active: route.path === '/' }"
              @click="navigateFromMenu('/', $event)"
          >
            Home
          </a>
        </li>
        <li>
          <a
              href="/#portfolio"
              :class="{ active: isProjectsActive }"
              @click="goPortfolio"
          >
            Projects
          </a>
        </li>
        <li>
          <a
              href="/#blog"
              :class="{ active: isBlogActive }"
              @click="goBlog"
          >
            Blog
          </a>
        </li>
        <li>
          <a
              href="/#contact"
              :class="{ active: route.path === '/' && route.hash === '#contact' }"
              @click="goContact"
          >
            Contact
          </a>
        </li>
        <li v-if="!authStore.isAuthenticated">
          <a
              href="/login"
              @click="navigateFromMenu('/login', $event)"
          >
            Admin
          </a>
        </li>
        <li v-else>
          <a
              href="/admin"
              @click="navigateFromMenu('/admin', $event)"
          >
            Dashboard
          </a>
        </li>
      </ul>

    </nav>

  </Teleport>

</template>

<script setup>

import { ref, computed, onMounted, onUnmounted, watch, nextTick } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { initAos } from '@/composables/useTemplateEffects'
import { scrollToHomeSection } from '@/utils/scroll'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()

const menuOpen = ref(false)

const isProjectsActive = computed(() => {

  return route.path.startsWith('/projects')
      || (route.path === '/' && route.hash === '#portfolio')
})

const isBlogActive = computed(() => {

  return route.path.startsWith('/posts')
      || (route.path === '/' && route.hash === '#blog')
})

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

function onDocumentClick(event) {

  if (!menuOpen.value) {
    return
  }

  const nav = document.getElementById('navmenu')
  const toggle = event.target.closest('.mobile-nav-toggle')

  if (toggle || nav?.contains(event.target)) {
    return
  }

  closeMobileNav()
}

function navigateFromMenu(path, event) {

  event.preventDefault()
  closeMobileNav()

  if (path === '/') {

    if (route.path === '/') {
      window.scrollTo({ top: 0, behavior: 'smooth' })
      return
    }

    router.push('/').then(() => {
      window.scrollTo({ top: 0, behavior: 'smooth' })
    })
    return
  }

  router.push(path)
}

function goHomeSection(hash, event) {

  event.preventDefault()
  closeMobileNav()

  if (route.path === '/') {

    scrollToHomeSection(hash)
    return
  }

  router.push({ path: '/', hash }).then(async () => {
    await nextTick()
    await scrollToHomeSection(hash)
  })
}

function goPortfolio(event) {

  goHomeSection('#portfolio', event)
}

function goBlog(event) {

  goHomeSection('#blog', event)
}

function goContact(event) {

  goHomeSection('#contact', event)
}

watch(() => route.path, closeMobileNav)

onMounted(() => {

  closeMobileNav()
  initAos()
  document.addEventListener('click', onDocumentClick)
})

onUnmounted(() => {

  closeMobileNav()
  document.removeEventListener('click', onDocumentClick)
})

</script>
