<template>
  <div class="modal">
    <div class="modal-overlay"></div>
    <modal-content modalWidth="3" modalHeight="1.2">
      <div class="modal-box">
          <div class="modal-title">
            <img :src="imgPath" alt="" style="height: 35px; width: 35px; margin-right: 15px;">
            <div>{{ this.title }}</div>
          </div>
          <div class="modal-content">{{ this.content }}</div>
          <div class="modal-buttons">
            <div class="btn" id="left" @click="movePage(this.pagePath[this.prePage])">이전으로</div>
            <div class="btn" id="right" @click="movePage('mypage')" v-if="rightBtn">마이페이지</div>
          </div>
      </div>
    </modal-content>
  </div>
</template>

<script>
import { ModalContent } from "@/components/styled-components/StyledModal.js";


export default {
  components: {
    ModalContent
  },
  props: {
    reservationStatus: {
      type: String,
      required: true,
    },
    prePage:{
      type: String,
      required: true
    }
  },
  data(){
    return {
      title: "",
      content: "예약이 불가능합니다.\n예약일을 다시 확인해주세요.",
      imgPath: "",
      rightBtn: true,
      pagePath: {'SAJU': 'saju', 'TARO': 'tarot'}
    }
  },
  created(){
    this.setContents()
  },
  methods:{
    setContents(){

      if(this.reservationStatus == "200"){
        this.title = "예약완료"
        this.imgPath = require("@/assets/success_icon.png")
        this.content = "마이페이지에서 예약 내역을 확인해주세요. "
        this.rightBtn = true

      }else{
        this.title = "예약실패"
        this.imgPath = require("@/assets/fail_icon.png")
        this.content = this.reservationStatus
        this.rightBtn = false
      }
    },
    movePage(path){
      this.$router.push({name: path })
      this.$emit('close-modal')
    }
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
  height: 300px;
  width: 100%;
  border-radius: 20px;
  border: 3px solid #D7D7D7;
  background: #FFF;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 40px 45px;
  box-sizing: border-box;
}
.modal-title{
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
  margin: 45px 25px;
  font-size: 17px;
}
.modal-buttons{
  width: 100%;
  height: 63px;
  display: flex;
  justify-content: right;
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
</style>