import axios from 'axios';

export default {
    getEvents() {
        return axios.get('/events')
    },

    getEventById(id){
        return axios.get(`/events/${id}`)

    },

    createEvent(newEvent){
        return axios.post('/events', newEvent)
    },

    deleteEventById(id){
        return axios.delete(`/events/${id}`)
    },
        
    // this doesn't exist yet
    getEventsByDjID(id){
        return axios.get(`/dj/${id}/events`)
    }


}