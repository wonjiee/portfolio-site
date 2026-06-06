import { createRouter, createWebHistory } from 'vue-router'
import AOS from 'aos'

import { useAuthStore } from '@/stores/auth'
import { isTokenExpired } from '@/utils/token'

import HomeView from '@/views/public/HomeView.vue'
import PostListView from '@/views/public/PostListView.vue'
import PostDetailView from "@/views/public/PostDetailView.vue";
import LoginView from "@/views/admin/LoginView.vue";
import AdminDashboardView from "@/views/admin/AdminDashboardView.vue";
import PostManageView from '@/views/admin/PostManageView.vue'
import ProjectManageView from "@/views/admin/ProjectManageView.vue";
import ContactManageView from '@/views/admin/ContactManageView.vue'
import ProjectListView from "@/views/public/ProjectListView.vue";
import ProjectDetailView from "@/views/public/ProjectDetailView.vue";
import NotFoundView from '@/views/public/NotFoundView.vue'
import { isPageReload } from '@/utils/navigation'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),

  scrollBehavior(to, from, savedPosition) {

    if (savedPosition) {
      return savedPosition
    }

    if (!from.matched.length || isPageReload()) {
      return { top: 0 }
    }

    if (to.hash) {
      return { el: to.hash, behavior: 'smooth' }
    }

    return { top: 0 }
  },

  routes: [
    {
      path: '/',
      component: HomeView
    },

    {
      path: '/projects',
      component: ProjectListView
    },
    {
      path: '/projects/:id',
      component: ProjectDetailView
    },
    {
      path: '/posts',
      component: PostListView
    },

    {
      path: '/posts/:id',
      component: PostDetailView
    },

    {
      path: '/login',
      component: LoginView
    },

    {
      path: '/admin',
      component: AdminDashboardView
    },
    {
      path: '/admin/projects',
      component: ProjectManageView
    },
    {
      path: '/admin/posts',
      component: PostManageView
    },
    {
      path: '/admin/contacts',
      component: ContactManageView
    },

    {
      path: '/:pathMatch(.*)*',
      name: 'not-found',
      component: NotFoundView
    }
  ]
})

router.beforeEach((to, from, next) => {

  const token = localStorage.getItem('token')
  const authStore = useAuthStore()

  if (to.path.startsWith('/admin')) {

    if (!token || isTokenExpired(token)) {

      authStore.logout()
      next('/login')
      return
    }
  }

  next()
})

router.afterEach((to, from) => {

  setTimeout(() => AOS.refresh(), 100)

  if (isPageReload() || !from.matched.length) {
    return
  }

  if (to.hash === '#contact' && to.path === '/') {

    setTimeout(() => {

      document.querySelector('#contact')?.scrollIntoView({
        behavior: 'smooth'
      })
    }, 300)
  }
})

export default router