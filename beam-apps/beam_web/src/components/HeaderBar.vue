<template>
  <div>
    <div class="header-bar">
      <div class="header__user-wrap">
        <div class="header__icon" style="background-image: url('/static/images/icon_store.png')"></div>
        <el-dropdown @command="handleCommand">
          <span>管理员</span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="1">管理员</el-dropdown-item>
            <el-dropdown-item command="2">修改密码</el-dropdown-item>
            <el-dropdown-item command="3">注销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
      <el-form :model="pwdForm" ref="AccountForm" :rules="rules" label-width="80px">
        <el-form-item label="旧密码" prop="oldPwd">
          <el-input type="password" v-model.trim="pwdForm.oldPwd" auto-complete="off" placeholder="旧密码"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPwd">
          <el-input type="password" v-model.trim="pwdForm.newPwd" auto-complete="off" placeholder="新密码"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="password_confirm">
          <el-input type="password" v-model.trim="pwdForm.password_confirm" placeholder="确认密码"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" size="mini">取 消</el-button>
        <el-button type="primary" @click="modifyPwd()" :loading="loading" size="mini">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import AccountApi from '../api/account.js';
  import { getToken, setToken, removeToken } from '@/utils/auth'

  export default {
    name: 'headerbar',
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.pwdForm.newPwd) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        loading: false,
        dialogFormVisible: false,
        pwdForm: {
          oldPwd: "",
          newPwd: "",
          password_confirm: ""
        },
        rules:{
          oldPwd: [
            { required: true, message: '请输入旧密码', trigger: 'blur' },
          ],
          newPwd: [
            { required: true, message: '请输入新密码', trigger: 'blur' },
          ],
          password_confirm:[
            {validator: validatePass, trigger: 'blur' }
          ]
        }
      }
    },
    mounted() {
    },
    methods: {
      handleCommand(command) {
        if (command == 2) {
          this.dialogFormVisible = true
        }
        else if(command==3){
        	this.handleLogout();
        	this.$router.push({path: '/login'});
        }
      },
      // 修改密码
      modifyPwd() {
        this.$refs.AccountForm.validate((valid) => {
          if (valid) {
            this.loading = true
            AccountApi.modifyPwd(this.pwdForm).then((res) => {
              this.loading = false
              if (res.error === false) {
                this.dialogFormVisible = false
                this.$message.success(res.msg);
               
//              location.reload()
              } else {
                this.$message.error(res.msg);
              }
            }, (err) => {
              this.loading = false
              this.$message.error(err.msg);
            })
          }
        });
      },
      handleLogout(){

          AccountApi.handleLogout().then((res) => {
          	
          }, (err) => {
            this.$message.error(err.msg);
          })
      },
    }
  }
</script>

<style lang="scss" scoped>

  @import '../common/style/variable.config.scss';

  .header-bar {
    position: fixed;
    left:0;
    right:0;
    top:0;
    height:$header_bar_height;
    z-index: 100;
    background: #fff;
    box-shadow: 0 1px 5px lightgrey;

    > div {
      float: right;
    }

    .common-wrap {
      border-left: 1px solid #f4f4f4;
      height: $header_icon_wrap_height;
      line-height: $header_icon_wrap_height;
      cursor: pointer;

    }

    .header__icon {
      width: $header_icon_size;
      height: $header_icon_size;
      background-repeat: no-repeat;
      background-size: $header_icon_size $header_icon_size;

    }

    .header__qq-service {
      height: 60px;
      line-height: 60px;
      cursor: pointer;
      margin-right: 20px;

      &:hover {
       text-decoration: underline;
      }
    }

    .header__user-wrap {
      padding-left: 54px;
      padding-right: $header_user_wrap_padding;
      border-left: 1px solid #f4f4f4;
      position: relative;
      @extend .common-wrap;

      .header__icon {
        position: absolute;
        left: $header_user_wrap_padding;

        top: ($header_icon_wrap_height - $header_icon_size) * 0.5;
      }

      span {
        padding-right: 24px;
        background: url("/static/images/btn_drop_grey.png") no-repeat right center / 14px 14px;
      }
    }

    .header__icon-wrap {
      width: $header_icon_wrap_width;
      position: relative;
      @extend .common-wrap;


      .header__icon {
        position: absolute;
        left: ($header_icon_wrap_width - $header_icon_size) * 0.5;
        top: ($header_icon_wrap_height - $header_icon_size) * 0.5;
      }
    }

    .note__icon-wrap:hover {

      .header__icon {
        -webkit-animation-duration: 1s;
        animation-duration: 1s;
        -webkit-animation-fill-mode: both;
        animation-fill-mode: both;
        animation-name: shake;
        transform-origin: 50% 20%;
      }

    }
  }

</style>
