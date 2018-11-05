<template>
  <div class="common-list-content">
    <slot name="title"></slot>

    <div class="content__main">
      <slot name="header">

      </slot>
      <div class="content__main-list">
        <slot name="list">

        </slot>

      </div>

    </div>
  </div>

</template>

<script>

</script>


<style lang="scss">
  @import "../common/style/adapter.config.scss";
  @import "../common/style/variable.config.scss";


  .common-list-content {

    .content__title {
      font-size: 16px;
      font-weight: 500;
      color: #333333;
      padding-bottom: $common_space_v;
    }

    .content__main {

      .content__main-operation {
        background: #f5f7fa;
        padding: $common_space_v $common_space 0;
        @include flex($al: center, $jc: space-between);
        @include flex-wrap();

        .operation-item {
          @include flex($al: center);
          margin-right: 20px;

          > span {
            margin-right: 6px;
          }
        }

        .operation-left, .operation-right {
          margin-bottom: $common_space_v;
          @include flex($al: center);

          .el-select, .el-input-group {
            width: 120px;
            margin-left: 10px;
          }
          .el-select:first-child {
            margin-left: 0;
          }
          .el-input-group {
            width:200px;
          }
        }
      }

      .content__main-list {
        margin-top: 10px;

        .el-table  {
          th, tr {
            background-color: transparent;

            &:hover {
            }

          }
          thead {

            background: #f5f7fa;
          }
        }


        .goods-item {
          @include flex($al:center);

          .item__image {
            width:60px;
            height: 60px;
            display: block;
            @include flex-full(0);
            flex: 0 0 auto;
            background: no-repeat center center / 60px;
            margin-right: 10px;
          }

          .item__name {
            overflow: hidden;
            text-overflow: ellipsis;
            display: box;
            display: -webkit-box;
            line-clamp: 2;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            margin-right: $common_space;
          }
        }

        .info-item {
          @include flex($al:center);

          .item__image {
            width:60px;
            height: 60px;
            display: block;
            @include flex-full(0);
            flex: 0 0 auto;
            background: no-repeat center center / 60px;
            margin-right: 10px;
          }

          .item__info {
            @include flex(column);
            .item__info-main {
              overflow: hidden;
              text-overflow: ellipsis;
              display: box;
              display: -webkit-box;
              line-clamp: 2;
              -webkit-line-clamp: 2;
              -webkit-box-orient: vertical;
              font-size: 14px;
              margin-right: $common_space;
            }

            .item__info-sub {
              color: #959595;
              font-size: 13px;
            }
          }
        }
      }
    }
  }

</style>

