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
              <span class="hero-title-name">Jiwon Eom</span>
              <span class="hero-title-sep"></span>
              <br>
              <span class="hero-title-sub">Game Developer &amp;<br class="d-sm-none"> Computer Science Student</span>
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
              <a
                  href="#portfolio"
                  class="btn btn-primary"
                  @click.prevent="scrollToPortfolio"
              >
                View My Work
              </a>
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
                머릿속 아이디어를 실제로 구현하여 사람들이 직접 경험할 수 있는 결과물로 만드는 것을 좋아합니다.
              </p>
              <p>
                게임 개발을 중심으로 다양한 프로젝트를 진행하며 기획부터 구현까지의 과정을 경험하고 있으며, 최근에는 절차적 콘텐츠 생성과 같은 기술을 활용해 더욱 흥미로운 경험을 만드는 방법에 관심을 가지고 있습니다.
              </p>
              <p>
                새로운 기술을 배우고 이를 프로젝트에 적용하며 꾸준히 성장하는 개발자를 목표로 하고 있습니다.
              </p>
            </div>

            <div class="journey-timeline">
              <div
                  v-for="item in journeyTimeline"
                  :key="`${item.year}-${item.title}`"
                  class="timeline-item"
              >
                <div class="year">{{ item.year }}</div>
                <div class="description">
                  <strong>{{ item.title }}</strong>
                  <div class="small text-muted mt-1">{{ item.detail }}</div>
                </div>
              </div>
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
          v-if="featuredLoading"
          class="text-center text-muted py-4"
      >
        불러오는 중…
      </p>

      <div
          v-else
          class="row g-4"
      >
        <div
            v-for="(project, index) in featuredProjects"
            :key="project.id"
            class="col-xl-4 col-md-6"
            data-aos="fade-up"
            :data-aos-delay="100 + index * 80"
        >
          <PortfolioCard
              :project="project"
              featured
              :revealed="isRevealed(project.id)"
              @click="onCardClick(project.id, $event)"
          />
        </div>
      </div>

      <p
          v-if="!featuredLoading && featuredProjects.length === 0"
          class="text-center text-muted mb-4"
          data-aos="fade-up"
      >
        추천으로 설정된 프로젝트가 없습니다.
      </p>

      <div
          v-if="!featuredLoading"
          class="text-center mt-4"
          data-aos="fade-up"
          data-aos-delay="150"
      >
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
  <section
      id="blog"
      class="section light-background"
  >

    <div class="container section-title">
      <h2>Blog</h2>
      <p>최근 게시물</p>
    </div>

    <div
        class="container"
        data-aos="fade-up"
    >

      <p
          v-if="postsLoading"
          class="text-muted py-4"
      >
        불러오는 중…
      </p>

      <div v-else>
        <div
            v-for="(post, index) in previewPosts"
            :key="post.id"
            class="mb-3"
            data-aos="fade-up"
            :data-aos-delay="100 + index * 80"
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
      </div>

      <router-link
          v-if="!postsLoading"
          to="/posts"
          class="btn btn-outline-primary mt-2"
          data-aos="fade-up"
          data-aos-delay="150"
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

import { ref, computed, onMounted, nextTick } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api/axios'
import { usePortfolioCardReveal } from '@/composables/usePortfolioCardReveal'
import { refreshAos } from '@/composables/useTemplateEffects'
import { isHomeSectionHash, scrollToHomeSection } from '@/utils/scroll'
import PortfolioCard from '@/components/PortfolioCard.vue'

const route = useRoute()
const { isRevealed, onCardClick } = usePortfolioCardReveal()

const featuredProjects = ref([])
const posts = ref([])
const featuredLoading = ref(true)
const postsLoading = ref(true)

const previewPosts = computed(() => posts.value.slice(0, 3))

const contactForm = ref({
  name: '',
  email: '',
  message: ''
})

const contactSending = ref(false)
const contactSuccess = ref('')
const contactError = ref('')

const journeyTimeline = [
  {
    year: '2024.09 - Present',
    title: '부경대학교 프로그래밍 동아리 WAP',
    detail: '게임 개발 및 알고리즘 스터디 참여'
  },
  {
    year: '2021.03 - Present',
    title: '부경대학교 컴퓨터 공학 전공',
    detail: '학사 과정 재학 중'
  }
]

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

function formatDate(date) {

  if (!date) return ''

  return new Date(date).toLocaleDateString('ko-KR')
}

function scrollToContact() {

  scrollToHomeSection('#contact')
}

function scrollToPortfolio() {

  scrollToHomeSection('#portfolio')
}

async function loadFeaturedProjects() {

  featuredLoading.value = true

  try {

    const res = await api.get('/api/projects/featured')
    featuredProjects.value = res.data

  } catch (error) {

    console.error(error)

  } finally {

    featuredLoading.value = false
  }
}

async function loadPosts() {

  postsLoading.value = true

  try {

    const res = await api.get('/api/posts')
    posts.value = res.data

  } catch (error) {

    console.error(error)

  } finally {

    postsLoading.value = false
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
  await nextTick()
  refreshAos()

  if (isHomeSectionHash(route.hash)) {

    await scrollToHomeSection(route.hash)
  }
})

</script>

<style scoped>
.hero-title-name {
  display: inline-block;
}

.hero-title-sep {
  display: inline-block;
  margin-left: 0.4rem;
  font-weight: 600;
  color: color-mix(in srgb, var(--heading-color), transparent 35%);
}

.hero-title-sub {
  display: inline-block;
  margin-top: 0.35rem;
  font-size: 0.72em;
  font-weight: 600;
  line-height: 1.25;
  color: color-mix(in srgb, var(--default-color), transparent 10%);
}

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
