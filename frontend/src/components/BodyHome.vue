<template>
    <div class="text-center" >
        <div id="frame" class="d-inline-block">
            <h1 class="d-inline-block">Proyecto 2ºDAM</h1><br/>
            <h5 class="d-inline-block">Pablo Barros y Carlos Vilas</h5>
        </div>
        <a href="https://github.com/PojoTeam/stocks_segunda"><h4>Repositorio de Github</h4></a>
    </div>
</template>

<script>
    export default {
        name: "BodyHome"
    }
</script>

<style scoped>
    h4 {
        margin-top: 50px !important;
    }
    a {
        color: dodgerblue;
    }
    h1, h5{
        padding: 40px;
    }
    #frame {
        border: solid 3px dodgerblue;
        border-radius: 5px;
    }
</style>