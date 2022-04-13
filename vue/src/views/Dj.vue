<template>
  <div>
      <h1>DJ page</h1>
      <h2>I am DJ: </h2>
      <h4>These are my songs:</h4>
      <div v-for="song in songs" v-bind:key="song.id">
          <hr>
        <h5>{{song.name}} by {{song.artistName}}</h5>
        

      </div>
  </div>
</template>

<script>
import songService from '../services/SongService.js'

export default {
    name: 'Dj',
    data(){
        return {
            songs: null
        }
    },
    created(){
        songService.getSongsByDjId(this.$route.params.id)
        .then((response) => {
            console.log(response)
            this.songs = response.data;
        })
    }

}
</script>

<style>

</style>