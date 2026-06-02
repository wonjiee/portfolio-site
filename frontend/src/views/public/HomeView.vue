<template>

  <!-- Hero -->
  <section
      id="hero"
      class="hero section"
  >

    <div class="container">
      <div class="row g-0 align-items-center">

        <div
            class="col-lg-6 hero-content"
            data-aos="fade-right"
        >
          <div class="content-wrapper">
            <h1 class="hero-title">
              Jiwon Eom —
              <span>Game Developer &
                Computer Science Student</span>
            </h1>
            <p class="lead">
              컴퓨터공학을 전공하며 Unity 기반 게임 개발과 소프트웨어 개발을 공부하고 있습니다.
              새로운 기술을 배우고 직접 프로젝트로 구현하는 것을 좋아합니다.
            </p>

            <div
                class="hero-actions"
                data-aos="fade-up"
                data-aos-delay="200"
            >
              <router-link
                  to="/projects"
                  class="btn btn-primary"
              >
                View My Work
              </router-link>
              <a
                  href="#contact"
                  class="btn btn-outline"
                  @click.prevent="scrollToContact"
              >
                Get In Touch
              </a>
            </div>

            <div
                class="social-links"
                data-aos="fade-up"
                data-aos-delay="300"
            >
              <a
                  href="https://github.com/wonjiee"
                  target="_blank"
                  rel="noopener"
              >
                <i class="bi bi-github"></i>
              </a>
            </div>
          </div>
        </div>

        <div
            class="col-lg-6 hero-image"
            data-aos="fade-left"
        >
          <div class="image-container">
            <div class="floating-elements">
              <div class="floating-card card-1">
                <i class="bi bi-controller"></i>
                <span>Game Development</span>
              </div>
              <div class="floating-card card-2">
                <i class="bi bi-journal-code"></i>
                <span>Computer Science</span>
              </div>
              <div class="floating-card card-3">
                <i class="bi bi-cpu"></i>
                <span>Problem Solving</span>
              </div>
            </div>
            <img
                src="/style/img/profile/profile.jpg"
                alt="Jiwon Eom"
                class="img-fluid hero-main-image"
            >
            <div class="image-overlay"></div>
          </div>
        </div>

      </div>
    </div>

  </section>

  <!-- About -->
  <section
      id="about"
      class="about section"
  >

    <div class="container section-title">
      <h2>About</h2>
      <p>소개</p>
    </div>

    <div
        class="container"
        data-aos="fade-up"
    >

      <div class="row align-items-center">

        <div
            class="col-lg-5"
            data-aos="fade-right"
        >
          <div class="profile-image-wrapper">
            <div class="profile-image">
              <img
                  src="/style/img/profile/profile.jpg"
                  alt="Profile"
                  class="img-fluid"
              >
            </div>
          </div>
        </div>

        <div
            class="col-lg-7"
            data-aos="fade-left"
        >
          <div class="about-content">
            <div class="intro">
              <h2>안녕하세요, 엄지원입니다</h2>
              <p>
                대학교에서 컴퓨터 공학을 전공하며
                게임 개발, 알고리즘, 네트워크, 데이터베이스 등
                다양한 분야를 학습했습니다.
              </p>
              <p>
                개인 프로젝트와 팀 프로젝트를 통해
                기획부터 구현까지의 과정을 경험했으며,
                문제를 분석하고 해결하는 과정에 큰 흥미를 가지고 있습니다.
              </p>
            </div>
          </div>
        </div>

      </div>
    </div>

  </section>

  <!-- Skills -->
  <section
      id="skills"
      class="skills section light-background"
  >

    <div class="container section-title">
      <h2>Skills</h2>
      <p>사용 기술</p>
    </div>

    <div
        class="container"
        data-aos="fade-up"
    >

      <p class="text-center text-muted mb-4 skills-intro">
        프로젝트를 진행하며 직접 적용해 보고, 지속적으로 학습 중인 기술들입니다.
      </p>

      <div class="row g-4 justify-content-center">

        <div
            v-for="category in skillCategories"
            :key="category.title"
            class="col-md-6 col-lg-3"
            data-aos="fade-up"
        >
          <div class="skill-category-card h-100">
            <div class="skill-category-icon">
              <i :class="category.icon"></i>
            </div>
            <h3>{{ category.title }}</h3>
            <p class="skill-category-desc">
              {{ category.description }}
            </p>
            <ul class="skill-tag-list">
              <li
                  v-for="tag in category.tags"
                  :key="tag"
              >
                {{ tag }}
              </li>
            </ul>
          </div>
        </div>

      </div>

    </div>

  </section>

  <!-- Featured Projects -->
  <section
      id="portfolio"
      class="portfolio section"
  >

    <div class="container section-title">
      <h2>Portfolio</h2>
      <p>주요 프로젝트</p>
    </div>

    <div
        class="container"
        data-aos="fade-up"
    >

      <p
          v-if="featuredProjects.length === 0"
          class="text-center text-muted mb-4"
      >
        추천으로 설정된 프로젝트가 없습니다.
      </p>

      <div class="row g-4">

        <div
            v-for="project in featuredProjects"
            :key="project.id"
            class="col-xl-4 col-md-6"
        >
          <article class="portfolio-entry">
            <figure class="entry-image">
              <img
                  :src="projectImage(project)"
                  class="img-fluid"
                  :alt="project.title"
                  loading="lazy"
              >
              <div class="entry-overlay">
                <div class="overlay-content">
                  <div class="entry-meta">{{ project.techStack }}</div>
                  <h3 class="entry-title">
                    {{ project.title }}
                    <span class="badge bg-warning text-dark ms-1">추천</span>
                  </h3>
                  <div class="entry-links">
                    <router-link :to="`/projects/${project.id}`">
                      <i class="bi bi-arrow-right"></i>
                    </router-link>
                  </div>
                </div>
              </div>
            </figure>
          </article>
        </div>

      </div>

      <div class="text-center mt-4">
        <router-link
            to="/projects"
            class="btn btn-primary"
        >
          전체 프로젝트 보기
        </router-link>
      </div>

    </div>

  </section>

  <!-- Blog preview -->
  <section class="section light-background">

    <div class="container section-title">
      <h2>Blog</h2>
      <p>최근 게시물</p>
    </div>

    <div
        class="container"
        data-aos="fade-up"
    >

      <div
          v-for="post in posts.slice(0, 3)"
          :key="post.id"
          class="mb-3"
      >
        <router-link
            :to="`/posts/${post.id}`"
            class="text-decoration-none"
        >
          <h5>
            <span
                v-if="post.pinned"
                class="badge bg-primary me-1"
            >고정</span>
            {{ post.title }}
          </h5>
        </router-link>
        <p class="text-muted small mb-0">
          {{ formatDate(post.createdAt) }}
        </p>
      </div>

      <router-link
          to="/posts"
          class="btn btn-outline-primary mt-2"
      >
        전체 글 보기
      </router-link>

    </div>

  </section>

  <!-- Contact -->
  <section
      id="contact"
      class="contact section light-background"
  >

    <div class="container section-title">
      <h2>Contact</h2>
      <p>연락처</p>
    </div>

    <div
        class="container"
        data-aos="fade-up"
    >

      <div class="row g-4 g-lg-5">

        <div class="col-lg-5">
          <div class="info-box">
            <h3>Contact Info</h3>
            <p class="mb-4">

            </p>

            <div class="info-item">
              <div class="icon-box">
                <i class="bi bi-github"></i>
              </div>
              <div class="content">
                <h4>GitHub</h4>
                <p>
                  <a
                      href="https://github.com/wonjiee"
                      target="_blank"
                      rel="noopener"
                  >
                    github.com/wonjiee
                  </a>
                </p>
              </div>
            </div>

            <div class="info-item">
              <div class="icon-box">
                <i class="bi bi-envelope"></i>
              </div>
              <div class="content">
                <h4>Email</h4>
                <p>
                  <a href="mailto:wonjiee01@gmail.com">
                    wonjiee01@gmail.com
                  </a>
                </p>
              </div>
            </div>

          </div>
        </div>

        <div class="col-lg-7">
          <div class="contact-form">
            <h3>Get In Touch</h3>
            <p>이메일과 메시지를 남겨 주세요.</p>

            <form @submit.prevent="submitContact">
              <div class="row gy-3">
                <div class="col-md-6">
                  <input
                      v-model="contactForm.name"
                      type="text"
                      class="form-control"
                      placeholder="이름 (선택)"
                  >
                </div>
                <div class="col-md-6">
                  <input
                      v-model="contactForm.email"
                      type="email"
                      class="form-control"
                      placeholder="이메일 *"
                      required
                  >
                </div>
                <div class="col-12">
                  <textarea
                      v-model="contactForm.message"
                      class="form-control"
                      rows="6"
                      placeholder="메시지 *"
                      required
                  ></textarea>
                </div>
                <div class="col-12">
                  <p
                      v-if="contactSuccess"
                      class="text-success small mb-2"
                  >
                    {{ contactSuccess }}
                  </p>
                  <p
                      v-if="contactError"
                      class="text-danger small mb-2"
                  >
                    {{ contactError }}
                  </p>
                  <button
                      type="submit"
                      class="btn btn-primary"
                      :disabled="contactSending"
                  >
                    {{ contactSending ? '전송 중…' : '메시지 보내기' }}
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>

      </div>

    </div>

  </section>

