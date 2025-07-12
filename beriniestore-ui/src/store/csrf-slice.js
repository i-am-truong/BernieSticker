import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

// Async thunk để lấy CSRF token từ server
export const fetchCsrfToken = createAsyncThunk(
  "csrf/fetchToken",
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get(
        `${import.meta.env.VITE_API_BASE_URL}/csrf-token`,
        {
          withCredentials: true,
        }
      );
      // Token thường được trả về trong response hoặc có thể extract từ headers
      const token =
        response.data?.token || response.headers["x-csrf-token"] || null;
      return token;
    } catch (error) {
      return rejectWithValue(
        error.response?.data?.message || "Failed to fetch CSRF token"
      );
    }
  }
);

const initialCsrfState = {
  token: null,
  isLoading: false,
  error: null,
  lastFetched: null,
};

const csrfSlice = createSlice({
  name: "csrf",
  initialState: initialCsrfState,
  reducers: {
    setCsrfToken(state, action) {
      state.token = action.payload;
      state.lastFetched = Date.now();
      state.error = null;
    },
    clearCsrfToken(state) {
      state.token = null;
      state.lastFetched = null;
      state.error = null;
    },
    clearCsrfError(state) {
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchCsrfToken.pending, (state) => {
        state.isLoading = true;
        state.error = null;
      })
      .addCase(fetchCsrfToken.fulfilled, (state, action) => {
        state.isLoading = false;
        state.token = action.payload;
        state.lastFetched = Date.now();
        state.error = null;
      })
      .addCase(fetchCsrfToken.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
      });
  },
});

export const { setCsrfToken, clearCsrfToken, clearCsrfError } =
  csrfSlice.actions;
export default csrfSlice.reducer;

// Selectors
export const selectCsrfToken = (state) => state.csrf.token;
export const selectCsrfIsLoading = (state) => state.csrf.isLoading;
export const selectCsrfError = (state) => state.csrf.error;
export const selectCsrfLastFetched = (state) => state.csrf.lastFetched;

// Helper function để kiểm tra xem token có cần refresh không (ví dụ sau 30 phút)
export const shouldRefreshCsrfToken = (state) => {
  const lastFetched = selectCsrfLastFetched(state);
  if (!lastFetched) return true;

  const THIRTY_MINUTES = 30 * 60 * 1000; // 30 minutes in milliseconds
  return Date.now() - lastFetched > THIRTY_MINUTES;
};
