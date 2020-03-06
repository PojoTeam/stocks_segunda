import axios from "axios";

const API_URL = "http://localhost:8090";

class DataService {
    retrieveBasicStockData() {
        return axios.get(`${API_URL}/invest`);
    }
    retrieveOneStockData(stock) {
        return axios.get(`${API_URL}/invest/${stock}`)
    }
}

export default new DataService();