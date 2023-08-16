<template>
  <div class="select-field-container">
    <div class="select-field-content">
      <select class="select-field" :value="value" @change="onChange" :disabled="isReadonly">
        <option v-for="op in options" :value="op" :key="op">{{ (op == 'none') ? label + '을 선택해주세요' : op }}</option>
      </select>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    index: Number,
    value: String,
    label: String,
    options: Array,
    isReadonly: Boolean
  },
  methods: {
    onChange(event) {
      if (this.index == null) {
        this.$emit('change-event', event.target.value);
      }
      this.$emit('change-event', event.target.value, this.index);
    }
  }
}
</script>

<style lang="scss">
.select-field-container{
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
}

.select-field-content{
  width: 93.5%;
  background-color: #F4F4F4;
  border: none;
  border-radius: 5px;
}

.select-field{
  width: 97%;
  height: 60%;
  justify-content: center;
  align-items: center;
  background-color: #F4F4F4;
  color: var(--title-text);
  border: none;
  box-shadow: none;
  font-family: Noto Sans KR;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  padding: 8px 0px;
}
select::placeholder{
  color:#999999;
}
</style>