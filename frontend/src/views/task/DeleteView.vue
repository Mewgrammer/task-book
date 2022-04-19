<template>
  <v-container>
    <h4>Delete Task</h4>
    <p>Are you Sure ?</p>
    <v-btn @click="deleteTask">Yes</v-btn>
  </v-container>
</template>

<script setup lang="ts">
import { useStore } from "@/store/application.store";
import { useRoute, useRouter } from "vue-router";
import { computed } from "vue";
import { FormMode } from "@/models/form-mode.enum";
import TaskForm from "@/components/TaskForm.vue";
import { TaskDto } from "@/api/taskbook";

const store = useStore();
const route = useRoute();
const router = useRouter();
const task = computed(() => store.tasks.find(t => t.id === route.params.id));

function deleteTask() {
  store.loadTasks()
  router.push(`/tasks`)
}
</script>
