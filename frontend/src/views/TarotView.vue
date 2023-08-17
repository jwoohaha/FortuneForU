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
          
          
          </div>
        
          <div class="hr-wrapper"></div>
      
        </div>
      
        <div class="cards-section">
          <div class="filter">
            <div>
              <ul class="filter-list">
                <li @click="orderByReview()" v-if="criteria=='review'" style="color: #A38BD9">후기순</li>
                <li @click="orderByReview()" v-else style="color: #333">후기순</li>
                <li @click="orderByRating()" v-if="criteria!='review'" style="color: #A38BD9">별점순</li>
                <li @click="orderByRating()" v-else style="color: #333">별점순</li>
              </ul>
            </div>
          </div>
          <div class="counselor-list">
            <div v-for="(counselor, idx) in counselors" :key="idx" @click="moveDetail(counselor.counselorNo)">
              <CounselorCard :counselor="counselor" :colorType="isTarot"></CounselorCard>
            </div>
          </div>
          <!-- 예외처리 -->
          <div class="counselor-list" v-if="emptyPage" style="height: 600px; display: flex; justify-content: center; font-size: 30px;">상담사 정보가 없습니다.</div>
      </div>
      
      <div class="paging-section">
        <PageButton :totalPages="totalPages" :pageType="TARO" @page-changed="handlePageChange"></PageButton>
      </div>
    </div>
  </div>
</template>

<script>
import CounselorCard from '../components/common/CounselorCard.vue';
import PageButton from '../components/common/PageButton.vue';
import { apiInstance } from '@/api/index';

export default {
  components: {
    CounselorCard,
    PageButton,
  },
  data() {
    return {
      emptyPage: false,
      counselors: null,
      criteria: 'review',
      isTarot: true
    };
  },
  methods: {
    getCounselorsByRatings(pageNum) {
      const getCounselorsRequest = apiInstance();
      getCounselorsRequest({
        method: 'GET',
        url: '/counselors/by_ratings',
        params: {
          counselorType: 'TARO',
          page: pageNum
        }
      })
      .then((res) => {
        this.counselors = res.data.content;
        this.totalPages = res.data.totalPages;
      })
      .catch((e) => {
        console.log(e)
      })
    },
    getCounselorsByReviews(pageNum) {
      const getCounselorsRequest = apiInstance();
      getCounselorsRequest({
        method: 'GET',
        url: '/counselors/by_reviews',
        params: {
          counselorType: 'TARO',
          page: pageNum
        }
      })
      .then((res) => {
        this.counselors = res.data.content
        this.totalPages = res.data.totalPages;
      })
      .catch((e) => {
        console.log(e)
      })
    },
    // 페이지 버튼을 눌렀을 때 후기 순, 별점 순에 맞게 해당 페이지 조회
    handlePageChange(pageNum) {
      if(this.criteria == "review") {
        this.getCounselorsByReviews(pageNum)
      } else {
        this.getCounselorsByRatings(pageNum)
      }
    },
    // 버튼 클릭 -> 정렬 순서 변경 처리
    orderByRating() {
      if(this.criteria == "rating") {
        return
      } else {
        this.criteria = "rating"
        this.getCounselorsByRatings(0)
      }
    },
    orderByReview() {
      if(this.criteria == "review") {
        return
      } else {
        this.criteria = "review"
        this.getCounselorsByReviews(0)
      }
    },
    moveDetail(id){
      this.$router.push({
        name: 'reservation',
        query: {
          id: id,
          pageType : 'TARO'
        }
      }
      );
    }
  },

  created() {
    this.getCounselorsByReviews(0);
  }
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
  margin-bottom: 200px;
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
  height: 1.5px;
  background: #000;
  width: 50%;
  padding: 0;
  margin-top: 20px;
}
.search {
  width: 400px;
  height: 40px;
  display: flex;
  justify-content: space-around;
  gap: 17px;
  padding-top: 10px;
  position: relative;
}
input {
  width: 110%;
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
  left: 20px;
  top: 20px;
}
.filter-list{
  width: 10%;
  list-style:none;
  padding-left:0px;
  font-size: 16px;
  font-weight: 400;
  line-height: normal;
  font-style: normal;
  margin-top: 30px;
  margin-bottom: 35px;
  display: flex;
  justify-content: space-between;
}
.counselor-list {
  width: 100%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
.paging-section{
  display: flex;
  justify-content: center;
  margin-top: 40px;
}
</style>