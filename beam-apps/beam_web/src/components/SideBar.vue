<template>
  <div class="sidebar">
    <div class="sidebar__logo-wrap">
      <div class="sidebar__logo" :style="{'backgroundImage': 'url(/static/images/avatar_logo.png)'}"></div>
    </div>
    <ul class="sidebar__container">
      <li v-for="item, index in sideItems" v-if="!item.exclude">
        <router-link :to="item.path">
          <template><i class="sidebar__logo" :class="item.icon?item.icon:'el-icon-tickets'"></i> {{item.name}}</template>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>

  import { routes } from '@/router'

  export default {
    name: 'sidebar',
    data () {
        return {
          sideItems: routes[1].children,
        }
    },
    computed: {
    },
    methods: {

    }
  }
</script>

<style lang="scss" >
  @import '../common/style/variable.config.scss';
  @import '../common/style/adapter.config.scss';
  @import "../common/style/animation.global.scss";

  .sidebar {
    position: fixed;
    left:0;
    top:0;
    bottom:0;
    background-image: linear-gradient(189deg, #297adc, #3b95ff);
    width: $sidebar_width;
    min-width: $sidebar_width;
    z-index: 100001;
    .el-badge__content.is-fixed {
      right: 4px;
      top: 10px;
    }
    .sidebar__logo-wrap {
      height: $logo_wrap_height;
      width: 100%;

      .sidebar__logo {
        width: $logo_size;
        height: $logo_size;
        background-size: contain;
        position: absolute;
        left: ($sidebar_width - $logo_size) * 0.5;
        top: ($logo_wrap_height - $logo_size) * 0.5;
      }
    }

    .sidebar__container {

      padding: $bar_padding_v $bar_padding_h;

      counter-reset: sidebar-index;

      .sidebar__icon {
        width: $bar_item_icon_size;
        height: $bar_item_icon_size;
        position: absolute;
        left: ($bar_item_padding_l - $bar_item_icon_size) * 0.5;
        top: ($bar_item_height - $bar_item_icon_size) * 0.5;
      }

      @for $i from 1 through 10 {
        .sidebar__icon-#{$i} { background-position: 0 (-($i - 1) * $bar_item_icon_size); }
      }



      .sidebar__icon::before {

      }

      a {
        position: relative;
        height: $bar_item_height + 4;
        line-height:$bar_item_height;
        display: block;
        color: rgba(255, 255, 255, 0.6);
        padding-left: $bar_item_padding_l;
        font-weight: 800;
        @include transition(all, 0.25s);

        &:hover, &.router-link-active {
          /*font-weight: 500;*/
          color: #fff;
          /*-webkit-animation:bounds-animation;*/
          /*-o-animation:bounds-animation;*/
          /*animation:bounds-animation 0.25s ease-in-out;*/
          /*animation-fill-mode: forwards;*/

          .sidebar__icon {
          }
        }


      }
    }
  }

</style>
