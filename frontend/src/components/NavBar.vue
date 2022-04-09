<template>
  <v-toolbar elevation="4" theme="dark">
    <v-toolbar-title>Task-Book</v-toolbar-title>
    <v-toolbar-items>
      <v-btn v-for="(navLink, i) in items" :key="i" :to="navLink.link"
      >{{ navLink.title }}
      </v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script setup lang="ts">
import { NavItem } from "@/models/nav-item.interface";
import { computed } from "vue";

const props = defineProps<{ items: Array<NavItem> }>();
const visibleNavLinks = computed(() => props.items.filter(navLink => navLink.condition?.call(this) === true ?? true));
console.log(visibleNavLinks.value);
</script>
