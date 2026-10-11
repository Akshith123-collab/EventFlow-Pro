<template>
  <div v-if="open" class="fixed inset-0 z-50 flex items-center justify-center bg-black/40">
    <div class="bg-slate-900 p-6 rounded w-full max-w-lg glass">
      <h3 class="text-xl mb-3">Add Status for: {{ project.title }}</h3>
      <form @submit.prevent="submit">
        <input v-model="facultyName" placeholder="Faculty name" class="w-full mb-3 p-2 rounded bg-slate-800" required />
        <textarea v-model="statusText" placeholder="Status update" class="w-full mb-3 p-2 rounded bg-slate-800" required></textarea>
        <input type="date" v-model="statusDate" class="mb-3 p-2 rounded bg-slate-800" />
        <input type="file" @change="onFile" class="mb-3" />
        <div class="flex gap-3">
          <button class="bg-emerald-500 px-3 py-1 rounded">Save</button>
          <button type="button" @click="$emit('close')" class="bg-gray-600 px-3 py-1 rounded">Cancel</button>
        </div>
      </form>
      <p v-if="message" class="text-green-300 mt-2">{{ message }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import api from '../api/api';

const props = defineProps({ open: Boolean, project: Object });
const emit = defineEmits(['close','saved']);

const facultyName = ref('');
const statusText = ref('');
const statusDate = ref('');
const file = ref(null);
const message = ref('');

watch(()=>props.open, v => {
  if (v) { facultyName.value = ''; statusText.value = ''; statusDate.value = new Date().toISOString().slice(0,10); file.value = null; message.value = ''; }
});

function onFile(e) { file.value = e.target.files[0]; }

async function submit() {
  try {
    const form = new FormData();
    form.append('status', JSON.stringify({ facultyName: facultyName.value, status: statusText.value }));
    if (file.value) form.append('file', file.value);
    const res = await api.post(`/projects/${props.project.id}/status`, form, { headers: { 'Content-Type': 'multipart/form-data' }});
    message.value = 'Status updated';
    emit('saved', res.data);
    setTimeout(()=>emit('close'), 800);
  } catch (e) {
    console.error(e);
    message.value = 'Update failed';
  }
}
</script>
