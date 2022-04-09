import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import LogoutView from "../views/LogoutView.vue";
import RegisterView from "../views/RegisterView.vue";
import AboutView from "../views/AboutView.vue";
import TasksView from "../views/TasksView.vue";
import { store } from "@/store/application.store";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home",
    component: HomeView
  },
  {
    path: "/tasks",
    name: "tasks",
    component: TasksView
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView
  },
  {
    path: "/login",
    name: "login",
    component: LoginView
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView
  },
  {
    path: "/about",
    name: "about",
    component: AboutView
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

router.beforeEach((to, from) => {
  if (to.name !== "login" && to.name !== "register" && !store.getters.authenticated) {
    return { name: "login" }; // redirect to login if unauthenticated
  }
});

export default router;
