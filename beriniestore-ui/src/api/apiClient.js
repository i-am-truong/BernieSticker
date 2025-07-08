import axios from 'axios';

const apiClient = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
        Accept: "application/json",
    },
    timeout: 10000,
})

apiClient.interceptors.request.use(
    (config) => {
        const jwtToken = localStorage.getItem('jwtToken');
        if (jwtToken) {
            config.headers.Authorization = `Bearer ${jwtToken}`;
        }
        return config;
    }
    ,
    (error) => {
        return Promise.reject(error);
    }
);

export default apiClient;