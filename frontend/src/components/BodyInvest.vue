<template>
    <div class="container-fluid d-flex justify-content-center" id="#listOfStocks">
        <div class="text-center">
            <div v-for="stock in stocks" v-bind:key="stock.id" class="card d-inline-block custom-fade">
                <router-link :to="{name: 'Invest2', params: { symbol: stock.symbol}}">

                    <div class="container card-body">
                        <div class="row custom-card-header">
                            <div class="col-3">
                                <img :src="stock.logo" alt="Logo de la empresa">
                            </div>
                            <div class="col-9 d-flex align-items-center justify-content-start">
                                <h5 class="companyName card-title">{{stock.companyName}}</h5>
                            </div>
                        </div>
                        <p class="descripcion">MARKET CAP: {{ stock.marketCap }} Millones</p>
                        <p class="descripcion">{{stock.description.substring(0,110)}}...</p>
                        <p class="card-text"><small class="text-muted"></small></p>
                    </div>

                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
    import DataService from "../service/DataService";

    export default {
        name: "BodyInvest",
        data(){
            return{
                intersectionObserver: null,
                elements: null,
                stocks: [],
                symbols: ["AXP","AAPL","BA","CAT","CSCO","CVX","XOM","GS","HD","IBM",
                "INTC","JNJ","KO","JPM","MCD","MMM","MRK","MSFT","NKE","PFE","PG",
                "TRV","UNH","UTX","VZ","V","WBA","WMT","DIS","DOW"]
            }
        },
        methods: {
            fadeIn(element){
                element.classList.add('fadingIn')
            },
            deleteFade(element){
                element.classList.remove('fadingIn')
            },
            listStocks(){
                DataService.retrieveBasicStockData().then((response) => {
                    this.stocks = response.data;
                });
            }
        },
        created() {
            this.listStocks();
        },
        updated() {
            this.intersectionObserver = new IntersectionObserver((entries, observer) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        // element is in viewport, do the stuff
                        if (entry.intersectionRatio > 0) {
                            this.fadeIn(entry.target);
                        }
                        observer.unobserve(entry.target);
                        /*}else{
                        this.deleteFade(entry.target);
                        }*/
                    }
                });
            });
            this.elements = [...document.querySelectorAll('.custom-fade')];
            this.elements.forEach((element) => this.intersectionObserver.observe(element));
        }
    }
</script>

<style scoped>
    @import url('https://fonts.googleapis.com/css?family=Titillium+Web:400,600&display=swap');

    img{
        width: 50px;
        height: 50px;
    }

    .card:hover{
        box-shadow: 1px 8px 20px grey;
        -webkit-transition:  box-shadow .1s ease-in;
        cursor: pointer;
    }

    .card{
        width: 18rem;
        height: 20rem;
        margin-right: 15px;
        margin-bottom: 20px;
        vertical-align: top;
        box-shadow: 1px 1px 1px black;
    }

    .companyName {
        font-family: 'Titillium Web', sans-serif;
        font-weight: 600;
        font-size: 1.2em;
        margin-bottom: 0;
    }

    .custom-fade{
        opacity: 0;
        transition: opacity .2s;
    }

    .fadingIn {
        opacity: 1;
        transition: opacity 1s;
    }

    .descripcion{
        text-align: left;
        font-family: 'Titillium Web', sans-serif;
        font-size: 1.1em;
        font-weight: 600;
        line-height: 1.7em;
        color: #767676;
        margin-top: 10px;
    }

    .custom-card-header{
        border-bottom: 1px solid rgba(0, 0, 0, 0.33);
        padding-bottom: 17px;
        text-align: left;
    }
</style>