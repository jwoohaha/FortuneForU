import { defineStore } from "pinia";
import { isJwtTokenValid } from "@/utils/validator";

export const useTokenStore = defineStore("token", {

    state: () => ({
        accessToken: null,
        loggedIn: false,
    }),
    getters: {
        getAccessToken() {
            return this.accessToken;
        },
        isLoggedIn() {
            if (isJwtTokenValid(this.accessToken)){
                console.log("유효한 토큰입니다.");
                return this.loggedIn
            }
            return false;
        }
    },
    actions: {
        saveAccessToken(accessToken) {
            this.accessToken = accessToken;
        },
        login() {
            this.loggedIn = true;
        },
        logout() {
            this.loggedIn = false;
        }
    }
});