<template>
    <div class="follow-card">
        <div class="upper-content">
            <img class="card-img" src="{{ follow.profileImg }}">
            <div class="card-info">
                <p id="card-name">{{ follow.name }} <span id="card-score">⭐ {{ follow.email }}</span> </p>
                <p class="card-txt">소개</p>
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