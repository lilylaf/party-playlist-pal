<template>
  <div>
      <h5>
          Edit Genres
      </h5>
      
      <div :v-for="genre in genres" v-bind:key="genre">
          <p>{{ genre }}</p>
      </div>

  </div>
</template>

<script>
import genreService from '../services/GenreService.js'
export default {
    name: 'EditGenres',
    props: ['userId'],
    data() {
        return {
            genres: []
        }
    },
    created(){
        genreService.getGenresByDj(this.userId)
            .then((response) => 
               this.genres = response.data
            )
    }
}
</script>

<style>

</style>