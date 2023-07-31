<template>
    <div class="counselor-card">
        <div class="upper-content">
            <img class="card-img" src="src/assets/dummy_counselor_img.jpg">
            <div class="card-info">
                <p id="card-name">{{ counselor.name }} <span id="card-score">⭐ {{ counselor.rating }}</span> </p>
                <p id="review-cnt"><span>{{ counselor.reviews }}</span>개의 후기</p>
                <p id="possible-time">상담 가능 시간 <br/> 13:00 - 17:00</p>
            </div>
        </div>
        <hr>
        <p class="card-txt">Unforgiven, I'm a villain, I'm a-Unforgiven, 난 그 길을 걸어Unforgiven, I'm a villain, I'm a-새 시대로 기억될 unforgivenYeah, what you want?불편함이 깃든 face, wanna shut me up
사냥감을 거듭해 찾는 워리어S너의 개임에 난 문제아 such a freak 골칫거리</p>
        <div class="btn">
            <ResButton></ResButton>
        </div>
    </div>
</template>

<script>
import ResButton from './ResButton.vue';

export default {
  components: {
    ResButton,
  },
  props: {
    counselor: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style lang="scss">
.counselor-card {
    width: 375px;
    height: 428px;
    border-radius: 10px;
    border: 2px solid #D7D7D7;
    background: #FFF;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
    //margin: 39px 36px 39px 37px;
    //margin-top: 304px;
    justify-content: space-between;
    padding: 39px 37px;
    box-sizing: border-box;
}
.upper-content {
    display: flex;
}
.card-img {
    width: 70px;
    height: 70px;
    border-radius: 70px;
    border: 1px solid var(--bg-gray, #F7F7F7);
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
    width: 302px;
    height: 138px;
    overflow: hidden;
    color: var(--title-text);
    text-overflow: ellipsis;
    font-size: 16px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    text-align: start;
}
#card-name { 
    color: var(--title-text);
    text-align: center;
    font-size: 20px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    text-align: left;
    margin-bottom: 7px;
}
#card-score {
    color: var(--title-text);
    text-align: center;
    font-size: 12px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    text-align: center;
}
#review-cnt{
    color: var(--title-text);
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
    color: var(--color-saju);
    text-decoration: underline;
}
#possible-time {
    color: var(--body-text, #666);
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