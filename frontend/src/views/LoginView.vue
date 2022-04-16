<template>
  <v-container>
    <v-card elevation="8">
      <v-card-title>Login</v-card-title>
      <login-form />
    </v-card>
  </v-container>
</template>

<script setup lang="ts">
import LoginForm from "@/components/LoginForm.vue";
import { useStore } from "@/store/application.store";
import { useRouter } from "vue-router";

const store = useStore();
const router = useRouter();

store.$subscribe((mutation, state) => {
  if (state.user) {
    router.push("/tasks")
  }
});

</script>
