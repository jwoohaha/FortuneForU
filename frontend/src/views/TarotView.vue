<template>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">  
  <div class="tarot">
    <div class="empty-box"></div>
      <div class="reservation-contents">

        <div class="reservation-header">
          <div class="top-header">
            <div class="left-header">
              <div class="res-title">
                타로 상담 예약
                  <span class="res-subtitle">
                    원하는 상담사를 선택한 후 예약하세요.
                  </span>
              </div>
            </div>
          
            <div class="search">
              <div class="input-box">
                <input type="text">
              </div>
              <div>
                <i class="bi bi-search"></i>
              </div>
              <RoundButton isTarot>검색</RoundButton>
            </div>
          
          </div>
        
          <div class="hr-wrapper"></div>
      
        </div>
      
        <div class="cards-section">
          <div class="filter">
            <div>
              <ul class="filter-list">
                <li>이름순</li>
                <li>후기순</li>
                <li>별점순</li>
              </ul>
            </div>
          </div>
          <div class="counselor-list">
            <div v-for="counselor in counselors" :key="counselor.id">
              <router-link to="/reservation"><CounselorCard :counselor="counselor"></CounselorCard></router-link> 
            </div>
          </div>
      </div>
      
      <div class="paging-section">
        <PageButton></PageButton>
      </div>
    </div>
  </div>
</template>

<script>
import CounselorCard from '../components/common/CounselorCard.vue';
import PageButton from '../components/common/PageButton.vue';
import { RoundButton } from "../components/styled-components/StyledButton";

export default {
  components: {
    CounselorCard,
    PageButton,
    RoundButton
  },
  data() {
    return {
      counselors: [
        { id: 1, name: 'John Doe', rating: 4.5, reviews: 20 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
        { id: 1, name: 'John Doe', rating: 4.5, reviews: 20 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
      ],
    };
  },
 };
</script>

<style lang="scss" scoped="scss">

.tarot {
  padding-left: 15%;
  padding-right: 15%;
}
.empty-box {
  height: 92px;
}
.reservation-contents {
  // margin: 0 auto;
  padding-top: 82px;
  width: 70vw;
}
.top-header {
  display: flex;
  justify-content: space-between;
}
.left-header {
  width: 44%;
  height: 49px;
}
.res-title {
  white-space : nowrap;
  color: #333;
  font-size: 30px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  text-align: left;
}
.res-subtitle {
  color: #333;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  margin-left: 2%;
  display: inline-block;
}
.hr-wrapper {
  border:#000000 0.5px solid;
  height: 0px;
  width: 65%;
  padding: 0;
  margin-top: 20px;
}
.search {
  width: 393px;
  height: 40px;
  display: flex;
  justify-content: space-between;
  gap: 17px;
  padding-top: 10px;
  position: relative;
}
input {
  width: 300px;
  border-radius: 100px;
  border: 3px solid #D7D7D7;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
  padding: 6px 12px 10px 12px;
  font-size: 14px;
}
.bi-search {
  color: #CCC;
  width: 28px;
  height: 28px;
  position: absolute;
  left: 10px;
  top: 20px;
}
.filter {
  width: 70%;
  height: 70px;
}
ul{
  width: 195px;
  list-style:none;
  padding-left:0px;
  float: left;
  font-size: 16px;
  font-weight: 400;
  line-height: normal;
  font-style: normal;
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between;
}
.cards-section {
  width: 100%;
}
.counselor-list {
  width: inherit;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
.paging-section{
  display: flex;
  justify-content: center;
  margin-top: 40px;
  margin-bottom: 210px;
}
</style>