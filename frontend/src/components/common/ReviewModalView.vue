<template>
    <div class="modal">
      <div class="modal-overlay"></div>
      <modal-content modalWidth="3" modalHeight="1.2">
        <div class="modal-box">
            <div class="modal-title">
              <img  src="@/assets/review_icon.png" alt="" style="height: 35px; width: 35px; margin-right: 15px;">
              <div>후기 작성</div>
            </div>
            <div class="modal-content">상담에 대한 솔직한 후기를 적어주세요! (150자 이내)</div>
            <div class="modal-inputbox">
                <textarea cols="20" rows="3" maxlength="150" v-model="inputtxt"></textarea>
            </div>            
            <div class="modal-score">
                평점
                <div class="star-rating space-x-4 mx-auto">
                    <input type="radio" id="5-stars" name="rating" value="5" @click="ratings = 5"  />
                    <label for="5-stars" class="star pr-4">★</label>
                    <input type="radio" id="4-stars" name="rating" value="4" @click="ratings = 4" />
                    <label for="4-stars" class="star">★</label>
                    <input type="radio" id="3-stars" name="rating" value="3" @click="ratings = 3" />
                    <label for="3-stars" class="star">★</label>
                    <input type="radio" id="2-stars" name="rating" value="2" @click="ratings = 2" />
                    <label for="2-stars" class="star">★</label>
                    <input type="radio" id="1-star" name="rating" value="1" @click="ratings = 1" />
                    <label for="1-star" class="star">★</label>
                </div>
                <span>{{ ratings }} 점</span>
            </div>
            
            <div class="modal-buttons">
              <div class="btn" id="left" @click="this.$emit('close-modal')">취소</div>
              <div class="btn" id="right" @click="setReview">작성 완료</div>
            </div>
        </div>
      </modal-content>
    </div>
  </template>
  
<script>
import { ModalContent } from "@/components/styled-components/StyledModal.js";
import { apiInstance } from '@/api/index';
  
  
export default {
components: {
    ModalContent
},
props:{
    reservationNo: {
        type: Number,
        required: true
    },
    counselorId: {
      type: Number,
      required: true,
    },
},

data(){
    return {
    ratings: 0,
    inputtxt: ""
    }
},
methods:{
    setReview(){
  
    if(this.inputtxt == "")
        this.inputtxt = "좋은 상담이었습니다.";

    const api = apiInstance();
    
    api({
        method: 'POST',
        url: `reservations/${this.reservationNo}/save_review`,
        data: {
        "counselorId": this.counselorId,
        "reservationReview": this.inputtxt,
        "reservationScore": this.ratings
        },
    })
    .then((result) => {
        console.log(result);
    
        if(result.status == 200){
        alert("후기 작성이 완료되었습니다.\n마이페이지로 이동합니다.")
        }else{
        alert("?")
        }
        
    }).catch((e) => {
        console.log("ERROR:" + e)
        this.reservationStatus = e
    })

    this.$router.push({name: 'mypage' })
    this.$emit('close-modal')
    },
}

};
</script>

<style lang="scss" scoped>
.modal,
.modal-overlay {
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
}
.modal-overlay {
    opacity: 0.5;
    background-color: #000000;
}

.modal-box{
    height: 500px;
    width: 100%;
    border-radius: 20px;
    border: 3px solid #D7D7D7;
    background: #FFF;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 40px 45px;
    box-sizing: border-box;
}
.modal-title{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: start;
    color:  #333;
    text-align: right;
    font-size: 25px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}

.modal-content{
    width: 95%;
    margin: 25px 25px;
    font-size: 18px;
    text-align: left;
}
.modal-score{
    width: 70%;
    padding: 20px 15px;
    font-size: 18px;
    text-align: left;
    box-sizing: border-box;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
}
.modal-inputbox{
    width: 95%;
    height: 40%;
    border-radius: 10px;
    border: 3px solid #D7D7D7;
    background: #FFF;
    display: flex;
    justify-content: center;
    align-items: center;
}
textarea{
    height: 92%;
    width: 95%;
    border: none;
    outline: none;
    resize: none;
    font-size: 22px;
}
.modal-buttons{
    width: 100%;
    height: 63px;
    display: flex;
    justify-content: right;
    align-items: end;
}
.btn{
    display: flex;
    width: 145px;
    height: 50px;
    flex-direction: column;
    justify-content: center;
    flex-shrink: 0;
    color: #333;
    text-align: center;
    font-size: 18px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    border-radius: 10px;
}
#left {
    background: #F7F7F7;
}
#right{
    background: #CCFFC7;
    margin-left: 10px
}

.star-rating {
  display:flex;
  flex-direction: row-reverse;
  font-size:1.5em;
  justify-content:space-around;
  padding:0.2em;
  text-align:center;
  width:5em;
}

.star-rating input {
  display:none;
}

.star-rating label {
  color:#ccc;
  cursor:pointer;
}

.star-rating :checked ~ label {
  color: rgb(254, 230, 94);
}

.star-rating label:hover,
.star-rating label:hover ~ label {
  color:gold;
}
</style>