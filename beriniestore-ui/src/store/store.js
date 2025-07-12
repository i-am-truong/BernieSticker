import { configureStore } from "@reduxjs/toolkit";
import cartReducer from "./cart-slice";
import authReducer from "./auth-slice";
import csrfReducer from "./csrf-slice";

const store = configureStore({
  reducer: {
    cart: cartReducer,
    auth: authReducer,
    csrf: csrfReducer,
  },
});

store.subscribe(() => {
  try {
    // CART persistence
    const cart = store.getState().cart;
    localStorage.setItem("cart", JSON.stringify(cart));

    // AUTH persistence
    const authSate = store.getState().auth;
    if (authSate.isAuthenticated) {
      localStorage.setItem("jwtToken", authSate.jwtToken);
      localStorage.setItem("user", JSON.stringify(authSate.user));
    } else {
      localStorage.removeItem("jwtToken");
      localStorage.removeItem("user");
    }
  } catch (error) {
    console.error("Failed to save state to localStorage:", error);
  }
});

export default store;
