<template>
  <div class="modal-container">
    <div class="modal-header">
      <div class="modal-header-logo">
        <div>FORTUNE</div>
        <div>FOR</div>
        <div>U</div>
      </div>
    </div>
    <div class="modal-logo">
      <div class="logo-typo-container">
        <div class="logo-typo1">불확실한 미래!</div>
        <div class="logo-typo2">로그인</div>
        <div class="logo-typo1">하여 확인해보세요!</div>
      </div>
      <img class="logo-image" src="@/assets/login/lock.png" />
    </div>
    <div class="modal-button-container">
      <social-login-button :key="platform.title" v-for="platform in platforms" :platform="platform"></social-login-button>
    </div>
    <router-link to="/admin/counselor-form-list"><a @click="$emit('close-modal')">관리자 로그인</a></router-link>
  </div>
</template>

<script>
import SocialLoginButton from "./SocialLoginButton.vue";

export default {
  components: {
    SocialLoginButton,
  },
  data() {
    return {
      platforms: [
        {
          provider: 'google',
          icon: "g-logo.png",
          color: "#FFFFFF",
          textColor: "rgba(0, 0, 0, 0.54)",
          title: "구글로 시작하기"
        },
        {
          provider: 'kakao',
          icon: "k-logo.png",
          color: "#FEE500",
          textColor: "rgba(0, 0, 0, 0.85)",
          title: "카카오로 시작하기"
        },
        {
          provider: 'naver',
          icon: "n-logo.png",
          color: "#03C75A",
          textColor: "#FFFFFF",
          title: "네이버로 시작하기"
        },
      ]
    }
  }
};
</script>

<style lang="scss">
.modal-container {
  min-height: 500px;
  background-color: white;
  border-radius: 10px;
}

.modal-header {
  display: flex;
  height: 70px;
  padding: 10px 40px;
  background-color: #BFAEE5;
  border-radius: 10px 10px 0px 0px;
  align-items: center;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);

  .modal-header-logo {
    font-family: "Kanit", sans-serif;
    font-size: 20px;
    text-decoration: none;
    text-align: left;
    color: black;
    font-style: normal;
    font-weight: 700;
    line-height: 20px;
    user-select: none;
  }
}

.modal-logo {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 10px 50px;
  gap: 30px;
}

.logo-typo1 {
  color: #333333;
  font-family: Noto Sans KR;
  font-size: 20px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  text-align: start;
  user-select: none;
}

.logo-typo2 {
  color: #BFAEE5;
  font-family: Noto Sans KR;
  font-size: 24px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  text-align: start;
  user-select: none;
}

.logo-image {
  width: 150px;
  height: 150px;
  -webkit-user-drag: none;
}

.modal-button-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}
</style>
