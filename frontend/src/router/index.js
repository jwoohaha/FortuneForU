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
    path: '/mypage',
    name: 'mypage',
    component: () => import(/* webpackChunkName: "mypage" */ '../views/MypageView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
