<template>

  <div class="container py-5">

    <!-- 소개 -->

    <section class="text-center py-5">

      <h1 class="display-3 fw-bold">
        Jiwon Eom
      </h1>

      <p class="lead mt-3">
        Computer Science Student
      </p>

      <p class="text-muted">
        Game Developer & Backend Developer
      </p>

      <p class="mt-4">
        Building games with Unity and web services with Spring Boot.
      </p>

    </section>

    <!-- Featured Projects -->

    <section class="mb-5">

      <div class="d-flex justify-content-between align-items-center">

        <h2>Featured Projects</h2>

        <router-link
            to="/projects"
            class="btn btn-outline-primary"
        >
          View All
        </router-link>

      </div>

      <div
          v-for="project in projects.slice(0, 3)"
          :key="project.id"
          class="card shadow-sm border-0 mt-3"
      >

        <div class="card-body">

          <router-link
              :to="`/projects/${project.id}`"
              class="text-decoration-none"
          >

            <h5 class="fw-bold">
              {{ project.title }}
            </h5>

          </router-link>

          <p class="mb-2">
            {{ project.summary }}
          </p>

          <p class="text-muted small mb-0">
            {{ project.techStack }}
          </p>

        </div>

      </div>

    </section>

    <!-- Tech Stack -->

    <section class="mb-5">

      <h2>Tech Stack</h2>

      <div class="mt-3">

        <h5>Frontend</h5>

        <span class="badge bg-primary me-2">
          Vue
        </span>

        <span class="badge bg-primary me-2">
          Pinia
        </span>

        <span class="badge bg-primary me-2">
          Bootstrap
        </span>

        <hr>

        <h5>Backend</h5>

        <span class="badge bg-success me-2">
          Spring Boot
        </span>

        <span class="badge bg-success me-2">
          JPA
        </span>

        <span class="badge bg-success me-2">
          JWT
        </span>

        <hr>

        <h5>Game</h5>

        <span class="badge bg-dark me-2">
          Unity
        </span>

        <span class="badge bg-dark me-2">
          C#
        </span>

        <span class="badge bg-dark me-2">
          Photon
        </span>

        <hr>

        <h5>Database</h5>

        <span class="badge bg-secondary me-2">
          MariaDB
        </span>

      </div>

    </section>

    <!-- Latest Posts -->

    <section class="mb-5">

      <div class="d-flex justify-content-between align-items-center">

        <h2>Latest Posts</h2>

        <router-link
            to="/posts"
            class="btn btn-outline-secondary"
        >
          View All
        </router-link>

      </div>

      <div
          v-for="post in posts.slice(0, 3)"
          :key="post.id"
          class="card shadow-sm border-0 mt-3"
      >

        <div class="card-body">

          <router-link
              :to="`/posts/${post.id}`"
              class="text-decoration-none"
          >

            <h5 class="fw-bold">
              {{ post.title }}
            </h5>

          </router-link>

          <p class="text-muted small mb-0">
            {{ formatDate(post.createdAt) }}
          </p>

        </div>

      </div>

    </section>

    <!-- Contact -->

    <section class="my-5">

      <h2>Contact</h2>

      <div class="mt-3">

        <p>
          GitHub :
          github.com/wonjiee
        </p>

        <p>
          Email :
          wonjiee01@gmail.com
        </p>

      </div>

    </section>

  </div>

</template>

<script setup>

import { ref, onMounted } from 'vue'
import api from '@/api/axios'

const projects = ref([])
const posts = ref([])

async function loadProjects() {

  try {

    const res =
        await api.get('/api/projects')

    projects.value = res.data

  } catch (error) {

    console.error(error)
  }
}

async function loadPosts() {

  try {

    const res =
        await api.get('/api/posts')

    posts.value = res.data

  } catch (error) {

    console.error(error)
  }
}

function formatDate(date) {

  if (!date) return ''

  return new Date(date)
      .toLocaleDateString('ko-KR')
}

onMounted(() => {

  loadProjects()
  loadPosts()

})

</script>