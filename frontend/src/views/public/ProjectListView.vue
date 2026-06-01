<template>

  <div class="container mt-4">

    <h2>프로젝트</h2>

    <div
        v-for="project in projects"
        :key="project.id"
        class="card mt-2"
    >
      <div class="card-body">

        <router-link
            :to="`/projects/${project.id}`"
            class="text-decoration-none"
        >
          <h5>{{ project.title }}</h5>
        </router-link>

        <p>{{ project.techStack }}</p>

        <p>{{ project.summary }}</p>

      </div>
    </div>

  </div>

</template>

<script setup>

import { ref, onMounted } from 'vue'
import api from '@/api/axios'

const projects = ref([])

async function loadProjects() {

  try {

    const res =
        await api.get('/api/projects')

    projects.value = res.data

  } catch (error) {

    console.error(error)
  }
}

onMounted(() => {

  loadProjects()

})

</script>