<template>

  <AdminPageShell
      title="블로그 관리"
      subtitle="Markdown · 고정 · 표시 순서"
  >

    <p class="text-muted small mb-3">
      고정 글이 목록 맨 위에 옵니다. 순서 숫자가 작을수록 앞에 표시됩니다.
    </p>

    <div class="admin-form-panel">

      <h3 class="h5 mb-2">
        {{ editId ? '게시글 수정' : '새 게시글' }}
      </h3>

      <p class="text-muted small mb-3">
        # 제목 · **굵게** · `코드` · ```코드 블록``` · [링크](url) 지원
      </p>

      <input
          v-model="title"
          class="form-control mb-2"
          placeholder="제목"
      >

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
                id="form-pinned"
                v-model="pinned"
                class="form-check-input"
                type="checkbox"
            >
            <label
                class="form-check-label"
                for="form-pinned"
            >
              상단 고정
            </label>
          </div>
        </div>
      </div>

      <div class="row g-3">
        <div class="col-lg-6">
          <label class="form-label small text-muted">본문 (Markdown)</label>
          <textarea
              v-model="content"
              class="form-control font-monospace"
              placeholder="## 소개&#10;&#10;내용을 작성하세요."
              rows="14"
          ></textarea>
        </div>
        <div class="col-lg-6">
          <label class="form-label small text-muted">미리보기</label>
          <div
              class="markdown-body admin-markdown-preview"
              v-html="previewHtml"
          />
        </div>
      </div>

      <div class="mt-3">
        <button
            class="btn btn-primary"
            type="button"
            @click="savePost"
        >
          {{ editId ? '수정 완료' : '게시글 작성' }}
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

    </div>

    <h3 class="h5 mb-3">등록된 게시글</h3>

    <div
        v-for="(post, index) in sortedPosts"
        :key="post.id"
        class="card admin-card mt-3"
    >
      <div class="card-body">

        <div class="d-flex flex-wrap justify-content-between gap-2">
          <div>
            <span
                v-if="post.pinned"
                class="badge bg-primary me-1"
            >
              고정
            </span>
            <span class="badge bg-secondary me-1">순서 {{ post.sortOrder }}</span>
            <h5 class="mb-0 mt-1">{{ post.title }}</h5>
          </div>
          <div class="btn-group btn-group-sm">
            <button
                type="button"
                class="btn btn-outline-secondary"
                :disabled="index === 0"
                @click="movePost(post, -1)"
            >
              ↑
            </button>
            <button
                type="button"
                class="btn btn-outline-secondary"
                :disabled="index === sortedPosts.length - 1"
                @click="movePost(post, 1)"
            >
              ↓
            </button>
          </div>
        </div>

        <div class="d-flex flex-wrap gap-3 mt-3 align-items-center">
          <div class="form-check">
            <input
                :id="`pinned-${post.id}`"
                :checked="post.pinned"
                class="form-check-input"
                type="checkbox"
                @change="togglePinned(post, $event)"
            >
            <label
                class="form-check-label"
                :for="`pinned-${post.id}`"
            >
              고정
            </label>
          </div>
          <label class="small mb-0">
            순서
            <input
                :value="post.sortOrder"
                type="number"
                class="form-control form-control-sm d-inline-block ms-1"
                style="width: 4.5rem"
                @change="updateSortOrder(post, $event)"
            >
          </label>
        </div>

        <div class="mt-3">
          <button
              class="btn btn-sm btn-danger"
              type="button"
              @click="deletePost(post.id)"
          >
            삭제
          </button>
          <button
              class="btn btn-sm btn-warning ms-2"
              type="button"
              @click="editPost(post)"
          >
            수정
          </button>
        </div>

      </div>
    </div>

    <p
        v-if="posts.length === 0"
        class="text-muted mt-3"
    >
      등록된 글이 없습니다.
    </p>

  </AdminPageShell>

</template>

<script setup>

import { ref, computed, onMounted } from 'vue'
import api from '@/api/axios'
import AdminPageShell from '@/components/admin/AdminPageShell.vue'
import { renderMarkdown } from '@/utils/markdown'

const posts = ref([])
const editId = ref(null)
const title = ref('')
const content = ref('')
const sortOrder = ref(1)
const pinned = ref(false)

const previewHtml = computed(() => renderMarkdown(content.value))

const sortedPosts = computed(() => {

  return [...posts.value].sort((a, b) => {

    if (a.pinned !== b.pinned) {
      return a.pinned ? -1 : 1
    }

    if (a.sortOrder !== b.sortOrder) {
      return a.sortOrder - b.sortOrder
    }

    return new Date(b.createdAt) - new Date(a.createdAt)
  })
})

function postPayload() {

  return {
    title: title.value,
    content: content.value,
    sortOrder: sortOrder.value,
    pinned: pinned.value
  }
}

function toPayload(post) {

  return {
    title: post.title,
    content: post.content,
    sortOrder: post.sortOrder,
    pinned: post.pinned
  }
}

function resetForm() {

  editId.value = null
  title.value = ''
  content.value = ''
  sortOrder.value = sortedPosts.value.length + 1
  pinned.value = false
}

function editPost(post) {

  editId.value = post.id
  title.value = post.title
  content.value = post.content
  sortOrder.value = post.sortOrder
  pinned.value = post.pinned
}

async function loadPosts() {

  const res = await api.get('/api/posts')
  posts.value = res.data
}

async function deletePost(id) {

  if (!confirm('삭제할까요?')) {
    return
  }

  await api.delete(`/api/posts/${id}`)
  await loadPosts()
}

async function savePost() {

  if (editId.value) {
    await api.put(`/api/posts/${editId.value}`, postPayload())
  } else {
    await api.post('/api/posts', postPayload())
  }

  resetForm()
  await loadPosts()
}

async function putPost(post) {

  await api.put(`/api/posts/${post.id}`, toPayload(post))
}

async function togglePinned(post, event) {

  await putPost({ ...post, pinned: event.target.checked })
  await loadPosts()
}

async function updateSortOrder(post, event) {

  const value = parseInt(event.target.value, 10)

  if (Number.isNaN(value)) {
    return
  }

  await putPost({ ...post, sortOrder: value })
  await loadPosts()
}

async function movePost(post, delta) {

  const list = sortedPosts.value
  const index = list.findIndex((p) => p.id === post.id)
  const other = list[index + delta]

  if (!other) {
    return
  }

  if (post.pinned !== other.pinned) {
    alert('고정 글과 일반 글 사이에서는 순서만 바꿀 수 없습니다. 고정을 해제하거나 같은 유형끼리 이동해 주세요.')
    return
  }

  const aOrder = post.sortOrder
  const bOrder = other.sortOrder

  await putPost({ ...post, sortOrder: bOrder })
  await putPost({ ...other, sortOrder: aOrder })
  await loadPosts()
}

onMounted(async () => {

  await loadPosts()
  sortOrder.value = posts.value.length + 1
})

</script>

<style scoped>
.admin-markdown-preview {
  min-height: 280px;
  max-height: 420px;
  overflow-y: auto;
  padding: 1rem;
  border: 1px solid color-mix(in srgb, var(--accent-color), transparent 85%);
  border-radius: 6px;
  background: var(--surface-color);
  font-size: 0.9rem;
}

.font-monospace {
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, monospace;
  font-size: 0.875rem;
}
</style>
