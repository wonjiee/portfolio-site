<template>

  <div class="container mt-4">

    <h2>프로젝트 목록</h2>

    <!-- 추가 폼 -->

    <div class="mb-3">

      <input
          v-model="title"
          class="form-control mb-2"
          placeholder="프로젝트 제목"
      >

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

      <button
          @click="saveProject"
      >

        {{ editId
          ? '수정 완료'
          : '프로젝트 추가' }}

      </button>

    </div>

    <hr>

    <!-- 기존 목록 -->

    <div
        v-for="project in projects"
        :key="project.id"
        class="card mt-2"
    >

      <div class="card-body">

        <h5>{{ project.title }}</h5>

        <p>{{ project.techStack }}</p>

        <p>{{ project.description }}</p>

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

import axios from 'axios'
import { ref, onMounted } from 'vue'

const projects = ref([])

const editId = ref(null)

function editProject(project){

  editId.value = project.id

  title.value =
      project.title

  techStack.value =
      project.techStack

  description.value =
      project.description
}

const title = ref('')
const techStack = ref('')
const description = ref('')

async function loadProjects(){

  const res =
      await axios.get(
          'http://localhost:8081/api/projects'
      )

  projects.value = res.data
}

async function createProject(){

  await axios.post(

      'http://localhost:8081/api/projects',

      {
        title: title.value,
        techStack: techStack.value,
        description: description.value
      }

  )

  title.value = ''
  techStack.value = ''
  description.value = ''

  await loadProjects()
}

async function deleteProject(id){

  await axios.delete(
      `http://localhost:8081/api/projects/${id}`
  )

  await loadProjects()
}

async function saveProject(){

  if(editId.value){

    await axios.put(
        `http://localhost:8081/api/projects/${editId.value}`,
        {
          title: title.value,
          techStack: techStack.value,
          description: description.value
        }
    )

  }else{

    await axios.post(
        'http://localhost:8081/api/projects',
        {
          title: title.value,
          techStack: techStack.value,
          description: description.value
        }
    )
  }
  title.value = ''
  techStack.value = ''
  description.value = ''

  editId.value = null

  await loadProjects()
}

onMounted(() => {
  loadProjects()
})

</script>