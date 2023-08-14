<template>
  <div id="counselor-form-header-container">
    <div class="counselor-form-top-header">
      <div class="counselor-form-left-header">
        <div class="counselor-form-title">
          상담가 신청 목록
        </div>
      </div>
    </div>
    <div class="hr-wrapper"></div>
    <div style="display: flex;">
      <button :style="{color: buttonTextColor['']}" class="counselor-form-paging" value="전체" @click="onFilteringButtonClick('')">전체</button>
      <button :style="{color: buttonTextColor['WAITING']}" class="counselor-form-paging" value="대기중" @click="onFilteringButtonClick('WAITING')">대기중</button>
      <button :style="{color: buttonTextColor['PASS']}" class="counselor-form-paging" value="승인" @click="onFilteringButtonClick('PASS')">승인</button>
      <button :style="{color: buttonTextColor['REJECT']}" class="counselor-form-paging" value="반려" @click="onFilteringButtonClick('REJECT')">반려</button>
    </div>
    <div class="hr-wrapper"></div>
  </div>
  <div id="counselor-form-list-container">
    <table id="counselor-form-list-table">
      <tr>
        <th class="counselor-form-list-th" style="width: 30%;">이름</th>
        <th class="counselor-form-list-th" style="width: 55%;">신청일시</th>
        <th class="counselor-form-list-th" style="width: 15%;">상태</th>
      </tr>
      <tr>
        <td><div class="hr-wrapper"></div></td>
        <td><div class="hr-wrapper"></div></td>
        <td><div class="hr-wrapper"></div></td>
      </tr>
      <tr v-for="form in counselorForms" :key="form.no">
          <td style="cursor: pointer;" class="counselor-form-list-td clickable" @click="viewCounselorForm(form.no)"><div>{{ form.name }}</div></td>
          <td class="counselor-form-list-td"><div>{{ form.created }}</div></td>
          <td class="counselor-form-list-td">
            <status-box :statusCode="statusCode[form.status]"></status-box>
          </td>
      </tr>
    </table>
    <div style="display:flex; align-items: center; justify-content: center; margin-top: 30px;">
      <page-button :totalPages="this.totalPages" @page-changed="onPageChange"></page-button>
    </div>
  </div>
</template>

<script>
import StatusBox from '@/components/admin/StatusBox.vue';
import { apiInstance } from '@/api/index.js';
import PageButton from '@/components/common/PageButton.vue';

export default {
  components: {
    StatusBox,
    PageButton
},
  setup() {
    const api = apiInstance();

    return {
      api,
    }
  },
  data() {
    return {
      counselorForms: [],
      statusCode: {
        WAITING: { label: '대기중', color: '#FFC700'},
        PASS: { label: '승인', color: '#00CA45'},
        REJECT: { label: '반려', color: '#FF008A'},
      },
      buttonTextColor: {
        "" : "#9C7AE7",
        "WAITING" : "#333333",
        "PASS" : "#333333",
        "REJECT" : "#333333"
      },
      totalPages: 0,
      filter: "",
      pageNum: 0,
      pageSize: 6
    }
  },
  created() {
    this.getCounselorForms(this.filter, this.pageNo, this.pageSize);
  },
  methods: {
    viewCounselorForm(no) {
      this.$router.push({
        name: 'counselor-form-view',
        params: { formNo: no }
      })
    },
    async getCounselorForms(filter, pageNum, pageSize) {
      
      await this.api.get(`/admin/counselor-forms?filter=${filter}&page=${pageNum}&size=${pageSize}`)
        .then((response) => {
            this.counselorForms = response.data.content;
            this.totalPages = response.data.totalElements / pageSize;
            console.log(response.data);
        })
        .catch((error) => {
          console.log('상담사 목록을 불러오지 못했습니다.\n' + error);
        })
    },
    onPageChange(value) {
      this.pageNum = value;
      this.getCounselorForms(this.filter, this.pageNum, this.pageSize);
    },
    onFilteringButtonClick(filter) {
      this.buttonTextColor[''] = "#333333";
      this.buttonTextColor['WAITING'] = "#333333";
      this.buttonTextColor['PASS'] = "#333333";
      this.buttonTextColor['REJECT'] = "#333333";
      this.buttonTextColor[filter] = "#9C7AE7";
      this.getCounselorForms(filter, this.pageNum, this.pageSize);
    }
  }
}
</script>

<style lang="scss" scoped>
#counselor-form-header-container{
  width: 50%;
  margin-left: auto;
  margin-right: auto;
  margin-top: 120px;
}

.counselor-form-top-header {
  display: flex;
  justify-content: space-between;
}
.counselor-form-left-header {
  width: 44%;
  height: 49px;
}

.counselor-form-title {
  white-space : nowrap;
  color: #333;
  font-size: 30px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  text-align: left;
}

.hr-wrapper {
  border:#333333 0.5px solid;
  height: 0px;
  width: 100%;
  padding: 0;
  margin-top: 10px;
  margin-bottom: 10px;
}

#counselor-form-list-container {
  width: 50%;
  margin-left: auto;
  margin-right: auto;
}

#counselor-form-list-content {
  width: 75%;
  margin-left: auto;
  margin-right: auto;
  border: solid 2px #E5E9E9;
  border-radius: 10px;
  padding: 40px 20px;
  margin-bottom: 100px;
}

#counselor-form-list-table{
  width: 100%;
  height: auto;
}

.counselor-form-list-th {
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
}

.counselor-form-list-td{
  padding-top: 10px;
  padding-bottom: 10px;
  vertical-align: middle;
}

.counselor-form-paging{
  margin: 2px 20px;
  background-color: #FFFFFF;
  border: none;
  font-family: Noto Sans KR;
  font-size: 16px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  cursor: pointer;
}
</style>