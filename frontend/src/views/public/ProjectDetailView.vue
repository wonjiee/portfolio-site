<template>

  <div class="container py-5">

    <div class="card border-0 shadow-sm">

      <div class="card-body p-4">

        <h1 class="mb-2">
          {{ project.title }}
        </h1>

        <p
            v-if="project.summary"
            class="text-muted fs-5 mb-4"
        >
          {{ project.summary }}
        </p>

        <img
            v-if="project.imageUrl"
            :src="project.imageUrl"
            class="img-fluid rounded shadow-sm mb-4"
            style="max-height: 500px; width: 100%; object-fit: cover;"
            alt="프로젝트 이미지"
        >

        <h4>Overview</h4>

        <p>
          {{ project.description }}
        </p>

        <hr>

        <h4>Tech Stack</h4>

        <p class="mb-0">
          {{ project.techStack }}
        </p>

        <hr>

        <h4>Period</h4>

        <p class="mb-0">
          {{ project.period }}
        </p>

        <hr>

        <h4>Role</h4>

        <p class="mb-0">
          {{ project.role }}
        </p>

        <hr>

        <div class="mt-4">

          <a
              v-if="project.githubUrl"
              :href="project.githubUrl"
              target="_blank"
              class="btn btn-dark me-2"
          >
            GitHub
          </a>

          <a
              v-if="project.demoUrl"
              :href="project.demoUrl"
              target="_blank"
              class="btn btn-danger me-2"
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

</template>

<script setup>

import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api/axios'

const route = useRoute()

const project = ref({})

async function loadProject() {

  try {

    const res =
        await api.get(
            `/api/projects/${route.params.id}`
        )

    project.value = res.data

  } catch (error) {

    console.error(error)
  }
}

onMounted(() => {

  loadProject()

})

</script>