import axios from "axios";

const API_URL = "http://localhost:8090";

class DataService {
    retrieveBasicStockData() {
        return axios.get(`${API_URL}/invest`);
    }
}

export default new DataService();