import Vue from 'vue';
import VueRouter from 'vue-router';

import IntroPage from '@/views/intro/IntroPage.vue';
import IntroContent from '@/views/intro/IntroContent.vue';
import IntroLogin from '@/views/intro/IntroLogin.vue';
import IntroSignup from '@/views/intro/IntroSignup.vue';

import RoomPage from '@/views/room/RoomPage';
import RoomDashboard from '@/views/room/RoomDashboard';
import RoomCreate from '@/views/room/RoomCreate';
import RoomInfo from '@/views/room/RoomInfo';
import RoomModify from '@/views/room/RoomModify';
import RoomTutorial from '@/views/room/RoomTutorial';

import MeetingRoom from '@/views/meetingRoom/MeetingRoom.vue';

import MySetting from '@/views/mypage/MySetting.vue';

import MyPage from '@/views/mypage/MyPage.vue';
import MyInfo from '@/views/mypage/MyInfo.vue';
import MyPresentation from '@/views/mypage/MyPresentation.vue';
import MyNote from '@/views/mypage/MyNote.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: IntroPage,
    children: [
      {
        path: '',
        component: IntroContent,
      },
      {
        path: '/login',
        component: IntroLogin,
      },
      {
        path: '/signup',
        component: IntroSignup,
      },
    ],
  },
  {
    path: '/setting',
    name: 'MySetting',
    component: MySetting,
  },
  {
    path: '/dashboard',
    component: RoomPage,
    children: [
      {
        path: '',
        component: RoomDashboard,
      },
      {
        path: '/dashboard/create',
        component: RoomCreate,
      },
      {
        path: '/dashboard/info',
        component: RoomInfo,
      },
      {
        path: '/dashboard/modify',
        component: RoomModify,
      },
      {
        path: '/dashboard/tutorial',
        component: RoomTutorial,
      },
    ],
  },
  {
    path: '/meetingroom',
    name: 'MeetingRoom',
    component: MeetingRoom,
  },
  {
    path: '/mypage',
    component: MyPage,
    children: [
      {
        path: '',
        component: MyInfo,
      },
      {
        path: '/mypage/presentation',
        component: MyPresentation,
      },
      {
        path: '/mypage/note',
        component: MyNote,
      },
    ],
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
