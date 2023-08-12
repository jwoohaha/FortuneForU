<template>
  <header>
    <div class="nav-contents">
      <Logo />
      <nav>
        <router-link to="/tarot">타로상담</router-link>
        <router-link to="/saju">사주상담</router-link>
        <router-link to="/community">커뮤니티</router-link>
      </nav>
      <button @click="this.test()">Test</button>
      <div v-if="tokenStore.isLoggedIn">
        <router-link to="/mypage"><button>마이페이지</button></router-link>
        <router-link to="/counselor"><button>상담사전용</button></router-link>
      </div>
      <div v-else>
        <button @click="isModalVisible = true">로그인</button>
      </div>
    <modal-view v-if="isModalVisible" @close-modal="isModalVisible = false">
      <login-content />
    </modal-view>
    </div>
  </header>
</template>

<script>
import Logo from "../common/Logo.vue";
import ModalView from "@/components/common/ModalView.vue";
import { useTokenStore } from "@/stores/token";
import { apiInstance } from '@/api/index'

export default {
  components: {
    Logo,
    ModalView,
  },
  data() {
    return {
      isModalVisible: false,
    };
  },
  setup() {
    const tokenStore = useTokenStore();
    const api = apiInstance();
    return {
      tokenStore,
      api
    }
  },
  created() {
    let uri = window.location.href.split('?');
    console.log(uri[1]);
    this.isModalVisible = uri[1];
  },
  methods: {
    async test() {
      await this.api.get('/auth/test')
        .then(response => this.onSuccess(response))
        .catch((error) => this.onError(error))
    },
    onSuccess(response) {
      console.log(response);
    },
    onError(error) {
      console.log(error);
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
  width: 70%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
nav {
  display: flex;
  align-items: flex-start;
  gap: 63px;
  color: var(--title-text, #333);
  font-family: Noto Sans KR;
  font-size: 20px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
}
.router-link-exact-active {
  color: var(--title-text, #333);
}
button {
  display: flex;
  padding: 0.5% 1.25%;
  position: relative;
  justify-content: center;
  align-items: center;
  gap: 10px;
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
