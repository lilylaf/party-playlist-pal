import axios from 'axios';

export default {
    getHosts() {
        return axios.get('/hosts')
    },

    getHostsByEventId(id) {
        return axios.get(`/hosts/${id}`)
    },

    updateHostsOnEvent(id, hostArray) {
        return axios.get(`/event/${id}/host`, hostArray)
    }


}