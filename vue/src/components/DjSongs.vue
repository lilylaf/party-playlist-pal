<template>
  <div>
      <h2>I am a DJ</h2>
  </div>
</template>

<script>
import songService from '../services/SongService.js'

export default {
    name: 'DjSongs',
    data() {
        return {
            songs:null,
        }
    },
    created(){
        console.log(this.$store.state.user.authorities[0].name);
        songService.getSongsByDjId()
        .then((response) => {
            this.songs = response.data;
        })
    }
}
</script>

<style>

</style>