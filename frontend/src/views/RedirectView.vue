<template>
<div>
</div>
</template>
<script>
import router from '@/router';
import { apiInstance, silentReissue } from '@/api/index'
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
            console.log(authToken)

            if (authToken) {
                await this.api.post('/auth', authToken)
                .then(response => this.onSuccess(response))
                .catch((error) => this.onError(error))
            } else {
                window.alert("로그인에 실패하였습니다.");
                router.push({path: '/'});
            }   
        },
        onSuccess(response) {
            const accessToken = response.headers.authorization;
            const roles = response.data;
            this.store.saveAccessToken(accessToken);
            this.store.saveRoles(roles);
            this.store.login();
            if (!this.store.isIntervalStarted) {
                const intervalId = setInterval(silentReissue,  30 * 60 * 1000);
                this.store.startInterval(intervalId);
            }
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