import { useSelector, useDispatch } from "react-redux";
import { useEffect } from "react";
import {
  fetchCsrfToken,
  selectCsrfToken,
  selectCsrfIsLoading,
  selectCsrfError,
  shouldRefreshCsrfToken,
} from "../store/csrf-slice";

/**
 * Custom hook để quản lý CSRF token
 * @param {boolean} autoFetch - Tự động fetch token khi component mount (default: true)
 * @returns {object} Object chứa token, loading state, error và refresh function
 */
export const useCsrfToken = (autoFetch = true) => {
  const dispatch = useDispatch();
  const token = useSelector(selectCsrfToken);
  const isLoading = useSelector(selectCsrfIsLoading);
  const error = useSelector(selectCsrfError);

  const refreshToken = () => {
    dispatch(fetchCsrfToken());
  };

  useEffect(() => {
    if (autoFetch) {
      const state = { csrf: { token, lastFetched: Date.now() } };
      if (!token || shouldRefreshCsrfToken(state)) {
        refreshToken();
      }
    }
  }, [autoFetch, token]);

  return {
    token,
    isLoading,
    error,
    refreshToken,
  };
};
