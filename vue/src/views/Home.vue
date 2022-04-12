<template>
  <div class="home">
    <h1>Home</h1>
    <p>you don't need to be authenticated to see this</p>
      <main>
        <event-search />
        <dj-grid />
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
  main {
    display: flex;
}
</style>
