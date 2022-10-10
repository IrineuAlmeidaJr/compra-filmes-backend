const vform = {
    data() {
        return {
            id: '',
            nome: 'Zé das couves',
            numero: 14,
            partido: 1,
            partidos:""
        }
    },
    mounted(){
        let url = 'http://localhost:8080/apis/partido/buscar-todos?filtro='
        axios
            .get(url)
            .then(response => {this.partidos = response.data })
            .catch(err=>{console.log(err)})
    },
    methods: {
        gravarCandidato() {

            let url = 'http://localhost:8080/apis/candidato/incluir'
            const dados = {id:this.id,nome:this.nome,numero:this.numero,partido: this.partido}
            console.log(dados)
            if(dados.id !=""){
                url = 'http://localhost:8080/apis/candidato/atualizar'
            }
            console.log(url)
            axios({
                method: 'post',
                url: url,
                timeout: 4000, // 4 segundos timeout
                data: dados
            })
                .then(response => {
                    myModal.toggle()
                    otable.carregarTabela()
                })
                .catch(error => console.error('timeout excedido'))
        },
        apagarForm(){
            this.id = ""
            this.nome = ""
            this.numero = ""
        }
    },
    template:
    `
        <div class="modal" id="modalcandidato">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Partido</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <!-- Aqui colocar um formulário para preencher os dados -->
                    <div class="container mt-1">
                        <form>
                            <div class="mb-3 mt-3">
                                <label for="id">ID:</label>
                                <input type="id" class="form-control" id="id" v-model="id" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="nome">Nome:</label>
                                <input type="text" class="form-control" id="nome" v-model="nome" placeholder="Informe nome do candidato" >
                            </div>
                            <div class="mb-3">
                                <label for="nome">Número:</label>
                                <input type="number" class="form-control" id="numero" v-model="numero" placeholder="Informe numero do candidato" >
                            </div>
                            <div class="mb-3">
                                <label for="nome">Partido:</label>
                                <select id="partidos">
                                    <option  v-for="partido in partidos" v-bind:value="partido.id" >{{partido.nome}}</option>
                                </select>
                            </div>                   
                        </form>
                        <button @click="gravarCandidato" class="btn btn-primary">Cadastrar</button>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
    `
}

const vtabela = {
    data() { return { candidatos: '', filtro: "" ,
    id: '',
    nome: 'Zé das couves',
    numero: 14,
    partido: 1,
    partidos:""} },
    mounted() {
        this.carregarTabela();
    },
    methods: {
        carregarTabela() {
            let url = 'http://localhost:8080/apis/candidato/buscar-todos?filtro='+this.filtro
            axios
                .get(url)
                .then(response => {this.candidatos = response.data })
                .catch(err=>{console.log(err)})
        },
        apagar(id) {
            if(confirm("Deseja Excluir?")) {
                axios
                    .delete(`http://localhost:8080/apis/candidato/excluir?id=${id}`)
                    .then(response => {otableC.carregarTabela() })
                    .catch(err=>{console.log(err);alert("Exclusão não realizada com sucesso")})
            }

        },
        alterar(id) { 
            axios
                .get(`http://localhost:8080/apis/candidato/buscar-um/${id}`)
                .then(response => {
                    let candidato = response.data
                    oformC.id = candidato.id
                    oformC.nome = candidato.nome
                    oformC.numero = candidato.numero
                    oformC.partido = candidato.partido.id
                    myModal.toggle()
                 })
                .catch(err=>{console.log(err)})

            
            
         }
    },
    template:
    `
        <div class="d-flex mt-3">
            <label for="filtro" class="form-label me-3">Filtro:</label>
            <input type="text" @keyup="carregarTabela" class="form-control" id="filtro" v-model="filtro" placeholder="informe o filtro" >
        </div>
        
        <table class="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Número</th>
              <th>Partido</th>
              <th> </th>
              <th> </th>
            </tr>
          </thead>
          <tbody>
          <tr v-for="candidato in this.candidatos">
              <td>{{candidato.id}}</td>
              <td>{{candidato.nome}}</td>
              <td>{{candidato.numero}}</td>
              <td>{{candidato.partido.nome}}</td>
              <td @click="alterar(candidato.id) " data-bs-toggle="modal" data-bs-target="#alteramodalcandidato">A</td>
              <td @click="apagar(candidato.id)">X</td>
          </tr>
          </tbody>
        </table> 

        <div class="modal" id="alteramodalcandidato">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Partido</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <!-- Aqui colocar um formulário para preencher os dados -->
                    <div class="container mt-1">
                        <form>
                            <div class="mb-3 mt-3">
                                <label for="id">ID:</label>
                                <input type="id" class="form-control" id="id" v-model="id" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="nome">Nome:</label>
                                <input type="text" class="form-control" id="nome" v-model="nome" placeholder="Informe nome do candidato" >
                            </div>
                            <div class="mb-3">
                                <label for="nome">Número:</label>
                                <input type="number" class="form-control" id="numero" v-model="numero" placeholder="Informe numero do candidato" >
                            </div>
                            <div class="mb-3">
                                <label for="nome">Partido:</label>
                                <select id="partidos">
                                    <option  v-for="partido in partidos" v-bind:value="partido.id" >{{partido.nome}}</option>
                                </select>
                            </div>                   
                        </form>
                        <button @click="gravarCandidato" class="btn btn-primary">Cadastrar</button>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
    `
};
const otableC = Vue.createApp(vtabela).mount('#tabela')
const oformC = Vue.createApp(vform).mount('#appmodal')
var myModal = new bootstrap.Modal(document.getElementById('alteramodalcandidato'), {})
