<template>

  <div class="container mt-4">

    <!-- 소개 -->

    <section class="mb-5">

      <h1>엄지원 포트폴리오</h1>

      <p class="mt-3">
        컴퓨터공학과 학생이며
        게임 개발과 웹 개발에 관심이 있습니다.
      </p>

      <p>
        Unity, Spring Boot, Vue를 활용한 프로젝트를 진행하고 있습니다.
      </p>

    </section>

    <!-- 프로젝트 -->

    <section class="mb-5">

      <div class="d-flex justify-content-between align-items-center">

        <h2>프로젝트</h2>

        <router-link
            to="/projects"
            class="btn btn-outline-primary"
        >
          전체 보기
        </router-link>

      </div>

      <div
          v-for="project in projects.slice(0, 3)"
          :key="project.id"
          class="card mt-3"
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

    </section>

    <!-- 블로그 -->

    <section>

      <div class="d-flex justify-content-between align-items-center">

        <h2>블로그</h2>

        <router-link
            to="/posts"
            class="btn btn-outline-secondary"
        >
          전체 보기
        </router-link>

      </div>

      <div
          v-for="post in posts.slice(0, 3)"
          :key="post.id"
          class="card mt-3"
      >

        <div class="card-body">

          <router-link
              :to="`/posts/${post.id}`"
              class="text-decoration-none"
          >
            <h5>{{ post.title }}</h5>
          </router-link>

          <p class="text-muted">
            {{ formatDate(post.createdAt) }}
          </p>

        </div>

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