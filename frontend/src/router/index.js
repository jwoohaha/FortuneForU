import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'
import { useTokenStore } from '@/stores/token'

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView
  },
  {
    path: '/tarot',
    name: 'tarot',component: () => import(/* webpackChunkName: "tarot" */ '../views/TarotView.vue')
  },
  {
    path: '/saju',
    name: 'saju',
    component: () => import(/* webpackChunkName: "saju" */ '../views/SajuView.vue')
  },
  {
    path: '/community',
    name: 'community',
    component: () => import(/* webpackChunkName: "community" */ '../views/CommunityView.vue')
  },
  {
    path: '/reservation',
    name: 'reservation',
    component: () => import(/* webpackChunkName: "reservation" */ '../views/ReservationView.vue'),
    props: true
  },
  {
    path: '/redirect',
    name: 'login',
    component: () => import('../views/RedirectView.vue')
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: () => import(/* webpackChunkName: "mypage" */ '../views/Mypage/MypageView.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/mypage/reservationlist',
    name: 'reservationlist',
    component: () => import(/* webpackChunkName: "reservationlist" */ '../views/Mypage/MypageView_myreservationlist.vue')
  },
  {
    path: '/mypage/counslingresult',
    name: 'counslingresult',
    component: () => import(/* webpackChunkName: "counslingresult" */ '../views/Mypage/MypageView_counselingresult.vue')
  },
  {
    path: '/mypage/review',
    name: 'review',
    component: () => import(/* webpackChunkName: "review" */ '../views/Mypage/MypageView_myreview.vue')
  },
  {
    path: '/counselor',
    name: 'counselor',
    component: () => import(/* webpackChunkName: "counselor" */ '../views/Mypage/CounselorView.vue')
  },
  {
    path: '/counselor/counseling',
    name: 'counseling',
    component: () => import(/* webpackChunkName: "counseling" */ '../views/Mypage/CounselorView_counseling.vue')
  },
  {
    path: '/counselor/timeset',
    name: 'timeset',
    component: () => import(/* webpackChunkName: "timeset" */ '../views/Mypage/CounselorView_timeset.vue')
  },
  {
    path: '/counselor/counreview',
    name: 'counreview',
    component: () => import(/* webpackChunkName: "counreview" */ '../views/Mypage/CounselorView_counreview.vue')
  },
  {
    path: '/chatview',
    name: 'chatview',
    component: () => import(/* webpackChunkName: "chatView" */ '../views/room/ChatView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to) => {
  const tokenStore = useTokenStore();

  // 1. meta 확인 -> 사용자가 로그인한 상태여야 접근이 가능한 페이지라면
  // 2. tokenStore에서 사용자가 로그인했는지 여부 확인
  if (to.meta.requiresAuth && !tokenStore.isLoggedIn) {
    alert('로그인이 필요한 페이지입니다.');
      return '/'
  }
})

export default router
