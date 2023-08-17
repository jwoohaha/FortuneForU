<template>
    <div class="reservation-view">
      <div class="empty-box"></div>
      <div class="reservation-contents">
        
        <div class="reservation-header">
          <div class="top-header">
            <div class="left-header">
              <div class="top-title">
                {{ typeTxt }} 상담 예약
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
                안녕하세요. {{ typeTxt }} 상담사 {{ counselor.name }}입니다.
                <div class="hr-wrapper"></div>
            </div>

           <div class="reservation-section">
             <div class="profile-section">
                <div class="profile-img">
                    <img :src="imgUrl">
                    <div class="follow-btn">
                      <img v-if="isFollowing" :src="selectedIcon" @click="unfollowRequest">
                      <img v-else :src="unselectedIcon" @click="followRequest"> 
                    </div>
                </div>
                <div class="profile-txt">
                    <div>성명 : {{ counselor.name }}</div>
                    <div>경력 : {{ counselor.career }}</div>
                    <div>전문분야 : {{ counselor.major }}</div>
                    <div class="tag-info" v-if="pageType=='SAJU'" style="color: #F6B5C6;">{{ counselor.intro }}</div>
                    <div class="tag-info" v-else>{{ counselor.intro }}</div>
                </div>
            </div>
            
            <div class="review-section">
                <div class="review-title">생생한 상담 후기</div>
                <div v-for="review in reviews" :key="review.id">
                    <ReviewCard :review="review"></ReviewCard>
                </div>
                <!-- 예외처리 -->
                <div v-if="emptyReview" style="font-size: 20px; background-color: bisque;">후기가 없습니다.</div>
            </div>

            <div class="calendar-section">
                <div class="res-calendar">
                    <div class="calendar-txt">예약일</div>
                    <div class="calendar-poster">
                        <!-- <VCalendar /> -->
                        <VDatePicker v-model="clicked_date" transparent borderless @click="setDate(this.counselor.counselorNo, this.clicked_date)"/>
                        <div>선택된 날짜 : {{ this.formatted_date }}</div>
                    </div>
                    <div class="calendar-hr"></div>
                    <div class="calendar-txt">예약 가능 시간</div>
                    <div class="time-section">
                      <div v-for="(resTime, idx) in availableTimes" :key="idx" style="margin: 2px 4px;" >
                        <SquareButton v-if="pageType=='SAJU'" :style="{ backgroundColor: clickedBtnIdx === idx ? '#F47F9E' : '' }" @click="clickedButton(resTime, idx)">{{ resTime }}</SquareButton>
                        <SquareButton isTarot v-else :style="{ backgroundColor: clickedBtnIdx === idx ? '#9C7AE7' : '' }" @click="clickedButton(resTime, idx)">{{ resTime }}</SquareButton>
                      </div>
                    </div>
                </div>
                <SquareButton class="res-btn" @click="reserve" v-if="pageType=='SAJU'">예약하기</SquareButton>
                <SquareButton isTarot class="res-btn" @click="reserve" v-else>예약하기</SquareButton>
                <!-- <div v-if="this.reservationStatus!=''" style="font-size: 20px; height: 100px;">{{ this.reservationStatus }}</div> -->
            </div>
           </div>
          </div>
        </div>
        
        <modal-view v-if="isModalVisible" @close-modal="isModalVisible = false" :reservationStatus="reservationStatus" :prePage="this.pageType">
          {{ this.reservationStatus }}
        </modal-view>

    </div>
    
  </template>

<script>
import ReviewCard from '../components/common/ReviewCard.vue';
import { SquareButton } from '../components/styled-components/StyledButton'
import ModalView from "@/components/common/AlertModalView.vue";
import { apiInstance } from '@/api/index';
import { useTokenStore } from '@/stores/token';

