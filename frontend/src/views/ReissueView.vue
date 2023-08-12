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
        this.reissue(); 
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
        async reissue() {
            const refreshToken = this.$route.query.token;

            if (refreshToken) {
                await this.api.post('/auth/reissue', refreshToken)
                .then(response => this.onSuccess(response.headers))
                .catch((error) => this.onError(error))
            } else {
                window.alert("로그인에 실패하였습니다.");
                router.push({path: '/'});
            }   
        },
        onSuccess(headers) {
            console.log(headers);
            const accessToken = headers.authorization;
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