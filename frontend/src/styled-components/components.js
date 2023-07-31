import styled from "vue3-styled-components";

const modalProps = {
  modalWidth: String,
  modalHeight: String,
};

export const ModalContent = styled("div", modalProps)`
  width: ${(props) => props.modalWidth}px;
  height: ${(props) => props.modalHeight}px;
  position: relative;
  display: inline-flex;
  align-items: flex-start;
  margin: auto;
  margin-top: 150px;
  background-color: rgba(255, 255, 255, 0);
`;
