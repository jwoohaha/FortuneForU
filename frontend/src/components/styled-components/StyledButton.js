import styled from 'vue3-styled-components';

const btnProps = { isTarot: Boolean };
const timebtnProps = { isClicked: Boolean };

export const RoundButton = styled('button', btnProps)`
  display: inline-flex;
  height: 40px;
  padding: 7px 23px;
  justify-content: center;
  align-items: center;
  gap: 10px;
  border-radius: 100px;
  background: ${props => props.isTarot ? '#BFAEE5' : '#F6B5C6'};
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
  color: #FFF;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  border-color: transparent;
  box-sizing: border-box;
  white-space: nowrap;
`;

export const SquareButton = styled('button', btnProps)`
  display: inline-flex;
  height: 40px;
  padding: 7px 23px;
  justify-content: center;
  align-items: center;
  gap: 10px;
  border-radius: 5px;
  background: ${props => props.isTarot ? '#BFAEE5' : '#F6B5C6'};
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
  color: #FFF;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  border-color: transparent;
  box-sizing: border-box;
  white-space: nowrap;
`;

export const TimeButton = styled('div', timebtnProps)`
  display: flex;
  width: 113px;
  height: 58px;
  padding: 7px 23px;
  justify-content: center;
  align-items: center;
  gap: 10px;
  flex-shrink: 0;
  border-radius: 5px;
  border: 2px solid #9C7AE7;
  background: ${props => props.isClicked ? '#9C7AE7' : '#FFF'};
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
  color: ${props => props.isClicked ? '#FFF' : '#9C7AE7'};
  font-family: Inter;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  box-sizing: border-box;
  white-space: nowrap;
`;
