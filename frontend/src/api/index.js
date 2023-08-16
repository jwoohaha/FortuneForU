import axios from 'axios'
import { useTokenStore } from "@/stores/token"
// import router from '@/router';

function apiInstance() {
    
    const store = useTokenStore();

    const instance = axios.create({
        // baseURL: 'https://i9a403.p.ssafy.io/api',
        baseURL: 'http://localhost:5000/api',
        timeout: 5000
    });

    instance.interceptors.request.use(
        (config) => {
            config.headers['Content-Type'] = 'application/json;charset=utf-8';
            config.headers['Authorization'] = store.getAccessToken;
            config.headers['']
            config.withCredentials = true;
            
            return config;
        },
        (error) => {
            console.log("request error: " + error);
            return Promise.reject(error);
        }
    )

    instance.interceptors.response.use(
        (response) => {
            // console.log("successed response: " + response);
            // setTimeout(silentReissue, 8 * 1000);
            return response;
        },
        (error) => {
            // console.log("response error: " + error);
            reissue();
            return error;
        }
    )

    return instance;
}

async function reissue() {

    const store = useTokenStore();

    const instance = axios.create({
        method: 'GET',
        // baseURL: 'https://i9a403.p.ssafy.io/api/auth/reissue',
        baseURL: 'http://localhost:5000/api/auth/reissue',
        timeout: 5000,
        withCredentials: true
    })
    instance.interceptors.response.use(
        (response) => {
            const accessToken = response.headers.authorization;
            // console.log("newAccessToken: " + accessToken);
            store.saveAccessToken(accessToken);
            store.login();
            setTimeout(silentReissue, 5 * 1000);
        },
        (error) => {
            console.log("Access Token 재발급 실패" + error)
            alert("로그인이 만료되었습니다❌\n다시 로그인하세요.");
            store.logout();
            return error;
        }
    )
}

function silentReissue() {

    const store = useTokenStore();

    const instance = axios.create({
        method: 'GET',
        // baseURL: 'https://i9a403.p.ssafy.io/api/auth/reissue',
        baseURL: 'http://localhost:5000/api/auth/reissue',
        timeout: 5000,
        withCredentials: true
    })
    instance.interceptors.response.use(
        (response) => {
            const accessToken = response.headers.authorization;
            // console.log("newAccessToken: " + accessToken);
            store.saveAccessToken(accessToken);
            store.login();
        },
        (error) => {
            console.log("로그인이 필요합니다." + error)
            store.logout();
            return "";
        }
    )

    instance();
}

export { apiInstance, silentReissue }