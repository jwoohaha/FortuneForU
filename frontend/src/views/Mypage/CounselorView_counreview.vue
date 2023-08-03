<template>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">  
        <div class="mypage">
            <div class="empty-box"></div>
            <div class="mypage-area">
                <div class="mypage-header">
                    <div>마이페이지</div>
                    <div class="header-line"></div>
                </div>
    
                <div class="mypage-contents">
                    <div class="profile-nav">
                        <div class="profile-img"></div>
                        <ul class="nav-menu">
                            <router-link to="/mypage"><li> | 개인 정보 수정</li></router-link>
                            <router-link to="/mypage/reservationlist"><li> | 나의 예약 목록</li></router-link> 
                            <router-link to="/mypage/counslingresult"><li> | 상담 결과</li></router-link> 
                            <router-link to="/mypage/review"><li> | 나의 후기</li></router-link>
                            <li> 상담사 전용</li>
                            <router-link to="/counselor"><li> | 상담사 정보 수정</li></router-link>
                            <router-link to="/counselor/counseling"><li> | 상담 관리</li></router-link>
                            <router-link to="/counselor/review"><li> | 후기 관리</li></router-link>
                        </ul>
                    </div>
                    <div class="userinfo-part">
                        <div class="sub-title">회원 정보</div>
                        <div class="info-box">
                            <div class="up-info">
                                <div class="info-labels">
                                    <div class="each-label">이메일</div>
                                    <div class="each-label">이름</div>
                                    <div class="each-label">닉네임</div>
                                    <div class="each-label">생년월일</div>
                                </div>
                                <div class="info-fields">
                                    <div class="each-field">abc@gmail.com</div>
                                    <div class="each-field">장원영</div>
                                    <div class="each-field">원영이</div>
                                    <div class="each-field">2000.10.10</div>
                                </div>
                            </div>
                            <div class="down-info">
                                <div class="each-label">성별</div>
                                <div class="each-field" id="select-field">
                                    <label>
                                        <input type="radio" name="male" value="male" />
                                        <span>남성</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="female" value="female" />
                                        <span>여성</span>
                                    </label>
                                </div>
                            </div>
                        </div>
    
                        <SquareButton id="info-edit-btn">상담사 정보 수정</SquareButton>
    
                    </div>
                    <div class="like-part">
                        <div class="sub-title">관심 상담가</div>
                        <div class="like-box">
                            <i class="bi bi-chevron-compact-left"></i>
                            <div class="like-list">
                                <div v-for="counselor in counselors" :key="counselor.id">
                                    <ReviewCard :counselor="counselor" id="reviewcard"></ReviewCard>
                                </div>
                            </div>
    
                            <i class="bi bi-chevron-compact-right"></i>
                        </div>
                    </div>
                </div>
    
            </div>
        </div>
    </template>
    
    <script>
    import { SquareButton } from "../../components/styled-components/StyledButton";
    import ReviewCard from '../../components/common/ReviewCard.vue';
    
    export default {
        components: {
            SquareButton,
            ReviewCard,
      },
      data() {
        return {
          counselors: [
            { id: 1, name: 'John Doe', rating: 4.5, reviews: 20 },
            { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
            { id: 2, name: 'Jane Smith', rating: 5.0, reviews: 15 },
          ],
        };
      },
    }
    </script>
    
    <style lang="scss" scoped="scss">
    
    .mypage {
        display: inline-flex;
        justify-content: center;
        padding-top: 92px;
    }
    .empty-box {
      height: 92px;
    }
    .mypage-area{
        height: 900px;
        width: 1273px;
        margin-top: 96px;
        // margin-left: 15%;
        // margin-right: 15%;
    }
    .mypage-header { 
        height: 57px;
        width: 344px;
        text-align: left;
        color: #333;
        font-size: 34px;
        font-style: normal;
        font-weight: 700;
        line-height: normal;
    }
    .header-line {
        height: 1px;
        width: 100%;
        background-color: #333;
        margin-top: 10px;
    }
    .mypage-contents{
        height: 588px;
        width: 100%;
        margin-top: 81px;
        display: flex;
        justify-content: space-around;
        // background-color: red;
    }
    .profile-nav {
        height: 588px;
        width: 181px;
    }
    .profile-img{
        width: 180.9px;
        height: 180px;
        border-radius: 180.9px;
        background-image: url('data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBIVFRgWFRUYGBgaHBgaGBgaGBgYGRoYGhoaHBoaGBgcIS4lHB4rHxoaJjgmKy8xNTU1GiQ7QDs0Py40NTEBDAwMEA8QHBISGDEhISE0NDE0NDQ0MTQ0NDQ0NDQ0NDQ0NDQ0PzQ0NDQ0NDQ0NDQ0NDQ0MTQ0NDQ0NDQ0NDQ0NP/AABEIAPsAyQMBIgACEQEDEQH/xAAcAAAABwEBAAAAAAAAAAAAAAAAAQIDBAUGBwj/xAA9EAACAQIDBQcCBAQFBAMAAAABAgADEQQhMQUSQVFxBiJhgZGh8LHBBxMy0VJi4fEUQnKCshWSorMjJDT/xAAZAQADAQEBAAAAAAAAAAAAAAAAAQIDBAX/xAAdEQEBAQEAAwEBAQAAAAAAAAAAAQIRITFBAxJR/9oADAMBAAIRAxEAPwDbKsdVYFEUBBEgARQEAEUBEAAhgQwIIAYEFocAEFBBaHaR8fjadBGqVXCIupPsAOJPKA4fNgLnIDUnSZba/bnC0iVp/wDzP/KbUwf9ed/9oMxHabtVVxbFFJSjwT+LxcjU+Gg8dZQqQNZUz/pWtVjO2mNqfoZUHJFANv8AU1z6WlNUx1Vjd6zk8y7MfrIQf5+8Xa/zMx8RacbaLab7kHhc29zEUtobpvc+sZqrwGZ8NBGGpn+kYb/Y/bRkUK4LrbIlu8PPiPnTU4DtFh6ourqP5SRf01nGqL7vGSDWseXIiLko7Y7mlQHTP0h3E5PsTtRUoEKx305cR08PCdJ2VtSniEDobjiOIOWok2cVL1PIhWirQERA2REER0iJIgDLLEbseIhWjHCwIoCACKEQARQEAgEAEVaACHBQAQ7QwIYgDOJrpTRndgqKCWY6ACcZ7U9onxlS+a0lJ/LTkP425ufYZc7334k7f33/AMMh7q2ap4tqq9BqfLlMCpvHmFTgaBT/AE/eJY8IbmwlpOB4YqSN9tYEuYEsqbC33jb0y38q+5/f6RFIc9B89ZL3SRl/bwgciAUA0hvmMrAx16BgCa3HCR1X8mMPV3sjLzs9tV8NUDA3U/rW+RHMcj4zMk7r5ZZ3k+xyYSvifrumFxAdFdc1YXBkgTE9gNp76NRY5rmt+XEev1m2WQqCtEkRwiJIgZsiFaLIhWgkoCKAhCHBQRQEAihAABDAgEVJAgJWdodqLhqD1DqB3RzY5AestJy78Ttq79RKCnu0xvPy3zoPIX9YBha9VnYu5uzEknmTqfWEukRqY6g9prAAyhGIZrxw6RpMsY8osPHj9h1jVJc7nQfBHb5+PHzi6ch+gpJ+es0uzNms6jLKV2xcGXYC3iT4D+s6HgsIFUCZa02xjvms7/0RTwsZW4vZJBt7c50EUBG8Rg1YaTOasaXMrkm0tmMveAylfh8UUO6dJ1faGzlZSLTmPaDZ35b3GkvG++Ky3+fJ2LvsxjxTro+gvY9DlOwUWuAQZ592VXzAnauy2N/MorfUZHy/veXWa8MIxZiTAEmJtFmFADEMQhFAQAxFCEIoQACHBBJMxj8UtJHdv0opZugH9h5zgW08W1Wo7tq7Mx8z/adO/E3ae5QWiDnUN2/0Ln7n6GcnYxwfAQ8Y6Rl84xpP6/PaKqNYfOn1M0IhGufP2EXUOUQmVugim9oWlIGlh5mO4Zbm/wA+fvGCbk+ksMFTuwHSZ2rkbXstgrJvHj9OH7+c1dITO7P2lTVQtt0AAcOEvcFjab/pYHzmV66M8k4sFWEyxawmiNCxFO4mD7Y4QbhM6HUXKYztat0PQwz7GvOXNMGbN5zqvYXFG275+Fxf639pyvDL3j1m/wCxlWz25kD584zeuOenVDEmGug6QGBkGC0MwoAYihCEUIAcUIQhyQEBMEibUxQp03c/5VJ9BeBuRdv9ofm4plBySy+Y1+tvKZd9Y/XqF3ZmNySST4k5xhtZc9FS0EbxD8On1i97L5xjJzY8veMHE0ziS2d4ZMJVJkmdpC5mm7PIikF7c8/nSVey8FvHPwHS8s32MzXAB0O7mdeF5Gr8a5zffGzV8M4sUU+PH1EZXY6XvTYg9f2lL2W7N75b8zfS293lfdN8rWHEjPwl5sk1EdqbtvFLbrWtvob2Y+ORB6SLLnz3q82atnOLrZ7vbdfUScZHw2bQ8S8XV8RsTjqa3BYXmN7T4tSrWIOst8dhaTk3bPrMl2n2aqIXVz4gjnllHnlqNdk9MthBf6zbdkj3/On/AOxfteY7ApNt2MW9X0byQg5+YA85rb5YSeOup0v0jpDMNFsAPAQGJJBghmCUAEUIQihJAxBAIqBgJj/xExu5ht29i53fLK/tNhOWfihi71EQHQEkdcvsYBhEGpiQvz2+/tFsbL1hqLCX8Iy5iVHARbQ6aQAwh5ydh6QALH4Y1Rp3+a+Ms8DhN8i/6Rr4nl4ybeNM57Vv2ewRO7l3nOXIX1PpYTdf9LQqBbTQyq2Jhbd466DwHITUU8xMe9dFnPSrbZ2VjZuqi/rBSwKJ3t0BiLZADL4TLi0r8bUAMOQdoYQ2JkDbK1CjBP1nJb6ecn4HO8W9MG8RsJsrZDOXOJcqVVhffKsWuCrKAdALjkbzJbWxVQpuOSQHNmIsWUaX87Tq+NwCsCCL9ZzTtpQCVEXwY+4mmNdvLGG88lsqow2gm57BLeuRzSx/7lP2mEwx7om17D1LYlM9VIlX2z+OrGEYcIwIkwoZhSiGIoQhFSQMQ4mKEDE7WBM4d2yxf5mKfO4SyeY1/wDImdl2vihTpO50Ck+QGftOBV2ZndzqzE+pvAQ07XI8M/2hl+HrEWgtH1XBgfPvHUF+kQgvJFNe8IwsMHhmewAy+aTV7KwIFspA2WgsJocMsx1rroxnizwq2lrhzKyg0n0XkxWqfrPYGUKnfG+xt1OUu3MqcTsim43HUMu9vAEXs3MR0pUnZbjMSYy5mVWE2caQIQ2F8gcwPADlLOlcCxNzxMRkVFynH+3mKD4sqpypqqH/AFG7H/kPSdQ7QbSXD0XqN/lGQ5sclA6kgThtZ2dmdjdmYsx5km595f5Z89Y/trkkScI2VpquyNbdr07/AMajXgSL9cgfWY+iSLecvtj1l30Y5FSCeBtces1sc8vh3aAwqbAgEcQD6wzJUSYUMwoEMRQiRFCACKiYbsALmBsf+I+0fy8PuDWod23hqcuWXuJyGtVz8ZrfxE2t+biCgyWn3RzJ45cJimeB+oVvx6lSLa5fYQUaHFvT95MQg9PqZcibRU0toMuF/qYYNjf5aLZh5c+fTwjLVBYngB/aPngu+Wk2TXyE02GfKc42XjtwjO4+azbbPxQYCxnPqcdeNStFQeP4qu6IzIhdgMlBAv6yvoPLAVMpK1NS7Q1GyKWPibe5ykldtMLbyEX5gi/Q6GNYzCg57t5GSiB+lmXoTDrpznOp44v8JtJHNvQyeWFrzO4DZSklyW9SLmJ7U7aGFoEjNz3UGufM+A1hO1h+v85vj4x34iba/Mqigh7lMgv4vwH+0H1PhMk66dBEO5LFmNySSSdSb5kx0ZgdJ1ZzycefvV1elUBp5x+i263Q/PtG6Qzt8zj7pmDzt+0NCO59n65ehTJO9dEIbmCBr4g3B6SzMyf4dYkthijf5GIU/wArEn/lvTWTNQomKhQIBFCJEUIGEp+020f8PQepx/Sg/mPwnylxObfidt9LrhkzZDvOeAJUgL1s1/C4gHPMXVLszscyTGQ4GkQzxsvylSDqQ1aOJU4sbD6yOosPGKC31z+e0qRNpbVWc8gIuq9l3R5xo1AOvDkI2nWXIi0EJU3H9DNDsjFm10N7arxHTwlARF0KjIwZTYj5Y+EneOqx+n810rZu0Q1heaCi4MxGyqlPEpvDuutg1tQeHUGXNLGPSF3I3Rq97Adb6Tls5eO3OpZ1rKdIGG+FTkJWYDa9NxdXB6EGPY/bVCiheo4AHDVj4KupMBbz6RtjaVPDU2dzYDQDVjwVRznMtuYpsQDUc2ORUagDPIeX3kLtHt2pi3Lt3UUkIn8I5nmx/pHHxKbg76E2F7XJyJHK3HQTbGOeXL+n6f14npSmP0hf3jKiP0dPOaIGhzPUS0w1yosMwwIHuB6iVbjLzlvsjvOo5kDzk6Vl1TsVgWp0STxsB4gFjf1YzSGR8BVDopAtlYgcCMiJIMgxQoZhQIBFCJEMQAybTzrtLFl2eoTdqju5vna7H7/QT0UReebcZTIdlPBmFuVmOUcCM0CwNDp0y3SaSdTbw4DeOU6JPywjtOiBrHw01mZPbO20lMKnG5i1wdP+H3P7w0kumkrs/wAKSmhhU5fX94l9nKdDb3k9RDtF/U/w/wCUDA1amGqK4zXRgNGXiPA8RJvbHaQcU0Q3Rhvnx4KD6HzEJgDkRcHUSmx+FK6dV6cpGvzlv9RWf0slyYpXGhI6G0FQktckk24m8Th2gc6/OUgwVcoQWDl5R5k7jdIGbpjKPKuR6RuicvSPJoYAnUSbgN5GUnK+annY2y85CGRl7iWT/C4ci28GcZcha5PmR6Sacdc2U90Rxo6oW/1boBP0lrM/2TrB8Mnhf3zP1l8JBgYUMwoABFCIEVAGsZiBTR3bRFZj0UEzzxiam8zMdSWJ8852T8Q8d+XhHQHvVLIOjHvf+O9OKYk6+OnmZU9n8NUk3j4SathGqa2EWTOjM5GGr0u8cBjKmLQx2iHkMlI0iLJFMyaqJiGO2jKGPqYuq4ZYSPiE3ltxGY/aTHWR2l5vWepxR/l2NxoY23GT8XSte3KQFYEA89fDnI1nh5vRNJuHXeBHPL1kE6SRhWmdaQ1SFrg+I85KQ2hbRp7lQngwVh/uGfveEmvz5/eMgqCxl12fwb170lW+pLZ2VcrDzYj0lS4uOk6L2KpLSwr1WyBctfmV7oUc8wfeRVLzsMAKLKODa+o+wmnlR2ZwRpUVDCzG7EHUbxJAPiAZbmKgkwoDBGAEMtbWJEZxb923PXpx9rmAcy/EbHl6qIDkAWt1uB52BPmJz5mu1+Uv+0+N36rv/EcvBdFH/aFlAojxO0t3mZDlzADE3gvN2J8RamNxSxU0hI+hkZTH0MS4lIY+jSMrXAPkY8piM/e8jvHVMRVEeaNRHqpcWlBSFmZfMfOk0N5WbSw9iHXh+rpK15jOeKiH+sFNrQ3iVEysaRJxNbeVBxUEX8Ccr+8FNtDIyNc+0kUdIp6P6mImc6v2Y2eXp0mcWRFXcT+Ij/O3U5zmOy90sA2gPtw9517YNcld0nTTprI+rvpeQjCBgiSIwQjBKAiZV7crbtF25I9s+JG6PrLImUna3/8AO9zYEAepH7QDi+16gLtbS/sMvpaQo7j3u56xkn56S/z9J/T2O8BMIwm0PSas0lMxHBmIxh3z+aGP/pa3AxU4NDHkaR2yMdUxKTKJzI5x5WkJW0kq/GSZ5j+8U2YvEKYSNY24GHo/cNtG6uh4+HMco84jTzSeKyvmKZ1A004fYH6RFNrGSMXSzJHwf3kMHORqcqs3sOAWbz+8lYfXy+kik3zkik0lcWGEOc6J2K2kG7jHvrbzU5X9be05vg3lrgMW1Kojg6a8iPGZXxWlnY7ZAZGwOKWoisOIBy8RJBlIEYUBgvAE8ZVdoqReg4/lNustJD2i4CG/Jj6AwS8/4s949Y1f56SRtlwar7um+x95Gv8AeaZ9Fr2VeGYkmKmiCaLaGT3XeHiJXU+I8ZOwj3FuUQGrXHiNYqk3CJfutfgdYlmsbxKSRJNJriQ968dovnEcTKbQ6oyvyjanOO3iMRNwDG4ENiV55jrA00zexGpyk0kplir6EGzZ90njbiP3lFiKe6b8JdVRlflIeKpg9DHrPYmXlQFPvHqbSKMjYx9DMG0S6T2MtaVRXRRowNuoOnofr4SmpnOPU3KkESNRpK7J2Kr72GT36i4P0BmiJnPOwG2qYLUWbdJN1ByB52POdADXhEXxSiYUSYI0kPUABJOQmB7Z9pgqMiZ8Lnif2FvO3IZtdoe0uRUkn+X9It4gafWY/C4OtjKqrnZjrnYDif6mANUNkE4SriXvkUVPEFrMZTE6dftOkfiAlPDYSlh0tmR6IPpmJzZvuJpktFtpFAxBgQ5S0CX9R8Y8j7pvGH19I7e+cIKnuAw8DIgPAxWHq2yOnCCsOIhwdOUX4R5TISvxkhWgcqejZR4NlIlF5IQyWkCsLi41GYhht4X5/WARlTZiOBzHWGbylqdhwH9jGKiWy+Wjzf3iTmJrllVTiaXzwkem8tK6X6yrqpY3Ejefqsa+JKGXGy8EtaoqFt3eyBGefDLrKKjUlpgK5RlZdVNx4jjMNNoRXpvSqMpNmU5MMxcaZ8Os6b2R7UCsAlQ7tQAA30e3EePhMOrrUeozC+8Va3Ei/fA/23En4XZd8G9Zbhqb7yOMu6tlIB6X9IRNdavBKns3jmq0FL/rW6seZHH0tLS8Cca2T2bxGJfvggZXvlYePLprOm7I2XSwyWUZ2zawuf2EcwNMKGsLSN2iqFcPVYGxCNY8rCMd65X252t/iMS26bpTui8jY94+vsBM85yMKrrDbQ9JWfRUZgQ6wjAvGaIKbWKU2iG1HT7mKbSL1T52FkRYa4iOECazRBBNjH6TxitDThJpxYUHzkoNICSYJNaSpBPGN4hbi44ZwDSLGkkzaPcX9YLxujqYszTNRqEuvzwkLE0/eTjp5yPV0PWXWarZbGP0MRYxNbSMLMNTjbNaDAVu9kbEgi/gRYkeM6LtGkibOKIcgl7jxE5ZgM7dRNdhcS7YGqCxIte3W8y9Lvny2/ZUf/XV9N9nYdCTb2EuN+V+yhahRAy7if8AESbKQ//Z');
        background-repeat : no-repeat;
        background-size : cover;
        margin-bottom: 14px;
        // background: lightgray 50% / cover no-repeat, #D7D7D7;
    }
    .nav-menu{
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 50px;
        margin-top: 70px;
    }
    .nav-menu li{
        color:  #333;
        font-size: 24px;
        font-style: normal;
        font-weight: 700;
        line-height: normal;
        white-space: nowrap;
    }
    .userinfo-part{
        height: 575px;
        width: 446px;
    }
    .sub-title {
        color: #333;
        font-size: 20px;
        font-style: normal;
        font-weight: 700;
        line-height: normal;
        text-align: left;
        margin-bottom: 20px;
    }
    .info-box {
        width: 446px;
        height: 424px;
        border-radius: 10px;
        background: #FCFAF1;
        box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        margin-bottom: 52px;
    }
    .up-info {
        height: 295px;
        width: 378px;
        display: inline-flex;
        justify-content: space-between;
    }
    .info-labels {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }
    .info-fields {
        display: flex;
        flex-direction: column;
        justify-content: space-around;
    }
    .info-fields {
        display: flex;
        flex-direction: column;
        justify-content: space-around;
    }
    .each-label {
        height: 47px;
        color:  #333;
        font-size: 17px;
        font-style: normal;
        font-weight: 700;
        line-height: normal;
        text-align: left;
        display: flex;
        align-items: center;
        margin-top: 15px;
    }
    .each-field {
        height: 47px;
        width: 280px;
        display: flex;
        width: 280px;
        padding: 13px 41px;
        justify-content: center;
        align-items: center;
        gap: 10px;
        border-radius: 10px;
        background: #FFF;
        box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
        color:  #333;
        font-size: 17px;
        font-style: normal;
        font-weight: 700;
        line-height: normal;
        box-sizing: border-box;
        margin-top: 15px;
    }
    .down-info{
        width: 378px;
        display: inline-flex;
        justify-content: space-between;
    }
    .down-info span{
        margin-left: 18px;
        margin-right: 10px;
    }
    #info-edit-btn{
        height: 50px;
        width: 446px;
    }
    .like-box{
        width: 446px;
        height: 526px;
        border-radius: 10px;
        background: #FCFAF1;
        box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.10);
        display: flex;
        justify-content: space-evenly;
        align-items: center;
    }
    .like-list{
        height: 480px;
        width: 344px;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
    }
    .each-like{
        height: 84px;
        background-color: aliceblue;
    }
    i{
        width: 13.911px;
        height: 25px;
    }
    
    #reviewcard{
        // width: 344px;
        width: 100%;
        margin-bottom: 5px;
    }
    
    </style>