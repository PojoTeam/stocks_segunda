<template>
    <div class="container-fluid d-flex justify-content-center align-items-start">
        <div class="card">
            <form class="card-body">
                <h2>Log In</h2>
                <div class="form-group">
                    <label for="InputUsername">Nombre de Usuario</label>
                    <input type="text" class="form-control" id="InputUsername">
                </div>
                <div class="form-group">
                    <label for="InputPassword">Contraseña</label>
                    <input type="password" class="form-control" id="InputPassword">
                </div>
                <button type="button" class="btn btn-primary" v-on:click="login">Log In</button>
                <div class="alert alert-danger" v-if="error" role="alert">
                    Credenciales erroneas
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    import DataService from "../service/DataService";
    import router from "../routes";

    export default {
        name: "LogIn",
        data() {
            return {
                error: false
            }
        },
        methods: {
            login() {
                let user = document.getElementById('InputUsername').value;
                let password = document.getElementById('InputPassword').value;
                DataService.postLogin(user, password).then(response => {
                    console.log(response);
                    localStorage.setItem("user", response.data.userName);
                    localStorage.setItem("password", response.data.password)
                    router.push("/portfolio")
                    location.reload()
                }).catch( function(){
                    console.log("error")
                });
            }
        }
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