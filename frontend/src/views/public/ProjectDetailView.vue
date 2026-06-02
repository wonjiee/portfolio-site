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

      <div class="row gy-4">

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

import { ref, computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api/axios'

const route = useRoute()
const project = ref({})

const techTags = computed(() => {

  if (!project.value.techStack) {
    return []
  }

  return project.value.techStack
      .split(',')
      .map((t) => t.trim())
      .filter(Boolean)
})

async function loadProject() {

  try {

    const res = await api.get(
        `/api/projects/${route.params.id}`
    )

    project.value = res.data

  } catch (error) {

    console.error(error)
  }
}

onMounted(loadProject)

</script>
