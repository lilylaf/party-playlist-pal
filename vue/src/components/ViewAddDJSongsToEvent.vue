<template>
  <div>
      <div v-if="dj">
          <b-container class="dj-songs">
              <h4>
                Featuring {{dj.username}} 
              </h4>
        <b-table class="Table dj-library"  striped hover :items="allDjSongs" :fields="fields"  @row-clicked="songRowClickHandler"></b-table>
      </b-container>
      </div>
      
     
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
            allDjSongs: [], // filter out what is already in the event playlist
            
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
                console.log("trying to get songs by dj:")
                console.log(response.data)
                
                this.allDjSongs = response.data;
            })
    }
}
</script>

<style scoped>
.dj-songs {
    color: white;
}

</style>