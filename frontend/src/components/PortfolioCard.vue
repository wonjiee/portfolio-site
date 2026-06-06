<template>
  <article
      class="portfolio-entry"
      :class="{ 'is-revealed': revealed }"
      @click="$emit('click', $event)"
  >
    <figure class="entry-image">
      <img
          :src="imageUrl"
          class="img-fluid"
          :alt="project.title"
          loading="lazy"
      >
      <div class="entry-overlay">
        <div class="overlay-content">
          <ul
              v-if="techTags.visible.length"
              class="entry-tech-tags"
          >
            <li
                v-for="tag in techTags.visible"
                :key="tag"
            >
              {{ tag }}
            </li>
            <li
                v-if="techTags.extra > 0"
                class="entry-tech-more"
            >
              +{{ techTags.extra }}
            </li>
          </ul>
          <h3 class="entry-title">
            {{ project.title }}
            <span
                v-if="featured"
                class="entry-featured-badge"
            >
              추천
            </span>
          </h3>
          <p class="entry-summary">{{ project.summary }}</p>
          <div class="entry-links">
            <router-link :to="`/projects/${project.id}`">
              <i class="bi bi-arrow-right"></i>
            </router-link>
          </div>
        </div>
      </div>
    </figure>
  </article>
</template>

<script setup>

import { computed } from 'vue'
import { previewTechTags, projectImageUrl } from '@/utils/project'

const props = defineProps({
  project: {
    type: Object,
    required: true
  },
  featured: {
    type: Boolean,
    default: false
  },
  revealed: {
    type: Boolean,
    default: false
  }
})

defineEmits(['click'])

const imageUrl = computed(() => projectImageUrl(props.project))

const techTags = computed(() => previewTechTags(props.project.techStack, 4))

</script>
