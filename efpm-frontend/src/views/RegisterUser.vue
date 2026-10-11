<script setup>
import { ref } from "vue";
import { useAuthStore } from "../store/auth";
import { useRouter } from "vue-router";

const name = ref("");
const email = ref("");
const password = ref("");
const router = useRouter();
const auth = useAuthStore();

async function register() {
  await auth.register(name.value, email.value, password.value);
  alert("Registered successfully! Please login.");
  router.push("/login");
}
</script>

<template>
  <div class="glass-card" style="max-width:400px;margin:80px auto;text-align:center;">
    <h2>Register</h2>
    <input v-model="name" placeholder="Name" /><br /><br />
    <input v-model="email" placeholder="College Email" type="email" /><br /><br />
    <input v-model="password" placeholder="Password" type="password" /><br /><br />
    <button @click="register">Register</button>
  </div>
</template>
