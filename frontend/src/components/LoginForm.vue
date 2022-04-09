<template>
  <v-form @submit.prevent="login">
    <v-container>
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
        <v-btn type="submit" color="primary">Login</v-btn>
      </v-row>
    </v-container>
  </v-form>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useStore } from "@/store/application.store";

const store = useStore();
const email = ref("");
const password = ref("");

function login() {
  store.dispatch("login", { email, password });
}
</script>
