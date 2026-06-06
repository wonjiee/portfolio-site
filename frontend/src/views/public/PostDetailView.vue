<template>

  <div class="page-title light-background">
    <div class="container d-lg-flex justify-content-between align-items-center">
      <h1 class="mb-2 mb-lg-0">{{ post.title || 'Blog' }}</h1>
      <nav class="breadcrumbs">
        <ol>
          <li><router-link to="/">Home</router-link></li>
          <li><router-link to="/posts">Blog</router-link></li>
          <li class="current">Post</li>
        </ol>
      </nav>
    </div>
  </div>

  <section class="section">
    <div
        class="container"
        data-aos="fade-up"
    >

      <p
          v-if="loading"
          class="text-muted"
      >
        불러오는 중…
      </p>

      <div
          v-else-if="notFound"
          class="text-center py-5"
      >
        <p class="lead mb-4">게시글을 찾을 수 없습니다.</p>
        <router-link
            to="/posts"
            class="btn btn-outline-primary"
        >
          블로그 목록으로
        </router-link>
      </div>

      <div
          v-else-if="loadError"
          class="text-center py-5"
      >
        <p class="lead mb-4 text-danger">게시글을 불러오지 못했습니다.</p>
        <button
            class="btn btn-outline-primary"
            type="button"
            @click="loadPost"
        >
          다시 시도
        </button>
      </div>

      <template v-else>
        <p class="text-muted mb-4">
          {{ formatDate(post.createdAt) }}
        </p>

        <article
            class="markdown-body post-markdown"
            v-html="renderedContent"
        />

        <router-link
            to="/posts"
            class="btn btn-outline-primary mt-4"
        >
          목록으로
        </router-link>
      </template>

    </div>
  </section>

</template>

<script setup>

import { ref, computed, watch } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api/axios'
import { renderMarkdown } from '@/utils/markdown'

const route = useRoute()
const post = ref({})
const loading = ref(true)
const notFound = ref(false)
const loadError = ref(false)

const renderedContent = computed(() => {

  return renderMarkdown(post.value.content)
})

function formatDate(date) {

  if (!date) return ''

  return new Date(date).toLocaleDateString('ko-KR')
}

async function loadPost() {

  loading.value = true
  notFound.value = false
  loadError.value = false
  post.value = {}

  try {

    const res = await api.get(`/api/posts/${route.params.id}`)
    post.value = res.data

  } catch (error) {

    if (error.response?.status === 404) {
      notFound.value = true
    } else {
      loadError.value = true
      console.error(error)
    }

  } finally {

    loading.value = false
  }
}

watch(
    () => route.params.id,
    loadPost,
    { immediate: true }
)

</script>

<style scoped>
.post-markdown {
  border: 1px solid color-mix(in srgb, #065cc2, transparent 85%);
  border-radius: 8px;
  padding: 2rem;
  max-width: 100%;
}
</style>
