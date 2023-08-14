import axios from 'axios'
import { useTokenStore } from "@/stores/token"

function apiInstance() {
    
    const store = useTokenStore();

    const instance = axios.create({
        baseURL: 'http://localhost:5000/api',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
            'Authorization': store.getAccessToken,
            'Refresh': store.getRefreshToken
        }
    });
    return instance;
}

export { apiInstance }