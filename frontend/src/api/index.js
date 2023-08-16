import axios from 'axios'
import { useTokenStore } from "@/stores/token"
// import router from '@/router';

function apiInstance() {
    
    const store = useTokenStore();

    const instance = axios.create({
        // baseURL: 'https://i9a403.p.ssafy.io/api',
        baseURL: 'http://localhost:5000/api',
        headers: {
            "Content-Type": 'application/json;charset=utf-8'
        },
        timeout: 5000
    });

    instance.interceptors.request.use(
        (config) => {
            config.headers['Authorization'] = store.getAccessToken;
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
            console.log("successed response: " + response);
            if (!store.isIntervalStarted) {
                const intervalId = setInterval(silentReissue, 9 * 1000);
                store.startInterval(intervalId);
            }
            return response;
        },
        (error) => {
            // console.log("response error: " + error);
            reissue();
            return Promise.reject(error);
        }
    )

    return instance;
}

function reissue() {

    const store = useTokenStore();

    const instance = axios.create({
        method: 'GET',
        // baseURL: 'https://i9a403.p.ssafy.io/api/auth/reissue',
        baseURL: 'http://localhost:5000/api/auth/reissue',
        timeout: 5000,
        withCredentials: true,
        headers: {
            "Content-Type": 'application/json;charset=utf-8'
        },
    })
    instance.interceptors.response.use(
        (response) => {
            console.log(response);
            const accessToken = response.headers.authorization;
            const roles = response.data;
            store.saveAccessToken(accessToken);
            store.saveRoles(roles);
            store.login();
        },
        (error) => {
            console.log("Access Token 재발급 실패" + error)
            alert("로그인이 만료되었습니다❌\n다시 로그인하세요.");
            store.logout();
            store.stopInterval();
            return Promise.reject();
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
        withCredentials: true,
        headers: {
            "Content-Type": 'application/json;charset=utf-8'
        },
    })

    instance.interceptors.response.use(
        (response) => {
            console.log(response);
            const accessToken = response.headers.authorization;
            const roles = response.data;
            store.saveAccessToken(accessToken);
            store.saveRoles(roles);
            store.login();
            if (!store.isIntervalStarted) {
                const intervalId = setInterval(silentReissue, 9 * 1000);
                store.startInterval(intervalId);
            }
        },
        (error) => {
            console.log("로그인이 필요합니다." + error);
            // store.logout();
            store.stopInterval();
            return "";
        }
    )

    instance();
}

export { apiInstance, silentReissue }