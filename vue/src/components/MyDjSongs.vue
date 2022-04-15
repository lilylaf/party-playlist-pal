<template>
  <div>
      <h2>I am a DJ - I am logged in {{this.$store.state.user}}</h2>
      <b-container>
        <b-table striped hover :items="songs" :fields="fields"></b-table>
      </b-container>
  </div>
</template>

<script>
import songService from '../services/SongService.js'

export default {
    name: 'MyDjSongs',
    data() {
        return {
            fields: ['artistName', 'name'],
            songs:null,
        }
    },
    created(){
        
        songService.getSongsByDjId(this.$store.state.user.id)
        .then((response) => {
            console.log(response)
            this.songs = response.data;
            console.log(response)
        })
    }
}
</script>

<style>

