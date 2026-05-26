<template>
  <div>
    {{ message }}
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const message = ref('로딩중')

onMounted(async () => {
  const res = await axios.get(
      'http://localhost:8080/test'
  )

  message.value = res.data
})
</script>