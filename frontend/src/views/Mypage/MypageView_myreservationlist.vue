<template>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">  
        <div class="mypage">
            <div class="empty-box"></div>
            <div class="mypage-area">
                <div class="mypage-header">
                    <div>마이페이지</div>
                    <div class="header-line"></div>
                </div>
        
                <div class="mypage-contents" id="my-res-list">
                    <div class="profile-nav">
                        <div class="profile-img"><img :src="imgUrl" style="width: 100%; height: 100%; object-fit:cover;"></div>
                        <ul class="nav-menu">
                            <router-link to="/mypage"><li> | 개인 정보 수정</li></router-link>
                        <router-link to="/mypage/reservationlist"><li> | 나의 예약 목록</li></router-link> 
                        <router-link to="/mypage/reportlist"><li> | 상담 결과</li></router-link> 
                        <router-link to="/mypage/review"><li> | 나의 후기</li></router-link>
                        </ul>
                    </div>
    
                    <div class="res-list-part">
                        <!-- <div class="status-part">
                            <div id="status1">🤍 예약중</div>
                            <div id="status2">🤍 완료</div>
                            <div id="status3">🤍 취소</div>
                        </div> -->
                        <div class="table-header">
                            <div>상담사</div>
                            <div class="divider">|</div>
                            <div>예약 시간</div>
                            <div class="divider">|</div>
                            <!-- <div>취소가능날짜</div>
                            <div class="divider">|</div> -->
                            <div>현재 상태</div>
                            <div class="divider">|</div>
                            <div>상담실</div>
                            <div class="divider">|</div>
                            <div>취소</div>
                        </div>
                        <div class="table-contents">
                            
                            <router-link to="/tarot"><h3 v-if="noReservation" style="margin-top: 150px; font-size:25px; text-decoration: underline;">예약하러 가기🎪</h3></router-link>
                            <div v-for="(reservation, idx) in reservationList" :key="idx" class="each-row">
                                <div id="coun-name">{{ reservation.counselorName }}</div>
                                <div class="divider">|</div>
                                <div id="coun-date">{{ reservation.reservationDateTime }}</div>
                                <div class="divider">|</div>
                                <!-- <div id="cancel-date">{{ reservation.cancelableReservationDate }}</div>
                                <div class="divider">|</div> -->
                                <div id="coun-status" >{{ reservation.reservationStatus }}</div>
                                <div class="divider">|</div>
                                <div id="coun-room">
                                    
                                    <div v-if="reservation.reservationStatus==='상담 진행'" >
                                        <!-- <a href={{reservation.sessionId}}>🏠</a> -->
                                        <router-link :to="{ name: 'chatviewforuser', params: { IdforSession: reservation.sessionId} }">입장하기🏠</router-link>
                                    </div>
                                    <div v-if="reservation.reservationStatus=='상담 전'" style="white-space: nowrap;">대기중💥</div>
                                    <div v-else style="width: 20%;"></div>
                                </div>
                                <div class="divider">|</div>
                                <div id="coun-cancel" v-if="reservation.reservationStatus=='상담 전'" @click="cancelReservation(reservation.reservationNo)" >취소하기❌</div>
                                <div v-else style="width: 20%;"></div>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </template>
    
<script>
import { apiInstance } from '@/api/index';

export default {
    components: {
    },
    data() {
        return {
            member: null,
            reservationList: null,
            noReservation: true,
            imgUrl: require ('@/assets/profile_default_img.png'),
        };
    },
    methods: {
        getMemberInfo() {
            const getRezInfoRequest = apiInstance();
            getRezInfoRequest({
                method: 'GET',
                url: 'members/info',
            })
            .then((res) => {
                this.member = res.data
                if (res.data.profileImage != null){
                    this.imgUrl = res.data.profileImage
                }
            })
            .catch((e) => {
                console.log(e)
            })
        },
        getRezInfo() {
            const getRezInfoRequest = apiInstance();
            getRezInfoRequest({
                method: 'GET',
                url: `reservations/member_rez_info`,
            })
            .then((res) => {
                if(res.data.length !== 0) {
                    this.reservationList = this.handleRezInfo(res.data)
                    this.noReservation = false;
                }
            })
            .catch((e) => {
                console.log(e)
            })
        },
        // 예약 정보 변환(영 -> 한, 시간 다듬기)
        handleRezInfo(reservationList) {
            const statusTable = {
                "WAITING": "상담 전",
                "PROCEEDING": "상담 진행",
                "CANCEL": "상담 취소",
                "END": "상담 종료",
            }
            reservationList.forEach((reservation) => {
                reservation.reservationStatus = statusTable[reservation.reservationStatus];
                reservation.reservationDateTime = reservation.reservationDateTime.replace("T", " ");
                reservation.reservationDateTime = reservation.reservationDateTime.substring(0, 16);
            });
            return reservationList
        },
        cancelReservation(reservationNo) {
            const cancelRezRequest = apiInstance();
            cancelRezRequest({
                method: 'PATCH',
                url: `reservations/cancel/${reservationNo}`,
            })
            .then((res) => {
                alert(res.data)
                this.getRezInfo();
                this.getMemberInfo();
            })
            .catch((e) => {
                console.log(e)
                alert("취소가 불가능한 예약입니다")
            })
        },
    },
    created() {
        this.getRezInfo();
        this.getMemberInfo();
    }
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
    //background-image: url('https://t1.daumcdn.net/cfile/tistory/99A2E4475F05CDA90F');
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
.res-list-part{
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
}
.each-row {
    width: 98%;
    height: 60px;
    margin: 16px 0px;
    padding-left: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color:#333;
    font-size: 17px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
}
#coun-name{
    width: 20%;
}
#coun-date{
    width: 20%;
}
#coun-status{
    width: 20%;
    margin-left: 20px;
}

#coun-room {
    width: 20%;
    margin-left: 20px;
}
.each-row .divider{
    color: white;
}
#coun-room div{
    font-size: 16px;
}
#coun-cancel{
    width: 20%;
    font-size: 16px;
}
</style>