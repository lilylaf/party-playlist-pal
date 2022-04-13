<template>
  <div>
      <b-container>
      <h1>DJ page</h1>
      <h2>I am DJ: {{ this.$route.params.username }}</h2>
      <h2>My songs:</h2>
      <b-table striped hover :items="songs" :fields="fields"></b-table>
      </b-container>
  </div>
</template>

<script>
import songService from '../services/SongService.js'

export default {
    name: 'Dj',
    data(){
        return {
            fields: ['artistName', 'name'],
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