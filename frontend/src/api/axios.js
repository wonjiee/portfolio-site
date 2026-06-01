import axios from 'axios'
import { useAuthStore } from '@/stores/auth'

const api = axios.create({

    baseURL: 'http://localhost:8081'
})

api.interceptors.request.use(

    (config) => {

        const token =
            localStorage.getItem('token')

        if (token) {

            config.headers.Authorization =
                `Bearer ${token}`
        }

        return config
    }
)

api.interceptors.response.use(

    (response) => response,

    (error) => {

        const status = error.response?.status

        const isAuthLogin =
            error.config?.url?.includes('/api/auth/login')

        if (status === 401 && !isAuthLogin) {

            const authStore = useAuthStore()

            authStore.logout()

            import('@/router').then(({ default: router }) => {

                if (router.currentRoute.value.path !== '/login') {

                    router.push('/login')
                }
            })
        }

        return Promise.reject(error)
    }
)

export default api
