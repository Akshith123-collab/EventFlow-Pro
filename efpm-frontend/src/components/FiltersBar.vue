<script setup>
defineProps({ faculties: Array, years: Array });
const emit = defineEmits(['filter-changed','open-notify']);
import { ref } from 'vue';
import api from '../api/api';

const faculty = ref('');
const academicYear = ref('');
const amountFilter = ref('');
const agency = ref('');
const status = ref('');

function apply() {
  emit('filter-changed', {
    faculty: faculty.value,
    academicYear: academicYear.value,
    minAmount: amountFilter.value ? Number(amountFilter.value) : null,
    fundingAgency: agency.value,
    status: status.value
  });
}

async function downloadCsv() {
  const params = new URLSearchParams();
  if (academicYear.value) params.append('academicYear', academicYear.value);
  if (faculty.value) params.append('faculty', faculty.value);
  if (agency.value) params.append('fundingAgency', agency.value);
  if (amountFilter.value) params.append('minAmount', amountFilter.value);
  const url = `/projects/download?${params.toString()}`;
  const fullUrl = api.defaults.baseURL + url;
  window.open(fullUrl, '_blank');
}
</script>

<template>
  <div class="glass-card" style="display:flex;flex-wrap:wrap;gap:10px;align-items:center;">
    <select v-model="faculty"><option value="">All Faculty</option><option v-for="f in faculties" :key="f">{{f}}</option></select>
    <select v-model="academicYear"><option value="">All Years</option><option v-for="y in years" :key="y">{{y}}</option></select>
    <input type="text" v-model="agency" placeholder="Funding Agency" />
    <input type="number" v-model="amountFilter" placeholder="Min Amount (₹)" />
    <button @click="apply">Apply</button>
    <button @click="downloadCsv">Download CSV</button>
  </div>
</template>
