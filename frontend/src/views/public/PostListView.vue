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

    <div class="container">

      <p
          v-if="loading"
          class="text-muted py-4"
      >
        불러오는 중…
      </p>

      <TransitionGroup
          v-else
          tag="div"
          name="list-content-reveal"
          appear
      >
        <article
            v-for="(post, index) in posts"
            :key="post.id"
            class="mb-4 pb-3 border-bottom"
            :style="revealDelay(index)"
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
      </TransitionGroup>

      <p
          v-if="!loading && posts.length === 0"
          class="text-muted"
      >
        등록된 글이 없습니다.
      </p>

      <div
          v-if="!loading"
          class="d-flex justify-content-center mt-4 list-section-actions"
      >
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

import { ref, onMounted, nextTick } from 'vue'
import api from '@/api/axios'
import { excerptFromMarkdown } from '@/utils/markdown'
import { refreshAos } from '@/composables/useTemplateEffects'

const posts = ref([])
const loading = ref(true)

function excerpt(content) {

  return excerptFromMarkdown(content, 100)
}

function formatDate(date) {

  if (!date) return ''

  return new Date(date).toLocaleDateString('ko-KR')
}

function revealDelay(index) {

  return { '--reveal-delay': `${index * 60}ms` }
}

async function loadPosts() {

  loading.value = true

  try {

    const res = await api.get('/api/posts')
    posts.value = res.data

  } catch (error) {

    console.error(error)

  } finally {

    loading.value = false
    await nextTick()
    refreshAos()
  }
}

onMounted(loadPosts)

</script>
