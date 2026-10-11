import { createRouter, createWebHistory } from "vue-router";
import Login from "../views/LoginUser.vue";
import Register from "../views/RegisterUser.vue";
import Dashboard from "../views/DashBoard.vue";

const routes = [
  { path: "/", redirect: "/login" },
  { path: "/login", component: Login },
  { path: "/register", component: Register },
  { path: "/dashboard", component: Dashboard },
];

export default createRouter({
  history: createWebHistory(),
  routes,
});
