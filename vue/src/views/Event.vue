<template>
  <div class="Event">
      <div class="event-data">
        <h1>
            EVENT: {{event.name}}
        </h1>
        <h4>
            THE DEETS:  {{event.information}}
        </h4>
        
    </div>
       <div v-if="hasPermissionToEditEvent">
         <router-link v-bind:to="{name: 'eventEdit', params:{id: this.event.id}}">
            <b-button  variant="warning">EDIT this Event</b-button>
        </router-link>
    </div>
    <div v-if="eventSongs !== null" class="Table">
        <h4>The Songs:</h4>
        <b-table striped hover :items="eventSongs" :fields="fields"></b-table>
    </div>
  
  </div>
</template>

<script>
import eventService from '../services/EventService.js'
import songService from '../services/SongService.js'
import hostService from '../services/HostService.js'

export default {
    name: 'event',
    data(){
        return {  
            fields: ['artistName', 'name'],
            event: {},
            eventSongs: [],
            hosts: [],
            hostsForThisEvent: [],
        }
    },
    methods: {
      
    },
    computed: {
         hasPermissionToEditEvent(){
            const hasThisHostInEvent = this.hostsForThisEvent.some((host) => host.id = this.$store.state.user.id)
            if(this.$store.state.token == ''){
                return false;
                // || this.$store.state.user.id == 
            }else if(this.$store.state.user.authorities[0].name == 'ROLE_DJ' || this.$store.state.user.authorities[0].name == 'ROLE_HOST' && this.$store.state.user.id == this.event.userId || hasThisHostInEvent  ){
                return true;
            } else {
                return false;
            }
        }
    },
    created(){
        //todo add error handling for the request
        eventService.getEventById(this.$route.params.id)
        .then(
            (response)=>{
                this.event = response.data; 
            
            });
        
        songService.getSongsByEvent(this.$route.params.id)
            .then((response) => {
                this.eventSongs = response.data;
            });
        
        // this really only needs to be done for the DJ view - when logged in
        hostService.getHosts()
            .then((response) => {
                this.hosts = response.data;
            });
        hostService.getHostsByEventId(this.$route.params.id)
            .then((response) => {
                console.log(response)
                this.hostsForThisEvent = response.data;
            });
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