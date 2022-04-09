<template>
  <h4>You have been logged out.</h4>
</template>

<script setup lang="ts">
import { useStore } from "@/store/application.store";
import { useRouter } from "vue-router";
import { onMounted } from "vue";

const store = useStore();
const router = useRouter();
onMounted(async () => {
  await store.dispatch("logout");
  await router.push("/");
});
</script>
