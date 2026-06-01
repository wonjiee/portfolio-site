import { createRouter, createWebHistory } from 'vue-router'
import ProjectListView from "@/views/ProjectListView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      component:ProjectListView
    }
  ]
})

export default router
