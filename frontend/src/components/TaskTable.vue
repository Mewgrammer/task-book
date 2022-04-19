<template>
  <v-btn to="/tasks/create">
    <v-icon>mdi-plus</v-icon>
    New Task
  </v-btn>
  <v-table>
    <thead>
    <tr>
      <th :key="key" v-for="key of keysToShow">
        {{ key }}
      </th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <tr :key="task.id" v-for="task of taskPage.content">
      <td :key="key" v-for="key of keysToShow">
        {{ task[key] }}
      </td>
      <td>
        <v-btn size="small" :to="`/tasks/${task.id}/details`">
          <v-icon> mdi-information
          </v-icon>
        </v-btn>
        <v-btn size="small" color="primary" :to="`/tasks/${task.id}/edit`">
          <v-icon>mdi-pencil-outline</v-icon>
        </v-btn>
        <v-btn size="small" color="error" :to="`/tasks/${task.id}/delete`">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </td>
    </tr>
    <tr v-if="taskPage.content.length === 0">
      <td :colspan="keysToShow.length + 1">
        <span class="text-center">
          No Tasks available
        </span>
      </td>
    </tr>
    </tbody>
  </v-table>
  <v-pagination
    v-model="taskPage.page"
    :length="taskPage.totalPages"
    @update:modelValue="pageChanged"
  ></v-pagination>
</template>

<script setup lang="ts">
import { useStore } from "@/store/application.store";
import { storeToRefs } from "pinia";

const store = useStore();
const { taskPage } = storeToRefs(store);
const keysToShow = ["id", "title", "description", "createdAt"];

function pageChanged(page: number) {
  store.loadTasks(page - 1, store.taskPage?.size);
}

</script>
