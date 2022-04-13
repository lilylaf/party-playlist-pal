<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'login'}" v-if="$store.state.token == '' ">Login as DJ or Host | </router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
#nav{
  background-color: #0278E4;
   
}
a{
  color: #090531;
}
</style>
