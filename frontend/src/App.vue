<template>
  <div id="app" class="wrapper">
    <router-view></router-view>
    <div id="content">
      <Navbar></Navbar>
    </div>
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue'

export default {
  name: 'App',
  components: {
    Navbar
  }
}
</script>

<style>

</style>
