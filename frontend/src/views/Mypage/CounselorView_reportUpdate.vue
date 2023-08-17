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
                            상담 일시: {{ reportDetail.reservationDateTime }}
                            <div class="header-subtxt">
                                <p v-if="!isEditable">상담 내용 요약을 수정하고 싶으시면 아래의 버튼을 클릭해주세요.</p>
                                <p v-else>상담 내용 요약을 수정하고 있습니다. 수정이 완료되면 다시 버튼을 클릭해주세요.</p>
                            </div>
                        </div>
                        <div class="result-content" v-if="!isEditable">
                            {{this.reportTxt}}
                          </div>
                        <textarea v-model="reportTxt" v-else ></textarea>

                        <SquareButton isTarot v-if="isEditable" @click="updateReport(this.reservationNo)">상담 결과서 수정하기</SquareButton>
                        <SquareButton isTarot v-else @click="changeEditable">상담 결과서 수정하기</SquareButton>
                    </div>
                </div>
            </div>
        </div>
    </template>
    
<script>
import { apiInstance } from '@/api';
import { SquareButton } from "../../components/styled-components/StyledButton";

export default {
    components: {
        SquareButton,

    },
    data() {
        return {
            reservationNo: null,
            reportDetail: {
                reservationDateTime: null,
                reservationNo: null,
                reservationReport: null
            },
            reportTxt: null,
            isEditable: false
        };
    },
    created() {
        this.reservationNo = this.$route.params.rezNo;
        console.log(this.reservationNo)

        if (this.reservationNo) {
            this.getReportDetail(this.reservationNo);
        }
    },
    methods: {
        changeEditable(){
            this.isEditable = true;
        },
        getReportDetail(reservationNo){
           
            const api = apiInstance();
            api({
                method: 'GET',
                url: `/reservations/report/${reservationNo}`,
            })
            .then((res) => {
                console.log(res.data)
             
                this.reportDetail = res.data;
                this.reportTxt = this.reportDetail.reservationReport;

                this.handleRezInfo(res.data)
            })
            .catch((e) => {
                console.log(e)
            })
        },
        handleRezInfo(reportDetail) {

            reportDetail.reservationDateTime = reportDetail.reservationDateTime.replace("T", " ");
            reportDetail.reservationDateTime = reportDetail.reservationDateTime.substring(0, 16);
            console.log(reportDetail.reservationDateTime)
            return reportDetail;
        },
        updateReport(num){

            const confirmUpdate = window.confirm("수정하시겠습니까?")
            
            if (confirmUpdate) {
                const api = apiInstance();
                api({
                method: 'PUT',
                url: `/reservations/counseling_results/${num}`,
                data: {
                    counselingResult: this.reportTxt
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

            this.isEditable = false;
        }
        
        
    },
    
}
</script>

<style lang="scss" scoped="scss">
* {
    font-family: 'Noto Sans KR', sans-serif;
}
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
    width: 344px;
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
        width: 100%;
        padding: 0;
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
    padding: 10px;
    box-sizing: border-box;
}
.result-header{
    width: 974px;
    height: 75px;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: flex-start;
    color: #000;
    font-size: 20px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    margin: 0px 20px;
}
.header-subtxt{
    font-size: 13px;
    text-align: left;
    font-weight: 500;
}
.result-content {
    width: 974px;
    height: 70%;
    display: flex;
    padding: 21px 28px;
    justify-content: left;
    align-items: start;
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
    overflow: auto;
}
.coun-result-part textarea{
    width: 974px;
    height: 70%;
    border: solid 0.8px rgba(0, 0, 0, 0.25);
    resize: none;
    padding: 20px;
    box-sizing: border-box;
}

</style>