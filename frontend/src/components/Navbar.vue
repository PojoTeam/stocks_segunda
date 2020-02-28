<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <button type="button" id="sidebarCollapse" v-on:click="collapseSidebar" class="btn btn-info">
                <font-awesome-icon :icon="faBars"/>
            </button>

            <router-view name="modalBtn"></router-view>

        </div>
    </nav>
</template>

<script>
    import { faBars } from '@fortawesome/free-solid-svg-icons'

    export default {
        name: "Navbar",
        computed: {
            faBars() {
                return faBars
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
</style>