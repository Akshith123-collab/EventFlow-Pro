<script setup>
defineProps({ project: Object });

</script>

<template>
  <div class="glass-card" style="margin:15px;">
    <h3>{{ project.title }}</h3>
    <p><b>Funding Agency:</b> {{ project.fundingAgency }}</p>
    <p><b>PI:</b> {{ project.principalInvestigator }}</p>
    <p><b>Amount:</b> ₹{{ project.amountSanctioned }}</p>
    <a v-if="project.agreementFilePath" :href="project.agreementFilePath" target="_blank">📄 View Document</a>
  </div>
</template>
