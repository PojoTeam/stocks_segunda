<template>
    <div class="container-fluid d-flex justify-content-center">
        <div class="text-center">
            <div v-for="stock in stocks" v-bind:key="stock.id" class="card d-inline-block custom-fade">
                <div class="container card-body">
                    <div class="row">
                        <div class="col-3 d-flex justify-content-center">
                            <img :src="stock.logo" alt="Logo de la empresa">
                        </div>
                        <div class="col-9 d-flex align-items-center justify-content-start">
                            <h5 class="companyName card-title">{{stock.companyName}}</h5>
                        </div>
                    </div>
                    <p>{{stock.descripcion}}</p>
                    <p class="card-text"><small class="text-muted"></small></p>
                </div>
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
        mounted: function () {
            this.intersectionObserver = new IntersectionObserver((entries, /**observer**/) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        // element is in viewport, do the stuff
                        if (entry.intersectionRatio > 0) {
                            this.fadeIn(entry.target);
                        }
                        // it's good to remove observer,
                        // if you don't need it any more
                        // observer.unobserve(entry.target);
                    }else{
                        this.deleteFade(entry.target);
                    }
                });
            });
            this.elements = [...document.querySelectorAll('.custom-fade')];
            this.elements.forEach((element) => this.intersectionObserver.observe(element));

        },
        methods: {
            fadeIn(element){
                element.classList.add('fadingIn')
            },
            deleteFade(element){
                element.classList.remove('fadingIn')
            },
            listStocks(response, i){
                let stock = {id: i, companyName: response.data[i].companyName, descripcion: response.data[i].description, logo: response.data[i].logo};
                console.log(stock)
            }
        },
        created() {
            DataService.retrieveBasicStockData().then(function(response){
                let i = 0;
                this.listStocks(response, i);
            });
        }
    }
</script>

<style scoped>


    img{
        width: 50px;
        height: 50px;
    }

    .card{
        width: 18rem;
        height: 20rem;
        margin-right: 15px;
        margin-bottom: 20px;
        vertical-align: top;
    }

    .companyName {
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
</style>