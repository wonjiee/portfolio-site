<template>

  <div class="page-title light-background">
    <div class="container d-lg-flex justify-content-between align-items-center">
      <h1 class="mb-2 mb-lg-0">Projects</h1>
      <nav class="breadcrumbs">
        <ol>
          <li><router-link to="/">Home</router-link></li>
          <li class="current">Projects</li>
        </ol>
      </nav>
    </div>
  </div>

  <section class="portfolio section">
    <div class="container section-title">
      <h2>Portfolio</h2>
      <p>프로젝트 목록</p>
    </div>

    <div class="container">

      <p
          v-if="loading"
          class="text-center text-muted py-4"
      >
        불러오는 중…
      </p>

      <TransitionGroup
          v-else
          tag="div"
          name="list-reveal"
          appear
          class="row g-4"
      >
        <div
            v-for="(project, index) in projects"
            :key="project.id"
            class="col-xl-4 col-lg-4 col-md-6"
            :style="revealDelay(index)"
        >
          <PortfolioCard
              :project="project"
              :revealed="isRevealed(project.id)"
              @click="onCardClick(project.id, $event)"
          />
        </div>
      </TransitionGroup>

      <p
          v-if="!loading && projects.length === 0"
          class="text-center text-muted mt-4"
      >
        등록된 프로젝트가 없습니다.
      </p>

      <div
          v-if="!loading"
          class="d-flex justify-content-center mt-4 list-section-actions"
      >
        <router-link
            to="/"
            class="btn btn-outline-primary"
        >
          <i class="bi bi-house-door me-1"></i>
          홈으로
        </router-link>
      </div>

    </div>
  </section>

</template>

<script setup>

import { ref, onMounted, nextTick } from 'vue'
import api from '@/api/axios'
import { usePortfolioCardReveal } from '@/composables/usePortfolioCardReveal'
import { refreshAos } from '@/composables/useTemplateEffects'
import PortfolioCard from '@/components/PortfolioCard.vue'

const { isRevealed, onCardClick } = usePortfolioCardReveal()

const projects = ref([])
const loading = ref(true)

function revealDelay(index) {

  return { '--reveal-delay': `${index * 70}ms` }
}

async function loadProjects() {

  loading.value = true

  try {

    const res = await api.get('/api/projects')
    projects.value = res.data

  } catch (error) {

    console.error(error)

  } finally {

    loading.value = false
    await nextTick()
    refreshAos()
  }
}

onMounted(loadProjects)

</script>
