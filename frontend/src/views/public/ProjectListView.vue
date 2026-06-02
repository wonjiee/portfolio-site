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

    <div
        class="container"
        data-aos="fade-up"
    >

      <div class="row g-4">

        <div
            v-for="project in projects"
            :key="project.id"
            class="col-xl-4 col-lg-4 col-md-6"
        >
          <article class="portfolio-entry">
            <figure class="entry-image">
              <img
                  :src="projectImage(project)"
                  class="img-fluid"
                  :alt="project.title"
                  loading="lazy"
              >
              <div class="entry-overlay">
                <div class="overlay-content">
                  <div class="entry-meta">{{ project.techStack }}</div>
                  <h3 class="entry-title">{{ project.title }}</h3>
                  <p class="small text-white-50 mb-2">{{ project.summary }}</p>
                  <div class="entry-links">
                    <router-link :to="`/projects/${project.id}`">
                      <i class="bi bi-arrow-right"></i>
                    </router-link>
                  </div>
                </div>
              </div>
            </figure>
          </article>
        </div>

      </div>

      <p
          v-if="projects.length === 0"
          class="text-center text-muted mt-4"
      >
        등록된 프로젝트가 없습니다.
      </p>

      <div class="d-flex justify-content-center mt-4">
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

import { ref, onMounted } from 'vue'
import api from '@/api/axios'

const projects = ref([])

function projectImage(project) {

  return project.imageUrl || '/style/img/abstract-bg-1.webp'
}

async function loadProjects() {

  try {

    const res = await api.get('/api/projects')
    projects.value = res.data

  } catch (error) {

    console.error(error)
  }
}

onMounted(loadProjects)

</script>
