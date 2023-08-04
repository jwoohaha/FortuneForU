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
            console.log('AuthToken', authToken);

            if (authToken) {
                await this.api.post('/auth', authToken)
                .then((result) => this.onSuccess(result.data))
                .catch((error) => this.onError(error));
            } else {
                window.alert("로그인에 실패하였습니다.");
                router.push({path: '/'});
            }   
        },
        async onSuccess(tokens) {
            const accessToken = tokens.accessToken;
            const refreshToken = tokens.refreshToken;
            console.log("accessToken: " + accessToken);
            console.log("refreshToken: " + refreshToken);
            this.store.saveTokens(accessToken, refreshToken);
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