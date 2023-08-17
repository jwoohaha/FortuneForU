<template>
  <header>
    <div class="nav-contents">
      <Logo />
      <nav>
        <router-link to="/tarot">타로상담</router-link>
        <router-link to="/saju">사주상담</router-link>
        <!-- <router-link to="/community">커뮤니티</router-link> -->
      </nav>
      <div class="button-section" v-if="tokenStore.isLoggedIn">
        <button @click="this.logout">로그아웃</button>
        <div v-if="!tokenStore.checkRolesIncludes('ROLE_ADMIN')">
          <router-link to="/mypage"><button>마이페이지</button></router-link>
        </div>
        <div v-if="tokenStore.checkRolesIncludes('ROLE_COUNSELOR')&&!tokenStore.checkRolesIncludes('ROLE_ADMIN')">
          <router-link to="/counselor"><button>상담사전용</button></router-link>
        </div>
        <div v-if="tokenStore.checkRolesIncludes('ROLE_ADMIN')">
          <router-link to="/admin/counselor-form-list"><button>관리자</button></router-link>
        </div>
      </div>
      <div v-else>
        <button @click="tokenStore.makeLoginModalVisible">로그인</button>
      </div>
    <modal-view v-if="tokenStore.getLoginModalStatus" @close-modal="tokenStore.makeLoginModalInvisible">
      <login-content />
    </modal-view>
    </div>
  </header>
</template>

<script>
import Logo from "../common/Logo.vue";
import ModalView from "@/components/common/ModalView.vue";
import { useTokenStore } from "@/stores/token";
import { apiInstance } from "@/api";

export default {
  components: {
    Logo,
    ModalView,
  },
  setup() {
    const tokenStore = useTokenStore();
    const api = apiInstance();
    return {
      tokenStore,
      api
    }
  },
  methods: {
    logout() {
      const yes = confirm("로그아웃 하시겠습니까?");
      if (yes) {
        this.api.get('/auth/logout')
        .then(() => {
          this.tokenStore.logout();
        })
        .catch(() => {
          alert("로그아웃을 요청하는 도중 오류가 발생했습니다.");
        })
      }
    }
  }
};
</script>

<style lang="scss" scoped>
header {
  width: 100%;
  height: 90px;
  display: flex;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  padding: 0 14%;
  //justify-content: center;
  background: rgba(247, 247, 247, 0.7);
  z-index: 1;
}
.nav-contents {
  position: relative;
  width: 80%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
nav {
  display: flex;
  align-items: flex-start;
  gap: 63px;
  color:  #333;
  font-family: Noto Sans KR;
  font-size: 20px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
}
.router-link-exact-active {
  color: var(--title-text, #333);
}
.button-section{
  width: 320px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;

}
button {
  padding: 0.5% 1.25%;
  gap: 10px;
  height: 40px;
  padding: 0px 10px;
  border-radius: 8px;
  background: var(--title-text, #333);
  color: #fff;
  text-align: center;
  font-size: 1em;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  white-space: nowrap;
}
</style>
