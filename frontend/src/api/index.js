import axios from 'axios'
import { useTokenStore } from "@/stores/token"

const store = useTokenStore();

function apiInstance() {
    
    const instance = axios.create({
        baseURL: 'https://i9a403.p.ssafy.io/api',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
            'Authorization': store.getAccessToken,
            'Refresh': store.getRefreshToken
        }
    });
    return instance;
}

export { apiInstance }