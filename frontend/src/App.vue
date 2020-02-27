<template>
  <div id="app" class="wrapper">
    <Sidebar></Sidebar>
    <div id="content">
      <Navbar></Navbar>
    </div>
  </div>
</template>

<script>
import Sidebar from './components/Sidebar.vue'
import Navbar from './components/Navbar.vue'

export default {
  name: 'App',
  components: {
    Sidebar,
    Navbar
  }
}
</script>

<style>

</style>