</template>

<script setup>

import { ref, onMounted, nextTick } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api/axios'

const route = useRoute()

const featuredProjects = ref([])
const posts = ref([])

const contactForm = ref({
  name: '',
  email: '',
  message: ''
})

const contactSending = ref(false)
const contactSuccess = ref('')
const contactError = ref('')

const skillCategories = [
  {
    title: 'Game',
    icon: 'bi bi-controller',
    description: '유니티 기반 클라이언트 구현',
    tags: ['Unity', 'C#']
  },
  {
    title: 'Frontend',
    icon: 'bi bi-window',
    description: '웹/앱 화면 구성 및 데이터 연동',
    tags: ['Android', 'Vue 3', 'Vite', 'Pinia', 'Bootstrap']
  },
  {
    title: 'Backend',
    icon: 'bi bi-server',
    description: '서버 구축 및 데이터 처리',
    tags: ['Spring Boot', 'JPA', 'JWT', 'MariaDB']
  },
  {
    title: 'Tools',
    icon: 'bi bi-tools',
    description: '개발 환경 및 협업 도구',
    tags: ['Git', 'GitHub', 'Gradle', 'IntelliJ']
  }
]

function projectImage(project) {

  return project.imageUrl || '/style/img/abstract-bg-1.webp'
}

function formatDate(date) {

  if (!date) return ''

  return new Date(date).toLocaleDateString('ko-KR')
}

