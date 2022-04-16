<template>
  <h4>Tasks</h4>
  <task-list></task-list>
</template>

<script setup lang="ts">
import { useStore } from "@/store/application.store";
import { useRouter } from "vue-router";
import TaskList from "@/components/TaskList.vue";

const store = useStore();
const router = useRouter();
</script>
