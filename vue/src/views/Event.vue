<template>
  <div class="Event">
      <h1>
         EVENT: {{event.name}}
      </h1>
    <h4>
        THE DEETS:  {{event.information}}
    </h4>
    <div v-if="hasPermissionToDeleteEvent"><b-button variant="danger">DELETE this Event</b-button></div>
    <div class="Table">
    <h4>The Songs:</h4>
     <b-table striped hover :items="eventSongs" :fields="fields"></b-table>
    </div>
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
    methods: {
        deleteEvent(){

        }
    },
    computed: {
        hasPermissionToDeleteEvent(){
            if(this.$store.state.user.authorities[0].name == 'ROLE_DJ' && this.$store.state.user.id == this.event.userId){
                return true;
            }else{
                return false;
            }
        },

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
.Event{
    background-color: #090531;
    color: white;
    font-family: "Audiowide", sans-serif;
}
.Table{
    background-color: #01F8E9;
    
}
</style>