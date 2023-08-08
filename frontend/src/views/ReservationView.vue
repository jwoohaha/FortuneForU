<template>
    <div class="reservation-view">
      <div class="empty-box"></div>
      <div class="reservation-contents">
        
        <div class="reservation-header">
          <div class="top-header">
            <div class="left-header">
              <div class="top-title">
                상담 예약
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
                안녕하세요. 타로 외길 인생 24년, {{ counselor.name }}입니다.
                <div class="hr-wrapper"></div>
            </div>

           <div class="reservation-section">
             <div class="profile-section">
                <div class="profile-img">
                    <img src={{counselor.profileImg}}>
                </div>
                <div class="profile-txt">
                    <div>성명 : {{ counselor.name }}</div>
                    <div>경력 : {{ counselor.career }}</div>
                    <div>전문분야 : {{ counselor.major }}</div>
                    <div class="tag-info">#솔직담백 #연애전문</div>
                </div>
            </div>
            
            <div class="review-section">
                <div class="review-title">생생한 상담 후기</div>
                <div v-for="coun in counselors" :key="coun.id">
                    <ReviewCard :counselor="coun"></ReviewCard>
                </div>
            </div>

            <div class="calendar-section">
                <div class="res-calendar">
                    <div class="calendar-txt">예약일</div>
                    <div class="calendar-poster">
                        <!-- <VCalendar /> -->
                        <VDatePicker v-model="date" transparent borderless/>
                    </div>
                    <div class="calendar-hr"></div>
                    <div class="calendar-txt">예약 가능 시간</div>
                    <div class="time-section">
                        <div class="res-btn">12:00</div>
                        <div class="res-btn">12:00</div>
                        <div class="res-btn">12:00</div>
                        <div class="res-btn">12:00</div>
                    </div>
                    <div class="time-section">
                        <div class="res-btn">12:00</div>
                        <div class="res-btn">12:00</div>
                        <div class="res-btn">12:00</div>
                        <div class="res-btn">12:00</div>
                    </div>
                </div>
                <div class="res-btn">예약하기</div>
            </div>
           </div>
          </div>
        </div>
        
    </div>
    
  </template>

<script>
import ReviewCard from '../components/common/ReviewCard.vue';
import { apiInstance } from '@/api/index';

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
      counselor: [],
    };
  },
  methods: {
    async getCounselorInfo(id){
      const getCounselorsRequest = apiInstance();
      await getCounselorsRequest.get(`counselors/${id}`)
      .then((re) => {
        console.log(re);
        this.counselor = re.data;
      })
      .catch((e) => console.log(e))
    }
    
  },
  created(){
    this.getCounselorInfo(this.$route.params.id)
  }
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
.reservation-contents{
  padding-top: 82px;
  width: 70vw;
  margin-bottom: 200px;
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
  height: 1.5px;
  background: #000;
  width: 50%;
  padding: 0;
  margin-top: 20px;
    margin-bottom: 67px;
}
.reservation-box {
    width: 100%;
    height: 900px;
    border-radius: 10px;
    border: 2px solid #D7D7D7;  
    padding-top: 75px;
    padding-left: 65px;
    padding-bottom: 64px;
    padding-right: 65px;
    box-sizing: border-box;
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
.reservation-section {
    display: flex;
    justify-content: space-between;
    margin-top: 64px;
    flex-wrap: wrap;
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
// .calendar-poster {
//     // height: 293px;
//     // width: 319px;
//     // display: inline;
//     // align-content: center;
    
// }
.res-calendar {
    width: 407px;
    height: 553px;
    border-radius: 20px;
    border: 2px solid #D7D7D7;
    background: #FFF;
    margin-bottom: 45px;
    padding-top: 10px;
    padding-bottom: 22px;
    box-sizing: border-box;
}
.calendar-hr {
    height: 2px;
    width: 100%;
    padding: 0;
    margin-top: 30px;
    background-color: #D7D7D7;
}
.calendar-txt{
    color:  #333;
    font-size: 16px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    margin: 12px 0px 10px 55px;
    text-align: left;
}
.time-section{
    margin: 20px 33px;
    display: flex;
    justify-content: space-between;
    flex-wrap : wrap;
}
.time-section .res-btn {
    width: 80px;
    height: 40px;
    box-sizing: border-box;
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
