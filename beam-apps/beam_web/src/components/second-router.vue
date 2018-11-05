<template>
  <div id="second-router">
    <div class="router-left" v-if="!routeInfo.hidden_child">
      <ul class="second-menu" v-if="routeInfo">
        <li class="menu__item" v-for="item in routeInfo.children || []" v-if="item.name">
          {{item.name}}
          <ul>
          <li  v-for="sub_item, index in item.children || []" v-if="!sub_item.exclude" >
            <router-link v-if="!sub_item.disabled" class="menu-sub__item"
                         @click.native="c(routeInfo.path + '/' + item.path + '/' + sub_item.path)"
                         :to="routeInfo.path + '/' + item.path + '/' + sub_item.path"><el-badge v-if="badge && badge.index === index && badge.val > 0" :value="badge.val">{{sub_item.name}}</el-badge><template v-else>{{sub_item.name}}</template></router-link>
            <span v-else="!sub_item.disabled" class="menu-sub__item disabled" @click="$message.warning('敬请期待！！！')">{{sub_item.name}}</span>
          </li>
        </ul>

        </li>

      </ul>

    </div>
    <div class="router-right">

      <div class="router-right__content">
        <router-view></router-view>
      </div>

    </div>
  </div>

</template>

<script>

  export default {
    props: [
      'routeInfo',
      'badge'
    ],
    methods: {
      c(path) {
//        location.reload()
      }
    }

  }
</script>


<style lang="scss">
  @import "../common/style/adapter.config.scss";
  @import "../common/style/variable.config.scss";

  $sub_item_width: 96px;
  $sub_item_height: 30px;

  #second-router {
    @include flex();
    height:100%;
    .router-left {
      padding-right: 12px;

      .second-menu {
        padding: $common_space;
        .menu__item {
          font-size: 12px;
          font-weight: 500;
          color: rgba(51, 51, 51, 0.3);
          margin-bottom: $common_space;

          ul {
            margin-top: 8px;
            li {
              font-size: 14px;
              margin-bottom: 3px;
              color: rgba(51, 51, 51, 0.6);

              .menu-sub__item {
                padding: 0 12px;
                display: block;
                @include box-sizing();
                width: $sub_item_width;
                height: $sub_item_height;
                line-height: 30px;
                position: relative;
                z-index:0;

                &.disabled {
                  color: lightgrey;
                  cursor: no-drop;

                  &:hover {
                    color: lightgrey;
                    &:after {
                      background: none;
                    }
                  }
                }
                &:hover, &.router-link-active {
                  color: #0087f8;
                  &:after {
                    background: #cee7fd;
                    width: $sub_item_width;

                  }
                }

                &:after {
                  content: '';
                  position: absolute;
                  left: 0;
                  top:0;
                  background: transparent;
                  z-index: -1;
                  width:0;
                  border-radius: 4px;
                  height: $sub_item_height;

                  @include transition(all, 0.25s);

                }
              }
            }
          }
        }
      }

    }

    .router-right {
      @include flex-full();

      padding-top: $common_space;
      padding-right: $common_space;

      .router-right__content {
        overflow-y: scroll;

        height: calc(100% - 20px);
        border-radius: 4px;
        background: #fff;
        padding: $common_space_v $common_space;
        @include box-sizing();
      }
    }

  }


</style>