function scrollToContact() {

  document.querySelector('#contact')?.scrollIntoView({
    behavior: 'smooth'
  })
}

async function loadFeaturedProjects() {

  try {

    const res = await api.get('/api/projects/featured')
    featuredProjects.value = res.data

  } catch (error) {

    console.error(error)
  }
}

async function loadPosts() {

  try {

    const res = await api.get('/api/posts')
    posts.value = res.data

  } catch (error) {

    console.error(error)
  }
}

async function submitContact() {

  contactSuccess.value = ''
  contactError.value = ''
  contactSending.value = true

  try {

    await api.post('/api/contacts', {
      name: contactForm.value.name,
      email: contactForm.value.email,
      message: contactForm.value.message
    })

    contactSuccess.value = '메시지가 전달되었습니다. 감사합니다!'
    contactForm.value = {
      name: '',
      email: '',
      message: ''
    }

  } catch (error) {

    const data = error.response?.data

    contactError.value =
        data?.message
        || data?.detail
        || '전송에 실패했습니다. 잠시 후 다시 시도해 주세요.'

  } finally {

    contactSending.value = false
  }
}

onMounted(async () => {

  await Promise.all([loadFeaturedProjects(), loadPosts()])

  if (route.hash === '#contact') {

    await nextTick()
    scrollToContact()
  }
})

</script>

<style scoped>
.skills-intro {
  max-width: 36rem;
  margin-left: auto;
  margin-right: auto;
}

.skill-category-card {
  background: var(--surface-color);
  border: 1px solid color-mix(in srgb, var(--accent-color), transparent 85%);
  border-radius: 8px;
  padding: 1.5rem;
  text-align: center;
  transition: 0.3s;
}

.skill-category-card:hover {
  border-color: var(--accent-color);
  box-shadow: 0 8px 24px rgba(6, 92, 194, 0.1);
}

.skill-category-icon {
  width: 56px;
  height: 56px;
  margin: 0 auto 1rem;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  background: color-mix(in srgb, var(--accent-color), transparent 88%);
  color: var(--accent-color);
  font-size: 1.5rem;
}

.skill-category-card h3 {
  font-family: var(--heading-font);
  font-size: 1.1rem;
  color: var(--heading-color);
  margin-bottom: 0.5rem;
}

.skill-category-desc {
  font-size: 0.875rem;
  color: color-mix(in srgb, var(--default-color), transparent 25%);
  margin-bottom: 1rem;
}

.skill-tag-list {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  justify-content: center;
}

.skill-tag-list li {
  font-size: 0.8rem;
  font-weight: 500;
  padding: 0.35rem 0.65rem;
  border-radius: 4px;
  background: color-mix(in srgb, var(--accent-color), transparent 90%);
  color: var(--heading-color);
}
</style>
