<template>
  <div class="home">
    <h1>Home</h1>
    <p>you don't need to be authenticated to see this</p>
    <event-search />
  </div>
</template>

<script>
import EventSearch from '../components/EventSearch.vue';
export default {
  components: { EventSearch },
  name: "home"
};
</script>
