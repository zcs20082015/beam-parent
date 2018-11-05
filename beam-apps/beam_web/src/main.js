// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import { router }from './router'
import ElementUI from 'element-ui'
import store from './store'
import Sortable from 'sortablejs'
import VueHtml5Editor from 'vue-html5-editor'

import 'element-ui/lib/theme-chalk/index.css'
import './common/style/base.css'


import  '../static/UE/ueditor.config.js'
import '../static/UE/ueditor.all.min.js'
import '../static/UE/lang/zh-cn/zh-cn.js'
import '../static/UE/ueditor.parse.min.js'

Vue.use(ElementUI);

Vue.directive('focus', {
  update: function (el, {value}) {
    // 聚焦元素
    if (value) {
      el.focus()
    }
  }
});

Vue.directive('sortable', {
  inserted: function (el, binding) {
    new Sortable(el, binding.value || {})
  }
});

Vue.use(VueHtml5Editor, {
  showModuleName: true,
  image: {
    sizeLimit: 2 * 1024 * 1024,
    upload: {
      url : "/energy_ht/file/upload",
      fieldName : 'upfile',
    },
    uploadHandler(res){
      res = JSON.parse(res)
      return res.filePath
    },
    compress: {
      width: 750,
      quality: 80
    },
    width: 500,
    height: 500,
    quality: 80
  },
  language: "zh-cn",
  // 隐藏不想要显示出来的模块
  hiddenModules: ["image"],
  // 自定义要显示的模块，并控制顺序
  // visibleModules: [],
  // 扩展模块，具体可以参考examples或查看源码
  modules: [
    {
      name: 'multi images',
      icon: 'fa fa-file-picture-o',
      show: true,
      handler() {
        store.commit('showUpload', true)
      }
    },
  ]

});


// Vue.config.productionTip = true

new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
