<template>
  <h4>Create Task</h4>
  <task-form :mode="FormMode.CREATE" :task="task" @save="onTaskSaved" />
</template>

<script setup lang="ts">
import { FormMode } from "@/models/form-mode.enum";
import TaskForm from "@/components/TaskForm.vue";
import { useRouter } from "vue-router";
import { TaskDto, TaskState } from "@/api/taskbook";

const router = useRouter();
const task: TaskDto = {
  id: "",
  title: "",
  description: "",
  status: TaskState.PENDING
};

function onTaskSaved(task: TaskDto) {
  router.push(`/tasks/${task.id}/details`)
}

</script>
