<template>
    <div class="container">
        <h2 class="my-5 text-center h1">Como usar</h2>
        <div class="row mb-5" style="color: white">
            <div class="col-lg-3 my-3">
                <div class="card m-auto" style="width: 16rem; background-image: linear-gradient(to bottom, #fe6567 , #fc3636)">
                    <div class="card-body text-center">
                        <h5 class="card-title text-center">Login</h5>
                        <hr>
                        <img class="my-4" style="width: 50%;" src="../../public/assets/cachorro-marrom-direita.png" alt="">
                        <hr>
                        <p class="card-text">Fazendo login você poderá cadastrar seu pet colocando todas as suas informações.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-1 d-none d-lg-block text-center">
                <img style="margin-top: 150px" src="../../public/assets/right-arrow.png" alt="">
            </div>
            <div class="col-lg-3 my-3">
                <div class="card m-auto" style="width: 16rem; background-image: linear-gradient(to bottom, #24fc7e , #19c561) ">
                    <div class="card-body text-center">
                        <h5 class="card-title text-center">RG</h5>
                        <hr>
                        <img class="my-4" style="width: 50%;" src="../../public/assets/rg.png" alt="">
                        <hr>
                        <p class="card-text">Colocando uma foto do seu pet nós iremos gerar um RG para o seu pet.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-1 d-none d-lg-block text-center">
                <img style="margin-top: 150px" src="../../public/assets/right-arrow.png" alt="">
            </div>
            <div class="col-lg-3 my-3">
                <div class="card m-auto" style="width: 16rem; background-image: linear-gradient(to bottom, #fa8a4a , #fd650d)">
                    <div class="card-body text-center">
                        <h5 class="card-title text-center">QR Code</h5>
                        <hr>
                        <img class="my-4" style="width: 50%;" src="../../public/assets/qr-code.png" alt="">
                        <hr>
                        <p class="card-text">Você obterá um QR Code mediante um pagamento prévio</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col text-center">
                <h5>Para ler o QRCode instale um aplicativo para leitura no smartphone</h5>
                <ul style="list-style: none">
                    <li class="d-inline-block mx-3 mt-2">
                        <a href="https://play.google.com/store/apps/details?id=me.scan.android.client&hl=pt" target="_blank">
                            <img style="width: 180px" src="../../public/assets/android.jpg">
                        </a>
                    </li>
                    <li class="d-inline-block mx-3 mt-2">
                        <a href="https://apps.apple.com/br/app/qr-code-reader-by-scan/id698925807" target="_blank">
                            <img style="width: 180px" src="../../public/assets/apple.jpg">
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'comoUsar'
}
</script>
