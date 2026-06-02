import MarkdownIt from 'markdown-it'

const md = new MarkdownIt({
  html: false,
  linkify: true,
  breaks: true
})

export function renderMarkdown(source = '') {

  if (!source) {
    return ''
  }

  return md.render(source)
}

/** 목록 미리보기용 — 마크다운 기호 제거 */
export function excerptFromMarkdown(source = '', maxLength = 120) {

  const plain = source
      .replace(/```[\s\S]*?```/g, '')
      .replace(/`[^`]+`/g, '')
      .replace(/!\[[^\]]*]\([^)]*\)/g, '')
      .replace(/\[[^\]]*]\([^)]*\)/g, '')
      .replace(/[#>*_~\-]/g, '')
      .replace(/\s+/g, ' ')
      .trim()

  if (plain.length <= maxLength) {
    return plain
  }

  return `${plain.slice(0, maxLength)}…`
}
