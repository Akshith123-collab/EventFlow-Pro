import { defineStore } from "pinia";
import api from "../api/api";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    user: null,
    token: localStorage.getItem("token") || null,
  }),
  actions: {
    async login(email, password) {
      const res = await api.post("/auth/login", { email, password });
      this.token = res.data.token;
      localStorage.setItem("token", this.token);
    },
    async register(name, email, password) {
      await api.post("/auth/register", { name, email, password });
    },
    logout() {
      this.user = null;
      this.token = null;
      localStorage.removeItem("token");
    },
  },
});
