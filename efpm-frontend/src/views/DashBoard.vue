<script setup>
import Navbar from "../components/NavbarUser.vue";
import FiltersBar from "../components/FiltersBar.vue";
import ProjectCard from "../components/ProjectCard.vue";
import api from "../api/api";
import { ref, onMounted } from "vue";

const projects = ref([]);
const faculties = ref(["John Doe", "Akshith", "Dr. Kumar"]);
const years = ref(["2024-2025", "2025-2026"]);
const form = ref({
  title: "",
  fundingAgency: "",
  duration: "",
  startDate: "",
  endDate: "",
  principalInvestigator: "",
  academicYear: "",
  amountSanctioned: "",
  summary: "",
});
const file = ref(null);

async function fetchProjects() {
  const res = await api.get("/projects/all");
  projects.value = res.data;
}

async function uploadProject() {
  const fd = new FormData();
  fd.append("project", new Blob([JSON.stringify(form.value)], { type: "application/json" }));
  fd.append("file", file.value);
  await api.post("/projects/add", fd, {
    headers: { "Content-Type": "multipart/form-data" },
  });
  alert("Project uploaded!");
  fetchProjects();
}

function handleFilter(f) {
  console.log("Filters:", f);
}
onMounted(fetchProjects);
</script>

<template>
  <Navbar />
  <div style="padding:20px;">
    <FiltersBar :faculties="faculties" :years="years" @filter-changed="handleFilter" />
    <div class="glass-card" style="margin-top:20px;">
      <h3>Add New Project</h3>
      <input v-model="form.title" placeholder="Title" />
      <input v-model="form.fundingAgency" placeholder="Funding Agency" />
      <input v-model="form.duration" placeholder="Duration" />
      <input type="date" v-model="form.startDate" />
      <input type="date" v-model="form.endDate" />
      <input v-model="form.principalInvestigator" placeholder="PI" />
      <input v-model="form.academicYear" placeholder="Academic Year" />
      <input type="number" v-model="form.amountSanctioned" placeholder="Amount Sanctioned" />
      <textarea v-model="form.summary" placeholder="Summary (100 words)"></textarea>
      <input type="file" @change="(e) => (file.value = e.target.files[0])" />
      <button @click="uploadProject">Upload Project</button>
    </div>

    <div style="margin-top:20px;">
      <h3>All Projects</h3>
      <div v-for="p in projects" :key="p.id">
        <ProjectCard :project="p" />
      </div>
    </div>
  </div>
</template>
