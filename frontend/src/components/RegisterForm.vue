<template>
  <form @submit.prevent="register">
    <v-container>
      <v-row>
        <v-text-field
          v-model="username"
          label="Username"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="email"
          label="E-Mail"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="password"
          label="Password"
          type="password"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-btn color="primary">Login</v-btn>
      </v-row>
    </v-container>
  </form>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useStore } from "@/store/application.store";

const store = useStore();
const email = ref("");
const username = ref("");
const password = ref("");

function register() {
  store.dispatch("register", { email, username, password });
}
</script>
