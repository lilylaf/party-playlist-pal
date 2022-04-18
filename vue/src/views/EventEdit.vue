<template>
  <div>
      <h2>EDIT THIS EVENT</h2>
  
        <b-alert v-model="showDismissibleAlert" variant="success" fade dismissible>
        Successfully Updated Event
        </b-alert>
      <b-form @submit="onSubmit" v-if="isFormDataLoaded">
      <b-form-group
        id="input-group-1"
        label="Event name:"
        label-for="input-name"
        
      >
        <b-form-input
          id="input-name"
          v-model="form.name"
          placeholder="Enter Event Name"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-textarea
      id="input-information"
      v-model="form.information"
      placeholder="Enter event information..."
      rows="3"
      max-rows="6"
    ></b-form-textarea>

      <b-button type="submit" variant="primary">Update</b-button>

    </b-form>
    <br>
      <div v-if="hasPermissionToDeleteEvent">
       
            <b-button  variant="danger" v-on:click="deleteEvent()">DELETE this Event</b-button>
       
    </div>
  </div>
</template>

<script>
import eventService from '../services/EventService.js'
import hostService from '../services/HostService.js'
export default {
    name: 'EditEvent',
    data(){
        return {
            isFormDataLoaded: false,
            form: {},
            showDismissibleAlert: false
        }
    },
    computed: {
         hasPermissionToDeleteEvent(){
            if(this.$store.state.token == ''){
                return false;
            }else if(this.$store.state.user.authorities[0].name == 'ROLE_DJ' && this.$store.state.user.id == this.form.userId){
                return true;
            } else {
                return false;
            }
        },
    },
    methods: {
        onSubmit(event){
            event.preventDefault()
            const editedEvent = this.form;

            eventService.updateEvent(this.form.id, editedEvent)
            // some message back to the user that the event was updated successfully?!
            this.showDismissibleAlert = true;

        },
        deleteEvent(){
            if(confirm("Are you sure you want to delete this event?")){
               console.log("Off to delete event!");
               console.log();
               eventService.deleteEventById(this.form.id)
                .then((response)=> {
                    console.log(response)
                    this.$router.push("/");
                })
            }}
    },
    created(){
        // get the event data and load it in
        eventService.getEventById(this.$route.params.id)
            .then((response) => {
                this.form = response.data;
                this.isFormDataLoaded = true;
            });
         hostService.getHosts()
            .then((response) => {
                this.hosts = response.data;
            });
    }

}
</script>

<style>

</style>