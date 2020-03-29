import axios from "axios";

const API_URL = "http://192.168.0.15:8090";

class DataService {
    retrieveBasicStockData() {
        return axios.get(`${API_URL}/invest`);
    }
    retrieveOneStockData(stock) {
        return axios.get(`${API_URL}/invest/${stock}`)
    }
    postLogin(user, password) {
        return axios.post(`${API_URL}/login`, {
            user: user,
            password: password
        })
    }
}

export default new DataService();