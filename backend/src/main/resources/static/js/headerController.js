const vHeader = {
    data(){
        return {
            paginas:[{"local":"inicio.html","nome":"Inicio"},{"local":"partidos.html","nome":"Partidos"},
            {"local":"candidatos.html","nome":"Candidatos"},{"local":"cargos.html","nome":"Cargos"},
            {"local":"eleicoes.html","nome":"Eleições"}]
        }
    },methods:{
        carregarCabecalho(){
            //vai carregar os métodos do login
        }
    },template:
    `
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Eleições</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                 <div class="navbar-nav" v-for="pagina in this.paginas">
                    <a class="nav-link" v-bind:href="pagina.local">{{pagina.nome}}</a>
                </div>
            </div>
        </div>
    </nav>
    `
}

Vue.createApp(vHeader).mount('#cabecalho')