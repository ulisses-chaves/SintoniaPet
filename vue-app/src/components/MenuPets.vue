<template>
    <div>
        <div class="container mt-4">
        <div class="row">
            <div class="col-sm-6 text-center text-sm-right">
                <img src="../../public/assets/cat.png" alt="">
            </div>
            <div class="col-sm-6 text-center text-sm-left pl-0 mt-3">
                <h1>Pets</h1>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-lg-9 m-auto">
                <ul style="list-style: none" class="pl-0">
                    <li class="mb-3">
                        <router-link style="color: black" v-bind:to="{ name: 'cadastroPet', params: { name: 'cadastro-pet' } }">
                            <img style="width: 25px" src="../../public/assets/plus.png" alt="">
                            <span class="ml-2" style="vertical-align: middle">Adicionar Pet</span>
                        </router-link>
                    </li>
                    <li> 
                        <div id="petCard" v-for="pet of pets" :key="pet.id" class="card mb-5">
                            <div class="text-center">
                                <img class="foto-pet" v-bind:src="pet.imagem">
                            </div>
                            <div class="card-body">
                                <h4 class="card-title">{{ pet.pet.nome }}</h4>
                                <h6 class="card-subtitle mb-2 text-muted">{{ pet.pet.raca }}</h6>
                                <!--<p class="card-text">Alguma frase?</p>-->
                            </div>
                            <div class="accordion" id="accordionExample1">
                                <div class="card">
                                    <div class="card-header" id="headingOne">
                                        <h5 class="mb-0">
                                        <button class="btn btn-link card-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                            Características do Pet
                                        </button>
                                        </h5>
                                    </div>
                                    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample1">
                                        <div class="card-body">
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item"><strong>Idade:</strong> {{pet.pet.idade}}</li>
                                                <li v-if="pet.pet.sexo === 'M'" class="list-group-item"><strong>Sexo:</strong> Macho</li>
                                                <li v-else class="list-group-item"><strong>Sexo:</strong> Fêmea</li>
                                                <li v-if="pet.pet.porte === 'P'" class="list-group-item"><strong>Porte:</strong> Pequeno</li>
                                                <li v-if="pet.pet.porte === 'M'" class="list-group-item"><strong>Porte:</strong> Médio</li>
                                                <li v-if="pet.pet.porte === 'G'" class="list-group-item"><strong>Porte:</strong> Grande</li>
                                                <li class="list-group-item"><strong>Data de Nascimento:</strong> {{ pet.pet.data_nascimento }}</li>
                                                <li class="list-group-item"><strong>Cor da Pelugem:</strong> {{pet.pet.cor_pelugem}}</li>
                                                <li class="list-group-item"><strong>Peso:</strong> {{pet.pet.peso}}</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion" id="accordionExample2">
                                <div class="card">
                                    <div class="card-header" id="headingTwo">
                                        <h5 class="mb-0">
                                        <button class="btn btn-link card-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            Informações do Cadastro
                                        </button>
                                        </h5>
                                    </div>
                                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample2">
                                        <div class="card-body">
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item"><strong>Número do RG:</strong> {{pet.pet.numero_rg}}</li>
                                                <li class="list-group-item"><strong>RG do Dono:</strong> {{pet.pet.rg_dono}}</li>
                                                <li class="list-group-item"><strong>Data de Expedição:</strong> {{ pet.pet.data_exp }}</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer text-muted">
                                <ul class="p-0">
                                    <li class="d-inline-block mx-2">
                                        <a v-on:click.prevent="pegarPet(pet), resolucao()" data-toggle="modal" data-target="#rg" class="card-link" href="">
                                            <ul class="p-0">
                                                <li class="d-inline mx-2"><img class="m-0 p-0" style="width: 64px" src="../../public/assets/rg.png" alt=""></li>
                                                <li class="d-inline">RG</li>
                                            </ul>
                                        </a>
                                    </li>
                                    <li class="d-inline-block mx-2">
                                        <a v-on:click.prevent="pegarPet(pet)" data-toggle="modal" data-target="#edit" class="card-link" href="">
                                            <ul class="p-0">
                                                <li class="d-inline mx-2"><img style="width: 45px" class="m-0 p-0" src="../../public/assets/pen.png" alt=""></li>
                                                <li class="d-inline">Editar</li>
                                            </ul>
                                        </a>
                                    </li>
                                    <li class="d-inline-block mx-2">
                                        <a v-on:click.prevent="pegarPet(pet)" data-toggle="modal" data-target="#delete" class="card-link" href="">
                                            <ul class="p-0">
                                                <li class="d-inline mx-2"><img style="width: 45px" class="m-0 p-0" src="../../public/assets/delete.png" alt=""></li>
                                                <li class="d-inline">Deletar</li>
                                            </ul>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li class="mt-5 text-center">
                        <router-link href="" class="card-link" v-bind:to="{ name: 'cadastroPet', params: { name: 'cadastro-pet' } }">
                            <img style="width: 128px" src="../../public/assets/plus.png" alt="">
                            <p style="font-size: 30px; color: black" class="my-2">Adicionar Pet</p>
                        </router-link>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="rg" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-md" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">RG Pet</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div id="impressao" class="modal-body">
                    <div class="row">
                        <div v-if="petSelecionado.pet.sexo === 'M'" style="position: relative" id="minhaImagem" class="col text-center">
                            <img class="rgPet" style="width: 70%" src="../../public/assets/rgPetM.jpeg" alt="">
                        </div>
                        <div v-if="petSelecionado.pet.sexo === 'F'" style="position: relative" id="minhaImagem" class="col text-center">
                            <img class="rgPet" style="width: 70%" src="../../public/assets/rgPetF.jpeg" alt="">
                        </div>
                        <img class="imagemPet positions" v-bind:src="petSelecionado.imagem">
                        <label class="nomePet positions" for="">{{petSelecionado.pet.nome}}</label>
                        <label class="dataPet positions" for="">{{petSelecionado.pet.data_nascimento}}</label>
                        <label class="dataExp positions" for="">{{petSelecionado.pet.data_exp}}</label>
                        <label class="filiacao positions" for="">{{petSelecionado.pet.filiação}}</label>
                        <label class="peso positions" for="">{{petSelecionado.pet.peso}}</label>
                        <label class="naturalidade positions" for="">{{petSelecionado.pet.naturalidade}}</label>
                        <label class="racaPet positions" for="">{{petSelecionado.pet.raca}}</label>
                        <label class="sexoPet positions" for="">{{petSelecionado.pet.sexo}}</label>
                        <label class="pelugemPet positions" for="">{{petSelecionado.pet.cor_pelugem}}</label>
                        <label class="usuario positions" for="">{{user.nome}}</label>
                        <label class="rua positions" for="">{{user.rua}}</label>
                        <label class="cidade positions" for="">{{user.cidade}}</label>
                        <label class="bairro positions" for="">{{user.bairro}}</label>
                        <label class="uf positions" for="">{{user.uf}}</label>
                        <label class="cep positions" for="">{{user.cep}}</label>
                        <label class="telefone positions" for="">{{user.numero_fixo}}</label>
                        <img class="qrCodePet positions" v-bind:src="qrCode">
                    </div>
                </div>
                <div class="modal-footer m-auto">
                    <ul>
                        <li class="d-inline-block mx-2">
                            <a v-on:click.stop.prevent="imprimirRG" href="">
                                <img style="width: 25px" src="../../public/assets/printer.png" alt="Impressora">
                                Imprimir RG
                            </a>
                        </li>
                        <!--<li class="d-inline-block mx-2">
                            <a href="">
                                <img style="width: 25px" src="../../public/assets/sendEmail.png" alt="email">
                                Enviar para o email
                            </a>
                        </li>-->
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div id="edit" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Editar Dados do Pet</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                        
                <div class="modal-body">
                    <form >
                        <div class="text-center my-3">
                            <img id = 'imagem' class="" v-bind:src="petAlterado.imagem">
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label style="color: #7e4732" for="nome">Nome</label>
                                    <input type="text" class="form-control" id="nome" required v-model="petAlterado.pet.nome" minlength="2" maxlength="15">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label style="color: #7e4732" for="pelugem">Cor da Pelugem</label>
                                    <input type="text" class="form-control" id="pelugem" required v-model="petAlterado.pet.cor_pelugem" minlength="2" maxlength="15">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label style="color: #7e4732" for="raca">Espécie/Raça</label>
                                    <input type="text" class="form-control" id="raca" required v-model="petAlterado.pet.raca" minlength="2" maxlength="15">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label style="color: #7e4732" for="idade">Idade</label>
                                    <input type="text" class="form-control" id="idade" required v-model="petAlterado.pet.idade" name="numbers" pattern="[0-9]+$" maxlength="3">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-row align-items-center">
                                    <div class="col-auto my-1">
                                        <label class="mr-sm-2 color-brown" for="inlineFormCustomSelect">Sexo</label>
                                        <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" v-model="petAlterado.pet.sexo">
                                            <option value="M">Macho</option>
                                            <option value="F">Fêmea</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-row align-items-center">
                                    <div class="col-auto my-1">
                                        <label class="mr-sm-2 color-brown" for="inlineFormCustomSelect">Porte</label>
                                        <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" v-model="petAlterado.pet.porte">
                                            <option value="P">Pequeno</option>
                                            <option value="M">Médio</option>
                                            <option value="G">Grande</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label style="color: #7e4732" for="data">Data de Nascimento</label>
                                    <input type="date" class="form-control" id="data" required v-model="petAlterado.pet.data_nascimento">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-row align-items-center">
                                    <div class="col-auto my-1">
                                        <label class="mr-sm-2 color-brown" for="inputCastrado">Castrado</label>
                                        <select class="custom-select mr-sm-2" id="inputCastrado" v-model="petAlterado.pet.castrado">
                                            <option value="S">Sim</option>
                                            <option value="N">Não</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label style="color: #7e4732" for="inputNaturalidade">Naturalidade</label>
                                    <input type="text" class="form-control" id="inputNaturalidade" required v-model="petAlterado.pet.naturalidade" minlength="2" maxlength="15">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label style="color: #7e4732" for="inputFiliacao">Filiação</label>
                                    <input type="text" class="form-control" id="inputFiliacao" required v-model="petAlterado.pet.filiação" maxlength="30">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label style="color: #7e4732" for="inputPeso">Peso</label>
                                    <input type="text" class="form-control" id="inputPeso" required v-model="petAlterado.pet.peso" name="numbers" pattern="[0-9]+$" maxlength="3">
                                </div>
                            </div>
                            <div class="col-sm-6 mt-4 text-sm-right text-center">
                                <div class="form-group mt-3">
                                    <input type="file" accept="image/png, image/jpeg" v-on:change="fotoSelecionada">
                        
                                </div>

                            </div>
                        </div>
                    </form>
                </div>
                <div id="erroAtt" style="width: 50%; display: none" class="m-auto text-center alert alert-danger" role="alert">
                    <div class="row">
                        <div class="col-md-4 text-md-right text-center">
                            <img style="width: 50px" src="../../public/assets/alert.png" alt="">
                        </div>
                        <div id ="msgAtt" class="col-md-6 text-center">

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button v-on:click.stop.prevent="atualizarPet" style="color: #7e4732" type="button" class="btn btn-warning">Salvar mudanças</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Excluir Pet do seu Perfil</h5>
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
                            <h6 style="font-size: 19px">Tem certeza que deseja excluir?</h6>
                            <p>Clicando em <strong style="color: #d9534f">deletar</strong>, fique ciente que você <strong style="color: #d9534f">PERDERÁ</strong> os dados cadastrados do seu Pet e só poderá tê-los de volta, cadastrando novamente!</p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
                    <button type="button" v-on:click.prevent="excluirPet" class="btn btn-danger">Deletar</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="deletado" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Excluído</h5>
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
                            <h6 style="font-size: 19px">Pet excluído com <strong style="color: #5cb85c">SUCESSO</strong>!</h6>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal">Ok!</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="atualizado" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Pet Atualizado</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-3 my-5 my-sm-3 text-center">
                            <img src="../../public/assets/checked.png" alt="">
                        </div>
                        <div class="col-sm-9 text-center">
                            <h6 class="mb-4" style="font-size: 19px">Seu pet foi atualizado com <strong style="color: #5cb85c">SUCESSO</strong> no nosso sitema!</h6>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal">Ok!</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="atencao" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Atenção!</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-3 my-5 my-sm-3 text-center">
                            <img src="../../public/assets/alert.png" alt="">
                        </div>
                        <div class="col-sm-9 text-center">
                            <h6 class="mb-4" style="font-size: 19px">Você está acessando nosso sistema por um smartphone!</h6>
                            <p>Para visualizar melhor o RG Pet, acesse o <strong class="color-warning">SintoniaPet</strong> através de um computador ou tablet</p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal">Ok!</button>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
