import { defineStore } from "pinia";
import router from "@/router";
import { isJwtTokenValid } from "@/utils/validator";

export const useTokenStore = defineStore("token", {

    state: () => ({
        accessToken: null,
        loggedIn: false,
        isLoginModalVisible: false,
        intervalId: null,
        roles: []
    }),
    getters: {
        getAccessToken() {
            return this.accessToken;
        },
        getLoginModalStatus() {
            return this.isLoginModalVisible;
        },
        isIntervalStarted() {
            return this.intervalId != null;
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
        saveRoles(roles) {
            this.roles = roles;
        },
        makeLoginModalVisible() {
            this.isLoginModalVisible = true;
        },
        makeLoginModalInvisible() {
            this.isLoginModalVisible = false;
        },
        startInterval(intervalId) {
            this.intervalId = intervalId;
        },
        stopInterval() {
            clearInterval(this.intervalId);
            this.intervalId = null;
        },
        checkRolesIncludes(role) {
            return this.roles.includes(role);
        },
        login() {
            this.loggedIn = true;
        },
        logout() {
            this.accessToken = null;
            this.loggedIn = false;
            router.push('/');
        }
    }
});