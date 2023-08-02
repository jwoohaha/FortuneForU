import axios from 'axios'

function apiInstance() {
    const instance = axios.create({
        baseURL: 'http://localhost:5000'
    });
    return instance;
}

export { apiInstance }