<template>
    <div>
        <div class="container text-center">
            <h1><img :src="stock[0].logo" alt="Logo de la empresa">{{symbol}} | {{stock[0].companyName}}</h1>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm text-center">
                    <line-chart class="small d-inline-block" :chartdata="datacollection" :options="options"></line-chart>
                </div>
                <div class="col-sm">
                    <div class="frame text-center">
                        <div id="borde">
                            <h4 class="d-inline">Precio tiempo real </h4><br/>
                            <p class="d-inline">{{ precioTiempoReal }} $</p>
                        </div>
                        <div class="form-group text-center">
                            <label for="InputBuy">Comprar Acciones</label><br/>
                            <input type="text" maxlength="4" class="form-control" id="InputBuy" @keypress="isNumber($event)">
                            <button class="button" v-on:click="comprar">Comprar</button>
                            <div class="alert alert-success alert-dismissible fade show" role="alert" v-if="alertaUp">
                                Compra realizada
                                <button type="button" class="close" aria-label="Close" v-on:click="alertaUp = false">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import DataService from "../service/DataService";
    import LineChart from './LineChart.js'

    export default {
        name: "BodySymbol",
        components: {
            LineChart
        },
        data() {
            return {
                symbol: this.$route.params.symbol,
                stock: [],
                dataPoints: [],
                dataPrices: [],
                dataDays: [],
                alertaUp: false,
                precioTiempoReal: null,
                datacollection: null,
                options: null
            }
        },
        created() {
            DataService.retrieveOneStockData(this.$route.params.symbol).then((response) => {
                this.stock.push(response.data);
            });
            console.log(this.stock);
            DataService.retrieveOneStockPrice(this.$route.params.symbol).then((response) => {
                console.log(response.data);
                this.precioTiempoReal = response.data
            });
            DataService.retrieveLast5DaysPrices(this.$route.params.symbol).then((response) => {
                console.log(response.data);
                for(let i = 0; i < response.data.length; i++){
                    this.dataPoints.push({x: i+1, y: response.data[i].close});
                    this.dataPrices.push(response.data[i].close);
                    this.dataDays.push(response.data[i].label)
                }
                this.datacollection =  {
                    datasets: [
                        {
                            backgroundColor: '#4d84f8',
                            data: this.dataPoints,
                            "lineTension":0,
                            "borderColor":"rgb(0,2,255)",
                            "pointBorderColor":"rgb(0, 0, 0)",
                            "pointBackgroundColor": "rgb(0,0,0)",
                            "fill": true
                        }
                    ]
                };
                let dataDays = this.dataDays;
                this.options = {
                    responsive: true,
                    maintainAspectRatio: true,
                    legend: {
                        display: false
                    },
                    scales: {
                        xAxes: [{
                            type: 'linear',
                            display: true,
                            ticks: {
                                max: 5,
                                min: 1,
                                stepSize: 1,
                                callback: function(value, index) {
                                    return dataDays[index];
                                }
                            },
                            scaleLabel: {
                                display: false
                            },
                        }],
                        yAxes: [{ // and your y axis customization as you see fit...
                            display: true,
                            ticks: {
                                max: Math.round((Math.max(...this.dataPrices) * 1.05)),
                                min: Math.round((Math.min(...this.dataPrices) * 0.95)),
                                stepSize: 5,
                                callback: function(value) {
                                    return  value + '$';
                                }
                            },
                            scaleLabel: {
                                display: false,
                                labelString: 'Valor €'
                            }
                        }],
                    }
                }
            });
        },
        methods: {
            isNumber: function(evt) {
                evt = (evt) ? evt : window.event;
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if ((charCode > 31 && (charCode < 48 || charCode > 57))) {
                    evt.preventDefault();
                } else {
                    return true;
                }
            },
            comprar: function(){
                let quantity = document.getElementById('InputBuy').value;
                if(quantity !== "" || quantity !== "0" || quantity !== "00" || quantity !== "0000" || quantity !== "000"){
                    let symbol = this.$route.params.symbol;
                    let username = localStorage.getItem('user');
                    var price = this.precioTiempoReal;
                    DataService.buyActions(username, symbol, quantity, price).then((response) => {
                        console.log(response)
                    });
                }
                document.getElementById('InputBuy').value = "";
                this.alertaUp = true;
            }
        }
    }
</script>

<style scoped>
    @import url('https://fonts.googleapis.com/css?family=Titillium+Web:400,600&display=swap');

    *{
        font-family: 'Titillium Web', sans-serif;
        font-weight: 600;
        font-size: calc(30% + 1vw + 1vh);
    }

    .container h1{
        border-bottom: 1px solid black;
    }

    img {
        width: 5%;
        margin-bottom: 10px;
        margin-right: 20px;

    }

    .frame {
        margin-top: 50px;
        border: 2.5px solid black;
        border-radius: 5px;
    }

    h4 {
        font-size: calc(5% + 1vw + 1vh);
        color: #767676;
    }

    p {
        font-size: calc(5% + 1vw + 1vh);
        color: #557f53;
    }

    .small{
        margin-top: 60px;
    }

    label {
        font-size: calc(5% + 1vw + 1vh);
        color: #767676;
    }

    input[type='text'] {
        font-size: 25px;
    }

    .form-control{
        display: inline-block;
        width: 120px;
        height: calc(0.6em + .75rem + 2px);
        margin-left: 20px;
    }

    .form-group{
        padding: 40px;
    }

    #borde {
        border-bottom: 2.5px solid black;
        padding: 40px;
    }

    .alert{
        font-size: 15px;
        margin-top: 10px;
        margin-bottom: 0px;
    }

    .close{
        padding: .1rem 1.25rem;
    }

    .button {
        background-color: #4CAF50;
        border: none;
        border-radius: 4px;
        color: white;
        text-align: center;
        font-size: 24px;
        cursor: pointer;
        margin-left: 20px;
    }


</style>