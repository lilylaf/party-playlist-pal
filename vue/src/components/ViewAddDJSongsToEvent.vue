<template>
  <div>
      <h3>{{ dj.username }} Library</h3>
    
      <b-container class="dj-songs">
        <b-table class="Table dj-library"  striped hover :items="allDjSongs" :fields="fields"  @row-clicked="songRowClickHandler"></b-table>
      </b-container>
      <!-- <div v-for="song in allDjSongs" :key="song.id">
          <p>{{song.name}} - {{song.artistName}}</p>
      </div> -->
  </div>
</template>

<script>
import songService from '../services/SongService.js'

export default {
    name: 'ViewAddDjSongsToEvent',
    props:['dj', 'eventSongs'],

    data(){
        return {
             fields: ['artistName', 'name', 'event'],
            allDjSongs: [], // fil;ter out what is already in the event playlist
            
        }
    },
    methods: {
        songRowClickHandler(record){
            if(confirm("Add " + record.name + " by " + record.artistName + " to the event playlist" )){
                // console.log(record.name + " will be added")
                // console.log(this.$route.params.id)
                // console.log(record.id)
                const requestBody = {};
                requestBody.eventId = this.$route.params.id;
                requestBody.songId = record.id;
                songService.addSongToEventPlaylist(requestBody)
                    .then((response) => {
                        console.log(response.data)
                        
                    })
            }
            
        }
    },
    created(){
        songService.getSongsByDjId(this.dj.id)
            .then((response) => {
                
                // console.log(response.data)
                // const responseOfDjSongs = response.data;
                // const filteredDjLibrary = responseOfDjSongs.filter((song) => {
                    
                // })



                this.allDjSongs = response.data
            }
                )
    }
}
</script>

<style scoped>
.dj-songs {
    color: white;
}

</style>