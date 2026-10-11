<template>
  <div class="p-8 max-w-3xl mx-auto">
    <h2 class="text-2xl font-semibold mb-6">Add New Project</h2>
    <form @submit.prevent="addProject" enctype="multipart/form-data" class="bg-white p-6 rounded shadow">
      <input v-model="title" placeholder="Project Title" class="input" required />
      <input v-model="principalInvestigator" placeholder="Principal Investigator" class="input" required />
      <input v-model="academicYear" placeholder="Academic Year (2025-2026)" class="input" required />
      <input v-model="fundingAgency" placeholder="Funding Agency" class="input" required />
      <input v-model.number="amountSanctioned" placeholder="Amount Sanctioned" class="input" required />
      <input type="file" @change="onFileChange" class="mb-4" />
      <button class="btn w-full">Submit</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import api from '../api/api';
const title = ref('');
const principalInvestigator = ref('');
const academicYear = ref('');
const fundingAgency = ref('');
const amountSanctioned = ref('');
const file = ref(null);

const onFileChange = (e) => { file.value = e.target.files[0]; };

const addProject = async () => {
  const formData = new FormData();
  const projectData = {
    title: title.value,
    principalInvestigator: principalInvestigator.value,
    academicYear: academicYear.value,
    fundingAgency: fundingAgency.value,
    amountSanctioned: amountSanctioned.value
  };
  formData.append('project', JSON.stringify(projectData));
  formData.append('file', file.value);
  await api.post('/projects/add', formData, { headers: { 'Content-Type': 'multipart/form-data' } });
  alert('Project added successfully');
};
</script>

<style scoped>
.input { width: 100%; margin-bottom: 1rem; padding: 8px; border: 1px solid #ccc; border-radius: 6px; }
.btn { background-color: #2563eb; color: white; padding: 10px; border: none; border-radius: 6px; cursor: pointer; }
</style>
