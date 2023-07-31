<template>
  <div class="modal">
    <div class="overlay" @click="$emit('close-modal')"></div>
    <modal-content modalWidth="500" modalHeight="550">
      <login-content></login-content>
    </modal-content>
  </div>
</template>

<script>
import { ModalContent } from "@/styled-components/components.js";
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
.overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  right: 0;
}

.overlay {
  opacity: 0.5;
  background-color: #000000;
}
</style>
