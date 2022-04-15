<template>
  <div id="app">
    <div id="nav">
  <b-nav>   

    <router-link v-bind:to="{ name: 'home' }"><b-button variant="outline-light">Home</b-button></router-link>
    <router-link v-bind:to="{name: 'login'}" v-if="$store.state.token == '' "><b-button variant="outline-light" >Login as DJ or Host</b-button></router-link> 
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"><b-nav-item>Logout</b-nav-item></router-link>
   
  </b-nav>
  
</div>
  <router-view />

  </div>
  
</template>
<style scoped>
#nav{
  background-color: #FC05F4;
  font-family: "Audiowide", sans-serif;
   
}
a{
  color: #090531;
}
</style>
