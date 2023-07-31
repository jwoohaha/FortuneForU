<template>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <div class="tarot">
    <div class="empty-box"></div>
    <div class="reservation-contents">

      <div class="res-header">
        <div class="res-title">
          타로 상담 예약
          <span class="res-subtitle">
            원하는 상담사를 선택한 후 예약하세요.
          </span>
        </div>
        <div class="hr-wrapper">
          <hr/>
        </div>
      </div>
        
        <!-- <div class="search">
          <div class="input-box">
            <input type="text">
          </div>
          <span class="material-symbols-outlined">search</span>
        </div>
        <button>검색</button> -->
      
      <div class="cards-section">
        <div class="filter-list">
            <ul>
              <li>이름순</li>
              <li>후기순</li>
              <li>별점순</li>
            </ul>
        </div>
        <div class="counselor-list">
          <div v-for="counselor in counselors" :key="counselor.id">
            <router-link to="/reservation"><CounselorCard :counselor="counselor"></CounselorCard></router-link> 
          </div>
        </div>
      </div>
    
    </div>
  </div>
</template>

<script>
import CounselorCard from '../components/common/CounselorCard.vue';

export default {
  components: {
    CounselorCard,
  },
  data() {
    return {
      counselors: [
        { id: 1, name: 'John Doe', rating: 4.5, reviews: 20 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
        { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
      ],
    };
  },
 };
</script>

<style lang="scss">

.tarot {
  padding-left: 15%;
  padding-right: 15%;
}
.empty-box {
  height: 92px;
}
.reservation-contents {
  margin: 0 auto;
  padding-top: 82px;
  display: flex;
  flex-direction: column;
}
.res-header {
  width: 70%;
  white-space : nowrap;
  display: flex;
  flex-direction: column;
  align-items: baseline;
}
.res-title {
  color: var(--title-text);
  font-size: 30px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  text-align: left;
  display: flex;
  flex-direction: row;
  align-items: baseline;
  display: inline-block;
}
.res-subtitle {
  color: var(--title-text);
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  margin-left: 2%;
  display: inline-block;
}
.hr-wrapper {
  display: flex;
  align-items: center;
}
.res-header hr {
    flex: 1;
    width: 905px;
    height: 2px;
    border: 0px;
    background-color: #666;
    margin-top: 20px;
    margin-bottom: 0;
}
// .search {
//   width: 300px;
//   height: 40px;
//   margin: 10px auto;
// }
// input {
//   width: 100%;
//   height: 100%;
//   border-radius: 100px;
//   border: 3px solid #D7D7D7;
//   box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
//   padding: 6px 12px 10px 12px;
//   font-size: 14px;
// }
// span {
//   width: 17px;
//   top: 10px;
//   left: 10px;
//   margin: 0;
//   color: #ccc;
//   display: flex;
//   justify-content: space-between;
// }
.filter-list {
  width: 70%;
  height: 70px;
  // margin-left: 283px;
  margin-right: 292px;
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
}
.counselor-list {
  display: flex;
  justify-content: space-between;
  //flex-wrap: wrap;
}
</style>