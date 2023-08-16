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
                    <div class="coun-result-part">
                        <div class="result-header">
                            <div>
                                상담 내용 요약
                            </div>
                        </div>
                        <div class="result-content">
                            <input type="text" v-model="reportDetail.reservationReport" style="width: 100%; height: 350px;">
                        </div>
                        <UpdateButton @click="updateReport(this.reservationNo)">수정</UpdateButton>
                    </div>
                </div>
            </div>
        </div>
    </template>
    
<script>
import { apiInstance } from '@/api';
import { UpdateButton } from '../../components/styled-components/StyledButton'
// import { SquareButton } from "../../components/styled-components/StyledButton";
// import { ReviewCard } from "../components/common/ReviewCard";
// import PageButton from '../../components/common/PageButton.vue';

export default {
    components: {
        UpdateButton,

    },
    data() {
        return {
            reservationNo: null,
            reportDetail: {
                reservationNo: "test",
                reservationReport: "test"
            },
        };
    },
    created() {
    const rezNo = this.$route.params.rezNo;
    console.log(rezNo)

    if (rezNo) {
      this.getReportDetail(rezNo);
        }
    },
    methods: {
        getReportDetail(reservationNo){
           
            const api = apiInstance();
            this.reservationNo = reservationNo
            api({
                method: 'GET',
                url: `/reservations/reports/${reservationNo}`,
            })
            .then((res) => {
                console.log(res.data)
             
                this.reportDetail = res.data;          
            })
            .catch((e) => {
                console.log(e)
            })
        },
        updateReport(reservationNo){
            const confirmUpdate = window.confirm("수정하시겠습니까?")
            
            if (confirmUpdate) {
                const api = apiInstance();
                api({
                method: 'PUT',
                url: `/reservations/counseling_results/${reservationNo}`,
                data: {
                    counselingResult: this.reportDetail.reservationReport
                    }
                })
                .then((res) => {
                    alert("수정되었습니다!")
                    console.log(res.data)
                    location.reload();
                }) 
                .catch((e) => {
                    console.log(e);
                })
            }          
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

.profile-nav {
    height: 588px;
    width: 210px;
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
.nav-menu #coun{
    color: #9C7AE7;
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