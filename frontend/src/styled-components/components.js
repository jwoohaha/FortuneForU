import styled from "vue3-styled-components";

const windowWidth = window.innerWidth;
const windowHeight = window.innerHeight;

const modalProps = {
  modalWidth: String,
  modalHeight: String
};

export const ModalContent = styled("div", modalProps)`
  width: ${(props) => windowWidth / props.modalWidth}px;
  height: ${(props) => windowHeight / props.modalHeight}px;
  position: relative;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: auto;
  background-color: rgba(255, 255, 255, 0);
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
`;