<template>
  <table class="xs-sku-table">
    <thead>
      <slot name="head"></slot>
    </thead>

    <template v-if="update">
      <tbody v-sortable="{onUpdate: onUpdate}">
        <slot name="body"></slot>
      </tbody>
    </template>
    <template v-else>
      <tbody>
      <slot name="body"></slot>
      </tbody>
    </template>

    <tfoot>
      <slot name="foot"></slot>
    </tfoot>

  </table>
</template>

<script>
  export default {
    name: 'xs-sku-table',
    props: {
      update: {
        type: Boolean,
        default: false

      },
    },

    methods: {
      onUpdate(val) {
          this.$emit('update', val);
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import "~common/style/adapter.config";

  .xs-sku-table {
    width: 100%;
    background-color: #fff;
    text-align: left;

    font-size: 12px;
    line-height: 1.3;

    .column {
      @include flex(column);

      .el-checkbox {
        margin-left: 0;
      }
    }

    th {
      padding: 10px 8px;
      font-weight: 400;
      font-size: 12px;

      &.th-saleprice {
        width: 100px;
      }

      &.th-baseprice {
        width: 100px;
      }

      &.th-stock {
        width: 20%;
      }

      &.th-operation {
        width: 100px;
        text-align: right;
      }
    }


    td {
      border: 1px solid #e5e5e5;
      padding: 8px;
      word-break: break-word;
      font-size: 12px;
      color: #333;

      &:first-of-type {
        border-left: none;
      }

      &:last-of-type {
        border-right: none;
        text-align: right;
      }
    }

    .setting-wrap {
      @include flex($al:center);

      span {
        flex: 0 0 auto;
      }

      .xs-input {
        width:auto;
        margin-left: 10px;
        flex: 2;
      }

      .xs-button {
        margin-left: 10px;
      }
      .setting__total-wrap {
        @include flex($al: center);
        margin-left: 30px;
        flex:1;
        min-width: 150px;
      }
    }

  }
</style>
