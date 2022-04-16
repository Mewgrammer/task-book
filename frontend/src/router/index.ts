import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import LogoutView from "../views/LogoutView.vue";
import AboutView from "../views/AboutView.vue";
import TaskListView from "../views/task/ListView.vue";
import TaskCreateView from "../views/task/CreateView.vue";
import TaskDetailsView from "../views/task/DetailsView.vue";
import TaskEditView from "../views/task/EditView.vue";
import { useStore } from "@/store/application.store";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home",
    component: HomeView
  },
  {
    path: "/tasks",
    name: "tasks",
    component: TaskListView,
    children: [
      {
        path: "create",
        component: TaskCreateView
      },
      {
        path: ":id/details",
        component: TaskDetailsView
      },
      {
        path: ":id/details",
        component: TaskEditView
      }
    ]
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
  if (to.name !== "login" && to.name !== "register" && !useStore().authenticated) {
    return { name: "login" }; // redirect to login if unauthenticated
  }
});

export default router;
