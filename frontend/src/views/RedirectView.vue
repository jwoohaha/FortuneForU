<template>
<div>
</div>
</template>
<script>
import router from '@/router';
import { apiInstance } from '@/api/index'

const api = apiInstance();

export default { 
    
    created() {
        this.auth();  
    },
    methods: {
        async auth() {
            const authToken = this.$route.query.token;
            console.log('AuthToken', authToken);

            if (authToken) {
                await api({
                    method: 'POST',
                    url: '/auth',
                    data: authToken,
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    },
                }).then((result) => console.log(result)).catch((error) => console.log(error));
            } else {
                window.alert("로그인에 실패하였습니다.");
                router.push({path: '/'});
            }   
        }
    }
}
</script>
<style>
</style>