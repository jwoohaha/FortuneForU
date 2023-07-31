<template>
    <div class="reservation-view">
      <div class="empty-box"></div>
      <div class="contents">
        
        <div class="reservation-header">
          <div class="top-header">
            <div class="left-header">
              <div class="top-title">
                타로 상담 예약
                  <span class="top-subtitle">
                    원하는 상담사를 선택한 후 예약하세요.
                  </span>
              </div>
            </div>
          
          </div>
        
          <div class="hr-wrapper"></div>
      
        </div>

          <div class="reservation-box">
            <div class="res-title">
                안녕하세요. 타로 외길 인생 24년, 소윤파크입니다.
                <div class="hr-wrapper"></div>
            </div>

           <div class="reservation-content">
             <div class="profile-section">
                <div class="profile-img">
                    <img src="../assets/dummy_counselor_img.jpg" alt="">
                </div>
                <div class="profile-txt">
                    <div>성명 : 소윤파크</div>
                    <div>소개 : MBTI 박사 과정</div>
                    <div>전문분야 : 궁합/애정, 취업/시험</div>
                    <div class="tag-info">#솔직담백 #연애전문</div>
                </div>
            </div>
            
            <div class="review-section">
                <div class="review-title">생생한 상담 후기</div>
                <div v-for="counselor in counselors" :key="counselor.id">
                    <ReviewCard :counselor="counselor"></ReviewCard>
                </div>
            </div>

            <div class="calendar-section">
                <div class="res-calendar"></div>
                <div class="res-btn">예약하기</div>
            </div>
           </div>
          </div>
        </div>
        
    </div>
  </template>

<script>
import ReviewCard from '../components/common/ReviewCard.vue';

export default {
  components: {
    ReviewCard,
  },
  data() {
    return {
      counselors: [
        { id: 1, name: 'John Doe', rating: 4.5, reviews: 20 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
      ],
    };
  },
 };
</script>

<style lang="scss" scoped>
.reservation-view {
    padding-left: 15%;
    padding-right: 15%;
}
.empty-box {
  height: 92px;
}
.top-header {
  display: flex;
  justify-content: space-between;
}
.left-header {
  width: 44%;
  height: 49px;
}
.top-title {
  white-space : nowrap;
  color: #333;
  font-size: 30px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  text-align: left;
}
.top-subtitle {
  color: #333;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  margin-left: 2%;
  display: inline-block;
}
.hr-wrapper {
    border:#000000 0.5px solid;
    height: 0px;
    width: 65%;
    padding: 0;
    margin-top: 20px;
    margin-bottom: 67px;
}
.contents {
    width: 70%;
    padding-top: 82px;
    margin-bottom: 300px;
}
.reservation-box {
    width: 1353px;
    height: 836px;
    flex-shrink: 0;
    border-radius: 10px;
    border: 2px solid #D7D7D7;  
    padding-top: 75px;
    padding-left: 65px;
    padding-bottom: 64px;
    padding-right: 65px;
}
.res-title {
    color: #333;
    font-size: 24px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    text-align: start;
    //text-decoration: underline;
    //text-underline-position : under;
}
.reservation-content {
    display: flex;
    justify-content: space-between;
    margin-top: 64px;
}
.profile-section {
    display: inline-flex;
    flex-direction: column;
    align-items: flex-start;
    gap: 46px;
}
img {
    width: 300px;
    height: 320px;
    object-fit: fill;
    flex-shrink: 0;
    border-radius: 10px;
    border: 2px solid #D7D7D7;
    background:  lightgray 50% / contain no-repeat;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
}
.profile-txt {
    border-left: #000000 3px solid;
    height: 144px;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    color: var(--title-text);
    font-size: 17px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    text-align: left;
    padding-left: 24px;
}
.tag-info {
    color: #BFAEE5;
}
.review-section {
    display: inline-flex;
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
}
.review-title{
    color: #333;
    font-size: 20px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.calendar-section {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.res-calendar {
    width: 407px;
    height: 553px;
    flex-shrink: 0;
    border-radius: 20px;
    border: 2px solid #D7D7D7;
    background: #FFF;
    margin-bottom: 45px;
}
.res-btn {
    display: flex;
    width: 346px;
    height: 40px;
    padding: 7px 23px;
    justify-content: center;
    align-items: center;
    gap: 10px;
    flex-shrink: 0;
    border-radius: 5px;
    background: #BFAEE5;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
    color: #FFF;
    font-family: Inter;
    font-size: 16px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
</style>
