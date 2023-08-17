<template>
    <div class="counselor-card">
        <div class="upper-content">
            <img class="card-img" :src="currentImgUrl">
            
            <div class="card-info">
                <p id="card-name">{{ counselor.name }} <span id="card-score">⭐ {{ counselor.ratingAvg }}</span> </p>
                <p id="review-cnt"><span>{{ counselor.reviewCnt }}</span>개의 후기</p>
                <p id="career">경력 : {{ counselor.career }}</p>
            </div>
        </div>
        <hr>
        <p class="card-txt">{{ counselor.intro }}</p>
        <div class="btn">
            <RoundButton isTarot v-if="colorType">예약</RoundButton>
            <RoundButton  v-else style="background-color: '#F6B5C6';">예약</RoundButton>
        </div>
    </div>
</template>

<script>
import { RoundButton } from "../styled-components/StyledButton";

export default {
  components: {
    RoundButton,
  },
  props: {
    counselor: {
      type: Object,
      required: true,
    },
    colorType: {
        type: Boolean,
        required: true
    } 
  },
  data() {
    return {
        defaultImgUrl: require ('@/assets/profile_default_img.png'),
        imgLoadError: false,
    }
  },
  methods: {
    handleImgError() {
      this.imgLoadError = true;
    }
  },
  computed: {
    currentImgUrl() {
        return this.imgLoadError ? this.defaultImgUrl : (this.counselor.profileImg || this.defaultImgUrl);
    }
  }
};
</script>

<style lang="scss" scoped>
.counselor-card {
    width: 350px;
    height: 410px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 10px;
    border: 2px solid #D7D7D7;
    background: #FFF;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
    margin-bottom: 20px;
    padding: 10px 30px;
    box-sizing: border-box;
}
.upper-content {
    display: flex;
    width: 100%;
}
.card-img {
    width: 70px;
    height: 70px;
    object-fit: fill;
    border: 1px solid #F7F7F7;
    border-radius: 40px;
    background: lightgray 50% / cover no-repeat;
    margin-right: 21px;
}
hr {
    height: 2px;
    width : 302px;
    background: #D7D7D7;
    margin-top: 30px;
    margin-bottom: 30px;
    border: 0px;
}
.card-txt {
    height: 120px;
    width: 95%;
    overflow: hidden;
    color: #333;
    text-overflow: ellipsis;
    font-size: 16px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    text-align: start;
}
#card-name { 
    color: #333;
    text-align: center;
    font-size: 20px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    text-align: left;
    margin-bottom: 7px;
}
#card-score {
    color: #333;
    text-align: center;
    font-size: 12px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    text-align: center;
    margin-left: 5px;
}
#review-cnt{
    color: #333;
    text-align: center;
    font-size: 12px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    text-align: left;
    margin-top: 9px;
    margin-bottom: 9px;
}
#review-cnt span{
    color: #9C7AE7;
    text-decoration: underline;
}
#career {
    color:  #666;
    font-size: 12px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    text-align: left;
}
.btn {
    display: flex;
    justify-content: center;
    margin-top: 25px;
}
</style>