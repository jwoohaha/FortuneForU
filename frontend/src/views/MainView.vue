<template>
  <div class="main">
    
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

    <div class="part-box" id="part1">
      <div id="part1-txt">
        <div id="part1-txt1">언제 어디서나 <span id="part-txt-pink">궁금한 나</span>에 대해 알아보는 시간!</div>
        <div id="part1-txt2">화상으로 편하게 타로/사주 상담가를 만나보세요.</div>
      </div>
      <img src="../assets/mainview-part1-img.png" alt="human_computer">
    </div>

    <div class="part-box" id="part2">
      <div id="part2-txt">
        <div id="part2-txt1">지금 이 순간, 가장 <span id="part-txt-purple">HOT</span>한 타로 상담가</div>
        <div class="card-section">
          <ul>
            <!-- v-for -->
            <li v-for="(selectedTaroCounselor, idx) in selectedTaroCounselors" :key="idx">
              <div class="card">
                <div class="upper-content">
                  <img class="card-img" src="../assets/dummy_counselor_img.jpg">
                  <div class="card-info">
                    <p id="card-name">{{ selectedTaroCounselor.name }}</p>
                    <p id="card-score">⭐ {{ selectedTaroCounselor.ratingAvg }}</p>
                  </div>
                </div>
                <hr>
                <p class="card-txt">{{ selectedTaroCounselor.intro }}</p>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div id="part2-txt">
        <div id="part2-txt1">지금 이 순간, 가장 <span id="part-txt-pink">HOT</span>한 사주 상담가</div>
        <div class="card-section">
          <ul>
            <li v-for="(selectedSajuCounselor, idx) in selectedSajuCounselors" :key="idx">
              <div class="card">
                <div class="upper-content">
                  <img class="card-img" src="../assets/dummy_counselor_img.jpg">
                  <div class="card-info">
                    <p id="card-name">{{ selectedSajuCounselor.name }}</p>
                    <p id="card-score">⭐ {{ selectedSajuCounselor.ratingAvg }}</p>
                  </div>
                </div>
                <hr>
                <p class="card-txt">{{ selectedSajuCounselor.intro }}</p>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div class="part-box" id="part3">
      <div class="part3-txt"> <span id="part-txt-blue">생생</span>한 후기들을 <br/>만나보세요.</div>
      <img class="review-img" src="../assets/review_img.png">
    </div>

    <div class="part-box" id="part4">
      <div class="part4-txt">최초의 사주&타로 화상 상담 서비스 <br/>Fortune for U와 함께 할 <br/>상담사를 찾습니다.</div>
      <button class="counselor-form-btn">자세히 보기</button>
      
    </div>  
  </div>
</template>

<script>
import { apiInstance } from '@/api';

export default {
  name: 'MainView',
  components: {
  },
  data() {
    return {
      sajuCounselors:[],
      taroCounselors:[],
      
      selectedSajuCounselors:[],
      selectedTaroCounselors:[],
    }
  },
  setup(){
    const api = apiInstance();

    return {
      api
    }
  },
  methods:{
    getTaroCounselorsByRatings(){
      this.api.get('/counselors/by_ratings?counselorType=TARO')
      .then((response) => {
        console.log(response.data)
        this.taroCounselors = response.data.content
        this.selectRandomTaroCounselors();
      })
      .catch((error) =>[
        console.log(error)
      ])
    },

    getSajuCounselorsByRatings(){
      this.api.get('/counselors/by_reviews?counselorType=SAJU')
      .then((response) => {
        console.log(response)
        this.sajuCounselors = response.data.content
        this.selectRandomSajuCounselors();
      })
      .catch((error) => {
        console.log(error)
      })
    },

    selectRandomTaroCounselors(){
      const shuffledTaroCounselors = [...this.taroCounselors].sort(() => 0.5 - Math.random());
      this.selectedTaroCounselors = shuffledTaroCounselors.slice(0, 4);
    },

    selectRandomSajuCounselors(){
      const shuffledSajuCounselors = [...this.sajuCounselors].sort(() => 0.5 - Math.random());
      this.selectedSajuCounselors = shuffledSajuCounselors.slice(0, 4);
    }

  },
  created(){
    this.getTaroCounselorsByRatings();
    this.getSajuCounselorsByRatings();
  }

}
</script>

<style lang="scss" scoped="scss">
// @import "../assets/scss/color.scss";

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
  color: #333;
  font-size: 2.25em;
  margin-bottom: 30px;
  font-weight: 700;
  line-height: normal;
}
#part-txt-pink {
  color: #F6B5C6;
}
#part-txt-purple {
  color: #BFAEE5;
}
#part-txt-blue {
  color:  #50E6F6;
}
#part1-txt2 {
  font-size: 1.7em;
  color: #666;
  font-weight: 700;
  line-height: normal;
}
#part1 img {
  background-color: transparent;
  background: transparent;
}
img {
  height: 220px;
  width: 340px;
}

#part2 {
  height: 900px;
  background: linear-gradient(180deg, #F3F4FF 0%, rgba(243, 244, 255, 0.00) 100%);
  justify-content: space-around;
  align-items: center;
  display: flex;
  flex-direction: column;
  padding: 85px 0px;
}
#part2-txt {
  font-size: 1.7em;
  font-weight: 700;
  line-height: normal;
  text-align: left;
}
.card-section {
  height: 240px;
  width: 1311px;
  margin-top: 30px;
}
ul {
  list-style:none;
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-between;
}
li {
  flex-direction: column;
}
.card {
  width: 306px;
  height: 240px;
  border-radius: 10px;
  border: 1px solid #D7D7D7;
  background: #FFF;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
  justify-content: space-between;
  padding: 25px 42px;
  box-sizing: border-box;
}
.upper-content {
  display: flex;
}
.card-img {
  width: 70px;
  height: 70px;
  border-radius: 70px;
  border: 1px solid #F7F7F7;
  background: lightgray 50% / cover no-repeat;
  margin-right: 21px;
}
hr {
  height: 1px;
  width : 221.002px;
  background: #D7D7D7;
}
.card-txt {
  width: 222px;
  height: 80px;
  overflow: hidden;
  color: #333;
  text-overflow: ellipsis;
  /* body1 */
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
#card-name { 
  color: #333;
  text-align: center;
  /* subheading */
  font-size: 17px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  text-align: left;
  margin-bottom: 7px;
}
#card-score {
  color: #333;
  text-align: center;
  /* caption */
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-align: left;
}

#part3{
  height: 840px;
  background: linear-gradient(180deg, #F3FCFF 0%, rgba(243, 254, 255, 0.00) 100%);
  justify-content: center;
  align-items: center;
  display: flex;
}
.part3-txt {
  font-size: 2.25em;
  color: #333;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  text-align: left;
  margin-right: 7.24em;
}
.review-img {
  height: 466px;
  width: 766px;
  background-color: transparent;
  background: transparent;
}
#part4{
  height: 840px;
  background: linear-gradient(180deg, #F3FFFC 0%, rgba(243, 254, 255, 0.00) 100%);
  text-align: left;
  display: flex;
  justify-content: center;
  align-items: left;
  flex-direction: column;
  padding: 7.5em;
  box-sizing: border-box;
}
.part4-txt {
  font-size: 1.7em;
  font-weight: 700;
  line-height: normal;
}
.counselor-form-btn {
  display: flex;
  height: 45px;
  width: 169px;
  gap: 10px;
  border-radius: 10px;
  background: #333;
  color: white;
  margin-top: 40px;
  font-size: 16px;
  align-items: center;
  justify-content: center;
}
</style>