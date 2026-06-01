<template>

  <div class="container mt-4">

    <h2>프로젝트 관리</h2>

    <div class="mb-3">

      <input
          v-model="title"
          class="form-control mb-2"
          placeholder="프로젝트 제목"
      >

      <textarea
          v-model="summary"
          class="form-control mb-2"
          placeholder="한 줄 소개"
      ></textarea>

      <input
          v-model="techStack"
          class="form-control mb-2"
          placeholder="기술 스택"
      >

      <textarea
          v-model="description"
          class="form-control mb-2"
          placeholder="설명"
      ></textarea>

      <input
          v-model="githubUrl"
          class="form-control mb-2"
          placeholder="GitHub URL"
      />

      <input
          v-model="demoUrl"
          class="form-control mb-2"
          placeholder="시연 영상 URL"
      />

      <input
          v-model="imageUrl"
          class="form-control mb-2"
          placeholder="이미지 URL"
      />

      <input
          v-model="period"
          class="form-control mb-2"
          placeholder="개발 기간"
      />

      <textarea
          v-model="role"
          class="form-control mb-2"
          placeholder="담당 역할"
      />

      <button
          class="btn btn-primary"
          @click="saveProject"
      >
        {{ editId ? '수정 완료' : '프로젝트 추가' }}
      </button>

    </div>

    <hr>

    <div
        v-for="project in projects"
        :key="project.id"
        class="card mt-2"
    >
      <div class="card-body">

        <h5>{{ project.title }}</h5>

        <p>{{ project.techStack }}</p>

        <p>{{ project.description }}</p>

        <p><strong>기간:</strong> {{ project.period }}</p>

        <p><strong>GitHub:</strong> {{ project.githubUrl }}</p>

        <button
            class="btn btn-danger"
            @click="deleteProject(project.id)"
        >
          삭제
        </button>

        <button
            class="btn btn-warning ms-2"
            @click="editProject(project)"
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

const projects = ref([])

const editId = ref(null)

const title = ref('')
const techStack = ref('')
const summary = ref('')
const description = ref('')

const githubUrl = ref('')
const demoUrl = ref('')
const imageUrl = ref('')
const period = ref('')
const role = ref('')

function editProject(project) {

  editId.value = project.id

  title.value = project.title
  techStack.value = project.techStack
  description.value = project.description
  summary.value = project.summary
  githubUrl.value = project.githubUrl
  demoUrl.value = project.demoUrl
  imageUrl.value = project.imageUrl
  period.value = project.period
  role.value = project.role
}

async function loadProjects() {

  const res =
      await api.get('/api/projects')

  projects.value = res.data
}

async function deleteProject(id) {

  await api.delete(
      `/api/projects/${id}`
  )

  await loadProjects()
}

async function saveProject() {

  if (editId.value) {

    await api.put(
        `/api/projects/${editId.value}`,
        {
          title: title.value,
          techStack: techStack.value,
          summary: summary.value,
          description: description.value,
          githubUrl: githubUrl.value,
          demoUrl: demoUrl.value,
          imageUrl: imageUrl.value,
          period: period.value,
          role: role.value
        }
    )

  } else {

    await api.post(
        '/api/projects',
        {
          title: title.value,
          techStack: techStack.value,
          summary: summary.value,
          description: description.value,
          githubUrl: githubUrl.value,
          demoUrl: demoUrl.value,
          imageUrl: imageUrl.value,
          period: period.value,
          role: role.value
        }
    )
  }

  title.value = ''
  techStack.value = ''
  summary.value =''
  description.value = ''

  githubUrl.value = ''
  demoUrl.value = ''
  imageUrl.value = ''
  period.value = ''
  role.value = ''

  editId.value = null

  await loadProjects()
}

onMounted(() => {

  loadProjects()

})

</script>