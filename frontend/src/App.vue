<template>

  <template v-if="isAdminRoute">

    <AdminBar />

    <main class="admin-main">
      <router-view />
    </main>

  </template>

  <template v-else>

    <Header />

    <main class="main">
      <router-view />
    </main>

    <Footer />

    <a
        href="#"
        class="scroll-top d-flex align-items-center justify-content-center"
        @click.prevent="scrollToTop"
    >
      <i class="bi bi-arrow-up-short"></i>
    </a>

  </template>

</template>

<script setup>

import { computed, onUnmounted, watch } from 'vue'
import { useRoute } from 'vue-router'

import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import AdminBar from '@/components/AdminBar.vue'
import { useTemplateEffects } from '@/composables/useTemplateEffects'

const route = useRoute()
const { scrollToTop } = useTemplateEffects()

const isAdminRoute = computed(() => {

  return route.path.startsWith('/admin')
})

function setBodyClass() {

  document.body.classList.remove(
      'index-page',
      'portfolio-details-page',
      'admin-page'
  )

  if (isAdminRoute.value) {
    document.body.classList.remove('mobile-nav-active', 'scrolled')
    document.body.classList.add('admin-page')
    return
  }

  if (route.path.startsWith('/projects/')) {
    document.body.classList.add('portfolio-details-page')
  } else {
    document.body.classList.add('index-page')
  }
}

watch(() => route.path, setBodyClass, { immediate: true })

onUnmounted(() => {

  document.body.classList.remove(
      'index-page',
      'portfolio-details-page',
      'admin-page',
      'scrolled',
      'mobile-nav-active'
  )
})

</script>
