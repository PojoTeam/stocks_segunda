<template>
    <div class="container-fluid d-flex justify-content-center align-items-start">
        <div class="card">
            <form class="card-body">
                <h2>Sign Up</h2>
                <div class="form-group">
                    <label for="InputUsername">Nombre de Usuario</label>
                    <input type="email" class="form-control" id="InputUsername" aria-describedby="emailHelp">
                </div>
                <div class="form-group">
                    <label for="InputPassword">Contraseña</label>
                    <input type="password" class="form-control" id="InputPassword">
                </div>
                <button type="submit" class="btn btn-primary" v-on:click="login">Sign Up</button>
                <div class="alert alert-danger" v-if="error" role="alert">
                    Credenciales erroneas
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Register"
    }
</script>

<style scoped>
    .container-fluid {
        height: calc(100vh - 121px);
    }

    .card {
        width: 18rem;
    }

    .alert {
        margin-top: 20px;
    }
</style>