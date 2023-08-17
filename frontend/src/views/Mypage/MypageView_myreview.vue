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
                        <div class="profile-img" >
                            <img :src="imgUrl" style="width: 100%; height: 100%; object-fit:cover;">
                        </div>
                        <ul class="nav-menu">
                            <router-link to="/mypage"><li> | 개인 정보 수정</li></router-link>
                        <router-link to="/mypage/reservationlist"><li> | 나의 예약 목록</li></router-link> 
                        <router-link to="/mypage/reportlist"><li> | 상담 결과</li></router-link> 
                        <router-link to="/mypage/review"><li> | 나의 후기</li></router-link>
                        </ul>
                    </div>
    
                    <div class="my-review-part">
                        <img src="@/assets/left_btn.png"  @click="prePage"/>
                        
                        <div class="review-cards-section">
                            <div class="review-card" v-for="(reviewIndex, idx) in displayedIndexes" :key="idx">
                                <img class="card-img" :src="imgUrl">
                                <div class="coun-info">
                                    <div id="review-title">{{ reviewIndex.counselorName }} <span> ⭐ {{ reviewIndex.reservationScore }}</span></div>
                                    <div class="review-txt">{{ reviewIndex.review }}</div>
                                </div>
                                <div @click="removeReview(reviewIndex.reservationNo)">✖</div>
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
            imgUrl: require ('@/assets/profile_default_img.png'),
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
        getMemberInfo() {
            const getRezInfoRequest = apiInstance();
            getRezInfoRequest({
                method: 'GET',
                url: 'members/info',
            })
            .then((res) => {
                if (res.data.profileImage != null){
                    this.imgUrl = res.data.profileImage
                }
            })
            .catch((e) => {
                console.log(e)
            })
        },
        getMyReviews(){
            this.api.get('/reservations/reviews')
            .then((response) => {
                response.data.forEach(element => {
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
                if(response.status == 200){
                    alert('해당 후기를 삭제하였습니다')
                    window.location.reload();
                }else{
                    console.log('삭제 실패 status : ', response.status);
                    alert("실패!")
                }
            })
            .catch((error) =>{
                console.log(error)
            })
        }
    },
    created(){
        this.getMemberInfo();
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
.profile-img{
    width: 180.9px;
    height: 180px;
    border-radius: 180.9px;
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
    justify-content: space-evenly;
    padding: 10px;
    margin-bottom: 10px;
}
.card-img {
    width: 70px;
    height: 70px;
    border-radius: 70px;
    border: 1px solid #F7F7F7;
    background: lightgray 50% / cover no-repeat;
}
.coun-info {
    color: #333;
    text-align: center;
    font-size: 18px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    text-align: left;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: start;
    padding-left: 15px;
}
#review-title{
    width: 220px;
    margin: 5px 10px 5px 0px;
}
#review-title span{
    margin-left: 10px;
}
.review-txt {
    width: 220px;
    color: #333;
    font-size: 16px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    margin-top: 5px;
    height: 65%;
    overflow:auto;
}
</style>