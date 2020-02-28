<template>
    <div class="container-fluid">
        <button type="button" id="modal-btn" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">
            <font-awesome-icon :icon="faSearch"/>&nbsp;&nbsp;&nbsp;&nbsp;{{ searchMessage }}
        </button>
        <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" id="app-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div id="custom-search-input">
                        <div class="input-group">
                            <input id="search" type="text" class="form-control input-lg" placeholder="Search" v-on:keyup="getCoincidences" />
                        </div>
                        <ul class="list-group">
                            <li class="list-group-item" v-for="stock in stocks" v-bind:key="stock.id" v-on:click="sendPost($event)">{{ stock }}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    import {faSearch} from '@fortawesome/free-solid-svg-icons'

    export default {
        name: "ModalSearch",
        data() {
            return {
                stocks: [],
                window: {
                    width: null
                },
                searchMessage: "Buscar Stocks"
            }
        },
        computed: {
            faSearch(){
                return faSearch
            }
        },
        methods: {
            sendPost: function(event) {
                axios({
                    method: 'post',
                    url: '/invest',
                    crossDomain: true,
                    data: {
                        stock_name: event.target.innerText
                    }
                });
            },
            handleResize(){
                if (window.innerWidth < 768) {
                    this.searchMessage = null
                }else{
                    this.searchMessage = "Buscar Stocks"
                }
            }
        },
        created() {
            window.addEventListener('resize', this.handleResize);
            this.handleResize();
        },
    }
</script>

<style scoped>
    .input-group {
        height: 50px;
        border-bottom: 2px solid gray;
    }

    .form-control {
        border-radius: 0rem !important;
        height: auto !important;
    }

    .list-group {
        border: none !important;
    }

    .list-group-item:last-child {
        border-radius: 0rem !important;
    }

    .list-group-item:first-child {
        border-radius: 0rem !important;
    }

    #modal-btn {
        position: absolute;
        left: 50%;
        transform: translatex(-50%);
    }

    @media (max-width: 768px) {
        .btn-primary{
            width: 30%
        }
    }
</style>