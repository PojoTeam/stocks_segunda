import Vue from "vue";
import Router from "vue-router";
// import VueRouter from "vue-router";
import ModalSearch from "./components/ModalSearch";
// import Navbar from "./components/Navbar";
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
                bodyInvest: null
            }
        },
        {
            path: "/portfolio",
            name: "Portfolio",
            components: {
                default: Sidebar,
                modalBtn: null,
                bodyInvest: null
            }
        },
        {
            path: "/invest",
            name: "Invest",
            components: {
                default: Sidebar,
                modalBtn: ModalSearch,
                bodyInvest: BodyInvest
            }
        }
    ]
});

export default router;