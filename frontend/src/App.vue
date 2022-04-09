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
import { store } from "@/store/application.store";

const navLinks: NavItem[] = [
  {
    link: "/",
    title: "Home"
  },
  {
    link: "/tasks",
    title: "My Tasks",
    condition: () => store.getters.authenticated ?? false
  },
  {
    link: "/login",
    title: "Login"
  },
  {
    link: "/register",
    title: "Register"
  },
  {
    link: "/logout",
    title: "Logout",
    condition: () => store.getters.authenticated ?? false
  },
  {
    link: "/about",
    title: "About"
  }
];
</script>
