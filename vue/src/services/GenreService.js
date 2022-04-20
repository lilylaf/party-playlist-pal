import axios from 'axios';

export default {
    getGenresByDj(id) {
        return axios.get(`/dj/${id}/genres`)
    }


}



