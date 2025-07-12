import axios from "axios";
import store from "../store/store";
import {
  fetchCsrfToken,
  selectCsrfToken,
  shouldRefreshCsrfToken,
} from "../store/csrf-slice";

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
  timeout: 10000,
  withCredentials: true,
});

apiClient.interceptors.request.use(
  async (config) => {
    const jwtToken = localStorage.getItem("jwtToken");
    if (jwtToken) {
      config.headers.Authorization = `Bearer ${jwtToken}`;
    }

    const safeMethods = ["GET", "HEAD", "OPTIONS"];
    if (!safeMethods.includes(config.method.toUpperCase())) {
      const state = store.getState();
      let csrfToken = selectCsrfToken(state);

      // Kiểm tra xem có cần fetch token mới không
      if (!csrfToken || shouldRefreshCsrfToken(state)) {
        try {
          const result = await store.dispatch(fetchCsrfToken());
          if (fetchCsrfToken.fulfilled.match(result)) {
            csrfToken = result.payload;
          } else {
            throw new Error("Failed to fetch CSRF token");
          }
        } catch (error) {
          console.error("CSRF token fetch error:", error);
          throw new Error("Failed to retrieve CSRF token");
        }
      }

      if (csrfToken) {
        config.headers["X-XSRF-TOKEN"] = csrfToken;
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

apiClient.interceptors.response.use(
  (response) => response,
  async (error) => {
    if (error.response && error.response.status === 401) {
      const jwtToken = localStorage.getItem("jwtToken");
      if (jwtToken) {
        localStorage.removeItem("jwtToken");
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  }
);

export default apiClient;
