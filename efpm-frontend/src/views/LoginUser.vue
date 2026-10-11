<script setup>
import { ref } from "vue";
import { useAuthStore } from "../store/auth";
import { useRouter } from "vue-router";

const email = ref("");
const password = ref("");
const router = useRouter();
const auth = useAuthStore();

async function login() {
  try {
    await auth.login(email.value, password.value);
    router.push("/dashboard");
  } catch {
    alert("Invalid credentials");
  }
}
</script>

<template>
  <div class="glass-card" style="max-width:400px;margin:80px auto;text-align:center;">
    <h2>Login</h2>
    <input v-model="email" placeholder="College Email" type="email" /><br /><br />
    <input v-model="password" placeholder="Password" type="password" /><br /><br />
    <button @click="login">Login</button>
    <p>Don't have an account? <a @click="$router.push('/register')">Register</a></p>
  </div>
</template>
