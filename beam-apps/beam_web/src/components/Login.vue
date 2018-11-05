<template>
  <el-form ref="AccountFrom" :model="account" :rules="rules" label-position="left" label-width="0px"
           class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="account.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="account.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="login" :loading="loading">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import loginApi from '../api/login';
  import { getToken, setToken, removeToken } from '@/utils/auth'


  export default {
    data() {
      return {
        loading: false,
        account: {
          username: 'admin',
          password: 'admin'
        },
        rules: {
          username: [
            { required: true, message: '请输入账号', trigger: 'blur' },
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
          ]
        },
        checked: true
      };
    },
    methods: {
      login() {
        this.$refs.AccountFrom.validate((valid) => {
          if (valid) {
            this.loading = true
            loginApi.login(this.account).then((res) => {
              this.loading = false


              if (res.error === false) {
                this.$message.success(res.msg);
                setToken(res.data)
                this.$router.push({path: '/'});
              } else {
                this.$message.error(res.msg);
              }
            }, (err) => {
              this.loading = false
              this.$message.error(err.msg);
            })
          }
        });
      }
    }
  }
</script>

<style lang="scss" scoped>
  .login-container {
      /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
      -webkit-border-radius: 5px;
      border-radius: 5px;
      -moz-border-radius: 5px;
      background-clip: padding-box;
      margin: 180px auto;
      width: 350px;
      padding: 35px 35px 15px 35px;
      background: #fff;
      border: 1px solid #eaeaea;
      box-shadow: 0 0 25px #cac6c6;

      .title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
      }
      .remember {
        margin: 0px 0px 35px 0px;
      }
  }
</style>
