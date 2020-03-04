import axios from "axios";

const API_URL = "https://sandbox.iexapis.com/stable";

class DataService {
    retrieveBasicStockData(symbol) {
        return axios.get(`${API_URL}/stock/${symbol}/company?token=${process.env.VUE_APP_API_KEY}`);
    }
    retrieveStockLogo(symbol){
        return axios.get(`${API_URL}/stock/${symbol}/logo?token=${process.env.VUE_APP_API_KEY}`);
    }
}

export default new DataService();