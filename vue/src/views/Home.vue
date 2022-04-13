<template>
    
  <div class="Home">
    <h1>Party Animals</h1>
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
  /* main {
    display: grid;
    grid-template-areas:
    "event dj";
    grid-template-columns: 1fr 1fr;
    column-gap: 10px;
} */

/* .event-search {
  grid-area:event;

}

.dj-grid {
  grid-area: dj;
} */

</style>
