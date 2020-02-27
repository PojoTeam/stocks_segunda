<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <button type="button" id="sidebarCollapse" v-on:click="collapseSidebar" class="btn btn-info">
                <font-awesome-icon :icon="faBars"/>
            </button>

            <button type="button" id="modal-btn" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">
                <font-awesome-icon :icon="faSearch"/>&nbsp;&nbsp;&nbsp;Buscar Monedas
            </button>

            <ModalSearch></ModalSearch>

        </div>
    </nav>
</template>

<script>
    import ModalSearch from "./ModalSearch";
    import { faBars } from '@fortawesome/free-solid-svg-icons'
    import { faSearch } from '@fortawesome/free-solid-svg-icons'


    export default {
        name: "Navbar",
        components: {
            ModalSearch
        },
        computed: {
            faBars() {
                return faBars
            },
            faSearch(){
                return faSearch
            }
        },
        methods: {
            collapseSidebar(){
                this.$root.$emit('toogleActive')
            }
        }
    }
</script>

<style scoped>
    .navbar {
        padding: 15px 10px;
        min-height: 81px;
        background: #fff;
        border: none;
        border-radius: 0;
        margin-bottom: 40px;
        box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1);
    }

    #modal-btn {
        position: absolute;
        left: 50%;
        transform: translatex(-50%);
    }
</style>