<template>
  <div v-if="this.isSilentReissueDone">
    <Header></Header>
    <router-view/>
  </div>
</template>

<script>
import Header from './components/common/Header.vue';
import { silentReissue } from '@/api/index.js';

export default {
  components: {
    Header,
  },
  data() {
    return {
      isSilentReissueDone: false
    }
  },
  created() {
    silentReissue()
      .then(() => {
        this.isSilentReissueDone = true;
      })
  }
 };
</script>

<style lang="scss">
#app {
  font-family: Noto Sans KR, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
