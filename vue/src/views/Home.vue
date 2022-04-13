<template>
  <div class="home">
    <h1>Home</h1>
    <p>you don't need to be authenticated to see this</p>
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
