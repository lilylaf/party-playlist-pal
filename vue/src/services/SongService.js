import axios from 'axios';

export default {
    getSongsByDjId(id){
        return axios.get(`/djs/${id}/songs`)

    },

    getSongsByEvent(eventId){
        return axios.get(`/events/${eventId}/songs`)
    },

  


}