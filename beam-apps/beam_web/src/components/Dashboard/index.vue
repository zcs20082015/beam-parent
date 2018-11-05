<template>
  <div class="dashboard">
    <div class="dashboard-center">
      这里是首页
    </div>
  </div>
</template>

<script>

  import UE from 'base/ue/ue.vue'
  export default {
    components: {UE},
    data() {
      return {
        defaultMsg: '这里是UE测试',
        config: {
          initialFrameWidth: null,
          initialFrameHeight: 350
        }
      }
    },
  }
</script>
