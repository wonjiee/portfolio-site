import { ref, onMounted, onUnmounted } from 'vue'

function isTouchLikeDevice() {

  return window.matchMedia('(hover: none), (pointer: coarse)').matches
}

export function usePortfolioCardReveal() {

  const revealedId = ref(null)

  function isRevealed(projectId) {

    return revealedId.value === projectId
  }

  function onCardClick(projectId, event) {

    if (!isTouchLikeDevice()) {
      return
    }

    event.stopPropagation()

    const detailLink = event.target.closest('.entry-links a')

    if (revealedId.value === projectId) {

      if (detailLink) {
        return
      }

      revealedId.value = null
      return
    }

    if (detailLink) {
      event.preventDefault()
    }

    revealedId.value = projectId
  }

  function closeRevealed() {

    revealedId.value = null
  }

  onMounted(() => {

    document.addEventListener('click', closeRevealed)
  })

  onUnmounted(() => {

    document.removeEventListener('click', closeRevealed)
  })

  return {
    isRevealed,
    onCardClick
  }
}
