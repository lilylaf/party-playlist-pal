import axios from 'axios';

export default {
    getHosts() {
        return axios.get('/hosts')
    }


}