import { http, getLogin, getSenha } from '../services/config';

export default {
    name: 'menuPets',
    data () {
        return {
            user: {},
            pets: [],
            petSelecionado: {
                image: null,
                pet: {}
            },
            petAlterado: {
                image: null,
                pet: {
                    numero_rg: null,
                    rg_dono: null,
                    nome: null,
                    cor_pelugem: null,
                    raca: null,
                    idade: null,
                    sexo: null,
                    porte: null,
                    data_nascimento: null,
                    castrado: null,
                    caminho_foto: null,
                    data_exp: null,
                    filiação: null,
                    peso: null,
                    naturalidade: null
                }
            },
            qrCode: null
        }
    },
    mounted () {   
        let vm = this;
        http.get ('pet/all/' + getLogin())
            .then (function (response) {
                vm.pets = response.data
            })
        http.get ('usuario/get/' + getLogin())
            .then (function (response) {
                vm.user = response.data.usuario
            })
            .catch(error => {
                console.log (error)
            })
    },
    updated () {
        $('#edit').on('hide.bs.modal', event => {
            window.location.reload()
        })
    },
    methods: {
          fotoSelecionada (event) {
            
            var file = event.target.files[0]
            
            var reader = new FileReader();
            let vm = this;
            
            reader.onload = function (event)
            {
                document.getElementById("imagem").src = event.target.result;
                document.getElementById("imagem").style.width  = "150px";
                vm.petAlterado.imagem = document.getElementById("imagem").src.toString();
            };
            
            reader.readAsDataURL(file);
               
        },
       
       atualizarPet() {
            let vm = this;
            this.petAlterado.pet.numero_rg = this.petSelecionado.pet.numero_rg;
            if (this.petAlterado.pet.idade == null) {
                this.petAlterado.pet.idade = -1
            }
            if (this.petAlterado.pet.sexo == null) {
                this.petAlterado.pet.sexo = 'z'
            }
            if (this.petAlterado.pet.castrado == null) {
                this.petAlterado.pet.castrado = 'z'
            }
            if (this.petAlterado.pet.peso == null) {
                this.petAlterado.pet.peso = -1
            }
            console.log (this.petAlterado)
            http.put ('pet/update/' + getLogin(), this.petAlterado, {
                auth: {
                    username: getLogin(),
                    password: getSenha()
                }
            })
                .then ( function (response) {
                    $('#atualizado').modal('show')
                    $('#atualizado').on('hide.bs.modal', event => {
                        window.location.reload()
                    })
                })
                .catch (error => {
                    console.log (error.response); //*
                    document.getElementById('msgAtt').innerHTML = "Algum problema ocorreu <br> Não foi possível atualizar";
                    document.getElementById('erroAtt').style.display = 'block'
                })
        },
        imprimirRG () {
            window.print ();
        },
        pegarPet (pet) {
            this.petSelecionado = pet;
            this.petAlterado = pet;
            if (this.user.isPremmium) {
                this.qrCode = document.createElement('img');
                this.qrCode = 'https://chart.googleapis.com/chart?chs=150x150&cht=qr&chl=http://aw-sintonia-pet-api.herokuapp.com/pagina/pet/' + this.petSelecionado.pet.numero_rg
            }
        },
        excluirPet () {
            let vm = this;
            http.delete ('pet/delete/' + getLogin (), { data: this.petSelecionado.pet }, {
                auth: {
                    username: getLogin (),
                    password: getSenha ()
                }
            })
                .then (function (response) {
                    $('#delete').modal('hide')
                    $('#deletado').modal('show')
                    $('#deletado').on('hide.bs.modal', event => {
                        window.location.reload()
                    })
                })
                .catch (error => {
                    console.log (error)
                }) 
        },
        resolucao () {
            if (window.innerWidth < 576) {
                $('#atencao').modal('show')
            }
        }
    }
}
</script>

