<template>
    <div class="review-card">
        <div class="upper-content">
            <img class="card-img" src="https://a-static.besthdwallpaper.com/huh-yunjin-le-sserafim-kpop-girls-group-wallpaper-2048x1536-99987_26.jpg">
            <div class="card-info">
                <p id="card-name">{{ counselor.name }} <span id="card-score">⭐ {{ counselor.rating }}</span> </p>
                <p class="card-txt">Unforgiven, I'm a villain, I'm a-Unforgiven, 난 그 길을 걸어 Unforgiven, I'm a villain</p>
            </div>
        </div>
    </div>
</template>

<script>

export default {
  props: {
    counselor: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style lang="scss" scoped>
.review-card {
    width: 439px;
    height: 168px;
    flex-shrink: 0;
    border-radius: 10px;
    border: 1px solid #D9D9D9;
    background: #FFF;
    justify-content: space-between;
    padding: 22px 14px;
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
.card-txt {
    width: 100%;
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
</style>