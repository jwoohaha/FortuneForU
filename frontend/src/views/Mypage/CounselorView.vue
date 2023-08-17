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
                    <!-- <div class="profile-img"></div> -->
                    <ul class="nav-menu">
                        <!-- <router-link to="/mypage"><li> | 개인 정보 수정</li></router-link>
                        <router-link to="/mypage/reservationlist"><li> | 나의 예약 목록</li></router-link> 
                        <router-link to="/mypage/counslingresult"><li> | 상담 결과</li></router-link> 
                        <router-link to="/mypage/review"><li> | 나의 후기</li></router-link> -->
                        <!-- <li id="coun"> 상담사 전용</li> -->
                        <router-link to="/counselor"><li id="coun"> | 상담사 정보 수정</li></router-link>
                        <!-- <router-link to="/counselor/timeset"><li id="coun"> | 상담가능시간설정</li></router-link> -->
                        <router-link to="/counselor/counseling"><li id="coun"> | 상담 관리</li></router-link>
                        <router-link to="/counselor/counreview"><li id="coun"> | 후기 관리</li></router-link>
                    </ul>
                </div>
                
                <div class="coun-info-part">
                    
                    <div class="left-part">
                        <div class="each-row">
                            <h3 class="info-label">상담 분야</h3>
                            <div class="radio-btns">
                                <label>
                                    <input type="radio" name="saju" value="SAJU" v-model="radioval" v-if="!isEditable" onclick="return(false);"/>
                                    <input type="radio" name="saju" value="SAJU" v-model="radioval" v-else/>
                                    <span class="radio-label">사주</span>
                                </label>
                                <label>
                                    <input type="radio" name="tarot" value="TARO" v-model="radioval" v-if="!isEditable" onclick="return(false);"/>
                                    <input type="radio" name="tarot" value="TARO" v-model="radioval" v-else/>
                                    <span class="radio-label">타로</span>
                                </label>
                                <label>
                                    <input type="radio" name="sajutarot" value="BOTH" v-model="radioval" v-if="!isEditable" onclick="return(false);"/>
                                    <input type="radio" name="sajutarot" value="BOTH" v-model="radioval" v-else/>
                                    <span class="radio-label">사주/타로</span>
                                </label>
                            </div>
                        </div>
                        <div class="each-row">
                            <h3 class="info-label">전문 영역</h3>
                            <div class="info-edit" v-if="!isEditable">
                                <div id="intro-edit">{{ counselor.major }}</div>
                            </div>
                            <input class="info-edit" v-else v-model="majortxt" @input="majortxt = $event.target.value">
                        </div>
                        <div class="each-row">
                            <h3 class="info-label">매장 주소</h3>
                            <div class="info-edit" v-if="!isEditable">
                                <div id="intro-edit">{{ counselor.address }}</div>
                            </div>
                            <input class="info-edit" v-else v-model="addresstxt" @input="addresstxt = $event.target.value">
                        </div>
                        <div class="each-row">
                            <h3 class="info-label">전화번호</h3>
                            <div class="info-edit" v-if="!isEditable">{{ counselor.phone }}</div>
                            <input class="info-edit" v-else v-model="phonetxt" @input="phonetxt = $event.target.value">
                        </div>
                        <div class="each-row">
                            <h3 class="info-label">한 줄 소개</h3>
                            <div class="info-edit" v-if="!isEditable">
                                <div id="intro-edit">{{ counselor.intro }}</div>
                            </div>
                            <input class="info-edit" v-else v-model="introtxt" @input="introtxt = $event.target.value">
                        </div>
                        <div class="each-row">
                            <h3 class="info-label">상담 가능 시간</h3>
                            <div class="time-setting">
                                <div>시작</div>
                                <div v-if="!isEditable">{{ this.startTime }}</div>
                                <select class="time-control" v-model="startTime" v-else>
                                    <option :key="i" :v-model="`${time.h}:${time.m}`" v-for="(time, i) in options">{{ time.h }}:{{ time.m }}</option>
                                </select>
                                <div>마감</div>
                                <div v-if="!isEditable">{{ this.endTime }}</div>
                                <select class="time-control" v-model="endTime" v-else>
                                    <option :key="i" :v-model="`${time.h}:${time.m}`" v-for="(time, i) in options">{{ time.h }}:{{ time.m }}</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="right-part">
                        <h3 class="info-label">경력 사항</h3>
                        <div class="info-edit" id="career-edit" v-if="!isEditable">{{ counselor.career }}</div>
                        <textarea class="info-edit" v-else v-model="careertxt" maxlength="168"></textarea>
                        <SquareButton id="edit-save-btn" v-on:click="changeEditable">상담사 정보 수정하기</SquareButton>
                    </div>           
                    
                </div>

            </div>

        </div>
    </div>
</template>
    
<script>
import { SquareButton } from "../../components/styled-components/StyledButton";
import { apiInstance } from '@/api/index'

