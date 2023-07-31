<template>
  <header>
    <div class="nav-contents">
      <Logo />
      <nav>
        <router-link to="/tarot">타로상담</router-link> 
        <router-link to="/saju">사주상담</router-link>
        <router-link to="/community">커뮤니티</router-link>
      </nav>
      <button @click="click()">로그인</button>
    </div>
  </header>
</template>

<script>
import Logo from '../common/Logo.vue';

  export default {
  components: {
    Logo
  },
  methods:{
    click(){
      alert("버튼을 클릭 했습니다.")
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
  background: rgba(247, 247, 247, 0.70);
  z-index: 1;
}
.nav-contents{
  position: relative;
  width: 70%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
nav{
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
.router-link-exact-active{ 
  color: var(--title-text, #333);
}
button{
  display: flex;
  padding: 0.5% 1.25%;
  position: relative ;
  justify-content: center;
  align-items: center;
  gap: 10px;
  border-radius: 8px;
  background: var(--title-text, #333);
  color: #FFF;
  text-align: center;
  font-size: 1em;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
}
</style>
