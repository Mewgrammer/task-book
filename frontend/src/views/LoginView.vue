<template>
  <v-container>
    <v-card elevation="8">
      <v-card-title>Login</v-card-title>
      <login-form />
    </v-card>
  </v-container>
</template>

<script setup lang="ts">
import { mutations, useStore } from "@/store/application.store";
import { useRouter } from "vue-router";
import LoginForm from "@/components/LoginForm.vue";

const store = useStore();
const router = useRouter();
store.subscribe(async (mutation, state) => {
  if (mutation.type === mutations.SET_USER && !!state.user) {
    await router.push("/");
  }
});
</script>
