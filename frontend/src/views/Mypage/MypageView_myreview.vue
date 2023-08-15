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
    
                    <div class="my-review-part">
                        <i class="bi bi-chevron-compact-left" @click="prePage"></i>
                        
                        <div class="review-cards-section">
                            <!-- 카드하나 -->
                            <div class="review-card" v-for="(reviewIndex, idx) in displayedIndexes" :key="idx">
                                <img class="card-img" src="{{ reviewIndex.profileImage }}">
                                <div class="coun-info">
                                    <div>{{ reviewIndex.counselorName }} ⭐ {{ reviewIndex.reservationScore }}</div>
                                    <div class="review-txt">{{ reviewIndex.review }}</div>
                                </div>
                                <div @click="removeReview(reviewIndex.reservationNo)">✖</div>
                            </div>
                        </div>

                        <i class="bi bi-chevron-compact-right" @click="nextPage"></i>
                    </div>
                </div>
            </div>
        </div>
    </template>
    
<script>
import { apiInstance } from '@/api';


export default {
    components: {
    },
    data() {
        return {
            currentPage : 1,
            itemsPerPage : 6,
            reviews: [],
        }
    },
    computed: {
        displayedIndexes() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.reviews.slice(startIndex, endIndex);
        },
        totalPages() {
            return Math.ceil(this.reviews.length / this.itemsPerPage);
        }
    },
    setup(){
        const api = apiInstance();

        return {
            api
        }
    },
    methods:{
        getMyReviews(){
            this.api.get('/reservations/reviews')
            .then((response) => {
                console.log(response);
                console.log(response.data);

                response.data.forEach(element => {
                    console.log(element);
                    this.reviews.push(element);
                });

            })
            .catch((error) =>{
                console.log(error)
            })
        },
        prePage(){
            if (this.currentPage > 1) {
                this.currentPage--;
            }
        },
        nextPage(){
            if (this.currentPage < this.totalPages){
                this.currentPage++;
            }
        },
        removeReview(reservationNo){
            this.api.patch(`/reservations/${ reservationNo }`)
            .then((response) =>{
                console.log(response)
                if(response.status == 200){
                    alert('해당 후기를 삭제하였습니다')
                    window.location.reload();
                }else{
                    console.log('삭제 실패 status : ', response.status);
                }
            })
            .catch((error) =>{
                console.log(error)
            })
        }
    },
    created(){
        this.getMyReviews();
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
.my-review-part{
    height: 552px;
    width: 1021px;
    border-radius: 10px;
    background: #FCFAF1;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.30);
    display: flex;
    justify-content: space-around;
    align-items: center;
}
.review-cards-section {
    width: 743px;
    height: 500px;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}
.review-card {
    width: 344px;
    height: 140px;
    border-radius: 10px;
    border: 1px solid #D7D7D7;
    background: #FFF;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
    display: flex;
    padding: 10px;
    margin-bottom: 10px;
}
.card-img {
    width: 70px;
    height: 70px;
    border-radius: 70px;
    border: 1px solid #F7F7F7;
    background: lightgray 50% / cover no-repeat;
    margin-right: 21px;
}
.coun-info {
    color: #333;
    text-align: center;
    font-size: 16px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    text-align: left;
    margin-top: 10px;
}
.review-txt {
    overflow: hidden;
    color: #333;
    text-overflow: ellipsis;
    font-size: 12px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    margin-top: 5px;
}
</style>