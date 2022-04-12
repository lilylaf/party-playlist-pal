<template>
  <div>
      <h1>
         YOUR EVENT: {{event.name}}
      </h1>
    

  </div>
</template>

<script>
import eventService from '../services/EventService.js'

export default {
    name: 'event',
    data(){
        return {  
            event: null,
        }
    },
    created(){
        //todo add error handling for the request
        eventService.getEventById(this.$route.params.id)
        .then(
            (response)=>{
                this.event = response.data; 
            }
            )
    }
}

</script>

<style>

</style>