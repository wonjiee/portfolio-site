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
    </div>
  </section>

</template>

<script setup>

import { ref, computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api/axios'
import { renderMarkdown } from '@/utils/markdown'

const route = useRoute()
const post = ref({})

const renderedContent = computed(() => {

  return renderMarkdown(post.value.content)
})

function formatDate(date) {

  if (!date) return ''

  return new Date(date).toLocaleDateString('ko-KR')
}

async function loadPost() {

  try {

    const res = await api.get(`/api/posts/${route.params.id}`)
    post.value = res.data

  } catch (error) {

    console.error(error)
  }
}

onMounted(loadPost)

</script>

<style scoped>
.post-markdown {
  border: 1px solid color-mix(in srgb, #065cc2, transparent 85%);
  border-radius: 8px;
  padding: 2rem;
  max-width: 100%;
}
</style>
