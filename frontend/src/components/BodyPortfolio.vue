<template>
    <div class="container-fluid">
        <div class="row">
            <div class="col">

                <h2 class = "text-center">Historial Movimientos</h2>
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
                        <td class = "text-right">{{action.price}} $</td>
                        <td class = "text-right">{{Math.round((action.quantity * action.price + Number.EPSILON) * 100) / 100 * -1}} $</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col">
                <h2 class = "text-center">Cartera</h2>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col" class="text-center">COMPAÑÍA</th>
                        <th scope="col" class="text-center">ACCIONES</th>
                        <th scope="col" class="text-center">$/ACCION</th>
                        <th scope="col" class="text-center">VALOR TOTAL</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="action in stackedActions" v-bind:key="action.id">
                        <th scope="row" class = "text-center">{{ action.symbol }}</th>
                        <td class = "text-center">{{ action.quantity }}</td>
                        <td class = "text-right">{{action.price}} $</td>
                        <td class = "text-right">{{Math.round((action.quantity * action.price + Number.EPSILON) * 100) / 100}} $</td>
                    </tr>
                    </tbody>
                </table>

                <h2>Vender</h2>
                <div class="d-inline-block">
                    <div class="input-group mb-3" id="dropdownCompañia">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Compañia</button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" v-on:click="electedCompany" v-for="action in stackedActions" v-bind:key="action.id">{{ action.symbol }}</a>
                            </div>
                        </div>
                        <input type="text" id="companyElegir" class="form-control" aria-label="Text input with dropdown button" readonly>
                    </div>
                </div>
                <input type="text" maxlength="4" class="form-control" id="InputBuy" @keypress="isNumber($event)">
                <button class="button" v-on:click="sell">Vender</button>
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
                actionHistory: [],
                stackedActions: [],
                company: null
            }
        },
        methods: {
            async fillStacked(){
                for (const element of this.actionHistory) {
                    if(this.stackedActions.some(el => el.symbol === element.symbol)){
                        this.stackedActions.some(el => {
                            if(el.symbol === element.symbol){
                                el.quantity = el.quantity + element.quantity
                            }
                        })
                    }else{
                        const response = await DataService.retrieveOneStockPrice(element.symbol);
                        let precioTiempoReal = response.data;
                        this.stackedActions.push({symbol: element.symbol, quantity: element.quantity, price: precioTiempoReal})
                    }
                }
            },
            electedCompany(event){
                this.company = event.currentTarget.innerHTML
                document.getElementById('companyElegir').value = this.company
            },
            isNumber: function(evt) {
                evt = (evt) ? evt : window.event;
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if ((charCode > 31 && (charCode < 48 || charCode > 57))) {
                    evt.preventDefault();
                } else {
                    return true;
                }
            },
            sell(){
                let symbol = document.getElementById('companyElegir').value;
                if(symbol !== ''){
                    let quantity = document.getElementById('InputBuy').value;
                    let companyEnCartera = this.stackedActions.filter(function(el){ return el.symbol === symbol });
                    let price = companyEnCartera[0].price;
                    let max = companyEnCartera[0].quantity;
                    let username = localStorage.getItem('user');
                    console.log(username, symbol, quantity*-1, price);
                    if(quantity < max && quantity !== 0){
                        DataService.buyActions(username, symbol, quantity*-1, price).then((response) => {
                            console.log(response)
                        });
                        location.reload();
                    }
                }
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
                this.fillStacked();
            });
        }
    }
</script>

<style scoped>
    h2 {
        margin-bottom: 20px;
    }

    .col {
        padding: 40px;
    }

    .button {
        background-color: #af2b37;
        border: none;
        border-radius: 4px;
        color: white;
        text-align: center;
        font-size: 18px;
        padding: 5px;
        cursor: pointer;
        margin-left: 20px;
    }

    #dropdownCompañia{
        width: 200px
    }

    #InputBuy{
        width: 20%;
        display: inline-block;
        margin-left: 20px;
    }

    input[type='text'] {
        font-size: 16px;
    }
</style>