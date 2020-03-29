import Vue from "vue";
import Router from "vue-router";
// import VueRouter from "vue-router";
import ModalSearch from "./components/ModalSearch";
// import Navbar from "./components/Navbar";
import SignUp from "./components/SignUp";
import LogIn from "./components/LogIn";
import BodySymbol from "./components/BodySymbol";
import Sidebar from "./components/Sidebar";
import BodyInvest from "./components/BodyInvest";
import BodyHome from "./components/BodyHome";

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
                body: BodyHome
            }
        },
        {
            path: "/portfolio",
            name: "Portfolio",
            components: {
                default: Sidebar,
                modalBtn: null,
                body: BodyHome
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
            path: "/invest/:symbol",
            name: "Invest2",
            components: {
                default: Sidebar,
                modalBtn: null,
                body: BodySymbol
            },
            props: true
        },
        {
            path: "/login",
            name: "Log In",
            components: {
                default: Sidebar,
                modalBtn: null,
                body: LogIn
            }
        },
        {
            path: "/signup",
            name: "Sign Up",
            components: {
                default: Sidebar,
                modalBtn: null,
                body: SignUp
            }
        }
    ]
});

router.beforeEach((to, from, next) => {
    // redirect to login page if not logged in and trying to access a restricted page
    const publicPages = ['/', '/login', '/signup'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        return next({
            path: '/login',
            query: { returnUrl: to.path }
        });
    }

    next();
});

export default router;