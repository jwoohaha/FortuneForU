import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';

//라우터
import router from './router';

//캘린더
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

//EventBus 쓰기 위해


const app = createApp(App)
app.use(router)
app.use(VCalendar, {})
app.use(createPinia());
app.mount('#app')