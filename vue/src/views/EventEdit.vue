<template>
  <div>
      <h2>EDIT THIS EVENT</h2>
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

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
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
        }
    },
    methods: {

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