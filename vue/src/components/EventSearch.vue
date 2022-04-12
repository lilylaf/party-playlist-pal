<template>
  <div>
      <h2>Title of the Event Search</h2>
      <input type="text" placeholder="Type an event" v-model="eventSearchString" />
      <div v-for="event in filteredEvents" v-bind:key="event.id">
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
          
            eventsAll:[],
            eventSearchString: "",
        }
    },
    computed: {
        filteredEvents() {
            let events = this.eventsAll;
            let searchString = this.eventSearchString;
            let cleanSearchString = searchString.trim().toLowerCase();

             // if empty search string
            if(!searchString){
                return events;
            }

            events = events.filter((event) => {
                if(event.eventName.toLowerCase().indexOf(cleanSearchString) !== -1){
                    return event
                }
            })

            return events;
        }

    },


    created(){
        eventService.getEvents()
        .then((response) => {
            this.eventsAll = response.data;
            console.log(response.data);
        })
    }


}
</script>

<style scoped>
    
</style>