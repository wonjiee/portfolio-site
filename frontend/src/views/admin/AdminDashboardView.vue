<template>

  <AdminPageShell
      title="관리자 대시보드"
      subtitle="콘텐츠를 관리합니다"
      :breadcrumbs="false"
  >

    <div class="row g-4">

      <div class="col-md-4">
        <router-link
            to="/admin/projects"
            class="admin-dashboard-tile text-decoration-none"
        >
          <i class="bi bi-folder2-open"></i>
          프로젝트 관리
        </router-link>
      </div>

      <div class="col-md-4">
        <router-link
            to="/admin/posts"
            class="admin-dashboard-tile text-decoration-none"
        >
          <i class="bi bi-journal-richtext"></i>
          블로그 관리
        </router-link>
      </div>

      <div class="col-md-4">
        <router-link
            to="/admin/contacts"
            class="admin-dashboard-tile text-decoration-none"
        >
          <i class="bi bi-envelope-paper"></i>
          문의 메시지
        </router-link>
      </div>

    </div>

  </AdminPageShell>

</template>

<script setup>

import AdminPageShell from '@/components/admin/AdminPageShell.vue'

</script>
