<template>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">  
    <div class="mypage">
        <div class="empty-box"></div>
        <div class="mypage-area">
            <div class="mypage-header">
                <div>상담사 마이페이지</div>
                <div class="header-line"></div>
            </div>

            <div class="mypage-contents">
                <div class="profile-nav">
                    <ul class="nav-menu">
                        <!-- <li id="coun"> 상담사 전용</li> -->
                        <router-link to="/counselor"><li id="coun"> | 상담사 정보 수정</li></router-link>
                        <router-link to="/counselor/counseling"><li id="coun"> | 상담 관리</li></router-link>
                        <router-link to="/counselor/counreview"><li id="coun"> | 후기 관리</li></router-link>
                    </ul>
                </div>
                
                <div>
                    <div class="get-count">예정된 상담 : {{ this.waitingCnt }} 개</div>
                    <div class="counseling-info-part">
                        <div class="upper-part">
                            <div class="upbox" id="calendar">
                                <div class="upbox-txt">예약일</div>
                                    <div class="calendar-poster">
                                        <VDatePicker v-model="clicked_date" transparent borderless @click="getCoRezInfo(this.clicked_date)"/>
                                    </div>
                            </div>
                            <div class="upbox" id="lists">
                                <div class="upbox-txt">{{ shown_date }} 상담 목록</div>
                                <div class="list-table">
                                    <div class="list-title">
                                        <div>예약 번호</div>
                                        <div>|</div>
                                        <div>예약자</div>
                                        <div>|</div>
                                        <div>예약 시간</div>
                                        <div>|</div>
                                        <div>예약 상품</div>
                                        <div>|</div>
                                        <div>예약 상태</div>
                                    </div>
                                    <div class="list-contents">
                                        <!-- 각 row -->
                                        <div v-if="reservations && reservations.length === 0" class="list-row">
                                            <div colspan="5" style="width: 100%; margin-top: 100px;">해당 날짜에 예약이 없습니다.</div>
                                        </div>
                                        <div v-else v-for="reservation, idx in reservations" :key="idx" class="list-row" @click="handleReservationClick(reservation)">
                                            <div>{{ reservation.reservationNo }}</div>
                                            <div>{{ reservation.memberName }}</div>
                                            <div>{{ reservation.reservationDateTime }}</div>
                                            <div> {{ reservation.reservationType }}</div>
                                            <div>{{ reservation.reservationStatus }}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="selected-part" v-if="clickedReservation">
                            <div class="res-cell">
                                <div class="cell-label">예약 번호</div>
                                <div class="cell-txt">{{ clickedReservation.reservationNo }}</div>
                            </div>
                            <div class="res-cell">
                                <div class="cell-label">예약자</div>
                                <div class="cell-txt">{{ clickedReservation.memberName }}</div>
                            </div>
                            <div class="res-cell">
                                <div class="cell-label">예약 시간</div>
                                <div class="cell-txt">{{ clickedReservation.reservationDateTime }}</div>
                            </div>
                            <div class="res-cell">
                                <div class="cell-label">예약 상품</div>
                                <div class="cell-txt">{{ clickedReservation.reservationType }}</div>
                            </div>
                            <div class="res-cell">
                                <div class="cell-label">예약 상태</div>
                                <div class="cell-txt">{{ clickedReservation.reservationStatus }}</div>
                            </div>

                            <div class="res-cell" id="cell-btns" v-if="clickedReservation.reservationStatus === '상담 전'">
                                <SquareButton id="start-btn">
                                    <!-- <router-link to="/chatview">상담 시작</router-link> -->
                                    <router-link :to="{ name: 'chatviewforconsultant', params: { reservationNo: clickedReservation.reservationNo } }">상담 시작</router-link>
                                    </SquareButton>
                                <SquareButton isTarot id="cancel-btn" @click="cancelReservation()">취소</SquareButton>
                                
                            </div>
                            <div class="res-cell" id="cell-btns" v-if="clickedReservation.reservationStatus === '상담 종료'">
                                <SquareButton isTarot id="cancel-btn" @click="checkResult(clickedReservation.reservationNo)">결과보기</SquareButton>
                            </div>


                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>
</template>
    
<script>
import { SquareButton } from "../../components/styled-components/StyledButton";
import { apiInstance } from '@/api/index';

