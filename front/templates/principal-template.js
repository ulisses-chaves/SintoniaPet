const PrincipalTemplate = `
    <div>
        <navbar></navbar>
            <div class="row m-0">
                <div class="col-lg-4 mt-4">
                    <h1 class="color-warning text-center mb-4" style="font-size: 47px">Sintonia<span class="color-brown">Pet</span></h1>
                    <div class="card m-auto" style="width: 80%; background: #fdc536">
                        <h5 style="font-weight: 700" class="color-brown card-title text-center mt-4">Faça seu login e conheça nossos produtos</h5>
                        <div style="background-image: linear-gradient(to bottom , #fdc536, #fdad00);" class="card-body m-auto">
                             <form action="">
                                <div class="form-group">
                                    <label class="color-brown" for="login">Login</label>
                                    <input type="text" class="form-control" id="login" required>
                                </div>
                                <div class="form-group">
                                    <label class="color-brown" for="senha">Senha</label>
                                    <input type="password" class="form-control" id="senha" required>
                                    <a style="font-size: 13px" class="card-link" href="">Esqueceu a senha?</a>
                                </div>
                                <div class="form-group form-check">
                                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                                    <label class="form-check-label mb-0 color-brown" for="exampleCheck1">Lembrar-me</label>
                                </div>
                                <div class="text-center">
                                    <button style="width: 120px" type="submit" class="color-brown btn btn-light" hre><a href="">Entrar</a></button>
                                </div>
                            </form>
                        </div>
                        <div style="background: white" class="card-footer text-muted p-1 pl-2">
                            <p class="m-0 color-brown">É novo por aqui? <a href="#/cadastro">Crie sua conta</a></p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 p-0 d-none d-sm-flex h-100 mb-5 mt-4">
                    <div id="carouselExampleIndicators" class="carousel slide w-100" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img style="object-fit: cover; border-radius: 5px" class="d-block w-100" src="public/img/carousel/img-carousel-1.jpeg" alt="Primeiro Slide">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr class="container">
            <comoUsar></comoUsar>
            <hr class="container">
            <contato></contato>
            <rodape></rodape>
        </div>
`

export { PrincipalTemplate }