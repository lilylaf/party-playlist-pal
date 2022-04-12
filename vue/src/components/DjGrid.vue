<template>
  <div>
      <h2>DJs</h2>
      <div>
         <p>A list of DJs will go here</p>
      </div>
  </div>
</template>

<script>

import djService from '../services/DJService.js'

export default {
    name: 'Djgrid',
    data(){
        return {
            djs: []
        }
    },
    created(){
        djService.getDjs()
        .then((response) => {
            this.djs = response.data;
        })
    }

}
</script>

<style>

</style>