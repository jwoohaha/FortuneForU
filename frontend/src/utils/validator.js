import jwt_decode from 'jwt-decode'


// JWT Token 만료 여부를 확인
const isJwtTokenValid = (token) => {
    if (token == null) {
        return false;
    }

    const decodedToken = jwt_decode(token);

    const expTime = decodedToken.exp * 1000;    // exp(만료일) 추출
    const curTime = Date.now();

    console.log("exp: " + expTime);
    console.log("cur: " + curTime);

    return expTime > curTime;   // 만료일과 현재 시간을 비교하여 만료 여부 확인
};

export { isJwtTokenValid };