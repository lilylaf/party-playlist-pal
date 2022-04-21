<template>
   <div class="about">
        <img src="..\images\jampinktransp.png" />

         <b-container class="bv-example-row">
      <b-row>
        <b-col>
          <div class="deserae">
            <img class="dolphin" src="..\images\JamDolph.png" />
            <h2 class = "name"> Deserae </h2>
          </div>
        </b-col>
        <b-col><div class="lily">
          <center>
          <img class="cow"  src="..\images\LilyJamCow.png" />
          </center>
        <h2 class = "name"> Lily </h2>
        </div></b-col>
      </b-row>
       <b-row>
        <b-col>
          <div class="lindsey">
            <img src="..\images\dogmusic.gif" />
            <h2 class = "name"> Lindsey </h2>
            </div>
        </b-col>
        <b-col><div class="scott">
          <img class="beta" src="..\images\BetaPurpBlueLogo.png" />
        <h2 class = "name"> Scott </h2>
        </div></b-col>
      </b-row>
</b-container>
    </div>
      
</template>

<script>
export default {
    name: "About",
}
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Neonderthaw&display=swap');
.about{
  background-color: #090531;
  color: white;
  font-family: "Audiowide", sans-serif;
}
.name{
  font-family: "Neonderthaw";
  text-align: center;
  color: 0278E4;
}
.deserae{
    border:#003B77 5px solid;
    padding: 5px;
    border-radius: 10px;
    background-color: #01F8E9;
}
.lily{
    border:#003B77 5px solid;
    padding: 5px;
    border-radius: 10px;
    background-color: #FC05F4;
}
.lindsey{
    background-color: #01F8E9;
    border:#003B77 5px solid;
    padding: 5px;
    border-radius: 10px;
    background-color: #FC05F4;
}
.scott{
    border:#003B77 5px solid;
    padding: 5px;
    border-radius: 10px;
    background-color: #01F8E9;
   
}
.dolphin{
  height: 50vh;
  width: 65vh;
}
.beta{
  height: 50vh;
  width: 65vh;
}
.cow{
   height: 40vh;
   width: 30vh;
   
}
</style>
