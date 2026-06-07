import { onMounted, onUnmounted } from 'vue'
import AOS from 'aos'
import { syncHeaderHeight } from '@/utils/scroll'

let aosInitialized = false

export function initAos() {

  if (aosInitialized) {
    AOS.refresh()
    return
  }

  AOS.init({
    duration: 600,
    easing: 'ease-in-out',
    once: true,
    mirror: false
  })

  aosInitialized = true
}

export function refreshAos() {

  if (!aosInitialized) {
    return
  }

  AOS.refresh()
}

export function useTemplateEffects() {

  function toggleScrolled() {

    const header = document.querySelector('#header')

    if (
        !header
        || !header.classList.contains('fixed-top')
    ) {
      return
    }

    if (window.scrollY > 100) {
      document.body.classList.add('scrolled')
    } else {
      document.body.classList.remove('scrolled')
    }
  }

  function toggleScrollTop() {

    const scrollTop = document.querySelector('.scroll-top')

    if (!scrollTop) {
      return
    }

    if (window.scrollY > 100) {
      scrollTop.classList.add('active')
    } else {
      scrollTop.classList.remove('active')
    }
  }

  function onScroll() {

    toggleScrolled()
    toggleScrollTop()
  }

  function scrollToTop(event) {

    event?.preventDefault()
    window.scrollTo({ top: 0, behavior: 'smooth' })
  }

  onMounted(() => {

    initAos()
    syncHeaderHeight()

    document.addEventListener('scroll', onScroll, { passive: true })
    window.addEventListener('resize', syncHeaderHeight, { passive: true })
    toggleScrolled()
    toggleScrollTop()
  })

  onUnmounted(() => {

    document.removeEventListener('scroll', onScroll)
    window.removeEventListener('resize', syncHeaderHeight)
    document.body.classList.remove('scrolled')
  })

  return {
    scrollToTop
  }
}
