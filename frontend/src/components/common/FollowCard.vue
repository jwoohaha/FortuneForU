<template>
    <div class="follow-card">
        <div class="upper-content">
            <div id="img-border">
                <img class="card-img" :src="imgUrl" style="width: 100%; height: 100%; object-fit:cover;">                
            </div>
            <div class="card-info">
                <p id="card-name">{{ follow.name }} <span id="card-score">⭐ {{ follow.ratingAvg }}</span> </p>
                <p class="card-txt">{{ follow.intro }}</p>
            </div>
        </div>
    </div>
</template>

<script>

export default {
  props: {
    follow: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
        defaultImgUrlUrl: require ('@/assets/profile_default_img.png'),
    }
  },
  computed: {
    imgUrl() {
        return this.counselor.profileImg || this.defaultImgUrl;
    }
  }
};
</script>

<style lang="scss" scoped>
.follow-card {
    width: 439px;
    height: 168px;
    flex-shrink: 0;
    border-radius: 10px;
    border: 1px solid #D9D9D9;
    background: #FFF;
    justify-content: space-between;
    padding: 15px 10px;
    box-sizing: border-box;
}
.upper-content {
    display: flex;
    height: 100%;
}
#img-border{
    width: 70px;
    height: 70px;
    border-radius: 70px;
    margin-right: 15px;
}
.card-img {
    width: 70px;
    height: 70px;
    border-radius: 70px;
    // border: 0px solid var(--bg-gray, #F7F7F7);
    background: lightgray 50% / cover no-repeat;
}
.card-info{
    width: 72%;
}
.card-txt {
    width: 100%;
    height: 76%;
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
}
</style>