<template>

  <div class="container mt-4">

    <h2>블로그 관리</h2>

    <div class="mb-3">

      <input
          v-model="title"
          class="form-control mb-2"
          placeholder="제목"
      >

      <textarea
          v-model="content"
          class="form-control mb-2"
          placeholder="내용"
          rows="8"
      ></textarea>

      <button
          class="btn btn-primary"
          @click="savePost"
      >
        {{ editId ? '수정 완료' : '게시글 작성' }}
      </button>

    </div>

    <hr>

    <div
        v-for="post in posts"
        :key="post.id"
        class="card mt-2"
    >
      <div class="card-body">

        <h5>{{ post.title }}</h5>

        <button
            class="btn btn-danger"
            @click="deletePost(post.id)"
        >
          삭제
        </button>

        <button
            class="btn btn-warning ms-2"
            @click="editPost(post)"
        >
          수정
        </button>

      </div>

    </div>

  </div>

</template>

<script setup>

import { ref, onMounted } from 'vue'
import api from '@/api/axios'

const posts = ref([])

const editId = ref(null)

const title = ref('')
const content = ref('')

function editPost(post) {

  editId.value = post.id

  title.value = post.title
  content.value = post.content
}

async function loadPosts() {

  const res =
      await api.get('/api/posts')

  posts.value = res.data
}

async function deletePost(id) {

  await api.delete(
      `/api/posts/${id}`
  )

  await loadPosts()
}

async function savePost() {

  if (editId.value) {

    await api.put(
        `/api/posts/${editId.value}`,
        {
          title: title.value,
          content: content.value
        }
    )

  } else {

    await api.post(
        '/api/posts',
        {
          title: title.value,
          content: content.value
        }
    )
  }

  title.value = ''
  content.value = ''

  editId.value = null

  await loadPosts()
}

onMounted(() => {

  loadPosts()
})

</script>