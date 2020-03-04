import axios from "axios";

const API_URL = "http://192.168.4.5:8080";

class DataService {
    retrieveBasicStockData() {
        return axios.get(`${API_URL}/invest`);
    }
}

export default new DataService();