<template>
    <div class="modal">
      <div class="modal-overlay" @click="$emit('close-modal')"></div>
      <modal-content modalWidth="3" modalHeight="1.2">
        <div class="modal-box">
            <div class="modal-title">
              <img src="{{ this.iconPath }}">
              <div>{{ this.title }}</div>
            </div>
            <div class="modal-content">{{ this.content }}</div>
            <div class="modal-buttons">
              <div class="btn" id="left" @click="$emit('close-modal')">이전으로</div>
              <div class="btn" id="right" v-if="rezStatus!=200" @click="movePage(this.movePath)" style="background: #FFCBCB;">{{this.btnTxt}}</div>
              <div class="btn" id="right" v-else @click="movePage(this.movePath)" style="background: #CCFFC7;">{{this.btnTxt}}</div>
            </div>
        </div>
      </modal-content>
    </div>
  </template>
  
  <script>
  import { ModalContent } from "@/components/styled-components/StyledModal.js";

  
  export default {
    components: {
      ModalContent,
      
    },
    props:{
      rezStatus: {
        type: String,
        required: true,
      },
    },
    data() {
      return{
        iconPath: "",
        title: "",
        content: "",
        btnTxt: "",
        movePath: ""
      }
    },
    methods:{
      setContents(){
        if(this.rezStatus == "200"){
          iconPath = "/suc/";
          this.title = "예약완료";
          this.content = "마이페이지에서 예약 내역을 확인해주세요.";
          btnTxt = "마이페이지";
          movePath = ""
        }else{
          this.iconPath = "/fail/";
          this.title = "예약실패";
          this.content = "예약일이 불가능한 날짜입니다.";
          this.btnTxt = "메인페이지"
          movePath = ""
        }
      },
      movePage(movePage){
        if(movePage == "마이페이지") {
          this.$router.push({name: 'mypage'});
        }else if(movePage == "메인페이지"){
          this.$router.push({name: 'mainpage'});
        }
      },
      created(){
        this.setContents()
      }
    }

  };
  </script>
  
  <style lang="scss">
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
    height: 378px;
    width: 100%x;
    border-radius: 20px;
    border: 3px solid #D7D7D7;
    background: #FFF;
    padding: 30px 45px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .modal-title{
    display: flex;
    height: 70px;
    color:  #333;
    text-align: right;
    font-size: 36px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
  }
  .modal-title img{
    width: 70px;
    margin-right: 22px;
  }
  .modal-content{
    color: #333;
    font-size: 36px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    margin: 50px 20px;
  }
  .modal-buttons{
    width: 480px;
    display: flex;
  }
  .btn{
    display: flex;
    width: 227px;
    height: 83px;
    flex-direction: column;
    justify-content: center;
    border-radius: 10px;
    flex-shrink: 0;
    text-align: center;
    font-size: 32px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    border-radius: 10px;
  }
  #left{
    background: #F7F7F7;
  }
  </style>
  
