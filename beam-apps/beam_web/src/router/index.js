/**
 * Created by liqian on 2018/7/12.
 */
import Vue from 'vue'
import Router from 'vue-router'

import { getToken, setToken, removeToken } from '@/utils/auth'

import Home from '@/components/Home'
import Dashboard from '@/components/Dashboard'

import SettingRoute from './setting.js'


// 懒加载方式，当路由被访问的时候才加载对应组件
const Login = resolve => require(['@/components/Login'], resolve)

Vue.use(Router);

const routes = [
    {
        path: '/login',
        name: '登录',
        component: Login
    },
    {
      path: '/',
      name: 'home',
      component: Home,
      redirect: '/dashboard',
      leaf: true, // 只有一个节点
      menuShow: true,
      iconCls: 'iconfont icon-home', // 图标样式class
      children: [
        {path: '/dashboard', component: Dashboard, name: '首页', icon: 'el-icon-menu', meta: {title: "首页"}},
        SettingRoute,
      ]
    },
    {
        path: '*',
        name: '111',
        exclude: true,
        redirect: Dashboard.path
    }
];

const router = new Router({
    routes
});

const whiteList = ['/login', '/authredirect']// no redirect whitelist

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title
    }
    var token = getToken()
    if (token && token != "" ) { // determine if there has token
        next()
    } else {
        /* has no token*/
        console.log("has no token")
        if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
            next()
        } else {
            next('/login') // 否则全部重定向到登录页
        }
    }
})


export {routes, router};
