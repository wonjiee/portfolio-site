<template>

  <AdminPageShell
      title="프로젝트 관리"
      subtitle="순서·추천(홈 노출 최대 3개) 설정"
  >

    <p class="text-muted small mb-3">
      추천 {{ featuredCount }} / 3 · 순서 숫자가 작을수록 앞에 표시됩니다.
    </p>

    <div class="admin-form-panel">

      <h3 class="h5 mb-3">
        {{ editId ? '프로젝트 수정' : '새 프로젝트' }}
      </h3>

      <input
          v-model="title"
          class="form-control mb-2"
          placeholder="프로젝트 제목"
      >

      <textarea
          v-model="summary"
          class="form-control mb-2"
          placeholder="한 줄 소개"
          rows="2"
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
          rows="4"
      ></textarea>

      <input
          v-model="githubUrl"
          class="form-control mb-2"
          placeholder="GitHub URL"
      >

      <input
          v-model="demoUrl"
          class="form-control mb-2"
          placeholder="시연 영상 URL"
      >

      <input
          v-model="imageUrl"
          class="form-control mb-2"
          placeholder="이미지 URL"
      >

      <input
          v-model="period"
          class="form-control mb-2"
          placeholder="개발 기간"
      >

      <textarea
          v-model="role"
          class="form-control mb-2"
          placeholder="담당 역할"
          rows="2"
      ></textarea>

      <div class="row g-2 mb-3">
        <div class="col-auto">
          <label class="form-label small mb-0">표시 순서</label>
          <input
              v-model.number="sortOrder"
              type="number"
              min="1"
              class="form-control"
              style="width: 6rem"
          >
        </div>
        <div class="col-auto d-flex align-items-end">
          <div class="form-check">
            <input
                id="form-featured"
                v-model="featured"
                class="form-check-input"
                type="checkbox"
                :disabled="!featured && featuredCount >= 3"
            >
            <label
                class="form-check-label"
                for="form-featured"
            >
              홈 추천
            </label>
          </div>
        </div>
      </div>

      <p
          v-if="saveError"
          class="text-danger small"
      >
        {{ saveError }}
      </p>

      <button
          class="btn btn-primary"
          type="button"
          @click="saveProject"
      >
        {{ editId ? '수정 완료' : '프로젝트 추가' }}
      </button>

      <button
          v-if="editId"
          class="btn btn-outline-secondary ms-2"
          type="button"
          @click="resetForm"
      >
        취소
      </button>

    </div>

    <h3 class="h5 mb-3">등록된 프로젝트 (표시 순서)</h3>

    <div
        v-for="(project, index) in sortedProjects"
        :key="project.id"
        class="card admin-card mt-3"
    >
      <div class="card-body">

        <div class="d-flex flex-wrap align-items-start justify-content-between gap-2">
          <div>
            <span class="badge bg-secondary me-1">순서 {{ project.sortOrder }}</span>
            <span
                v-if="project.featured"
                class="badge bg-warning text-dark me-1"
            >
              추천
            </span>
            <h5 class="mb-1 mt-1">{{ project.title }}</h5>
            <p class="text-muted small mb-0">{{ project.techStack }}</p>
          </div>

          <div class="btn-group btn-group-sm">
            <button
                type="button"
                class="btn btn-outline-secondary"
                :disabled="index === 0"
                title="위로"
                @click="moveProject(project, -1)"
            >
              ↑
            </button>
            <button
                type="button"
                class="btn btn-outline-secondary"
                :disabled="index === sortedProjects.length - 1"
                title="아래로"
                @click="moveProject(project, 1)"
            >
              ↓
            </button>
          </div>
        </div>

        <div class="d-flex flex-wrap gap-3 mt-3 align-items-center">
          <div class="form-check">
            <input
                :id="`featured-${project.id}`"
                :checked="project.featured"
                class="form-check-input"
                type="checkbox"
                :disabled="!project.featured && featuredCount >= 3"
                @change="toggleFeatured(project, $event)"
            >
            <label
                class="form-check-label"
                :for="`featured-${project.id}`"
            >
              추천
            </label>
          </div>

          <label class="small mb-0">
            순서
            <input
                :value="project.sortOrder"
                type="number"
                class="form-control form-control-sm d-inline-block ms-1"
                style="width: 4.5rem"
                @change="updateSortOrder(project, $event)"
            >
          </label>
        </div>

        <div class="mt-3">
          <button
              class="btn btn-sm btn-danger"
              type="button"
              @click="deleteProject(project.id)"
          >
            삭제
          </button>
          <button
              class="btn btn-sm btn-warning ms-2"
              type="button"
              @click="editProject(project)"
          >
            수정
          </button>
        </div>

      </div>
    </div>

    <p
        v-if="projects.length === 0"
        class="text-muted mt-3"
    >
      등록된 프로젝트가 없습니다.
    </p>

  </AdminPageShell>

