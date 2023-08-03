import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'

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
    component: () => import(/* webpackChunkName: "reservation" */ '../views/ReservationView.vue')
  },
  {
    path: '/redirect',
    name: 'login',
    component: () => import('../views/RedirectView.vue')
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: () => import(/* webpackChunkName: "mypage" */ '../views/Mypage/MypageView.vue')
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
    component: () => import(/* webpackChunkName: "review" */ '../views/Mypage/MypageVue_myreview.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
