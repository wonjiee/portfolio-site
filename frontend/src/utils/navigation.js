export function isPageReload() {

  const entry = performance.getEntriesByType('navigation')[0]

  if (entry?.type === 'reload') {
    return true
  }

  // Legacy API (older Safari)
  if (performance.navigation?.type === 1) {
    return true
  }

  return false
}

export function disableBrowserScrollRestoration() {

  if ('scrollRestoration' in history) {
    history.scrollRestoration = 'manual'
  }
}

export function scrollToPageTop() {

  window.scrollTo(0, 0)
  document.documentElement.scrollTop = 0
  document.body.scrollTop = 0
}

export function forceScrollToTopOnReload() {

  if (!isPageReload()) {
    return
  }

  scrollToPageTop()
  requestAnimationFrame(scrollToPageTop)
  setTimeout(scrollToPageTop, 0)
  setTimeout(scrollToPageTop, 50)
}

export function initScrollToTopOnReload() {

  disableBrowserScrollRestoration()
  forceScrollToTopOnReload()

  window.addEventListener('pageshow', (event) => {

    if (event.persisted || isPageReload()) {
      forceScrollToTopOnReload()
    }
  })

  window.addEventListener('load', forceScrollToTopOnReload)
}
