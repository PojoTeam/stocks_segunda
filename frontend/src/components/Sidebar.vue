<template>
    <nav id="sidebar" v-bind:class="{ active: isActive}">

        <div class="sidebar-header">
            <h3>Stocks</h3>
            <strong>ST</strong>
        </div>

        <ul class="list-unstyled components">
            <li>
                <router-link class="nav-link" to="/">Home</router-link>
            </li>
            <li>
                <router-link v-if="authenticated" class="nav-link" to="/portfolio">Portfolio</router-link>
            </li>
            <li>
                <router-link v-if="authenticated" class="nav-link" to="/invest">Invest</router-link>
            </li>
            <li>
                <router-link v-if="!authenticated" class="nav-link" to="/login">Log In</router-link>
            </li>
            <li>
                <a href="/" v-if="authenticated" v-on:click="logout" class="nav-link">Log Out</a>
            </li>
            <li>
                <router-link v-if="!authenticated" class="nav-link" to="/signup">Sign Up</router-link>
            </li>
        </ul>

    </nav>
</template>

<script>
    import router from "../routes";

    export default {
        name: "sidebar",
        data() {
            return {
                isActive: false,
                authenticated: false
            }
        },
        mounted: function () {
            this.$root.$on('toogleActive', () => {
                this.isActive = !this.isActive
            })
        },
        created() {
            let user = localStorage.getItem('user');
            if (user != null){
                this.authenticated = true
            }else{
                router.push("/login")
            }
        },
        methods: {
            logout() {
                console.log("removing")
                localStorage.removeItem('user');
                localStorage.removeItem('password');
                router.push("/")
                location.reload()
            }
        }
    }
</script>

<style scoped>
    #sidebar {
        min-width: 250px;
        max-width: 250px;
        min-height: 100vh;
        background: #7386D5;
        color: #fff;
        transition: all 0.3s;
    }

    #sidebar.active {
        min-width: 80px;
        max-width: 80px;
        text-align: center;
    }

    #sidebar.active .sidebar-header h3,
    #sidebar.active .CTAs {
        display: none;
    }

    #sidebar.active .sidebar-header strong {
        display: block;
    }

    #sidebar ul li a {
        text-align: left;
    }

    #sidebar.active ul li a {
        padding: 20px 10px;
        text-align: center;
        font-size: 0.85em;
    }

    #sidebar.active ul li a i {
        margin-right: 0;
        display: block;
        font-size: 1.8em;
        margin-bottom: 5px;
    }

    #sidebar.active ul ul a {
        padding: 10px !important;
    }

    #sidebar.active .dropdown-toggle::after {
        top: auto;
        bottom: 10px;
        right: 50%;
        -webkit-transform: translateX(50%);
        -ms-transform: translateX(50%);
        transform: translateX(50%);
    }

    #sidebar .sidebar-header {
        padding: 20px;
        background: #6d7fcc;
    }

    #sidebar .sidebar-header strong {
        display: none;
        font-size: 1.8em;
    }

    #sidebar ul.components {
        padding: 20px 0;
        border-bottom: 1px solid #47748b;
    }

    #sidebar ul li a {
        padding: 10px;
        font-size: 1.1em;
        display: block;
    }

    #sidebar ul li a:hover {
        color: #7386D5;
        background: #fff;
    }

    #sidebar ul li a i {
        margin-right: 10px;
    }

    #sidebar ul li.active>a,
    a[aria-expanded="true"] {
        color: #fff;
        background: #6d7fcc;
    }

    a[data-toggle="collapse"] {
        position: relative;
    }

    ul ul a {
        font-size: 0.9em !important;
        padding-left: 30px !important;
        background: #6d7fcc;
    }

    ul.CTAs a {
        text-align: center;
        font-size: 0.9em !important;
        display: block;
        border-radius: 5px;
        margin-bottom: 5px;
    }

    @media (max-width: 768px) {
        #sidebar {
            min-width: 80px;
            max-width: 80px;
            text-align: center;
            margin-left: -80px !important;
        }

        #sidebar.active {
            margin-left: 0 !important;
        }
        #sidebar .sidebar-header h3,
        #sidebar .CTAs {
            display: none;
        }
        #sidebar .sidebar-header strong {
            display: block;
        }
        #sidebar ul li a {
            padding: 20px 10px;
        }
        #sidebar ul li a span {
            font-size: 0.85em;
        }
        #sidebar ul li a i {
            margin-right: 0;
            display: block;
        }
        #sidebar ul ul a {
            padding: 10px !important;
        }
        #sidebar ul li a i {
            font-size: 1.3em;
        }
        #sidebar {
            margin-left: 0;
        }
        #sidebarCollapse span {
            display: none;
        }
    }

</style>