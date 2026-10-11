<template>
  <nav class="glass-card" style="display:flex;justify-content:space-between;align-items:center;padding:15px 30px;">
    <h2>EFPM Portal</h2>
    <div>
      <button @click="$router.push('/dashboard')">Dashboard</button>
      <button @click="$router.push('/login')">Logout</button>
    </div>
  </nav>
</template>
