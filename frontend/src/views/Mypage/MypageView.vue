<template>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">  
    <div class="mypage">
        <div class="empty-box"></div>
        <div class="mypage-area">
            <div class="mypage-header">
                <div>마이페이지</div>
                <div class="header-line"></div>
            </div>

            <div class="mypage-contents">
                <div class="profile-nav">
                    <div class="profile-img"></div>
                    <SquareButton>프로필 사진 변경</SquareButton>
                    <ul class="nav-menu">
                        <router-link to="/mypage"><li> | 개인 정보 수정</li></router-link>
                        <router-link to="/mypage/reservationlist"><li> | 나의 예약 목록</li></router-link> 
                        <router-link to="/mypage/reportlist"><li> | 상담 결과</li></router-link> 
                        <router-link to="/mypage/review"><li> | 나의 후기</li></router-link>
                    </ul>
                </div>
                <div class="userinfo-part">
                    <div class="sub-title">회원 정보</div>
                    <div class="info-box">
                        <div class="up-info">
                            <div class="info-labels">
                                <div class="each-label">이메일</div>
                                <div class="each-label">이름</div>
                                <div class="each-label">닉네임</div>
                                <div class="each-label">생년월일</div>
                            </div>
                            <div class="info-fields">
                                <div class="each-field">abc@gmail.com</div>
                                <div class="each-field">장원영</div>
                                <div class="each-field">원영이</div>
                                <div class="each-field">2000.10.10</div>
                                <!-- <div class="each-field"></div> -->
                            </div>
                        </div>
                        <div class="down-info">
                            <div class="each-label">성별</div>
                            <div class="each-field" id="select-field">
                                <label>
                                    <input type="radio" name="male" value="male" />
                                    <span>남성</span>
                                </label>
                                <label>
                                    <input type="radio" name="female" value="female" />
                                    <span>여성</span>
                                </label>
                            </div>
                        </div>
                    </div>

                    <SquareButton id="info-edit-btn">회원 정보 수정</SquareButton>

                </div>
                <div class="like-part">
                    <div class="sub-title">관심 상담가</div>
                    <div class="like-box">
                        <i class="bi bi-chevron-compact-left"></i>
                        <div class="like-list">
                            <div v-for="counselor in counselors" :key="counselor.id">
                                <ReviewCard :review="counselor" id="reviewcard"></ReviewCard>
                            </div>
                        </div>

                        <i class="bi bi-chevron-compact-right"></i>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import { SquareButton } from "../../components/styled-components/StyledButton";
import ReviewCard from '../../components/common/ReviewCard.vue';

export default {
    components: {
        SquareButton,
        ReviewCard,
  },
  data() {
    return {
      counselors: [
        { id: 1, name: 'John Doe', rating: 4.5, reviews: 20 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
      ],
    };
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
  width: 35%;
  padding: 0;
  margin-top: 10px;
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
.userinfo-part{
    height: 575px;
    width: 446px;
}
.sub-title {
    color: #333;
    font-size: 20px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    text-align: left;
    margin-bottom: 20px;
}
.info-box {
    width: 446px;
    height: 424px;
    border-radius: 10px;
    background: #FCFAF1;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-bottom: 52px;
}
.up-info {
    height: 295px;
    width: 378px;
    display: inline-flex;
    justify-content: space-between;
}
.info-labels {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}
.info-fields {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}
.info-fields {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}
.each-label {
    height: 47px;
    color:  #333;
    font-size: 17px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    text-align: left;
    display: flex;
    align-items: center;
    margin-top: 15px;
}
.each-field {
    height: 47px;
    width: 280px;
    display: flex;
    width: 280px;
    padding: 13px 41px;
    justify-content: center;
    align-items: center;
    gap: 10px;
    border-radius: 10px;
    background: #FFF;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
    color:  #333;
    font-size: 17px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    box-sizing: border-box;
    margin-top: 15px;
}
.down-info{
    width: 378px;
    display: inline-flex;
    justify-content: space-between;
}
.down-info span{
    margin-left: 18px;
    margin-right: 10px;
}
#info-edit-btn{
    height: 50px;
    width: 446px;
}
.like-box{
    width: 446px;
    height: 526px;
    border-radius: 10px;
    background: #FCFAF1;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
    display: flex;
    justify-content: space-evenly;
    align-items: center;
}
.like-list{
    height: 480px;
    width: 344px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}
.each-like{
    height: 84px;
    background-color: aliceblue;
}
i{
    width: 13.911px;
    height: 25px;
}

#reviewcard{
    // width: 344px;
    width: 100%;
    margin-bottom: 5px;
}

</style>