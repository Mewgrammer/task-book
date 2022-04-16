<template>
  <v-table>
    <thead>
    <tr>
      <th :key="key" v-for="key of keysToShow">
        {{ key }}
      </th>
    </tr>
    </thead>
    <tbody>
    <tr :key="task.id" v-for="task of tasks">
      <td :key="key" v-for="key of keysToShow">
        {{ task[key] }}
      </td>
      <td>
        <v-btn :href="`tasks/${task.id}`">Edit</v-btn>
      </td>
    </tr>
    <tr v-if="tasks.length === 0">
      <td :colspan="keysToShow.length + 1">
        <span class="text-center">
          No Tasks available
        </span>
      </td>
    </tr>
    </tbody>
  </v-table>
</template>

<script setup lang="ts">
import { useStore } from "@/store/application.store";
import { reactive } from "vue";
import { TaskDto } from "@/api/taskbook";

const store = useStore();
const tasks = reactive<TaskDto[]>(store.tasks);
const keysToShow = ["id", "title", "description", "createdAt"];

</script>
