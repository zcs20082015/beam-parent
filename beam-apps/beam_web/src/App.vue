<template>
  <div id="app">
    <router-view/>

  </div>
</template>

<script>

export default {
  name: 'App',
  data () {
    return {
    }
  },
  mounted() {
  },
  methods: {

  }
}
</script>

<style lang="scss">
  @import './common/style/variable.config.scss';
  @import './common/style/adapter.config.scss';


  .el-input--mini .el-input__inner {
    padding-right: 5px;
  }
  html,body {
    height:100%;
    width: 100%;
  }

  body {
    background: $bg_color;
  }

  #app {
    height:100%;

    .el-input-group__prepend {
      background-color: #fff;
    }
  }

  .container {
    width: auto;
    height: calc(100% - 60px);

    .app {
      height:100%;
    }
  }

  .common-add-content {

    .content__title {
      font-size: 16px;
      font-weight: 500;
      color: #333333;
      padding-bottom: $common_space_v;

      .router-link-active {
        color: #409EFF;
        font-size: 14px;
      }
    }

    .express-item {
      margin-top:8px;
      .radio-row {
        @include flex($al: center);
        margin-bottom: 10px;

        .xs-radio {
          margin-right: 10px;
        }

        .xs-select, .xs-input {
          width:200px;
        }

        .form__button {
          margin-left: 16px;
        }
      }
    }

    .form__button {
      font-size: 12px;
      color: #38f;
      margin-left: 5px;
    }

    .content__main {
      .section-title {
        background: #f5f7fa;
        width: 100%;
        height: 40px;
        font-size: 14px;
        font-weight: 500;
        line-height: 40px;
        padding-left: 10px;
      }

      .section-content {
        padding: $common_space 0;
      }

      .radio-row {
        background: #fbfbfb;
        border: 1px solid #ddd;
        padding: 10px;
        display: block;
        width: 300px;
        @include box-sizing();
      }
    }
  }

  .app-image-list {
    li {
      float: left;
      margin: 0 10px 10px 0;
      width: 50px;
      height: 50px;
      line-height: 54px;
      border: 1px solid #ddd;
      background-color: #fff;
      position: relative;
      cursor: pointer;

      &:hover {
        .close-modal {
          display: block;
        }
      }
      i {
        font-size: 20px;
      }

      img {
        height: 100%;
        width: 100%;
        display: block;
      }
      .close-modal {
        display: none;
        position: absolute;
        z-index: 2;
        top: -9px;
        right: -9px;
        width: 20px;
        height: 20px;
        font-size: 16px;
        line-height: 16px;
        color: #fff;
        text-align: center;
        cursor: pointer;
        background: hsla(0,0%,60%,.6);
        border-radius: 10px;
      }
    }
  }


</style>
