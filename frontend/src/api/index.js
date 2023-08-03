import axios from 'axios'
import { useTokenStore } from "@/stores/token"

const store = useTokenStore();

function apiInstance() {
    
    const instance = axios.create({
        baseURL: 'http://localhost:5000',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
            'Authorization': store.getAccessToken,
            'Refresh': store.getRefreshToken
        }
    });
    return instance;
}

export { apiInstance }