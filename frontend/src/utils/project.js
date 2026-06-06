export function parseTechStack(techStack) {

  if (!techStack) {
    return []
  }

  return String(techStack)
      .split(',')
      .map((tag) => tag.trim())
      .filter(Boolean)
}

export function previewTechTags(techStack, limit = 4) {

  const tags = parseTechStack(techStack)
  const visible = tags.slice(0, limit)
  const extra = Math.max(tags.length - limit, 0)

  return { visible, extra }
}

export function projectImageUrl(project) {

  return project?.imageUrl || '/style/img/abstract-bg-1.webp'
}
