import axios from 'axios';

export default {
    getSongsByDjId(id){
        return axios.get(`/dj/${id}/songs`)

    },

    getSongsByEvent(eventId){
        return axios.get(`/event/${eventId}/songs`)
    }


}