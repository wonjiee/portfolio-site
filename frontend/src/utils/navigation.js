export function isPageReload() {

  const entry = performance.getEntriesByType('navigation')[0]

  return entry?.type === 'reload'
}

export function disableBrowserScrollRestoration() {

  if ('scrollRestoration' in history) {
    history.scrollRestoration = 'manual'
  }
}

export function scrollToPageTop() {

  window.scrollTo(0, 0)
}
