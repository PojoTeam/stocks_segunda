import Vue from "vue";
import Router from "vue-router";
// import VueRouter from "vue-router";
import ModalSearch from "./components/ModalSearch";
// import Navbar from "./components/Navbar";
import BodySymbol from "./components/BodySymbol";
import Sidebar from "./components/Sidebar";
import BodyInvest from "./components/BodyInvest";

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        {
            path: "/",
            name: "Home",
            components: {
                default: Sidebar,
                modalBtn: null,
                body: null
            }
        },
        {
            path: "/portfolio",
            name: "Portfolio",
            components: {
                default: Sidebar,
                modalBtn: null,
                body: null
            }
        },
        {
            path: "/invest",
            name: "Invest",
            components: {
                default: Sidebar,
                modalBtn: ModalSearch,
                body: BodyInvest
            }
        },
        {
            path: "/invest/symbol",
            name: "Invest2",
            components: {
                default: Sidebar,
                modalBtn: null,
                body: BodySymbol
            }
        }
    ]
});

export default router;