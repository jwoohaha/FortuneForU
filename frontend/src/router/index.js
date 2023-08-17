import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'
import { useTokenStore } from '@/stores/token'
import { reissue } from '@/api'

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
    path: '/reissue',
    name: 'reissue',
    component: () => import('../views/ReissueView.vue')
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
    component: () => import(/* webpackChunkName: "reservationlist" */ '../views/Mypage/MypageView_myreservationlist.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/mypage/counslingresult/:rezNo',
    name: 'counslingresult',
    component: () => import(/* webpackChunkName: "counslingresult" */ '../views/Mypage/MypageView_counselingresult.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/mypage/reportlist',
    name: 'reportlist',
    component: () => import(/* webpackChunkName: "reportlist" */ '../views/Mypage/MypageView_myreportlist.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/mypage/review',
    name: 'review',
    component: () => import(/* webpackChunkName: "review" */ '../views/Mypage/MypageView_myreview.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/counselor',
    name: 'counselor',
    component: () => import(/* webpackChunkName: "counselor" */ '../views/Mypage/CounselorView.vue'),
    meta: { requiresAuth: true, forCounselor: true }
  },
  {
    path: '/counselor/counseling',
    name: 'counseling',
    component: () => import(/* webpackChunkName: "counseling" */ '../views/Mypage/CounselorView_counseling.vue'),
    meta: { requiresAuth: true, forCounselor: true }
  },
  {
    path: '/counselor/counreview',
    name: 'counreview',
    component: () => import(/* webpackChunkName: "counreview" */ '../views/Mypage/CounselorView_counreview.vue'),
    meta: { requiresAuth: true, forCounselor: true }
  },
  {
    path: '/counselor/review-update/:rezNo',
    name: 'counselorreportupdate',
    component: () => import(/* webpackChunkName: "counreview" */ '../views/Mypage/CounselorView_reportUpdate.vue'),
    meta: { requiresAuth: true, forCounselor: true }
  },
  {
    path: '/counselor-form',
    name: 'counselor-form',
    component: () => import(/* webpackChunkName: "counreview" */ '@/views/CounselorFormView'),
    meta: { requiresAuth: true, onlyForUser: true }
  },
  {
    path: '/admin/counselor-form-list',
    name: 'counselor-form-list-view',
    component: () => import(/* webpackChunkName: "counreview" */ '@/views/admin/CounselorFormListView'),
    meta: { requiresAuth: true, forAdmin: true }
  },
  {
    path: '/admin/counselor-form-view/:formNo',
    name: 'counselor-form-view',
    component: () => import(/* webpackChunkName: "counreview" */ '@/views/admin/CounselorFormDetailView'),
    meta: { requiresAuth: true, forAdmin: true }
  },
  {
    path: '/chatview/:reservationNo',
    name: 'chatviewforconsultant',
    component: () => import(/* webpackChunkName: "chatView" */ '../views/room/ChatView.vue'),
    props:route=>({
      reservationNo: route.params.reservationNo
    }),
    meta: { requiresAuth: true }
  },
  {
    path: '/chatview/:IdforSession',
    name: 'chatviewforuser',
    component: () => import(/* webpackChunkName: "chatView" */ '../views/room/ChatView.vue'),
    props:route=>({
      IdforSession: route.params.IdforSession
    }),
    meta: { requiresAuth: true }
  },
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
      reissue().catch(() => {
        alert('로그인이 필요한 페이지입니다.');
        tokenStore.makeLoginModalVisible();
        return '/'
      }
      )
  }
  if (to.meta.onlyForUser && tokenStore.checkRolesIncludes("ROLE_COUNSELOR")) {
    alert('이미 상담가(혹은 관리자)로 등록되셨습니다.');
    return '/'
  }
  if (to.meta.forCounselor && !tokenStore.checkRolesIncludes("ROLE_COUNSELOR")) {
    reissue().catch(() => {
      alert('"상담가" 권한의 사용자만 접근가능합니다.');
      return '/'
    }
    )
  }
  if (to.meta.forAdmin && !tokenStore.checkRolesIncludes("ROLE_ADMIN")) {
    reissue().catch(() => {
      alert('"관리자" 권한의 사용자만 접근가능합니다.');
      tokenStore.makeLoginModalVisible();
      return '/'
    }
    )
  }
})

export default router
