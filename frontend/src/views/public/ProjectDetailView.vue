<template>

  <div class="container mt-5">

    <div class="card shadow">

      <div class="card-body">

        <h1 class="mb-4">
          {{ project.title }}
        </h1>

        <img
            v-if="project.imageUrl"
            :src="project.imageUrl"
            class="img-fluid rounded mb-4"
            alt="프로젝트 이미지"
        >

        <h4>📌 프로젝트 소개</h4>

        <p>
          {{ project.description }}
        </p>

        <hr>

        <h4>🛠 기술 스택</h4>

        <p>
          {{ project.techStack }}
        </p>

        <hr>

        <h4>📅 개발 기간</h4>

        <p>
          {{ project.period }}
        </p>

        <hr>

        <h4>👨‍💻 담당 역할</h4>

        <p>
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
            시연 영상
          </a>

          <router-link
              to="/projects"
              class="btn btn-secondary"
          >
            목록으로
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