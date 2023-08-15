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
                        <div class="profile-img"><img :src="getProfileImg"></div>
                        <!-- <div class="profile-img" :style="{ backgroundImage : `url(${this.member.profileImage})`}" ></div> -->
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
                            <div>리뷰 작성</div>
                            <div class="divider">|</div>
                            
                        </div>
                        <div class="table-contents">
                            <div v-if="noReservation" class="list-row">
                                            <div colspan="5" style="text-align: center;">상담 결과가 없습니다</div>
                            </div>                    
                            <div v-else v-for="reservation, idx in reservationList" :key="idx" class="each-row" >
                                <div id="coun-name">{{ reservation.counselorName }}</div>
                                <div class="divider">|</div>
                                <div id="coun-date">{{ reservation.reservationDateTime }}</div>
                                <div class="divider">|</div>
                                <div id="coun-satus" @click="reservation.reportStatus === '결과 보러가기' ? handleReservationClick(reservation) : null" >
                                    {{ reservation.reportStatus }}
                                </div>
                                <div class="divider">|</div>
                                <div id="coun-room">             
                                    <div v-if="reservation.reservationReview === null">리뷰 작성하기</div>
                                    <div v-else>작성 완료</div>
                                </div>
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
            reservationNo:null,
            clickedReservation : null,

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
            });
            return reservationList
        },
        handleReservationClick(reservation){
            const clickedReservationNo = reservation.reservationNo
            console.log()
            this.$router.push({
                name: 'counslingresult',
                params: {
                clickedReservationNo
                }
            });
        },
        getProfileImg() {
            const ImgUrl = this.member.profileImage;
            return ImgUrl
        }
    },
    created() {
        //this.getMemberInfo();
        this.getEndRezInfo();
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
    width: 344px;
    text-align: left;
    color: #333;
    font-size: 34px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.header-line {
    height: 1px;
    width: 100%;
    background-color: #333;
    margin-top: 10px;
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
    background-image: url('https://t1.daumcdn.net/cfile/tistory/99A2E4475F05CDA90F');
    background-repeat : no-repeat;
    background-size : cover;
    margin-bottom: 14px;
    // background: lightgray 50% / cover no-repeat, #D7D7D7;
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
    justify-content: space-between;
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
    height: 340px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.each-row {
    width: 975px;
    height: 60px;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    color:#333;
    font-size: 16px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
}
#coun-room {
    width: 93px;
    display: flex;
    justify-content: space-around;
}
.each-row .divider{
    color: white;
}
#coun-room div{
    font-size: 16px;
}
</style>