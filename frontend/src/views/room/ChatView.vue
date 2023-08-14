<template>
  <div id="main-container" class="container">
    <div id="session" v-if="session">
      <div id="session-header">        
      </div>

      <div id="video-container" class="col-md-6" style="display: flex; flex-direction: row; justify-content: center;">
        <user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)" style="margin-right: 10px; margin-left:10px;" />
        <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"
          @click="updateMainVideoStreamManager(sub)" style="margin-right: 10px; margin-left:10px;" />
      </div>
      <RoundButton isTarot @click="closing" value="Leave session">나가기</RoundButton>
      <RoundButton isTarot @click="convert">convert</RoundButton>
    </div>
    <modal-view v-if="isModalVisible" @close-modal="isModalVisible = false" :counselorId="this.reservationNo" :reservationNo="this.reservationNo"></modal-view>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import { OpenVidu } from "openvidu-browser";
  import UserVideo from "../../components/room/UserVideo";
  import{ useRoute }from "vue-router"
  import { apiInstance } from '@/api/index'
  axios.defaults.headers.post["Content-Type"] = "application/json";
  import ModalView from "@/components/common/ReviewModalView.vue";
  import { RoundButton } from "../../components/styled-components/StyledButton";

  export default {
    
    name:'chatview',
  
    components: {
      UserVideo,
      ModalView
    },

  setup(){
    const api = apiInstance();
    const username = useRoute().query.name;
    const sessionId= useRoute().query.sessionId;

    return {
      api,username,sessionId
    }
    },

  data() {
    return {
      // OpenVidu objects
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      isRecorder: false,
      // Join form

      myUserName: "Participant",
      outputMode:'INDIVIDUAL',
      hasAudio:'true',
      hasVideo:'true',
      forceRecordingId: "",
      res:{},
      mp4Url:'',
      roomRequest : {
        reservationNo : 1,
      },
      isModalVisible: false,

    };
  },
  created(){
    //chatView로 넘어오자마자 실행되는 함수
    // alert(this.username);
    // alert(this.sessionId);
    this.joinSession()
  },

  methods: {
    joinSession() {
      // --- 1) Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- 2) Init a session ---
      this.session = this.OV.initSession();
      console.log("joinSession실행");

      // --- 3) Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
        console.log("streamcreated 실행");
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
        console.log("streamDestroyed실행");
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.log("에러 발생");
        console.warn(exception);
      });

      // --- 4) Connect to the session with a valid user token ---

      // Get a token from the OpenVidu deployment
      this.getToken().then((token) => {
        console.log("getToken 자동 실행?");
        console.log("토큰 정보"+token);
        // First param is the token. Second param can be retrieved by every user on event
        // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
        if(this.isRecorder){
          this.myUserName="상담가";
        }else{
          this.myUserName="사용가";
        }
        this.session.connect(token, { clientData: this.myUserName  })
          .then(() => {
            console.log("connect실행");                                                                 
            // --- 5) Get your own camera stream with the desired properties ---
            
            // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
            // element: we will manage it on our own) and with the desired properties

            // if(this.isRecorder){
              
            // }
            
              let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            // Set the main video in the page to display our webcam and store our Publisher
            this.mainStreamManager = publisher;
            this.publisher = publisher;
            
            

            // --- 6) Publish your stream ---

            this.session.publish(this.publisher);
            console.log("publish 실행");
            if(this.isRecorder){
              this.startRecording();
            }
          })
          .catch((error) => {
            console.log("There was an error connecting to the session:", error.code, error.message);
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    async convert() {
      try{
        const response = await this.api.get('/convert');
        this.mp4Url = response.data;
        console.log('MP4 URL:', this.mp4Url);
      }catch(error){
        console.log("오류발생:" ,error);
      }
      
    },


    async closing() {
      if(this.isRecorder){
        //상담가일 경우 back으로 간 다음 세션을 종료하고 나간다.
        const response = await this.api.put('/sessions/'+this.sessionId)
        console.log(response);
        if(response.data=="success"){
          this.$router.push('/');
        }
      }else{
        //사용자일 경우 그냥 나간다 => 즉 세션을 종료하면 안된다.
        this.session = undefined;
        this.mainStreamManager = undefined;
        this.publisher = undefined;
        this.subscribers = [];
        this.OV = undefined;
      }
      
      // this.sessionId= response.data.sessionId;
      // return response.data.sessionId; // The sessionId
      // Remove beforeunload listener
      window.removeEventListener("beforeunload", this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

async startRecording() {
 const response = await axios.post('https://i9a403.p.ssafy.io/api/recording/'+this.sessionId, {
  // const response = await axios.post('https://ssafytest.shop/api/recording/'+this.sessionId, {
  session: this.session.sessionId,
  outputMode: this.outputMode,
  hasAudio: this.hasAudio,
  hasVideo: this.hasVideo
}, {
  headers: { 'Content-Type': 'application/json' },
  withCredentials: true
});
const responseData = response.data; // 받아온 데이터
console.log(responseData);
this.res=responseData;
return responseData; // The sessionId
},  
    async getToken() {
      // alert(this.sessionId==null);
      if(this.sessionId==null){
        //상담가일 떄
        this.isRecorder=true;
        console.log("1. getToken 함수 정상 실행");
        const sessionId = await this.createSession();
        console.log("createSession에서 값 받아오기 성공");
        return await this.createToken(sessionId);
      }else{
        //유저일 때
        this.isRecorder=false;
        return await this.createToken(this.sessionId);
      }      
    },

    async createSession() {
      
      const response = await this.api.post('/roomsession', this.roomRequest)
      console.log("2. createsession 함수 정상실행");
      console.log("받아온 sessionId"+response.data.sessionId);
      this.sessionId= response.data.sessionId;
      return response.data.sessionId; // The sessionId
    },

    async createToken(sessionId) {
      console.log("createToken 넘어옴");
      const response = await this.api.post('/sessions/' + sessionId + '/connections')
      console.log("createToken 함수 정상실행");
      console.log(response.data);
      return response.data.token; // The token
    },
    showModal(){
      this.isModalVisible = true;
    }
  },
};
</script>

<style>
#main-container{
  margin-top: 100px;
}
/* 추가 CSS 스타일 */
.custom-button {
background-color: #ff5733;
border-color: #d9534f;
color: white;
padding-left: 20px; /* 아이콘과 텍스트 사이의 간격 */

background-repeat: no-repeat;
background-position: 5px center; /* 아이콘 위치 */
/* 다른 스타일 변경 및 추가 */
}


</style>