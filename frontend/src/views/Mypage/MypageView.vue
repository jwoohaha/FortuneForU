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
                        <img :src="imgUrl" class="profile-img">
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
                                    <div class="each-label">이름</div>
                                    <div class="each-label">이메일</div>
                                </div>
                                <div class="info-fields">
                                    <div class="each-field">{{ this.name }}</div>
                                    <div class="each-field">{{ this.email }}</div>
                                </div>
                            </div>
                            <div v-if="imgbtnFlag===0">
                                <SquareButton @click="changeFlag(0)" style="width: 350px;">프로필 사진 변경</SquareButton>
                            </div>
                            <div v-else-if="imgbtnFlag===1&&selectedImage==null">
                                <input
                                style="display: none"
                                type="file"
                                @change="onFileChange"
                                ref="imgInput"
                                accept="image/*" />
                                <SquareButton @click="$refs.imgInput.click()">사진 선택</SquareButton>
                            </div>
                            <div v-else>
                                <SquareButton @click="uploadImage" style="width: 350px;">사진 적용하기</SquareButton>
                            </div>
                        </div>
                    </div>
                    <div class="like-part">
                        <div class="sub-title">관심 상담가</div>
                        <div class="like-box">
                            <div class="like-list">
                                <div v-if="!isCounselors">
                                    <router-link to="/tarot" style="font-size: 20px; font-weight: 600; text-decoration: underline;">상담사 찜하러 가기💛</router-link>
                                </div>
                                <div v-for="counselor in counselors" :key="counselor.id">
                                    <FollowCard :follow="counselor" id="followcard"></FollowCard>
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
    import FollowCard from '../../components/common/FollowCard.vue';
    import { apiInstance } from '@/api/index';

    export default {
        components: {
            SquareButton,
            FollowCard,
      },
      data() {
        return {
            email: null,
            name: null,
            counselors: null,
            isCounselors: false,
            selectedImage: null,
            imgUrl: require ('@/assets/profile_default_img.png'),
            imgbtnFlag: 0
        };
      },
      methods: {
        changeFlag(flag){
            if(flag === 0){
                this.imgbtnFlag = 1;
            }
        },  
        getMemberDetail() {
            const getRezInfoRequest = apiInstance();
            getRezInfoRequest({
                method: 'GET',
                url: 'members/details',
            })
            .then((res) => {
                this.email = res.data.email
                this.name = res.data.name
                this.counselors = res.data.followInfoList
                if (this.counselors.length > 0) {
                    this.isCounselors = true
                }
                if (res.data.profileImage != null){
                    this.imgUrl = res.data.profileImage
                }
            })
            .catch((e) => {
                console.log(e)
            })
        },
        onFileChange(event) {
            this.selectedImage = event.target.files[0]
        },
        uploadImage() {
            const updateImgRequest = apiInstance();
            if (this.selectedImage) {
            const formData = new FormData();
            formData.append('image', this.selectedImage, this.selectedImage.name);

            updateImgRequest({
                method: 'PUT',
                url: 'members/profileImage',
                data: formData,
                headers: {
                'Content-Type': 'multipart/form-data',
                },
            })
            .then(() => {
                window.location.reload();
            })
            .catch((e) => {
                console.log(e)
            })
          }
          this.imgbtnFlag = 0;
        }
      },
      created() {
        this.getMemberDetail()
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
        height: 190px;
        background-repeat : no-repeat;
        background-size : cover;
        margin-bottom: 14px;
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
        height: 340px;
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
        height: 180px;
        width: 378px;
        display: inline-flex;
        justify-content: space-between;
        margin-bottom: 50px;
    }
    .info-labels {
        display: flex;
        flex-direction: column;
        justify-content: space-around;
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
        overflow: auto;
        padding: 10px;
    }
    .each-like{
        height: 84px;
        background-color: aliceblue;
    }
    i{
        width: 13.911px;
        height: 25px;
    }

    #followcard{
        width: 100%;
        margin-bottom: 7px;
    }

    </style>
