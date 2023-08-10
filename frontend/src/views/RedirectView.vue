<template>
<div>
</div>
</template>
<script>
import router from '@/router';
import { apiInstance } from '@/api/index'
import { useTokenStore } from '@/stores/token'

export default { 

    created() {
        this.auth(); 
    },
    setup() {
        const store = useTokenStore();
        const api = apiInstance();
        return {
            store,
            api
        };
    },
    methods: {
        async auth() {
            const authToken = this.$route.query.token;

            if (authToken) {
                await this.api.post('/auth', authToken)
                .then(response => this.onSuccess(response.headers))
                .catch((error) => this.onError(error))
            } else {
                window.alert("로그인에 실패하였습니다.");
                router.push({path: '/'});
            }   
        },
        onSuccess(headers) {
            console.log(headers);
            const accessToken = headers.accessToken;
            console.log("accessToken: " + accessToken);
            this.store.saveAccessToken(accessToken);
            this.store.login();
            router.push({path: '/'});
        },
        onError(error) {
            console.log(error);
        }
    }
}
</script>
<style>
</style>