import axios from 'axios'
import { commonParams } from 'api/config'
import { Message } from 'element-ui'

const Http = {

  get(url, params) {
    const data = Object.assign({}, commonParams, params);
    url = "/beam_ht" + url
    return axios.get(url, {
      params: data,
    }).then((res) => {
      if(res.data.code == null){
        return Promise.resolve(res.data);
      }
      if (res.data.code === 403 || res.data.code == 402) {
        // this.$emit('goto', '/login')
        window.location = "/#/login"
        return Promise.reject(res) //拒绝
      }
      if (res.data.code === 200) {
        return Promise.resolve(res.data); //成功
      }else {
        Message.error(res.data.msg);
        return
      }
    }).catch((err) => {
      //超时之后在这里捕抓错误信息.
      if (err.response) {
        var res = {
          code: err.code,
          msg: err.message,
        }
        return Promise.reject(res)
      } else if (err.request) {
        if(err.request.readyState == 4 && err.request.status == 0){
          //我在这里重新请求
          var res = {
            code: 403,
            msg: "网络链接错误，请刷新重试！",
          }
          return Promise.reject(res)
          // this.$message.error("网络链接错误，请刷新重试！")
        }
      } else {
        Message.error( err.message)
      }
    })
  },

  post(url, data) {
    axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
    // axios.defaults.timeout = 50000;
    // const data = Object.assign({}, commonParams, params);
    url = "/beam_ht" + url
    return axios.post(url, data).then((res) => {
      if(res.data.code == null){
        return Promise.resolve(res.data);
      }
      if (res.data.code === 403 || res.data.code == 402) {
        // this.$emit('goto', '/login')
        window.location = "/#/login"
        return Promise.reject(res) //拒绝
      }
      if (res.data.code === 200) {
        return Promise.resolve(res.data); //成功
      }else {
        Message.error(res.data.msg);
        return
      }
    }).catch((err) => {
      //超时之后在这里捕抓错误信息.
      if (err.response) {
        var res = {
          code: err.code,
          msg: err.message,
        }
        return Promise.reject(res)
      } else if (err.request) {
        if(err.request.readyState == 4 && err.request.status == 0){
          //我在这里重新请求
          var res = {
            code: 403,
            msg: "网络链接错误，请刷新重试！",
          }
          return Promise.reject(res)
          // this.$message.error("网络链接错误，请刷新重试！")
        }
      } else {
        Message.error( err.message)
      }
    })
  },

};

export default Http;