export default {
  components: {
    ReviewCard,
    SquareButton,
    ModalView
},
  data() {
    return {
      pageType: "TARO",
      typeTxt : "타로",
      emptyReview: false,
      reviews: [],
      counselor: [],
      clicked_date: new Date(),
      cantReservations: null,
      availableTimes: [],
      formatted_date: null,
      clickedTime:null,
      clickedBtnIdx: null,
      reservationStatus: "",
      isModalVisible: false,
      selectedIcon: require ('@/assets/selected_icon.png'),
      unselectedIcon: require ('@/assets/unselected_icon.png'),
      isFollowing: false,
      defaultImgUrl: require ('@/assets/profile_default_img.png'),
    };
  },
  setup(){

  },
  methods: {
    getCounselorInfo(id){
      
    const api = apiInstance();

      api({
        method: 'GET',
        url: `/counselors/${id}/`,
      })
      .then((result) => {
        console.log(result.data);
        this.counselor = result.data;        
        this.getReviewInfo(this.counselor.counselorNo)
        this.setDate(this.counselor.counselorNo, this.clicked_date)
        this.getIsFollowing(result.data.memberNo)
      })
      .catch((e) => {
        console.log(e)
      })
    },
    getReviewInfo(id){
      
    const api = apiInstance();

      api({
        method: 'GET',
        url: 'reservations/'+ id + '/co_reviews',
      })
      .then((result) => {
        console.log(result);
        //예외처리
        if(result.data.length == 0){
          this.emptyReview = true;
          return;
        }
        
        this.reviews = result.data;
      })
      .catch((e) => {
        console.log(e)
      })
    },
    setDate(id, date){
        const day = date.getDate();
        var newday = day >= 10 ? day : '0' + day;      
        const month = date.getMonth() + 1;
        var newmonth = month >= 10 ? month : '0' + month;
        const year = date.getFullYear();

        if(this.formatted_date == `${year}-${newmonth}-${newday}`)  return;

        this.formatted_date = `${year}-${newmonth}-${newday}`;

        const api = apiInstance();
        api({
          method: 'GET',
          url: `reservations/availabledate/${id}/${this.formatted_date}`
        })
        .then((result) => {
          this.cantReservations = result.data;
          this.availableTimes = [];
          this.makeAvailableTimes();
        })
    },
    makeAvailableTimes(){
      
      //값이 없을 때의 오류 처리 필요
      if(this.counselor.startTime == null)
        return;

      var startTimeHour = Number(this.counselor.startTime.substr(0, 2));
      var startTimeMin = Number(this.counselor.startTime.substr(3, 5));
      var endTimeHour = Number(this.counselor.endTime.substr(0, 2));
      var endTimeMin = Number(this.counselor.endTime.substr(3, 5));

      while(startTimeHour <= endTimeHour){
        
        var txtHour = startTimeHour >= 10 ? startTimeHour.toString() : '0'+startTimeHour.toString();
        var txtMin = startTimeMin >= 10 ? startTimeMin.toString() : '0'+startTimeMin.toString();
        this.availableTimes.push( txtHour + ":" + txtMin )
        
        if(startTimeHour == endTimeHour && startTimeMin == endTimeMin){
          break;
        }  

        if(startTimeMin == 0){
          startTimeMin = 30;
        }else{
          startTimeHour += 1;
          startTimeMin = 0;
        }
      }
      if( this.cantReservations.length == 0 ) return;
      
      for(var i = 0; i <  this.availableTimes.length; i++){ 
        for(var j = 0; j < this.cantReservations.length; j++){

          var retxtHour = this.cantReservations[j].hour >= 10 ? this.cantReservations[j].hour.toString() : '0'+this.cantReservations[j].hour.toString();
          var retxtMin = this.cantReservations[j].minute >= 10 ? this.cantReservations[j].minute.toString() : '0'+this.cantReservations[j].minute.toString();
          var cantReservation = retxtHour + ":" + retxtMin;
          
          if ( this.availableTimes[i] === cantReservation) { 
            this.availableTimes.splice(i, 1); 
            i--; 
          }
        }
      }
    },
    clickedButton(e, idx){
      this.resTime = e;
      this.clickedBtnIdx = idx;
    },
    reserve(){

      const reservationDatetime =  this.formatted_date + "T" + this.resTime + ":00" 
      const api = apiInstance();
      
        api.post('reservations/reserve', {
            "counselorId": this.counselor.counselorNo,
            "reservationDate": reservationDatetime,
            "reservationType": this.pageType
          })
        .then((result) => {
          if(result.data == "예약이 완료 됐습니다."){
            this.reservationStatus = "200";
            this.isModalVisible = true;
          }else{
            this.reservationStatus = result.data
            this.isModalVisible = true;
          }
          
        })
        .catch((error) => {
          console.log(error)
          this.reservationStatus = error
          if (error.response.status == 401){
            const tokenStore = useTokenStore();
            alert("로그인이 필요한 페이지입니다.");
            tokenStore.makeLoginModalVisible();
          }
        })
    },
    getIsFollowing(memberNo) {
      const getFollowingRequest = apiInstance();
      getFollowingRequest({
          method: 'GET',
          url: `members/isfollowing/${memberNo}`,
      })
      .then((res) => {
          console.log('팔로잉???')
          console.log(res.data)
          this.isFollowing = res.data
      })
      .catch((e) => {
          console.log(e)
      })
    },
    followRequest() {
      const followRequest = apiInstance();
      followRequest({
          method: 'PUT',
          url: `members/follow/${this.counselor.memberNo}`,
      })
      .then((res) => {
          console.log(res)
          this.isFollowing = true
      })
      .catch((e) => {
          console.log(e)
      })
    },
    unfollowRequest() {
      const unfollowRequest = apiInstance();
      unfollowRequest({
          method: 'DELETE',
          url: `members/unfollow/${this.counselor.memberNo}`,
      })
      .then((res) => {
          console.log("unfollowid", this.counselor.memberNo)
          console.log(res)
          this.isFollowing = false
      })
      .catch((e) => {
          console.log(e)
      })
    },
  },
  created(){
    this.pageType = this.$route.query.pageType;
    if(this.pageType != "TARO"){
      this.typeTxt = "사주";
    }
    
    this.getCounselorInfo(this.$route.query.id)
  },
  computed: {
    imgUrl() {
        return this.counselor.profileImg || this.defaultImgUrl;
    }
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
    height: 800px;
    border-radius: 10px;
    border: 2px solid #D7D7D7;  
    padding-top: 75px;
    padding-left: 65px;
    padding-bottom: 64px;
    padding-right: 65px;
    // box-sizing: border-box;
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
}
.profile-txt {
    border-left: #000000 3px solid;
    width: 300px;
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
    border-radius: 20px;
    border: 2px solid #D7D7D7;
    background: #FFF;
    margin-bottom: 45px;
    padding-top: 10px;
    padding-bottom: 15px;
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
    margin: 12px 35px 10px 35px;
    text-align: left;
}
.time-section{
    height: 130px;
    overflow-y: scroll;
    margin: 15px 25px;
    display: flex;
    justify-content: start;
    align-items: center;
    flex-wrap : wrap;
}
.res-btn {
    width: 346px;
    height: 40px;
    padding: 20px 23px;
}
.profile-img {
  position: relative; /* Make the container a positioning context */
}
.follow-btn img {
  position: absolute;
  bottom: 0;
  right: 0;
  width: 70px;
  height: 70px;
  margin: 10px;
  padding: 10px;
  // background:  rgb(255, 254, 254) 50% / contain no-repeat;
  // box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
}
</style>
