<template>

  <div class="container mt-4">

    <h2>프로젝트 목록</h2>

    <div
        v-for="project in projects"
        :key="project.id"
        class="card mt-2"
    >

      <div class="card-body">

        <h5>{{ project.title }}</h5>

        <p>{{ project.techStack }}</p>

        <p>{{ project.description }}</p>

      </div>

    </div>

  </div>

</template>

<script setup>

import axios from 'axios'
import { ref,onMounted } from 'vue'

const projects = ref([])

onMounted(async()=>{

  const res =
      await axios.get(
          'http://localhost:8080/api/projects'
      )

  projects.value = res.data

})

</script>