<style scoped>
    @media print {
        body *{
            visibility: hidden;
        }
        #impressao, #impressao * { 
            visibility:visible; 
            } 
        #impressao { 
            position:absolute; left:0; top:0;
        }
    }
    .rgPet {
        z-index: 1;
    }
    .positions { 
        z-index: 2;
        position: absolute;
        font-size: 11px;
    }
    @media (max-width:354px) {
        .foto-pet {
            max-width: 260px;
            object-fit: cover;
            max-height: 600px;
        }
        .positions {
            font-size: 7px;
        }
        .imagemPet {
            margin: 37px 0 0 110px;
            height: 100px;
            width: 103px;
            object-fit: cover;
            border-radius: 50%
        }
        .nomePet {
            margin: 142px 0 0 100px
        }
        .dataPet {
            margin: 154px 0 0 120px
        }
        .dataExp {
            margin: 154px 0 0 190px
        }
        .filiacao {
            margin: 165px 0 0 110px
        }
        .peso {
            margin: 165px 0 0 190px
        }
        .naturalidade {
            margin: 176px 0 0 120px
        }
        .racaPet {
            margin: 189px 0 0 100px
        }
        .sexoPet {
            margin: 189px 0 0 185px
        }
        .pelugemPet {
            margin: 189px 0 0 215px
        }
        .usuario {
            margin: 200px 0 0 110px
        }
        .rua {
            margin: 219px 0 0 115px
        }
        .cidade {
            margin: 230px 0 0 105px
        }
        .bairro {
            margin: 230px 0 0 190px
        }
        .uf {
            margin: 243px 0 0 93px
        }
        .cep {
            margin: 243px 0 0 125px
        }
        .telefone {
            margin: 243px 0 0 190px
        }
        .qrCodePet {
            margin: 279px 0 0 125px;
            height: 85px;
        }
    }
    @media (min-width:355px) {
        .foto-pet {
            max-width: 260px;
            object-fit: cover;
            max-height: 600px;
        }
        .positions {
            font-size: 8px;
        }
        .imagemPet {
            margin: 40px 0 0 116px;
            height: 110px;
            width: 110px;
            object-fit: cover;
            border-radius: 50%
        }
        .nomePet {
            margin: 150px 0 0 105px
        }
        .dataPet {
            margin: 163px 0 0 124px
        }
        .dataExp {
            margin: 163px 0 0 200px
        }
        .filiacao {
            margin: 174px 0 0 115px
        }
        .peso {
            margin: 174px 0 0 200px
        }
        .naturalidade {
            margin: 188px 0 0 130px
        }
        .racaPet {
            margin: 201px 0 0 105px
        }
        .sexoPet {
            margin: 201px 0 0 198px
        }
        .pelugemPet {
            margin: 201px 0 0 226px
        }
        .usuario {
            margin: 214px 0 0 117px
        }
        .rua {
            margin: 231px 0 0 120px
        }
        .cidade {
            margin: 244px 0 0 108px
        }
        .bairro {
            margin: 244px 0 0 201px
        }
        .uf {
            margin: 257px 0 0 100px
        }
        .cep {
            margin: 257px 0 0 130px
        }
        .telefone {
            margin: 257px 0 0 195px
        }
        .qrCodePet {
            margin: 279px 0 0 125px;
            height: 85px;
        }
    }
    @media (min-width:375px) {
        .foto-pet {
            max-width: 260px;
            object-fit: cover;
            max-height: 600px;
        }
        .positions {
            font-size: 9px;
        }
        .imagemPet {
            margin: 40px 0 0 120px;
            height: 115px;
            width: 115px;
            object-fit: cover;
            border-radius: 50%
        }
        .nomePet {
            margin: 158px 0 0 110px
        }
        .dataPet {
            margin: 171px 0 0 129px
        }
        .dataExp {
            margin: 171px 0 0 208px
        }
        .filiacao {
            margin: 182px 0 0 121px
        }
        .peso {
            margin: 182px 0 0 204px
        }
        .naturalidade {
            margin: 196px 0 0 135px
        }
        .racaPet {
            margin: 209px 0 0 110px
        }
        .sexoPet {
            margin: 209px 0 0 203px
        }
        .pelugemPet {
            margin: 209px 0 0 233px
        }
        .usuario {
            margin: 223px 0 0 120px
        }
        .rua {
            margin: 242px 0 0 122px
        }
        .cidade {
            margin: 255px 0 0 109px
        }
        .bairro {
            margin: 256px 0 0 205px
        }
        .uf {
            margin: 269px 0 0 100px
        }
        .cep {
            margin: 268px 0 0 135px
        }
        .telefone {
            margin: 269px 0 0 200px
        }
        .qrCodePet {
            margin: 291px 0 0 132px;
            height: 90px;
        }
    }
    @media (min-width:387px){
        .foto-pet {
            max-width: 260px;
            object-fit: cover;
            max-height: 600px;
        }
        .positions {
            font-size: 9px;
        }
        .imagemPet {
            margin: 42px 0 0 125px;
            height: 120px;
            width: 120px;
            object-fit: cover;
            border-radius: 50%
        }
        .nomePet {
            margin: 165px 0 0 112px
        }
        .dataPet {
            margin: 178px 0 0 132px
        }
        .dataExp {
            margin: 178px 0 0 220px
        }
        .filiacao {
            margin: 193px 0 0 125px
        }
        .peso {
            margin: 193px 0 0 220px
        }
        .naturalidade {
            margin: 205px 0 0 140px
        }
        .racaPet {
            margin: 219px 0 0 110px
        }
        .sexoPet {
            margin: 219px 0 0 212px
        }
        .pelugemPet {
            margin: 219px 0 0 245px
        }
        .usuario {
            margin: 235px 0 0 126px
        }
        .rua {
            margin: 254px 0 0 125px
        }
        .cidade {
            margin: 268px 0 0 113px
        }
        .bairro {
            margin: 268px 0 0 220px
        }
        .uf {
            margin: 281px 0 0 105px
        }
        .cep {
            margin: 281px 0 0 140px
        }
        .telefone {
            margin: 281px 0 0 210px
        }
        .qrCodePet {
            margin: 306px 0 0 140px;
            height: 90px;
        }
    }
    @media (min-width: 400px) {
        .foto-pet {
            max-width: 260px;
            object-fit: cover;
            max-height: 600px;
        }
        .positions {
            font-size: 11px
        }
        .imagemPet {
            margin: 57px 0 0 166px;
            height: 164px;
            width: 164px;
            object-fit: cover
        }
        .nomePet {
            margin: 223px 0 0 150px
        }
        .dataPet {
            margin: 242px 0 0 180px
        }
        .dataExp {
            margin: 242px 0 0 298px
        }
        .filiacao {
            margin: 262px 0 0 160px
        }
        .peso {
            margin: 262px 0 0 286px
        }
        .naturalidade {
            margin: 282px 0 0 185px
        }
        .racaPet {
            margin: 300px 0 0 148px
        }
        .sexoPet {
            margin: 300px 0 0 286px
        }
        .pelugemPet {
            margin: 300px 0 0 332px
        }
        .usuario {
            margin: 318px 0 0 168px
        }
        .rua {
            margin: 346px 0 0 170px
        }
        .cidade {
            margin: 365px 0 0 155px
        }
        .bairro {
            margin: 365px 0 0 295px
        }
        .uf {
            margin: 383px 0 0 138px
        }
        .cep {
            margin: 383px 0 0 190px
        }
        .telefone {
            margin: 383px 0 0 280px
        }
        .qrCodePet {
            margin: 405px 0 0 175px;
            height: 150px;
        }
    }
    @media (min-width: 576px) {
        .foto-pet {
            max-width: 470px;
            object-fit: cover;
            max-height: 600px;
        }
    }
    @media (min-width: 768px) {
        .foto-pet {
            max-width: 650px;
            object-fit: cover;
            max-height: 600px;
        }
    }
    @media (min-width: 1200px) {
        .foto-pet {
            max-width: 823px;
            object-fit: cover;
            max-height: 600px;
        }
    }
</style>
