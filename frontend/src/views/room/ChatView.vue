<template>
    <div id="main-container" class="container">
      <!--
      <div id="join" v-if="!session">
        <div id="img-div">
          
        </div>
        <div id="join-dialog" class="jumbotron vertical-center">
          <h1>Join a video session</h1>
          <div class="form-group">
            <p>
              <label>Participant</label>
              <input v-model="myUserName" class="form-control" type="text" required />
            </p>
            <p>
              <label>Session</label>
              <input v-model="mySessionId" class="form-control" type="text" required />
            </p>
            <p class="text-center">
              <button class="btn btn-lg btn-success" @click="joinSession()">
                Join!
                
              </button>
              <nav>{{name}} 하이</nav>
            </p>
          </div>
        </div>
      </div>
    -->
      <div id="session" v-if="session">
        <div id="session-header">
          <h1 id="session-title">{{ username }}</h1>
          <h1>여기 위에 나와야함</h1>
          <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession"
            value="Leave session" />
        </div>
        <div id="main-video" class="col-md-6">
          <!-- <user-video :stream-manager="mainStreamManager" /> -->
        </div>
        <div id="video-container" class="col-md-6">
          <user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)" />
          <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"
            @click="updateMainVideoStreamManager(sub)" />
        </div>

        <div id="recording-btns">
				<div class="btns">
					<input class="btn btn-md" type="button" id="buttonStartRecording" @mouseup="startRecording()" value="Start recording">
					<form>
						<label class="radio-inline">
							<input type="radio" value="COMPOSED" id="radio-composed" v-model="outputMode">
						</label>
						<label class="radio-inline">
							<input type="radio" value="INDIVIDUAL" id="radio-individual" v-model="outputMode">
						</label>
					</form>
					<form>
						<label class="checkbox-inline">
							<input type="checkbox" id="has-audio-checkbox" checked>Has audio
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" id="has-video-checkbox" checked>Has video
						</label>
					</form>
				</div>
				<div class="btns">
					<input class="btn btn-md" type="button" id="buttonListRecording" @mouseup="listRecordings()" value="List recordings">
					<div class="vertical-separator-bottom"></div>
					<input class="btn btn-md" type="button" id="buttonGetRecording" @mouseup="getRecording()" value="Get recording" disabled>
					<input class="btn btn-md" type="button" id="buttonStopRecording" @mouseup="stopRecording()" value="Stop recording" disabled>
					<input class="btn btn-md" type="button" id="buttonDeleteRecording" @mouseup="deleteRecording()" value="Delete recording" disabled>
					<input class="form-control" id="forceRecordingId" type="text" @keyup="checkBtnsRecordings()">
				</div>
				<div class="textarea-container" id="textarea-http-container">
					<button type="button" class="btn btn-outline-secondary" id="clear-http-btn" @click="clearHttpTextarea()">Clear</button>
					<span>HTTP responses</span>
					<textarea id="textarea-http" readonly="true" class="form-control" name="textarea-http"></textarea>
          <pre>{{ JSON.stringify(res, null, "\t") }}</pre>
				</div>
				<div class="textarea-container" id="textarea-events-container">
					<button type="button" class="btn btn-outline-secondary" id="clear-events-btn" @click="clearEventsTextarea()">Clear</button>
					<span>OpenVidu events</span>
					<textarea id="textarea-events" readonly="true" class="form-control" name="textarea-events"></textarea>
				</div>
			</div>

      </div>
    </div>

    <modal-view v-if="isModalVisible" @close-modal="isModalVisible = false" :counselorId="this.reservationNo" :reservationNo="this.reservationNo"></modal-view>
  </template>
  
  <script>
  import axios from "axios";
  import { OpenVidu } from "openvidu-browser";
  import UserVideo from "../../components/room/UserVideo";
  import{ useRoute }from "vue-router"
  import { apiInstance } from '@/api/index'
  axios.defaults.headers.post["Content-Type"] = "application/json";
  import ModalView from "@/components/common/ReviewModalView.vue";
  

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
  
        // Join form

        myUserName: "Participant" + Math.floor(Math.random() * 100),
        outputMode:'INDIVIDUAL',
        hasAudio:'true',
        hasVideo:'true',
        forceRecordingId: "",
        res:{},

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
          this.session.connect(token, { clientData: this.myUserName })
            .then(() => {
              console.log("connect실행");
              // --- 5) Get your own camera stream with the desired properties ---
  
              // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
              // element: we will manage it on our own) and with the desired properties
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
            })
            .catch((error) => {
              console.log("There was an error connecting to the session:", error.code, error.message);
            });
        });
  
        window.addEventListener("beforeunload", this.leaveSession);
      },
  
      leaveSession() {
        // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
        if (this.session) this.session.disconnect();
  
        // Empty all properties...
        this.session = undefined;
        this.mainStreamManager = undefined;
        this.publisher = undefined;
        this.subscribers = [];
        this.OV = undefined;
  
        // Remove beforeunload listener
        window.removeEventListener("beforeunload", this.leaveSession);
      },
  
      updateMainVideoStreamManager(stream) {
        if (this.mainStreamManager === stream) return;
        this.mainStreamManager = stream;
      },
      checkBtnsRecordings() {
	if (document.getElementById("forceRecordingId").value === "") {
		document.getElementById('buttonGetRecording').disabled = true;
		document.getElementById('buttonStopRecording').disabled = true;
		document.getElementById('buttonDeleteRecording').disabled = true;
	} else {
		document.getElementById('buttonGetRecording').disabled = false;
		document.getElementById('buttonStopRecording').disabled = false;
		document.getElementById('buttonDeleteRecording').disabled = false;
	}
},
  //     async startRecording1() {
  //       await axios({
  //       method:"POST",
  //       url:"localhost:5000/recording/start",
  //       data:{
  //         session: this.session.sessionId,
  //         outputMode: this.outputMode,
  //         hasAudio: this.hasAudio,
  //         hasVideo: this.hasVideo
  //       },
  //     }).then(({data})=>{
  //       if(data.code==="fail"){
  //         console.log("fail");
  //       }else{
  //         console.log("success");
  //       }
  //     })
  // },

  async startRecording() {
  const response = await axios.post('http://localhost:5000/api/recording/'+this.sessionId, {
    session: this.session.sessionId,
    outputMode: this.outputMode,
    hasAudio: this.hasAudio,
    hasVideo: this.hasVideo
  }, {
    headers: { 'Content-Type': 'application/json' },
    withCredentials: true
  });
  document.getElementById('forceRecordingId').value = this.session.sessionId;
  this.checkBtnsRecordings();
  const responseData = response.data; // 받아온 데이터
  console.log(responseData);
  this.res=responseData;
  return responseData; // The sessionId
},


async stopRecording() {
  const response = await axios.post('http://localhost:5000/api/recording/stop/'+this.sessionId, {
    recording:this.session.sessionId
  }, {
    headers: { 'Content-Type': 'application/json' },
    withCredentials: true
  });
  console.log("여기까지됨");
  document.getElementById('forceRecordingId').value = "";
  const responseData = response.data; // 받아온 데이터
  this.res=responseData;
  this.forceRecordingId=responseData.id;
  //console.log(this.forceRecordingId);
  // console.log(responseData.id);
  return responseData; // The sessionId
},
  
  
      async getToken() {
        alert(this.sessionId==null);
        if(this.sessionId==null){
          //상담가일 떄
          console.log("1. getToken 함수 정상 실행");
          const sessionId = await this.createSession();
          console.log("createSession에서 값 받아오기 성공");
          return await this.createToken(sessionId);
        }else{
          //유저일 때
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
    },
    
    showModal(){
      this.isModalVisible = true;
    }
  };
  </script>
  
  <style>
#main-container{
  margin-top: 100px;
  }
</style>