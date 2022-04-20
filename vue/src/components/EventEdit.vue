<template>
  <div>
      <h1>
          Edit Event
      </h1>
  </div>
</template>

<script>
import hostService from '../services/HostService.js'
export default {
    name: 'EventEdit',
    data() {
        return {
            hosts: []
        }

    },
        created(){
      hostService.getHosts()
        .then((response) => {
          this.hosts = response.data;
        })
    }

}
</script>

<style>

</style>