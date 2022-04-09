<template>
  <h4>Tasks</h4>
</template>

<script setup lang="ts">
import { useStore } from "@/store/application.store";
import { useRouter } from "vue-router";

const store = useStore();
const router = useRouter();
</script>
