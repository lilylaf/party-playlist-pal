<template>
    
  <div class="Home">
    <img src="..\images\jampinktransp.png" />
    <p>you don't need to be authenticated to party</p>
      <b-container class="bv-example-row">
      <b-row>
        <b-col>
          <event-search class="event-search" />
        </b-col>
        <b-col><dj-grid class="dj-grid" /></b-col>
      </b-row>
</b-container>
      <main>
    <head>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Audiowide">

    </head>
        
      </main>
      
      
  </div>
</template>

<script>
import DjGrid from '../components/DjGrid.vue';
import EventSearch from '../components/EventSearch.vue';

export default {
  components: { 
    EventSearch,
    DjGrid  },
  name: "home"
};
</script>

<style scoped>

.Home{
  background-color: #090531;
  color: white;
  font-family: "Audiowide", sans-serif;
}

.event-search{
  background-color: darkmagenta;
}

.dj-grid{
  background-color: #01F8E9;
}

.event-search{
  background-color: #FC05F4;
}

.Home{
  text-align: center;
}
</style>
