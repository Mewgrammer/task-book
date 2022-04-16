<template>
  <h4>Edit Task</h4>
  <task-form :mode="FormMode.EDIT" :task="task" @save="onTaskSaved" />
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

function onTaskSaved(task: TaskDto) {
  router.push(`/tasks/${task.id}/details`)
}
</script>
