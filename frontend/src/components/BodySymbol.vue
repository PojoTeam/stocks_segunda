<template>
    <div>
        <div class="container">
            <h1><img :src="stock[0].logo" alt="Logo de la empresa">{{symbol}} | {{stock[0].companyName}}</h1>
        </div>
        <div class="small">
            <line-chart :chartdata="datacollection" :options="options"></line-chart>
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
                datacollection: null,
                options: null
            }
        },
        created() {
            DataService.retrieveOneStockData(this.$route.params.symbol).then((response) => {
                this.stock.push(response.data);
            });
            console.log(this.stock);
            DataService.retrieveLast5DaysPrices(this.$route.params.symbol).then((response) => {
                console.log(response.data);
                for(let i = 0; i < response.data.length; i++){
                    this.dataPoints.push({x: i+1, y: response.data[i].close})
                    this.dataPrices.push(response.data[i].close)
                }
                this.datacollection =  {
                    datasets: [
                        {
                            backgroundColor: '#f87979',
                            data: this.dataPoints,
                            "lineTension":0,
                            "borderColor":"rgb(117,116,192)",
                            "pointBorderColor":"rgb(0, 0, 0)",
                            "pointBackgroundColor": "rgb(0,0,0)",
                            "fill": false
                        }
                    ]
                };
                this.options = {
                    responsive: true,
                    maintainAspectRatio: true,
                    legend: {
                        display: false
                    },
                    scales: {
                        xAxes: [{
                            type: 'linear', // MANDATORY TO SHOW YOUR POINTS! (THIS IS THE IMPORTANT BIT)
                            display: true, // mandatory
                            ticks: {
                                max: 5,
                                min: 1,
                                stepSize: 1
                            },
                            scaleLabel: {
                                display: true, // mandatory
                                labelString: 'Dias' // optional
                            },
                        }],
                        yAxes: [{ // and your y axis customization as you see fit...
                            display: true,
                            ticks: {
                                max: (Math.max(...this.dataPrices) * 1.05),
                                min: (Math.min(...this.dataPrices) * 0.95),
                                stepSize: 10,
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

    .container{
        border-bottom: 1px solid black;
    }

    img {
        width: 5%;
        margin-bottom: 10px;
        margin-right: 20px;

    }

    .small {
        max-width: 600px;
        margin:  150px auto;
    }

</style>