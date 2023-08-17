<template>
  <div class="sns-login-button" :style="{background: platform.color}" @click="socialLogin(platform.provider)">
    <img class="sns-login-logo" :src='require(`@/assets/login/${platform.icon}`)' />
    <div class="sns-login-button-text" :style="{color: platform.textColor}">{{ platform.title }}</div>
  </div>
</template>

<script>
export default {
  props: {
    platform: Object
  },
  methods: {
    socialLogin(provider) {
      const url = 'https://i9a403.p.ssafy.io/oauth2/authorize/' + provider;
      // const url = `http://localhost:5000/oauth2/authorize/${provider}`;
      //local 에서는 ssafytest.shop 안됨
      location.href = url;
    }
  }
};
</script>

<style lang="scss" scoped>
.sns-login-button {
  display: flex;
  width: 350px;
  height: 60px;
  justify-content: center;
  align-items: center;
  gap: 10px;
  border-radius: 5px;
  box-shadow: 0px 2px 4px 2px rgba(0, 0, 0, 0.10);
}

.sns-login-logo {
  margin: auto;
  padding-left: 30px;
  width: 15%;
}

.sns-login-button-text {
  margin: auto;
  width: 80%;
  padding-left: 30px;
  text-align: start;
  font-family: Noto Sans KR;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  user-select: none;
}
</style>