export default {
    components: {
        SquareButton,
    },
    data() {
        return {
            isEditable : false,
            counselorNo: null,
            counselor:{},
            radioval: "",
            majortxt: "",
            addresstxt: "",
            introtxt: "",
            phonetxt: "",
            careertxt: "",
            startTime: "0:00",
            endTime: "18:00",
            options: [
                { h: "00", m: "00" },{ h: "00", m: "30" },{ h: "01", m: "00" },{ h: "01", m: "30" },{ h: "02", m: "00" },{ h: "02", m: "30" },{ h: "03", m: "00" },{ h: "03", m: "30" },
                { h: "04", m: "00" },{ h: "04", m: "30" },{ h: "05", m: "00" },{ h: "05", m: "30" },{ h: "06", m: "00" },{ h: "06", m: "30" },{ h: "07", m: "00" },{ h: "07", m: "30" },
                { h: "08", m: "00" },{ h: "08", m: "30" },{ h: "09", m: "00" },{ h: "09", m: "30" },{ h: "10", m: "00" },{ h: "10", m: "30" },{ h: "11", m: "00" },{ h: "11", m: "30" },
                { h: "12", m: "00" },{ h: "12", m: "30" },{ h: "13", m: "00" },{ h: "13", m: "30" },{ h: "14", m: "00" },{ h: "14", m: "30" },{ h: "15", m: "00" },{ h: "15", m: "30" },
                { h: "16", m: "00" },{ h: "16", m: "30" },{ h: "17", m: "00" },{ h: "17", m: "30" },{ h: "18", m: "00" },{ h: "18", m: "30" },{ h: "19", m: "00" },{ h: "19", m: "30" },
                { h: "20", m: "00" },{ h: "20", m: "30" },{ h: "21", m: "00" },{ h: "21", m: "30" },{ h: "22", m: "00" },{ h: "22", m: "30" },{ h: "23", m: "00" },{ h: "23", m: "30" },   
            ],
        };
    },
    setup(){
        
        const api = apiInstance();

        return {
            api
        };
    },
    methods: {
    async getUserInfo(){

        var result = "";
        await this.api.get('/counselors/info')
            .then((re) => result = re.data)
            .catch((error) => console.log(error));
            
        this.counselor= result;
        this.counselorNo = result.counselorNo;
        this.radioval = this.counselor.counselorType;
        this.majortxt = this.counselor.major;
        this.addresstxt = this.counselor.address;
        this.introtxt = this.counselor.intro;
        this.phonetxt = this.counselor.phone;
        this.careertxt = this.counselor.career;
        this.startTime = this.counselor.startTime;
        this.endTime = this.counselor.endTime;
        
    }, 

    async changeEditable(){
        if(this.isEditable){
            
            const phoneValid = await this.checkPhone();
            if(!phoneValid) return;

            this.isEditable = false;

            await this.editUserInfo();
            await this.getUserInfo();
        }            
        else{
            this.isEditable = true;
            this.majortxt = this.counselor.major;
            this.addresstxt = this.counselor.address;
            this.introtxt = this.counselor.intro;
            this.phonetxt = this.counselor.phone;
        }
    },

    async editUserInfo(){
        await this.api.patch('/counselors/update', 
            {
                counselorType: this.radioval,
                    major: this.majortxt,
                    intro: this.introtxt,
                    career: this.careertxt,
                    address: this.addresstxt,
                    phone: this.phonetxt,
                    startTime: this.startTime,
                    endTime: this.endTime
            })
        .then((re) => {
        }).catch((e) => {
            console.log(e)
        })
    },

    checkPhone(){
        if(this.phonetxt.length == 13){
            const msg = '전화번호 형식(010-1234-1234)을 준수해주세요.';
            if (/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/.test(this.phonetxt)) {
                return true;
            }
            alert(msg);
            return false;
        }else{
            const msg = '전화번호가 올바르지 않습니다.';
            alert(msg);
        }
    },

  },
  created(){
    this.getUserInfo();
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
    
    
.coun-info-part{
    height: 552px;
    width: 1021px;
    border-radius: 10px;
    background: #FCFAF1;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    display: flex;
    justify-content: space-between;
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    padding: 33px;
    box-sizing: border-box;
    margin-left: 70px;
    margin-top: 36px;
}   
.left-part {
    width: 547px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    padding-top: 20px;
} 
.info-label {
    color: #000;
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    text-align: left;
}
.each-row{
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-right: 30px;
}
.radio-btns{
    width: 320px;
    display: flex;
    justify-content: space-around;
}
.radio-label{
    margin-left: 10px;
    margin-right: 10px;
}
.time-setting{
    display: flex;
    width: 320px;
    justify-content: space-around;
}
.info-edit {
    width: 320px;
    height: 50px;
    border-radius: 10px;
    background: #FFF;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    display: flex;
    flex-direction: columns;
    align-items: center;
    justify-content: left;
    padding: 10px 33px;
    box-sizing: border-box;
}
#intro-edit{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
#career-edit{
    overflow:auto;
    text-align: start;
}
.each-row input{
    border: solid 0.8px rgba(0, 0, 0, 0.25);
}
textarea {
    border: solid 0.8px rgba(0, 0, 0, 0.25);
    resize: none;
}
.right-part {
    width: 465px;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    padding-left: 50px;
    box-sizing: border-box;
}
.right-part > .info-edit {
    height: 75%;
    align-items: start;
}
#edit-save-btn{
    width: 80%;
    height: 54px;
    margin-top: 20px;
    margin-left: 5px;
    margin-right: 5px;
}
</style>