<template>
  <v-container>
    <h1>Details</h1>
    <task-form :mode="FormMode.VIEW" :task="task" />
  </v-container>
</template>

<script setup lang="ts">
import { useStore } from "@/store/application.store";
import { useRoute } from "vue-router";
import { computed } from "vue";
import { FormMode } from "@/models/form-mode.enum";
import TaskForm from "@/components/TaskForm.vue";

const store = useStore();
const route = useRoute();
const task = computed(() => store.tasks.find(t => t.id === route.params.id));
</script>
