<template>
  <span v-if="store.authenticated">
    Logged in as <b>{{ user.username }}</b>
      <v-btn to="/logout">
        Logout
      </v-btn>
  </span>
  <span v-else>
      <v-btn to="/login">
        Login
      </v-btn>
  </span>
</template>

<script setup lang="ts">
import { useStore } from "@/store/application.store";
import { storeToRefs } from "pinia";

const store = useStore();
const { user } = storeToRefs(store);
</script>
