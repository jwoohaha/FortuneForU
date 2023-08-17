<template>
  <div class="input-field-container">
    <textarea class="text-field" :placeholder="placeholder" :value="value" @input="onInput" :readonly="isReadonly"/>
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
.text-field-container{
  display: flex;
  height: 100%;
  justify-content: center;
  align-items: center;
}

.text-field{
  display: flex;
  width: 90%;
  height: 200px;
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
  padding: 5px 10px;
  resize: none;
}
textarea::placeholder{
  color:#999999;
}

</style>