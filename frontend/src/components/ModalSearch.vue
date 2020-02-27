<template>
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
</template>

<script>
    import axios from "axios";

    export default {
        name: "ModalSearch",
        data() {
            return {
                stocks: []
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
            }
        }
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
</style>