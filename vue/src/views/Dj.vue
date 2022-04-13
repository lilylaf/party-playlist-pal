<template>
  <div>
      <h1>DJ page</h1>
      <h2>I am DJ: {{ this.$route.params.username }}</h2>
      <h2>These are my songs:</h2>
      <div v-for="song in songs" v-bind:key="song.id" class="dj-songs">

        <h4>{{song.name}} by {{song.artistName}}</h4>
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

<style scoped>

    .dj-songs {
        display:flex;
        width: 50%;
    }
</style>