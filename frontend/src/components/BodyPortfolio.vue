<template>
    <div class="container">
        <div class="row">
            <div class="col">

                <h2 class = "text-center">Historial</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col" class="text-center">SIMBOLO</th>
                            <th scope="col" class="text-center">ACCION</th>
                            <th scope="col" class="text-center">NUMERO</th>
                            <th scope="col" class="text-center">PRECIO</th>
                            <th scope="col" class="text-center">TOTAL</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr v-for="action in actionHistory" v-bind:key="action.id">
                        <th scope="row" class = "text-center">{{ action.symbol }}</th>
                        <td class = "text-center">{{action.action }}</td>
                        <td class = "text-center">{{ action.quantity }}</td>
                        <td class = "text-right">{{action.price}} €</td>
                        <td class = "text-right">{{Math.round((action.quantity * action.price + Number.EPSILON) * 100) / 100}} €</td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <div class="col">
            </div>
        </div>
    </div>

</template>

<script>
    import DataService from "../service/DataService";

    export default {
        name: "BodyPortfolio",
        data(){
            return{
                actionHistory: []
            }
        },
        mounted() {
            DataService.postLogin(localStorage.getItem('user'), localStorage.getItem('password')).then((response) => {
                console.log(response.data);
                this.actionHistory = response.data.companies.reverse();
                this.actionHistory.forEach((element) => {
                    if (element.quantity > 0){
                        //eslint-disable-next-line
                        element.action = "COMPRA"
                    } else{
                        //eslint-disable-next-line
                        element.action = "VENTA"
                    }
                });
            });
        }
    }
</script>

<style scoped>
    h2 {
        margin-bottom: 20px;
    }
</style>