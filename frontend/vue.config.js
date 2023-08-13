const { defineConfig } = require('@vue/cli-service');
module.exports = defineConfig({
  transpileDependencies: true,

  // npm run build 타겟 디렉토리 (백엔드)
  outputDir: "../backend/src/main/resources/static",

  devServer: {
    proxy: {
      '/': {
        // target: "https://i9a403.p.ssafy.io",
        target: "http://localhost:5000",
        changeOrigin : true,
      } 
    }
  }
});

//vue.config.js
module.exports = {
  css: {
    loaderOptions: {
      sass: {
        additionalData: `
                @import "@/assets/scss/reset.scss";
              `,
      },
    },
  },
};
