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
                        <div class="profile-img"></div>
                        
                        <ul class="nav-menu">
                            <router-link to="/mypage"><li> | 개인 정보 수정</li></router-link>
                        <router-link to="/mypage/reservationlist"><li> | 나의 예약 목록</li></router-link> 
                        <router-link to="/mypage/reportlist"><li> | 상담 결과</li></router-link> 
                        <router-link to="/mypage/review"><li> | 나의 후기</li></router-link>
                        </ul>
                    </div>
    
                    <div class="coun-result-part">
                        
                        <div class="result-header">
                            <div >
                                상담 날짜: {{ reportDetail.reservationDateTime }}
                            </div>
                            <div>
                                상담가: {{ reportDetail.counselorName }}
                            </div>
                        </div>
                        <div class="result-content">
                            {{ reportDetail.reservationReview }}
                        </div>
                
                        
                    </div>
                </div>
            </div>
        </div>
    </template>
    
<script>
import { apiInstance } from '@/api';
// import { SquareButton } from "../../components/styled-components/StyledButton";
// import { ReviewCard } from "../components/common/ReviewCard";
// import PageButton from '../../components/common/PageButton.vue';

export default {
    components: {
      
    },
    data() {
        return {
            reservationNo:1,
            reportDetail: {
                reservationDateTime: "test",
                counselorName: "test",
                reservationReview: "test",
            },
        };
    },
    methods: {
        getReportDetail(reservationNo){
           
            const api = apiInstance();
            api({
                method: 'GET',
                url: `/reservations/report/${reservationNo}`,
            })
            .then((res) => {
                console.log(res.data)
             
                this.reportDetail = res.data;
            
                this.handleRezInfo(res.data)
            })
            .catch((e) => {
                console.log(e)
            })
        },
        handleRezInfo(reportDetail) {
           
            reportDetail.reservationDateTime = reportDetail.reservationDateTime.replace("T", " ");
            console.log(reportDetail.reservationDateTime)
            return reportDetail;
        }
        
    },
    beforeRouteEnter(to, from, next) {
        const clickedReservationNo = to.params.clickedReservationNo;
        next(vm => {
        if (clickedReservationNo) {
            vm.getReportDetail(clickedReservationNo);
        }
        });
    },
    created() {
        const clickedReservationNo = this.$route.params.clickedReservationNo;
        console.log("check"+clickedReservationNo)
        if (clickedReservationNo) {
            console.log(clickedReservationNo)
            this.getReportDetail(clickedReservationNo);
            }
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
#my-res-list{
    // justify-content: space-between !important;
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
.coun-result-part{
    height: 552px;
    width: 1021px;
    border-radius: 10px;
    background: #FCFAF1;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
}
.result-header{
    width: 974px;
    height: 83px;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: flex-start;
    color: #000;
    font-size: 20px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}

.result-content {
    width: 974px;
    display: flex;
    padding: 21px 28px;
    justify-content: center;
    align-items: center;
    gap: 10px;
    border-radius: 10px;
    background: #FFFEF9;
    font-size: 16px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    padding: 10px;
    box-sizing: border-box;
    text-align: left;
}

</style>