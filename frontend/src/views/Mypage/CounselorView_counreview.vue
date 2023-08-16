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

                <div class="my-review-part">
                    <img src="@/assets/left_btn.png"  @click="prePage"/>
                    <div class="review-cards-section">
                        <!-- 카드하나 -->
                        <div class="review-card" v-for="(reviewIndex, idx) in displayedIndexes" :key="idx">
                                <div class="coun-info">
                                    <div id="review-title">{{ reviewIndex.memberName }} <span> ⭐ {{ reviewIndex.reservationScore }}</span></div>
                                    <div class="review-txt">{{ reviewIndex.review }}</div>
                                </div>
                            </div>
                    </div>
                    <img src="@/assets/right_btn.png"  @click="nextPage"/>
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
        };
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
        getMyReviews(){
            this.api.get('/reservations/co_reviews')
            .then((response) => {

                response.data.forEach(element => {
                    console.log(element);
                    this.reviews.push(element);
                });

            })
            .catch((error) =>{
                console.log(error)
            })
        },
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
.my-review-part{
    height: 552px;
    width: 1021px;
    border-radius: 10px;
    background: #FCFAF1;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.30);
    display: flex;
    justify-content: space-around;
    align-items: center;
    margin-left: 70px;
    margin-top: 36px;
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
.coun-info {
    color: #333;
    text-align: center;
    font-size: 18px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    text-align: left;
}
#review-title{
    margin: 5px 10px 5px 20px;
}
#review-title span{
    margin-left: 10px;
}
.review-txt {
    color: #333;
    font-size: 16px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    margin: 10px 20px 10px 20px;
    height: 65%;
    overflow:auto;
}
</style>