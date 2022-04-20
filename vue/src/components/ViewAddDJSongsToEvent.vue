<template>
  <div>
      <h3>DJ Songs Component</h3>
      <div v-for="song in allDjSongs" :key="song.id">
          <p>{{song.name}} - {{song.artistName}}</p>
      </div>
  </div>
</template>

<script>
import songService from '../services/SongService.js'

export default {
    name: 'ViewAddDjSongsToEvent',
    props:['dj'],

    data(){
        return {
            allDjSongs: [], // fil;ter out what is already in the event playlist
            
        }
    },
    created(){
        songService.getSongsByDjId(this.dj.id)
            .then((response) => {
                console.log(response.data)
                this.allDjSongs = response.data
            }
                )
    }
}
</script>

<style>

</style>