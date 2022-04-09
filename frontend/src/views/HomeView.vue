<template>
  <h4> Welcome {{ store.getters.user?.username }}</h4>
</template>

<script setup lang="ts">
import { useStore } from "@/store/application.store";

const store = useStore();
</script>
