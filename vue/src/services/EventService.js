import axios from 'axios';

export default {
    getEvents() {
        return axios.get('/events')
    },

    getEventById(id){
        return axios.get(`/event/${id}`)

    }


}