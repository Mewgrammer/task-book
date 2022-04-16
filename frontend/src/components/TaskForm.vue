<template>
  <v-form @submit.prevent="submit">
    <v-container>
      <v-row>
        <v-text-field
          v-if="mode !== FormMode.CREATE"
          v-model="task.id"
          label="Id"
          :readonly="mode !== FormMode.VIEW"
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="task.title"
          label="Title"
          required
          :readonly="mode !== FormMode.VIEW"
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="task.description"
          label="Title"
          required
          :readonly="mode !== FormMode.VIEW"
        ></v-text-field>
      </v-row>
      <v-row>
        <v-select label="Status" :items="Object.values(TaskState)">
        </v-select>
      </v-row>
      <v-row>
        <v-text-field
          v-model="task.createdAt"
          label="Created at"
          readonly
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="task.creator"
          label="Created By"
          readonly
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="task.description"
          label="Title"
          :readonly="mode !== FormMode.VIEW"
        ></v-text-field>
      </v-row>
      <v-row v-if="mode !== FormMode.VIEW">
        <v-btn type="submit" color="primary">Save</v-btn>
      </v-row>
    </v-container>
  </v-form>
</template>

<script setup lang="ts">
import { reactive, toRefs } from "vue";
import { useStore } from "@/store/application.store";
import { FormMode } from "@/models/form-mode.enum";
import { TaskDto, TaskState } from "@/api/taskbook";

const store = useStore();

const props = defineProps<{
  mode: FormMode,
  task: TaskDto
}>();

defineEmits<{ (e: "saved", task: TaskDto): void }>();

const { mode } = toRefs(props);
const task = reactive(props.task);

function submit() {
  if (mode.value === FormMode.CREATE) {
    store.addTask(task);
  } else if (mode.value === FormMode.EDIT) {
    store.updateTask(task.id, task);
  }
}

</script>