export default {
    
    components: {
        SquareButton,

    },
    data() {
        return {
            name:'consultant',
            counselor: null,
            reservations: null,
            clicked_date: new Date(),
            noReservation: true,
            shown_date: null,
            formatted_date: null,
            clickedReservation : null,
            clickedReservationNo: null,
            waitingCnt: 0
        };
    },
    methods: {
        getCounselorWaitingInfo(){
            const getCounselorInfoRequest = apiInstance(); 
            getCounselorInfoRequest({
                method: 'GET',
                url: `/reservations/counselor_waiting`,
            })
            .then((res) => {
                this.waitingCnt = res.data
                console.log(res.data)                
            })
            .catch((e) => {
                console.log(e)
            })
        },
        getCounselorInfo(){
            const getCounselorInfoRequest = apiInstance(); 
            getCounselorInfoRequest({
                method: 'GET',
                url: `/counselors/info`,
            })
            .then((res) => {
                this.counselor = res.data
                console.log(this.counselor)
                this.getCoRezInfo(this.clicked_date)
                
            })
            .catch((e) => {
                console.log(e)
            })
        },
        getCoRezInfo(date) {
            const day = date.getDate();
            const month = date.getMonth() + 1;
            const year = date.getFullYear();

            this.shown_date = `${year}년 ${month}월 ${day}일`
            
            var newmonth = month >= 10 ? month : '0' + month;
            var newday = day >= 10 ? day : '0' + day;     
            
            if(this.formatted_date == `${year}-${newmonth}-${newday}`)  return;

            this.formatted_date = `${year}-${newmonth}-${newday}`;

            const getCoRezInfoRequest = apiInstance();
            getCoRezInfoRequest({
                method: 'GET',
                url: `/reservations/counselor_rez_info/${this.formatted_date}`,
            })
            .then((res) => {
                console.log(res.data)
               
                this.reservations = this.handleRezInfo(res.data)
                this.noReservation = false;
                this.toggleReservationDetails();

            })
            .catch((e) => {
                console.log(e)
            })
        },
        handleRezInfo(reservations) {
            const statusTable = {
                "WAITING": "상담 전",
                "PROCEEDING": "상담 진행",
                "CANCEL": "상담 취소",
                "END": "상담 종료",
            }
            const typeTable = {
                "SAJU": "사주",
                "TARO": '타로'
            }
            reservations.forEach((reservation) => {
                reservation.reservationStatus = statusTable[reservation.reservationStatus];
                reservation.reservationType = typeTable[reservation.reservationType];
                reservation.reservationDateTime = reservation.reservationDateTime.substring(11, 16);
                
            });
            return reservations
        },
        toggleReservationDetails(){
            this.clickedReservation = this.clickedReservation ? null : this.clickedReservation;
        },
       
        handleReservationClick(reservation){
            this.clickedReservation = reservation;
            this.clickedReservationNo = reservation.reservationNo

        },

        cancelReservation() {
            const confirmCancel = window.confirm("예약을 취소하시겠습니까?");

            if (confirmCancel) {
               
                const cancelRequest = apiInstance();
                cancelRequest({
                    method: 'PATCH',
                    url: `reservations/cancel/${this.clickedReservationNo}`,
                })
                .then((res) => {
                    alert(res.data)
                    console.log(res.data);
                    location.reload();

                })
                .catch((e) => {
                    console.log(e);
                    
                })
            }            
        }, 
        checkResult(reservationNo) {
            const clickedReservationNo = reservationNo
            console.log(clickedReservationNo)
            this.$router.push({
                name: 'counselorreportupdate',
                params: {rezNo : clickedReservationNo}
            })
        }    

    },
   
    created() {
        this.getCounselorWaitingInfo();
        this.getCounselorInfo();
  
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
}
.profile-nav {
    height: 588px;
    width: 181px;
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
    white-space: nowrap;
}
.nav-menu #coun{
    color: #9C7AE7;
}
    
    
.counseling-info-part{
    height: 552px;
    width: 1080px;
    border-radius: 10px;
    background: #FCFAF1;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    display: flex;
    justify-content: space-between;
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;    
    padding: 44px 32px;
    display: flex;
    flex-direction: column;
    margin-left: 100px;
}   

.get-count {
    color: #000;
    font-size: 23px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
    text-align: left;
    margin-bottom: 20px;
    margin-left: 120px;
}

.upper-part{
    display: flex;
    justify-content: space-evenly;
    align-items: center;   
}

.upbox{
    border-radius: 20px;
    border: 2px solid #D7D7D7;
    background: #FFF;
}
#calendar{
    width: 350px;
    height: 423px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 10px;
    box-sizing: border-box;
}
.upbox-txt {
    color:  #333;
    font-size: 22px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
    margin-top: 20px;
    margin-bottom: 20px;
}
#lists{
    width: 642px;
    height: 423px;
    padding: 10px;
    box-sizing: border-box;
}
.list-table{
    padding-left: 10px;
    padding-right: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.list-title{
    width: 100%;
    height: 34px;
    background-color: rgba(242, 148, 173, 0.30);
    display: flex;
    justify-content: space-evenly;
    font-weight: 600;
    font-size: 18px;
    align-items: center;
}
.list-contents{
    height: 280px;
    display: flex;
    flex-direction: column;
    align-items: center;
    overflow: auto;
    margin-top: 7px;
    font-size: 18px;
}
.list-contents div div{
    width: 17%;
}
.list-row{
    width: 96%;
    display: flex;
    justify-content: space-between;
    margin-top: 5px;
    margin-bottom: 5px;
}
.selected-part{
    height: 116px;
    display: flex;
    justify-content: space-around;
    align-items: center;
    margin-top: 30px;
    border-radius: 10px;
    border: 2px solid #D9D9D9;
    background: #FFF;
}
.res-cell{
    width: 114px;
    height: 60px;
}
.cell-label{
    color: #000;
    font-size: 16px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.cell-txt{
    color: #333;
    font-size: 15px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    padding-top: 12px;
}
#cell-btns{
    width: 235px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>
