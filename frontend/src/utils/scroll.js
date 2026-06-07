export const HOME_SECTION_HASHES = [
  '#contact',
  '#portfolio',
  '#blog'
]

export function isHomeSectionHash(hash) {

  return HOME_SECTION_HASHES.includes(hash)
}

export function getHeaderOffset() {

  const raw = getComputedStyle(document.documentElement)
      .getPropertyValue('--header-height')
      .trim()

  const parsed = parseInt(raw, 10)

  if (parsed) {
    return parsed
  }

  const header = document.getElementById('header')

  return header?.offsetHeight ?? 72
}

export function scrollToElement(
    element,
    { behavior = 'smooth', extraOffset = 0 } = {}
) {

  if (!element) {
    return
  }

  const top = element.getBoundingClientRect().top
      + window.scrollY
      - getHeaderOffset()
      - extraOffset

  window.scrollTo({
    top: Math.max(0, top),
    behavior
  })
}

export function scrollToSelector(
    selector,
    options
) {

  scrollToElement(
      document.querySelector(selector),
      options
  )
}

export function scrollToHomeSection(
    hash,
    { behavior = 'smooth', maxAttempts = 40 } = {}
) {

  if (!hash) {
    return Promise.resolve(false)
  }

  return new Promise((resolve) => {

    let attempts = 0
    let finished = false

    const finish = (success) => {

      if (finished) {
        return
      }

      finished = true
      resolve(success)
    }

    const tryScroll = () => {

      const element = document.querySelector(hash)

      if (element) {

        scrollToElement(element, { behavior })
        finish(true)
        return
      }

      attempts++

      if (attempts >= maxAttempts) {
        return
      }

      requestAnimationFrame(tryScroll)
    }

    tryScroll()

    for (const delay of [150, 400, 800]) {

      setTimeout(() => {

        if (finished) {
          return
        }

        const element = document.querySelector(hash)

        if (element) {

          scrollToElement(element, { behavior })
          finish(true)
        }
      }, delay)
    }

    setTimeout(() => finish(false), 900)
  })
}

export function syncHeaderHeight() {

  const header = document.getElementById('header')

  if (!header) {
    return
  }

  document.documentElement.style.setProperty(
      '--header-height',
      `${header.offsetHeight}px`
  )
}
