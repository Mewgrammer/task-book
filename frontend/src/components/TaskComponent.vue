<template>
  <div>{{ task.title }}</div>
</template>

<script setup lang="ts">
import { Task } from "@/models/task.interface";

defineProps<{ task: Task }>();
</script>
