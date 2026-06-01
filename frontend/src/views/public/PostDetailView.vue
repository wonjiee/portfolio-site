<template>

  <div class="container mt-4">

    <h2>{{ post.title }}</h2>

    <hr>

    <p>
      {{ post.content }}
    </p>

  </div>

</template>

<script setup>

import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api/axios'

const route = useRoute()

const post = ref({})

async function loadPost() {

  try {

    const res =
        await api.get(
            `/api/posts/${route.params.id}`
        )

    post.value = res.data

  } catch (error) {

    console.error(error)
  }
}

onMounted(() => {

  loadPost()
})

</script>