import Router from '@/components/Setting/router';
import Index from '@/components/Setting/index';
import SysUser from '@/components/Setting/sysuser';


export default {
  path: '/setting',
  name: '系统管理',
  icon: 'el-icon-setting',
  component: Index,
  hidden_child: false,
  children: [
    {
      name: '系统管理',
      path: 'manager',
      component: Router,
      children: [
        {path: 'sysuser', component: SysUser, name: '用户管理', meta: {title: "用户管理"}},


      ],
      redirect: '/setting/manager/sysuser'
    },
  ],
  redirect: '/setting/manager'
}
