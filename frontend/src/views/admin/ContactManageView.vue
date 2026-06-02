<template>

  <AdminPageShell
      title="문의 메시지"
      subtitle="Contact 섹션에서 접수된 메일·메시지"
  >

    <p
        v-if="messages.length === 0"
        class="text-muted"
    >
      아직 접수된 문의가 없습니다.
    </p>

    <div
        v-for="item in messages"
        :key="item.id"
        class="card admin-card mt-3"
    >
      <div class="card-body">

        <div class="d-flex justify-content-between align-items-start flex-wrap gap-2">
          <div>
            <h5 class="mb-1">
              {{ item.name || '(이름 없음)' }}
            </h5>
            <a :href="`mailto:${item.email}`">
              {{ item.email }}
            </a>
            <p class="text-muted small mb-0 mt-1">
              {{ formatDate(item.createdAt) }}
            </p>
          </div>
          <button
              class="btn btn-sm btn-danger"
              type="button"
              @click="deleteMessage(item.id)"
          >
            삭제
          </button>
        </div>

        <hr>

        <p class="mb-0 contact-message-body">
          {{ item.message }}
        </p>

      </div>
    </div>

  </AdminPageShell>

</template>

<script setup>

import { ref, onMounted } from 'vue'
import api from '@/api/axios'
import AdminPageShell from '@/components/admin/AdminPageShell.vue'

const messages = ref([])

function formatDate(date) {

  if (!date) return ''

  return new Date(date).toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  })
}

async function loadMessages() {

  const res = await api.get('/api/contacts')
  messages.value = res.data
}

async function deleteMessage(id) {

  if (!confirm('이 문의를 삭제할까요?')) {
    return
  }

  await api.delete(`/api/contacts/${id}`)
  await loadMessages()
}

onMounted(loadMessages)

</script>

<style scoped>
.contact-message-body {
  white-space: pre-wrap;
  word-break: break-word;
}
</style>
