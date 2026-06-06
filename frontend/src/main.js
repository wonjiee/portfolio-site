import { createApp } from 'vue'
import { createPinia } from 'pinia'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'aos/dist/aos.css'
import '@/assets/style/main.css'
import '@/assets/style/admin.css'
import 'github-markdown-css/github-markdown.css'
import '@/assets/style/markdown-overrides.css'
import '@/assets/style/list-reveal.css'

import App from './App.vue'
import router from './router'
import { inject } from '@vercel/analytics'
import {
  forceScrollToTopOnReload,
  initScrollToTopOnReload
} from '@/utils/navigation'

inject()

initScrollToTopOnReload()

const app = createApp(App)

app.use(createPinia())
app.use(router)

router.isReady().then(() => {
  forceScrollToTopOnReload()
})

app.mount('#app')
