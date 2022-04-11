import axios from 'axios';

export default {
    getEvents() {
        return axios.get('/events')
    }


}