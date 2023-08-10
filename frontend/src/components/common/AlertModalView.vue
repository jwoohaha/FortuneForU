<template>
    <div class="modal">
      <div class="modal-overlay" @click="$emit('close-modal')"></div>
      <modal-content modalWidth="3" modalHeight="1.2">
        <div class="modal-box">
            안녕
        </div>
      </modal-content>
    </div>
  </template>
  
  <script>
  import { ModalContent } from "@/components/styled-components/StyledModal.js";

  
  export default {
    components: {
      ModalContent,
      
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

  .modal-box{
    height: 100%;
    width: 100%;
    background: #ffffff;
  }
  </style>
  