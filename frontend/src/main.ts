import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import { loadFonts } from "./plugins/webfontloader";
import { key, store } from "@/store/application.store";

loadFonts();

createApp(App).use(store, key).use(router).use(vuetify).mount("#app");
