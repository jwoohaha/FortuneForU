<template>
  <div class="modal">
    <div class="modal-overlay" @click="$emit('close-modal')"></div>
    <modal-content modalWidth="3" modalHeight="1.2">
      <login-content></login-content>
    </modal-content>
  </div>
</template>

<script>
import { ModalContent } from "@/components/styled-components/StyledModal.js";
import LoginContent from "@/components/login/LoginContent.vue";

export default {
  components: {
    ModalContent,
    LoginContent,
  },
};
</script>

<style lang="scss">
.modal,
.modal-overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
}

.modal-overlay {
  opacity: 0.5;
  background-color: #000000;
}
</style>
