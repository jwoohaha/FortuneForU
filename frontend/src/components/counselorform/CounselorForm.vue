<template>
    <div id="counselor-form-container">
    <form id="counselor-form-content">
      <table id="counselor-form-table">
        <table-row :headerText="'상담분야*'">
          <drop-down :label="'상담분야'" :options="counselorTypeOptions" :value="counselorForm.counselorType" @change-event="counselorTypeChange" :isReadonly="isReadonly"></drop-down>
        </table-row>
        <table-row :headerText="'전문영역1*'">
          <drop-down :label="'전문영역1'" :options="majorOptions" :index="0" :value="counselorForm.major[0]" @change-event="majorChange" :isReadonly="isReadonly"></drop-down>
        </table-row>
        <table-row :headerText="'전문영역2'">
          <drop-down :label="'전문영역2'" :options="majorOptions" :index="1" :value="counselorForm.major[1]" @change-event="majorChange" :isReadonly="isReadonly"></drop-down>
        </table-row>
        <table-row :headerText="'전문영역3'">
          <drop-down :label="'전문영역3'" :options="majorOptions" :index="2" :value="counselorForm.major[2]" @change-event="majorChange" :isReadonly="isReadonly"></drop-down>
        </table-row>
        <table-row :headerText="'경력*'">
          <text-field :placeholder="'학력, 자격증, 경력 등을 기재해주세요'" :value="counselorForm.career" @input-event="careerInput" :isReadonly="isReadonly"></text-field>
        </table-row>
        <table-row :headerText="'주소지*'">
          <input-field :placeholder="'주소를 기재해주세요'" :value="counselorForm.address" @input-event="addressInput" :isReadonly="isReadonly"></input-field>
        </table-row>
        <table-row :headerText="'전화번호*'">
          <input-field :placeholder="'연락이 가능한 전화번호를 기재해주세요'" :value="counselorForm.phone" @input-event="phoneInput" :isReadonly="isReadonly"></input-field>
        </table-row>
        <table-row :headerText="'한줄소개*'">
          <input-field :placeholder="'본인을 나타낼 수 있는 문구를 기재해주세요'" :value="counselorForm.intro" @input-event="introduceInput" :isReadonly="isReadonly"></input-field>
        </table-row>
        <table-row :headerText="'승인/반려사유'" v-if="isReadonly">
          <text-field :placeholder="'승인/반려사유를 작성해주세요'" :value="reason" @input-event="reasonInput" :isReadonly="!isReadonly"></text-field>
        </table-row>
      </table>
      <div class="button-container" v-if="isReadonly">
        <button class="button-content confirm" type="button" @click="this.updateStatus('PASS')">승인</button>
        <button class="button-content reject" type="button" @click="this.updateStatus('REJECT')">반려</button>
      </div>
      <div class="button-container" v-else>
        <button class="button-content confirm" type="button" @click="this.submit">제출</button>
        <button class="button-content reject" type="button">취소</button>
      </div>
    </form>
  </div>
</template>

<script>
import TableRow from '@/components/common/TableRow.vue'
import InputField from '@/components/common/InputField.vue';
import TextField from '@/components/common/TextField.vue';
import DropDown from '@/components/common/DropDown.vue';
import { apiInstance } from '@/api';
import router from '@/router';


export default {
  components: { TableRow, InputField, TextField, DropDown },
  props: {
    isReadonly: Boolean
  },
  setup() {
    const api = apiInstance();
    return {
      api
    }
  },
  data() {
    return {
      counselorForm: {
        counselorType: 'none',
        major: ['none', 'none', 'none'],
        career: '',
        address: '',
        phone: '',
        intro : '',
        
      },
      counselorTypeOptions: [
          'none', '사주', '타로', '사주/타로'
      ],
      majorOptions: [
          'none', '가족/건강', '직장/진로', '시험/진학', '연애/결혼', '사업/재물', '사주/신수', '작명/개명', '미래/해몽', '이사/풍수'
      ],
      counselorTypeToEnum: {
          'none': 'NONE',
          '사주': 'SAJU',
          '타로': 'TARO',
          '사주/타로': 'BOTH',
      },
      enumToCounselorType: {
        'SAJU': '사주',
        'TARO': '타로',
        'BOTH': '사주/타로'
      },
      reason : ""
    }
  },
  created() {
    if (this.isReadonly){
      this.getCounselorFormDetails();
    }
  },
  methods: {
    counselorTypeChange(value) {
      this.counselorForm.counselorType = value;
    },
    majorChange(value, index) {
      this.counselorForm.major[index] = value;
    },
    careerInput(value) {
      this.counselorForm.career = value;
    },
    addressInput(value) {
      this.counselorForm.address = value;
    },
    phoneInput(value) {
      this.counselorForm.phone = value;
    },
    introduceInput(value) {
      this.counselorForm.intro = value;
    },
    reasonInput(value) {
      this.reason = value;
    },
    async submit() {
      this.counselorForm.counselorType = this.counselorTypeToEnum[this.counselorForm.counselorType];
      this.counselorForm.major = (this.counselorForm.major[0] + ' ' + 
                                  (this.counselorForm.major[1] == 'none' ? '' : this.counselorForm.major[1]) + ' ' + 
                                  (this.counselorForm.major[2] == 'none' ? '' : this.counselorForm.major[2])).trim();
      console.log(this.counselorForm.counselorType);
      console.log(this.counselorForm.major);
      await this.api.post("/members/submit", this.counselorForm)
              .then(() => {
                alert("정상적으로 등록되었습니다.");
                router.push('/');
              })
              .catch(error => {
                alert("오류가 발생했습니다. 신청 양식을 재등록해주세요.");
                console.log(error)
              });
    },
    async getCounselorFormDetails() {
      await this.api.get(`/admin/counselor-forms/${this.$route.params.formNo}`)
              .then((response) => {
                const form = response.data;
                this.counselorForm = form;
                this.counselorForm.counselorType = this.enumToCounselorType[form.counselorType];
                this.counselorForm.major = form.major.split(' ');
              })
              .catch((error) => {
                console.log(error);
              });
    },
    async updateStatus(status) {
      await this.api.patch(`/admin/counselor-forms/${this.$route.params.formNo}/update`, 
                          { "reason": this.reason, "passState": status})
                    .then(() => {
                      alert("요청을 완료하였습니다.")
                      router.go(-1);
                    })
                    .catch(() => {
                      alert("요청이 실패하였습니다.")
                    })
    }
  } 
}
</script>

<style lang="scss">

#counselor-form-container {
  display: flex;
  width: 70%;
  margin-left: auto;
  margin-right: auto;
}

#counselor-form-content {
  width: 75%;
  margin-left: auto;
  margin-right: auto;
  border: solid 2px #E5E9E9;
  border-radius: 10px;
  padding: 60px 20px;
  margin-bottom: 100px;
}

#counselor-form-table{
  width: 100%;
  height: 60px;
}

.button-container {
  display: flex;
  align-items: center;
  justify-content: center;
}

.button-content {
  border: none;
  border-radius: 5px;
  margin-top: 40px;
  margin-left: 30px;
  margin-right: 30px;
  padding: 13px 30px;
  font-family: Noto Sans KR;
  font-size: 20px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  color: white;
}

.confirm {
    background-color: #00CA45;
  }
  .reject {
    background-color: #FF008A;
  }

</style>