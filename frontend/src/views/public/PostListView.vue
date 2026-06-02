<template>

  <div class="page-title light-background">
    <div class="container d-lg-flex justify-content-between align-items-center">
      <h1 class="mb-2 mb-lg-0">Blog</h1>
      <nav class="breadcrumbs">
        <ol>
          <li><router-link to="/">Home</router-link></li>
          <li class="current">Blog</li>
        </ol>
      </nav>
    </div>
  </div>

  <section class="section">
    <div class="container section-title">
      <h2>Blog</h2>
      <p>게시글 목록</p>
    </div>

    <div
        class="container"
        data-aos="fade-up"
    >

      <article
          v-for="post in posts"
          :key="post.id"
          class="mb-4 pb-3 border-bottom"
      >
        <router-link
            :to="`/posts/${post.id}`"
            class="text-decoration-none"
        >
          <h4>
            <span
                v-if="post.pinned"
                class="badge bg-primary me-1"
            >고정</span>
            {{ post.title }}
          </h4>
        </router-link>
        <p
            v-if="excerpt(post.content)"
            class="text-muted mb-2"
        >
          {{ excerpt(post.content) }}
        </p>
        <p class="text-muted small mb-0">
          {{ formatDate(post.createdAt) }}
        </p>
      </article>

      <p
          v-if="posts.length === 0"
          class="text-muted"
      >
        등록된 글이 없습니다.
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
import { excerptFromMarkdown } from '@/utils/markdown'

const posts = ref([])

function excerpt(content) {

  return excerptFromMarkdown(content, 100)
}

function formatDate(date) {

  if (!date) return ''

  return new Date(date).toLocaleDateString('ko-KR')
}

async function loadPosts() {

  try {

    const res = await api.get('/api/posts')
    posts.value = res.data

  } catch (error) {

    console.error(error)
  }
}

onMounted(loadPosts)

</script>
