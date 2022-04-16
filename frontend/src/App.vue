<template>
  <v-app>
    <v-main>
      <nav-bar :items="navLinks" />
      <router-view />
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
import NavBar from "@/components/NavBar.vue";
import { NavItem } from "@/models/nav-item.interface";
import { useStore } from "@/store/application.store";

const store = useStore();

const navLinks: NavItem[] = [
  {
    link: "/",
    title: "Home"
  },
  {
    link: "/tasks",
    title: "My Tasks",
    condition: () => store.authenticated ?? false
  },
  {
    link: "/login",
    title: "Login"
  },
  {
    link: "/about",
    title: "About"
  }
];
</script>
