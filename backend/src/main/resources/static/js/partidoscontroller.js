const vform = {
    data() {
        return {
            id: '',
            nome: 'PPQ'
        }
    },
    methods: {
        
        gravarPartido() {
            let url = 'http://localhost:8080/apis/partido/incluir'
            const dados = {id:this.id,nome:this.nome}
            console.log(dados)
            if(dados.id !=""){
                url = 'http://localhost:8080/apis/partido/atualizar'
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
        }
    },
    template:
    `
        <div class="modal" id="myModal">
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
                                <input type="text" class="form-control" id="nome" v-model="nome" placeholder="Informe nome do partido" >
                            </div>                  
                        </form>
                        <button @click="gravarPartido()" class="btn btn-primary">Cadastrar</button>
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
    data() { return { partidos: '', filtro: '' } },
    mounted() {
        this.carregarTabela();
    },
    methods: {
        carregarTabela() {
            let url = 'http://localhost:8080/apis/partido/buscar-todos?filtro='+this.filtro
            axios
                .get(url)
                .then(response => {this.partidos = response.data })
                .catch(err=>{console.log(err)})
        },
        apagar(id) {
            console.log(id)
            if(confirm("Deseja Excluir?")) {
                axios
                    .delete(`http://localhost:8080/apis/partido/excluir?id=${id}`)
                    .then(response => {otable.carregarTabela() })
                    .catch(err=>{console.log(err);alert("Exclusão não realizada com sucesso")})
            }

        },
        alterar(id) { 
            axios
                .get(`http://localhost:8080/apis/partido/buscar-um/${id}`)
                .then(response => {
                    let partido = response.data
                    oform.id = partido.id
                    oform.nome = partido.nome
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
              <th> </th>
              <th> </th>
            </tr>
          </thead>
          <tbody>
          <tr v-for="partido in this.partidos">
              <td>{{partido.id}}</td>
              <td>{{partido.nome}}</td>
              <td @click="alterar(partido.id) ">A</td>
              <td @click="apagar(partido.id)">X</td>
          </tr>
          </tbody>
        </table> 
    `
};
const otable = Vue.createApp(vtabela).mount('#tabela')
const oform = Vue.createApp(vform).mount('#appmodal')

var myModal = new bootstrap.Modal(document.getElementById('myModal'), {})