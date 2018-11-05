<template>
    <div id="home">
      <sidebar></sidebar>
      <div class="container">
        <headerbar></headerbar>

        <div class="app">
          <div class="app-inner">
            <transition :name="transitionName" mode="out-in">
              <router-view/>
            </transition>
          </div>
        </div>

      </div>
    </div>
</template>

<script>
  import Sidebar from './SideBar.vue';
  import Headerbar from './HeaderBar.vue'

  export default {
    name: 'HOME',
    components: {
      Sidebar,
      Headerbar
    },
    data() {
      return {
        transitionName: 'slide-bottom'
      }
    },
    mounted() {
    },
    methods: {

    },
  }
</script>

<style lang="scss">
  @import '../common/style/variable.config.scss';

  #home {
    height: 100%;

  }

  .container {
    padding-left: $sidebar_width;
    padding-top: $header_bar_height;
    width: auto;
    height: calc(100% - 60px);

    .app {
      height:100%;
    }

    .app-inner {
      height: 100%;
      /*padding: 20px;*/
      background: $bg_color;
      zoom: 1;
    }
  }

  .slide-bottom-leave-active {
    opacity: 0;
    transition: all .25s cubic-bezier(.55,0,.1,1);
    -webkit-transform: translate(0, 100px);
    transform: translate(0, 30px);
  }
  .slide-bottom-enter {
    /*opacity: 0;*/
    -webkit-transform: translate(0, -30px);
    transition: all .5s cubic-bezier(.55,0,.1,1);
    transform: translate(0, -30px);
  }
</style>
