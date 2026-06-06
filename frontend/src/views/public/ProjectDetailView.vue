<template>

  <div class="page-title light-background">
    <div class="container d-lg-flex justify-content-between align-items-center">
      <h1 class="mb-2 mb-lg-0">{{ project.title || 'Project' }}</h1>
      <nav class="breadcrumbs">
        <ol>
          <li><router-link to="/">Home</router-link></li>
          <li><router-link to="/projects">Projects</router-link></li>
          <li class="current">Details</li>
        </ol>
      </nav>
    </div>
  </div>

  <section class="portfolio-details section">

    <div
        class="container"
        data-aos="fade-up"
    >

      <p
          v-if="loading"
          class="text-muted"
      >
        불러오는 중…
      </p>

      <div
          v-else-if="notFound"
          class="text-center py-5"
      >
        <p class="lead mb-4">프로젝트를 찾을 수 없습니다.</p>
        <router-link
            to="/projects"
            class="btn btn-outline-primary"
        >
          프로젝트 목록으로
        </router-link>
      </div>

      <div
          v-else-if="loadError"
          class="text-center py-5"
      >
        <p class="lead mb-4 text-danger">프로젝트를 불러오지 못했습니다.</p>
        <button
            class="btn btn-outline-primary"
            type="button"
            @click="loadProject"
        >
          다시 시도
        </button>
      </div>

      <div
          v-else
          class="row gy-4"
      >

        <div
            class="col-lg-6"
            data-aos="fade-right"
        >
          <div class="portfolio-details-media">
            <div class="main-image">
              <img
                  v-if="project.imageUrl"
                  :src="project.imageUrl"
                  :alt="project.title"
                  class="img-fluid rounded"
              >
              <img
                  v-else
                  src="/style/img/abstract-bg-1.webp"
                  :alt="project.title"
                  class="img-fluid rounded"
              >
            </div>

            <div
                v-if="project.techStack"
                class="tech-stack-badges mt-3"
            >
              <span
                  v-for="tag in techTags"
                  :key="tag"
              >
                {{ tag }}
              </span>
            </div>
          </div>
        </div>

        <div
            class="col-lg-6"
            data-aos="fade-left"
        >
          <div class="portfolio-details-content">

            <p
                v-if="project.summary"
                class="lead"
            >
              {{ project.summary }}
            </p>

            <h3>Overview</h3>
            <p>{{ project.description }}</p>

            <ul class="list-unstyled">
              <li v-if="project.period">
                <strong>Period:</strong> {{ project.period }}
              </li>
              <li v-if="project.role">
                <strong>Role:</strong> {{ project.role }}
              </li>
            </ul>

            <div class="mt-4">
              <a
                  v-if="project.githubUrl"
                  :href="project.githubUrl"
                  target="_blank"
                  rel="noopener"
                  class="btn btn-dark me-2"
              >
                <i class="bi bi-github"></i> GitHub
              </a>
              <a
                  v-if="project.demoUrl"
                  :href="project.demoUrl"
                  target="_blank"
                  rel="noopener"
                  class="btn btn-primary me-2"
              >
                Demo
              </a>
              <router-link
                  to="/projects"
                  class="btn btn-outline-secondary"
              >
                Back to Projects
              </router-link>
            </div>

          </div>
        </div>

      </div>

    </div>

  </section>

</template>

<script setup>

import { ref, computed, watch } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api/axios'
import { parseTechStack } from '@/utils/project'

const route = useRoute()
const project = ref({})
const loading = ref(true)
const notFound = ref(false)
const loadError = ref(false)

const techTags = computed(() => parseTechStack(project.value.techStack))

async function loadProject() {

  loading.value = true
  notFound.value = false
  loadError.value = false
  project.value = {}

  try {

    const res = await api.get(
        `/api/projects/${route.params.id}`
    )

    project.value = res.data

  } catch (error) {

    if (error.response?.status === 404) {
      notFound.value = true
    } else {
      loadError.value = true
      console.error(error)
    }

  } finally {

    loading.value = false
  }
}

watch(
    () => route.params.id,
    loadProject,
    { immediate: true }
)

</script>
