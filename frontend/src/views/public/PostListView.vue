<template>

  <div class="container mt-4">

    <h2>블로그</h2>

    <div
        v-for="post in posts"
        :key="post.id"
        class="card mt-2"
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

  </div>

</template>

<script setup>

import { ref, onMounted } from 'vue'
import api from '@/api/axios'

const posts = ref([])

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

  loadPosts()

})

</script>