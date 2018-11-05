<template>
  <SecondRouter :route-info="route"></SecondRouter>
</template>

<script>

  import SecondRouter from '@/components/second-router.vue';
  import Route from '@/router/setting'


  export default {
    name: 'router',
    data() {
      return {
        route: Route
      }
    },
    components: {SecondRouter},
  }
</script>
