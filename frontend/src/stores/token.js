import { defineStore } from "pinia";
import { isJwtTokenValid } from "@/utils/validator";

export const useTokenStore = defineStore("token", {

    state: () => ({
        accessToken: null,
        refreshToken: null,
        loggedIn: false,
    }),
    getters: {
        getAccessToken() {
            return this.accessToken;
        },
        getRefreshToken() {
            return this.refreshToken;
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
        saveTokens(accessToken, refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        },
        login() {
            this.loggedIn = true;
        },
        logout() {
            this.loggedIn = false;
        }
    }
});