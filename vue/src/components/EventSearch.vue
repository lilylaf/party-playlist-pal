<template>
  <div>
      <h2>This is the title of the Event Search</h2>
      <div v-for="event in events" v-bind:key="event.id">
         <hr>
          <h2>{{ event.eventName }}</h2>
          <p>{{ event.information }}</p>
      </div>
  </div>
</template>

<script>
import eventService from '../services/EventService.js'


export default {
    name: 'EventSearch',
    data(){
        return {
            events:[]
        }
    },

    created(){
        eventService.getEvents()
        .then((response) => {
            this.events = response.data;
            console.log(response.data);
        })
    }


}
</script>

<style scoped>
    
</style>