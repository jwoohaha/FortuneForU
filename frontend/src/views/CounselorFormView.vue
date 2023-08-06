<template>
  <div id="counselor-form-header-container">
    <div class="counselor-form-top-header">
      <div class="counselor-form-left-header">
        <div class="counselor-form-title">
          상담사 모집
          <span class="counselor-form-subtitle">
              아래의 항목들을 기재하여 제출해주세요. (*는 필수기재항목)
          </span>
        </div>
      </div>
    </div>
    <div class="hr-wrapper"></div>
  </div>
  <counselor-form :isReadonly="false"></counselor-form>
</template>

<script>
import CounselorForm from '@/components/counselorform/CounselorForm.vue'
export default {
  components: { CounselorForm },

}
</script>

<style lang="scss">
#counselor-form-header-container{
  width: 59%;
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
.counselor-form-subtitle {
  color: #333;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  margin-left: 2%;
  display: inline-block;
}

.hr-wrapper {
  border:#333333 0.5px solid;
  height: 0px;
  width: 65%;
  padding: 0;
  margin-top: 10px;
  margin-bottom: 50px;
}
</style>