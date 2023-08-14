<template>
  <div class="input-field-container">
    <input class="input-field" :placeholder="placeholder" :value="value" @input="onInput" :readonly="isReadonly"/>
  </div>
</template>

<script>
export default {
  props: {
    placeholder: String,
    value: String,
    isReadonly: Boolean
  },
  methods: {
    onInput(event) {
      this.$emit('input-event', event.target.value);
    }
  }
}
</script>

<style lang="scss">
.input-field-container{
  display: flex;
  justify-content: center;
  align-items: center;
}

.input-field{
  display: flex;
  width: 90%;
  height: 60%;
  justify-content: center;
  align-items: center;
  background-color: #F4F4F4;
  color: var(--title-text);
  border: none;
  border-radius: 5px;
  box-shadow: none;
  font-family: Noto Sans KR;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  padding: 8px 15px;
}
input::placeholder{
  color:#999999;
}
</style>