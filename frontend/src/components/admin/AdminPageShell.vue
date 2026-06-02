<template>

  <div class="page-title light-background admin-page-title">
    <div class="container d-lg-flex justify-content-between align-items-center">
      <div>
        <h1 class="mb-1">{{ title }}</h1>
        <p
            v-if="subtitle"
            class="mb-0 text-muted"
        >
          {{ subtitle }}
        </p>
      </div>
      <nav
          v-if="breadcrumbs"
          class="breadcrumbs"
      >
        <ol>
          <li>
            <router-link to="/admin">Admin</router-link>
          </li>
          <li class="current">{{ title }}</li>
        </ol>
      </nav>
    </div>
  </div>

  <section class="section admin-section">
    <div class="container">
      <slot />
    </div>
  </section>

</template>

<script setup>

defineProps({
  title: {
    type: String,
    required: true
  },
  subtitle: {
    type: String,
    default: ''
  },
  breadcrumbs: {
    type: Boolean,
    default: true
  }
})

</script>
