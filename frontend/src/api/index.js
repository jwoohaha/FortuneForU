import axios from 'axios'
import { useTokenStore } from "@/stores/token"

function apiInstance() {
    
    const store = useTokenStore();

    const instance = axios.create({
        baseURL: 'https://i9a403.p.ssafy.io/api',
        // baseURL: 'http://localhost:5000/api',
    });

    instance.interceptors.request.use(
        (config) => {
            config.headers['Content-Type'] = 'application/json;charset=utf-8';
            config.headers['Authorization'] = store.getAccessToken;
            
            return config;
        },
        (error) => {
            console.log("interceptor error: " + error);
            return Promise.reject(error);
        }
    )

    return instance;
}

export { apiInstance }