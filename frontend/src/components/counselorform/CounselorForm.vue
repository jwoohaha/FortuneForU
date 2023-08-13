<template>
    <div id="counselor-form-container">
    <form id="counselor-form-content">
      <table id="counselor-form-table">
        <table-row :headerText="'상담분야*'">
          <drop-down :label="'상담분야'" :options="counselorTypeOptions" :value="counselorType" @change-event="counselorTypeChange" :isReadonly="isReadonly"></drop-down>
        </table-row>
        <table-row :headerText="'전문영역1*'">
          <drop-down :label="'전문영역1'" :options="majorOptions" :index="0" :value="major[0]" @change-event="majorChange" :isReadonly="isReadonly"></drop-down>
        </table-row>
        <table-row :headerText="'전문영역2'">
          <drop-down :label="'전문영역2'" :options="majorOptions" :index="1" :value="major[1]" @change-event="majorChange" :isReadonly="isReadonly"></drop-down>
        </table-row>
        <table-row :headerText="'전문영역3'">
          <drop-down :label="'전문영역3'" :options="majorOptions" :index="2" :value="major[2]" @change-event="majorChange" :isReadonly="isReadonly"></drop-down>
        </table-row>
        <table-row :headerText="'경력*'">
          <text-field :placeholder="'어떤 내용이 들어갈까요?\n\n'" :value="career" @input-event="careerInput" :isReadonly="isReadonly"></text-field>
        </table-row>
        <table-row :headerText="'주소지*'">
          <input-field :placeholder="'어떤 내용이 들어갈까요?\n\n'" :value="address" @input-event="addressInput" :isReadonly="isReadonly"></input-field>
        </table-row>
        <table-row :headerText="'전화번호*'">
          <input-field :placeholder="'어떤 내용이 들어갈까요?\n\n'" :value="phone" @input-event="phoneInput" :isReadonly="isReadonly"></input-field>
        </table-row>
        <table-row :headerText="'한줄소개*'">
          <input-field :placeholder="'어떤 내용이 들어갈까요?\n\n'" :value="introduce" @input-event="introduceInput" :isReadonly="isReadonly"></input-field>
        </table-row>
      </table>
    </form>
  </div>
</template>

<script>
import TableRow from '@/components/common/TableRow.vue'
import InputField from '@/components/common/InputField.vue';
import TextField from '@/components/common/TextField.vue';
import DropDown from '@/components/common/DropDown.vue';

export default {
  components: { TableRow, InputField, TextField, DropDown },
  props: {
    isReadonly: Boolean
  },
  data() {
    return {
      counselorType: 'none',
      major: ['none', 'none', 'none'],
      career: '',
      address: '',
      phone: '',
      introduce : '',
      counselorTypeOptions: [
        'none', '사주', '타로', '사주/타로'
      ],
      majorOptions: [
        'none', '가족/건강', '직장/진로', '시험/진학', '연애/결혼', '사업/재물', '사주/신수', '작명/개명', '미래/해몽', '이사/풍수'
      ]
    }
  },
  methods: {
    counselorTypeChange(value) {
      this.counselorType = value;
    },
    majorChange(value, index) {
      this.major[index] = value;
    },
    careerInput(value) {
      this.career = value;
    },
    addressInput(value) {
      this.address = value;
    },
    phoneInput(value) {
      this.phone = value;
    },
    introduceInput(value) {
      this.introduce = value;
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
  padding: 40px 20px;
  margin-bottom: 100px;
}

#counselor-form-table{
  width: 100%;
  height: 60px;
}


</style>