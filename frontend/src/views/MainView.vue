<template>
  <div class="main">
    <!-- <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <!-- <round-btn saju>Some Button</round-btn>
    <styled-title>Hello World, this is my first styled component!</styled-title> 
   -->
   <!-- FORTUNE FOR U -->
    <div class="boxgroup">
      <div class="empty-box"></div>
          
        <div class="color-boxgroup">
          <h1 class="logo-txt">FORTUNE FOR U</h1>
          <div class="color-box" id="box1"></div>
          <div class="color-box" id="box2"></div>
          <div class="color-box" id="box3"></div>
          <div class="color-box" id="box4"></div>
          <div class="color-box" id="box5"></div>
        </div>
    </div>

    <!-- 언제어디서나 -->
    <div class="part-box" id="part1">
      <div id="part1-txt">
        <div id="part1-txt1">언제 어디서나 <span id="part-txt1-pink">궁금한 나</span>에 대해 알아보는 시간!</div>
        <div id="part1-txt2">화상으로 편하게 타로/사주 상담가를 만나보세요.</div>
      </div>
      <img src="../assets/mainview-part1-img.png" alt="human_computer">
    </div>

    <div class="part-box" id="part2">
      <div id="part2-txt">
        <div id="part2-txt1">지금 이 순간, 가장 <span id="part-txt1-pink">HOT</span>한 타로 상담가</div>
        <div id="part2-txt2">타로</div>
      </div>
      <div id="part2-txt">
        <div id="part2-txt1">지금 이 순간, 가장 <span id="part-txt1-pink">HOT</span>한 사주 상담가</div>
        <div id="part2-txt2">사주</div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'
// import { RoundBtn } from "@/components/styled-components/RoundButton.js"; 
// import { StyledTitle } from "../components/styled-components/sample"; 

export default {
  name: 'MainView',
  components: {
    // RoundBtn,
    // StyledTitle
  }
}
</script>

<style lang="scss">

.boxgroup {
  height: 535px;
}
.empty-box {
  height: 92px;
}
.color-boxgroup {
  height: 443px;
  display: flex;
  position: relative;
}
.color-box {
  flex: 1;
}
.logo-txt {
  position: absolute;
  top: 145px;
  width: 100%;
  color: #FFF;
  text-align: center;
  font-family: Kanit;
  font-size: 4.8em;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
}
#box1 {
  background-color: #F6B5C6;
}
#box2 {
  background-color: #BFAEE5;
}
#box3 {
  background-color: #50E6F6;
}
#box4 {
  background-color: #6DF6E9;
}
#box5 {
  background-color: #F6F2DD;
}

.part-box {
  height: 840px;
}
#part1 {
  background: linear-gradient(180deg, #FCF3FF 0%, rgba(243, 244, 255, 0.00) 100%);
  display: flex;
  justify-content: center;
  align-items: center;
}
#part1-txt {
  text-align: left;
}
#part1-txt1 {
  color: var(--title-text, #333);
  font-size: 2.25em;
  margin-bottom: 30px;
  font-weight: 700;
  line-height: normal;
}
#part-txt1-pink {
  color: var(--unnamed, #F6B5C6);
}
#part1-txt2 {
  font-size: 1.7em;
  color: var(--body-text, #666);
  font-weight: 700;
  line-height: normal;
}
img {
  height: 220px;
  width: 340px;
}

#part2 {
  height: 900px;
  background: linear-gradient(180deg, #F3F4FF 0%, rgba(243, 244, 255, 0.00) 100%);
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
}
#part2-txt {
  font-size: 1.7em;
  font-weight: 700;
  line-height: normal;
}
</style>