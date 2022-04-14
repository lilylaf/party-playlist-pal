<template>
  <div id="app">
    <div id="nav">
  <b-nav>
    <b-button variant="outline-light"><router-link v-bind:to="{ name: 'home' }">Home</router-link></b-button>
      <b-button pill variant="outline-secondary"><router-link v-bind:to="{name: 'login'}">Login as DJ or Host </router-link> </b-button>
    <b-nav-item><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">| Logout</router-link></b-nav-item>

  </b-nav>
</div>
  <router-view />
  </div>
  
</template>
<style scoped>
#nav{
  background-color: #FC05F4;
   
}
a{
  color: #090531;
}
</style>
