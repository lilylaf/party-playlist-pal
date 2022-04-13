import axios from 'axios';

export default {
    getSongsByDjId(id){
        return axios.get(`/dj/${id}/songs`)

    }


}