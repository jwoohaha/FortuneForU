<template>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">  
        <div class="mypage">
            <div class="empty-box"></div>
            <div class="mypage-area">
                <div class="mypage-header">
                    <div>마이페이지</div>
                    <div class="header-line"></div>
                </div>
        
                <div class="mypage-contents" id="my-report-list">
                    <div class="profile-nav">
                        <div class="profile-img">
                            <img :src="imgUrl" style="width: 100%; height: 100%; object-fit:cover;">
                        </div>
                        <ul class="nav-menu">
                            <router-link to="/mypage"><li> | 개인 정보 수정</li></router-link>
                        <router-link to="/mypage/reservationlist"><li> | 나의 예약 목록</li></router-link> 
                        <router-link to="/mypage/reportlist"><li> | 상담 결과</li></router-link> 
                        <router-link to="/mypage/review"><li> | 나의 후기</li></router-link>
                        </ul>
                    </div>
    
                    <div class="my-report-list">
                        <div class="table-header">
                            <div>상담사</div>
                            <div class="divider">|</div>
                            <div>이용 날짜</div>
                            <div class="divider">|</div>
                            <div>상담 결과서</div>
                            <div class="divider">|</div>
                            <div>후기 작성</div>
                            
                        </div>
                        <div class="table-contents">
                            <div v-if="noReservation" class="list-row">
                                <div colspan="5" style="text-align: center; margin-top: 150px; font-size: 25px; font-weight: 600;">상담 결과가 없습니다</div>
                            </div>                    
                            <div v-else v-for="reservation, idx in reservationList" :key="idx" class="each-row" >
                                <div id="coun-name" >{{ reservation.counselorName }}</div>
                                <div class="divider">|</div>
                                <div id="coun-date">{{ reservation.reservationDateTime }}</div>
                                <div class="divider">|</div>
                                <div id="coun-status" v-if="reservation.reportStatus==='결과 보러가기'" @click="handleReservationClick(reservation)" style="text-decoration : underline; ">
                                    {{ reservation.reportStatus }}<img src="@/assets/review_icon.png" alt="">
                                </div>
                                <div id="coun-satus" v-else style="width: 18%;">
                                    {{ reservation.reportStatus }}
                                </div>
                                <div class="divider">|</div>
                                <div id="coun-room">             
                                    <div v-if="reservation.reservationReview === null" @click="writeReview(reservation.reservationNo, reservation.counselorId)" ><span style="text-decoration : underline;">후기 작성하기</span>🧾</div>
                                    <div v-else>작성 완료</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <modal-view v-if="isModalVisible" @close-modal="isModalVisible = false" :counselorId="this.clickedId" :reservationNo="this.clickednum"></modal-view>
        </div>
    </template>
    
<script>
import { apiInstance } from '@/api/index';
import ModalView from "@/components/common/ReviewModalView.vue";

export default {
    components: {
        ModalView
    },
    data() {
        return {
            member: null,
            reservationList: null,
            noReservation: true,
            reservationNo:null,
            clickedReservation : null,
            isModalVisible: false,
            clickednum: null,
            clickedId: null,
            imgUrl: require ('@/assets/profile_default_img.png'),
        };
    },
    methods: {
        getMemberInfo() {
            const getmember = apiInstance();
            getmember({
                method: 'GET',
                url: '/members/info',
            })
            .then((res) => {
                console.log(res.data)
                this.member = res.data
                console.log(this.member)
                if (res.data.profileImage != null){
                    this.imgUrl = res.data.profileImage
                }
            })
            .catch((e) => {
                console.log(e)
            })
        },
        getEndRezInfo() {
            const getRezInfoRequest = apiInstance();
            getRezInfoRequest({
                method: 'GET',
                url: '/reservations/member/reports',
            })
            .then((res) => {
                console.log(res.data)
                if(res.data.length !== 0) {
                    this.reservationList = this.handleRezInfo(res.data)
                    this.noReservation = false;
                }
            
            })
            .catch((e) => {
                console.log(e)
            })
        },
        // 예약 정보 변환
        handleRezInfo(reservationList) {
            const statusTable = {
                "NONE": "없음",
                "WAITING" : "대기",
                "COMPLETE": "결과 보러가기"
            }
            reservationList.forEach((reservation) => {
                reservation.reportStatus = statusTable[reservation.reportStatus];
                reservation.reservationDateTime = reservation.reservationDateTime.replace("T", " ");
                reservation.reservationDateTime = reservation.reservationDateTime.substring(0, 16);
            });
            return reservationList
        },
        handleReservationClick(reservation){
            const clickedReservationNo = reservation.reservationNo
           
            this.$router.push({
                name: 'counslingresult',
                params: { rezNo : clickedReservationNo }
            })
          
        },
        writeReview(reservationNo, counselorId){
            console.log("counselingId" + this.counselorId)
            
            this.isModalVisible = true;
            this.clickednum = reservationNo;
            this.clickedId = counselorId;
            
        }
    },
    created() {
        this.getMemberInfo();
        this.getEndRezInfo();
    },
}
</script>

<style lang="scss" scoped="scss">

.mypage {
    display: inline-flex;
    justify-content: center;
    padding-top: 92px;
}
.empty-box {
    height: 92px;
}
.mypage-area{
    height: 900px;
    width: 1273px;
    margin-top: 96px;
    // margin-left: 15%;
    // margin-right: 15%;
}
.mypage-header { 
    height: 57px;
    width: 80%;
    text-align: left;
    color: #333;
    font-size: 34px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.header-line {
    height: 1.5px;
    background: #000;
    width: 45%;
    padding: 0;
    margin-top: 20px;
    margin-bottom: 67px;
}
.mypage-contents{
    height: 588px;
    width: 100%;
    margin-top: 81px;
    display: flex;
    justify-content: space-around;
    // background-color: red;
}
.profile-nav {
    height: 588px;
    width: 181px;
}
.profile-img{
    width: 180.9px;
    height: 180px;
    border-radius: 180.9px;
    // background-image: url('https://t1.daumcdn.net/cfile/tistory/99A2E4475F05CDA90F');
    background-repeat : no-repeat;
    background-size : cover;
    margin-bottom: 14px;
    background: lightgray 50% / cover no-repeat, #D7D7D7;
}
.nav-menu{
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    gap: 50px;
    margin-top: 70px;
}
.nav-menu li{
    color:  #333;
    font-size: 24px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.my-report-list{
    height: 552px;
    width: 1021px;
    border-radius: 10px;
    border: 1px solid #D9D9D9;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
    padding-top: 30px;
    padding-bottom: 15px;
    box-sizing: border-box;
}
.status-part{
    width: 975px;
    display: flex;
    justify-content: end;
    margin-bottom: 19px;
}
.status-part div{
    margin-right: 30px;
}
.table-header {
    width: 975px;
    height: 60px;
    border-radius: 10px 10px 0px 0px;
    background: #FCFAF1;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    color: #333;
    font-size: 20px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.table-contents {
    width: 975px;
    height: 400px;
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;
    overflow: auto;
    margin-bottom: 15px;
}
.each-row {
    width: 96%;
    height: 60px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color:#333;
    font-size: 18px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
    margin: 20px 0px;
    margin-left: 10px;
}
#coun-name{
    width: 18%;
}
#coun-date{
    width:18%;
}
#coun-status {
    width: 18%;
}
#coun-status img{
    height: 25px;
    width: 25px
}
#coun-room {
    width: 18%;
    display: flex;
    justify-content: center;
    white-space: nowrap;
}
.each-row .divider{
    color: white;
}
</style>