</template>

<script setup>

import { ref, computed, onMounted } from 'vue'
import api from '@/api/axios'
import AdminPageShell from '@/components/admin/AdminPageShell.vue'

const projects = ref([])
const editId = ref(null)
const saveError = ref('')

const title = ref('')
const techStack = ref('')
const summary = ref('')
const description = ref('')
const githubUrl = ref('')
const demoUrl = ref('')
const imageUrl = ref('')
const period = ref('')
const role = ref('')
const sortOrder = ref(1)
const featured = ref(false)

const sortedProjects = computed(() => {

  return [...projects.value].sort((a, b) => {

    if (a.sortOrder !== b.sortOrder) {
      return a.sortOrder - b.sortOrder
    }

    return b.id - a.id
  })
})

const featuredCount = computed(() => {

  return projects.value.filter((p) => p.featured).length
})

function projectPayload(extra = {}) {

  return {
    title: title.value,
    techStack: techStack.value,
    summary: summary.value,
    description: description.value,
    githubUrl: githubUrl.value,
    demoUrl: demoUrl.value,
    imageUrl: imageUrl.value,
    period: period.value,
    role: role.value,
    sortOrder: sortOrder.value,
    featured: featured.value,
    ...extra
  }
}

function toPayload(project) {

  return {
    title: project.title,
    techStack: project.techStack,
    summary: project.summary,
    description: project.description,
    githubUrl: project.githubUrl,
    demoUrl: project.demoUrl,
    imageUrl: project.imageUrl,
    period: project.period,
    role: project.role,
    sortOrder: project.sortOrder,
    featured: project.featured
  }
}

function resetForm() {

  editId.value = null
  saveError.value = ''
  title.value = ''
  techStack.value = ''
  summary.value = ''
  description.value = ''
  githubUrl.value = ''
  demoUrl.value = ''
  imageUrl.value = ''
  period.value = ''
  role.value = ''
  sortOrder.value = sortedProjects.value.length + 1
  featured.value = false
}

function editProject(project) {

  editId.value = project.id
  saveError.value = ''
  title.value = project.title
  techStack.value = project.techStack
  description.value = project.description
  summary.value = project.summary
  githubUrl.value = project.githubUrl
  demoUrl.value = project.demoUrl
  imageUrl.value = project.imageUrl
  period.value = project.period
  role.value = project.role
  sortOrder.value = project.sortOrder
  featured.value = project.featured
}

async function loadProjects() {

  const res = await api.get('/api/projects')
  projects.value = res.data
}

async function deleteProject(id) {

  if (!confirm('삭제할까요?')) {
    return
  }

  await api.delete(`/api/projects/${id}`)
  await loadProjects()
}

async function saveProject() {

  saveError.value = ''

  try {

    if (editId.value) {
      await api.put(`/api/projects/${editId.value}`, projectPayload())
    } else {
      await api.post('/api/projects', projectPayload())
    }

    resetForm()
    await loadProjects()

  } catch (error) {

    saveError.value =
        error.response?.data?.detail
        || error.response?.data?.message
        || '저장에 실패했습니다.'
  }
}

async function putProject(project) {

  await api.put(`/api/projects/${project.id}`, toPayload(project))
}

async function toggleFeatured(project, event) {

  const next = event.target.checked

  try {
    await putProject({ ...project, featured: next })
    await loadProjects()
  } catch (error) {
    alert(error.response?.data?.detail || '추천은 최대 3개까지입니다.')
    event.target.checked = !next
  }
}

async function updateSortOrder(project, event) {

  const value = parseInt(event.target.value, 10)

  if (Number.isNaN(value)) {
    return
  }

  await putProject({ ...project, sortOrder: value })
  await loadProjects()
}

async function moveProject(project, delta) {

  const list = sortedProjects.value
  const index = list.findIndex((p) => p.id === project.id)
  const other = list[index + delta]

  if (!other) {
    return
  }

  const aOrder = project.sortOrder
  const bOrder = other.sortOrder

  await putProject({ ...project, sortOrder: bOrder })
  await putProject({ ...other, sortOrder: aOrder })
  await loadProjects()
}

onMounted(async () => {

  await loadProjects()
  sortOrder.value = projects.value.length + 1
})

</script>
