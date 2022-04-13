<template>
  <div>
      <h1>
         YOUR EVENT: {{event.name}}
      </h1>
    <h4>
        THE DEETS:  {{event.information}}
    </h4>
    <h4>The Songs:</h4>
     <b-table striped hover :items="eventSongs" :fields="fields"></b-table>

  </div>
</template>

<script>
import eventService from '../services/EventService.js'
import songService from '../services/SongService.js'

export default {
    name: 'event',
    data(){
        return {  
            fields: ['artistName', 'name'],
            event: null,
            eventSongs: null
        }
    },
    created(){
        //todo add error handling for the request
        eventService.getEventById(this.$route.params.id)
        .then(
            (response)=>{
                this.event = response.data; 
            }
            );
        
        songService.getSongsByEvent(this.$route.params.id)
        .then((response) => {
            this.eventSongs = response.data;
        })

    }
}

</script>

<style>

</style>