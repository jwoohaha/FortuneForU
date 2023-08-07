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
      <button class="counselor-form-paging" value="전체">전체</button>
      <button class="counselor-form-paging" value="사주">사주</button>
      <button class="counselor-form-paging" value="타로">타로</button>
      <button class="counselor-form-paging" value="사주/타로">사주/타로</button>
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
      <tr v-for="form in counselorForms" :key="form.id">
          <td style="cursor: pointer;" class="counselor-form-list-td clickable" @click="viewCounselorForm()"><div>{{ form.name }}</div></td>
          <td class="counselor-form-list-td"><div>{{ form.created }}</div></td>
          <td class="counselor-form-list-td">
            <status-box :statusCode="statusCode[form.status]"></status-box>
          </td>
      </tr>
    </table>
  </div>
</template>

<script>
import StatusBox from '@/components/admin/StatusBox.vue'

export default {
  components: {
    StatusBox
  },
  data() {
    return {
      counselorForms: [
          { id: 1, name: '김철수', created: '2023.07.19 16:00', status: 'WAITING'},
          { id: 2, name: '김철수', created: '2023.07.19 16:00', status: 'PASS'},
          { id: 3, name: '김철수', created: '2023.07.19 16:00', status: 'REJECT'},
      ],
      statusCode: {
        WAITING: { label: '대기중', color: '#FFC700'},
        PASS: { label: '승인', color: '#00CA45'},
        REJECT: { label: '반려', color: '#FF008A'},
      },
      buttonTextColor: "#333333"
    }
  },
  methods: {
    viewCounselorForm() {
      this.$router.push({
        name: 'counselor-form-view'
      })
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
}
</style>