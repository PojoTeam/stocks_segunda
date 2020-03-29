import axios from "axios";

require('dotenv').config()
const API_URL = "http://localhost:8090";
const API_IEX_URL = "https://cloud.iexapis.com/stable/stock/";

class DataService {
    retrieveBasicStockData() {
        return axios.get(`${API_URL}/invest`);
    }
    retrieveOneStockData(stock) {
        return axios.get(`${API_URL}/invest/${stock}`)
    }
    retrieveLast5DaysPrices(stock) {
        console.log(`${API_IEX_URL}${stock}/chart/5d?token=${process.env.VUE_APP_API_KEY}`)
        return axios.get(`${API_IEX_URL}${stock}/chart/5d?token=${process.env.VUE_APP_API_KEY}`)
    }
    postLogin(user, password) {
        return axios.post(`${API_URL}/login?userName=${user}&password=${password}`)
    }
    postRegister(user, password) {
        return axios.post(`${API_URL}/register?userName=${user}&password=${password}`)
    }
}

export default new DataService();