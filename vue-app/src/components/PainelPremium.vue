<template>
    <div>
        <div class="container">
            <div class="row mt-5">
                <div class="col">
                    <h2 class="mb-4 text-center h1">Painel Token</h2>
                    <h5 class="mb-5 text-center">Informe o token para se tornar premium</h5>
                    <form v-on:submit.prevent="premium">
                        <div class="form-group row">
                            <label for="inputToken" class="col-sm-1 col-form-label my-2 text-center text-sm-left">Token</label>
                            <div class="col-sm-8 my-2">
                                <input type="text" class="form-control" id="inputToken" placeholder="Insira o seu Token recebido" v-model="token" required>
                            </div>
                            <div class="col-sm-3 my-2 text-center text-sm-left">
                                <button class="btn btn-warning">Virar Premium</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal fade" id="premium" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Você agora é Premium</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-3 my-5 my-sm-3 text-center">
                                <img src="../../public/assets/checked.png" alt="">
                            </div>
                            <div class="col-sm-9 text-center text-sm-left">
                                <h6 style="font-size: 19px">Agora você possui um<strong style="color: #5cb85c"> QR Code </strong> para cada Pet!</h6>
                                <p>Com a leitura do QR Code, você será redirecionado para os dados do respectivo pet</p>
                                <p>O QR Code já está nos RGs dos pets cadastrados <strong class="color-warning">CONFIRA </strong>!</p>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" data-dismiss="modal">Ok!</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="falha" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Falha na atualização</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-3 my-5 my-sm-3 text-center">
                                <img src="../../public/assets/delete.png" alt="">
                            </div>
                            <div class="col-sm-9 text-center text-sm-left">
                                <h6 style="font-size: 19px">Token informado está <strong style="color: #d9534f">INCORRETO</strong>!</h6>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { http, getLogin, getSenha } from '../services/config';
export default {
    name: 'painelPremium',
    data () {
        return {
            token: ''
        }
    },
    methods: {
        premium () {
            let vm = this;
            http.post ('usuario/token/usar/' + getLogin () +"/"+ this.token, {}, {
                auth: {
                    username: getLogin (),
                    password: getSenha ()
                }

            })
                .then (function (response) {
                    //qrCode = document.createElement ("img");
                    //qrCode.src = 

                    $('#premium').modal('show')
                })
                .catch (error => {
                    console.log(error.response)
                    $('#falha').modal('show')
                    $('#falha').on('hide.bs.modal', event => {
                        vm.$router.push ('menu-pets')
                    })
                })
        }
    }
}
</script>

