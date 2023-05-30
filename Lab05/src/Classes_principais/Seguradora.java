package Classes_principais;
import java.time.LocalDate;
import java.util.*;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Seguro> listaSeguros;
    private List<Cliente> listaClientes;

    /* Construtor */
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSeguros = new ArrayList<Seguro>();
        this.listaClientes = new ArrayList<Cliente>();
    }

    /* Getters e Setters */
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public List<Cliente> getListaClientes() {
        return this.listaClientes;
    }
    
    public List<Seguro> getlistarSeguros() {
        return this.listaSeguros;
    }

    public List<Cliente> listarClientes(String tipoCliente) {
        /* Retorna uma lista com todos os clientes de um
         * determinado tipo passado, podendo ser "PF" ou "PJ"
         */
        List<Cliente> clientes = new ArrayList<Cliente>();
        if (tipoCliente.equals("PF")) {
            for (Cliente c : this.listaClientes) {
                if (c instanceof ClientePF) {
                    clientes.add(c);
                }
            }
        } else if (tipoCliente.equals("PJ")) {
            for (Cliente c : this.listaClientes) {
                if (c instanceof ClientePJ) {
                    clientes.add(c);
                }
            }
        } else {
            System.out.println("Tipo inválido");
        }
        return clientes;
    }

    public int gerarSeguro(LocalDate dataInicio, LocalDate dataFim,
                                ClientePF clientepf, Veiculo veiculo) {
        /* Gera um seguroPF e adiciona ele tanto na lista da seguradora
        * quanto na lista do cliente dono do seguro
        */
        Seguro novoSeguro = new SeguroPF(dataInicio, dataFim, this, veiculo, clientepf);
        this.listaSeguros.add(novoSeguro);
        clientepf.adicionarSeguro(novoSeguro);
        return novoSeguro.getID();
    }

    public int gerarSeguro(LocalDate dataInicio, LocalDate dataFim,
                            ClientePJ clientepj, Frota frota) {
        /* Gera um seguroPJ e adiciona ele tanto na lista da seguradora
         * quanto na lista do cliente dono do seguro
         */
        Seguro novoSeguro = new SeguroPJ(dataInicio, dataFim, this, frota, clientepj);
        this.listaSeguros.add(novoSeguro);
        clientepj.adicionarSeguro(novoSeguro);
        return novoSeguro.getID();
    }

    public boolean cancelarSeguro(int id) {
        /* 
         * Cancela o seguro cujo ID foi passado, sendo ele de  
         * qualquer tipo, e também remove ele da lista de seguros
         * do cliente a quem ele pertencia
        */
        Iterator<Seguro> itSeguro = this.listaSeguros.iterator();
        while (itSeguro.hasNext()) { // percorre a lista usando Iterator
            Seguro sList = itSeguro.next();
            if (sList.getID() == id) {
                itSeguro.remove();
                sList.getCliente().removerSeguro(id);
                return true;
            }
        }
        return false;
    }

    public void cadastrarSinistro(int id, LocalDate data, String endereco,Condutor condutor) {
        /* Chama a função que de fato gera o sinistro */
        for (Seguro seg : this.listaSeguros) {
            if(seg.getID() == id) {
                seg.gerarSinistro(data, endereco, condutor);
                return;
            }
        }
    }
    
    public boolean cadastrarCliente(Cliente newClient) { // Supõe que o cliente passado é válido
        /* Supõe que o cliente passado é válido e cadastra ele na lista de 
         * clientes da seguradora, se ele já não estiver la
         */
        for (Cliente client : this.listaClientes) {
            if(newClient.getCadastro().equals(client.getCadastro())) {
                return false;
            }
        }
        boolean result = this.listaClientes.add(newClient);
        return result;
    }

    public boolean removerCliente(String cliente) { // Supõe que o cliente passado é válido
        /* Remove o cliente passado da lista de clientes da seguradora,
         * assim como todos os seguros a ele vinculados
         */
        Iterator<Cliente> itClientes = this.listaClientes.iterator();
        boolean achou = false;
        while (itClientes.hasNext()) { // percorre a lista usando Iterator
            Cliente cList = itClientes.next();
            if (cList.getCadastro().equals(cliente)) {
                itClientes.remove();
                achou = true;
            }
        }

        if (!achou) { return false; } // se não achou nem adianta procurar os sinistros

        Iterator<Seguro> itSeguro = this.listaSeguros.iterator();
        while (itSeguro.hasNext()) { // percorre a lista usando Iterator
            Seguro sList = itSeguro.next();
            if (sList.getCliente().getCadastro().equals(cliente)) {
                itSeguro.remove();
            }
        }
        return true;
    }

    public void visualizarSinistro(String cadastro) {
        /* Mostra todos os sinistros registrados no nome de um cliente,
         * cujo cadastro é passado como parâmetro
         */
        for (Seguro seguro : this.listaSeguros) {
            if (seguro.getCliente().getCadastro().equals(cadastro)) {
                for (Sinistro s : seguro.getListaSinistros()) {
                    System.out.println(s.toString());
                }
            }
        }
    }

    public void visualizarSeguros(String cadastro) {
        /* Mostra todos os seguros registrados no nome de um cliente,
         * cujo cadastro é passado como parâmetro
         */
        for (Seguro seguro : this.listaSeguros) {
            if (seguro.getCliente().getCadastro().equals(cadastro)) {
                System.out.println(seguro.toString());
            }
        }
    }

    public double calculaReceita() {
        /* Soma o valor de todos os seguros registrados pela seguradora */
        double receita = 0;
        for (Seguro seguro : listaSeguros) {
            receita += seguro.getValorMensal();
        }
        return receita;
    }

    public ClientePF encontrar_clientePF(String cadastro) {
        /* Retorna o clientePF dono do CPF passado */
        for (Cliente c : this.listaClientes) {
            if (c instanceof ClientePF) {
                ClientePF clientePF = (ClientePF) c;
                if (clientePF.getCadastro().equals(cadastro)) {
                    return clientePF;
                }
            }
        }
        return null; // Esse CPF não pertence a nenhum cliente
    }
    
    public ClientePJ encontrar_clientePJ(String cadastro) {
        /* Retorna o clientePJ dono do CNPJ passado */
        for (Cliente c : this.listaClientes) {
            if (c instanceof ClientePJ) {
                ClientePJ clientePJ = (ClientePJ) c;
                if (clientePJ.getCadastro().equals(cadastro)) {
                    return clientePJ;
                }
            }
        }
        return null; // Esse CNPJ não pertence a nenhum cliente
    }

    public Cliente encontrar_cliente(String cadastro) {
        /* Retorna o cliente dono do CPF/CNPJ passado */
        for (Cliente c : this.listaClientes) {
            if (c.getCadastro().equals(cadastro)) {
                return c;
            }
        }
        return null; // Esse CPF/CNPJ não pertence a nenhum cliente
    }

    public Seguro encontrar_seguro(int id) {
        /* Retorna o seguro que possui o ID passado */
        for (Seguro s : this.listaSeguros) {
            if (s.getID() == id) {
                return s;
            }
        }
        return null; // Esse CPF/CNPJ não pertence a nenhum cliente
    }

    public void transferirSeguro(ClientePF c1, ClientePF c2) {
        /*
         * Transfere todos os seguros do clientePF c1 para o clientePF c2
         * (Depois de fazer o método me avisaram que não era necessário, mas como
         * já fiz decidi mantê-lo, caso isso dê algum acréscimo de nota)
         */
        List<Seguro> novaListaSeguros = c1.getListaSeguros();
        List<Seguro> listaAux = c2.getListaSeguros();
        for (Seguro seguro : listaAux) {
            seguro.setCliente(c1);
        }
        novaListaSeguros.addAll(listaAux);
        c1.setListaSeguros(novaListaSeguros);
        for(Seguro seguro : this.listaSeguros) {
            if (seguro.getCliente().getCadastro().equals(c2.getCadastro())) {
                seguro.setCliente(c1);
            }
        }
        c2.limparSeguros();
    }

    public void transferirSeguro(ClientePJ c1, ClientePJ c2) {
        /*
         * Transfere todos os seguros do clientePJ c1 para o clientePJ c2
         * (Depois de fazer o método me avisaram que não era necessário, mas como
         * já fiz decidi mantê-lo, caso isso dê algum acréscimo de nota)
         */
        List<Seguro> novaListaSeguros = c1.getListaSeguros();
        List<Seguro> listaAux = c2.getListaSeguros();
        for (Seguro seguro : listaAux) {
            seguro.setCliente(c1);
        }
        novaListaSeguros.addAll(listaAux);
        c1.setListaSeguros(novaListaSeguros);
        for(Seguro seguro : this.listaSeguros) {
            if (seguro.getCliente().getCadastro().equals(c2.getCadastro())) {
                seguro.setCliente(c1);
            }
        }
        c2.limparSeguros();
    }

    public static List<Seguro> getSegurosPorCliente(Cliente cliente) {
        /*
         * Retorna um lista com todos os seguros de um cliente
         */
        return cliente.getListaSeguros();
    }

    public static List<Sinistro> getSinistrosPorCliente(Cliente cliente) {
        /* 
         * Retorna uma lista com todos os sinistros associados a esse cliente
         */
        List<Sinistro> listaSinistros = new ArrayList<Sinistro>();
        for (Seguro seguro : cliente.getListaSeguros()) {
            listaSinistros.addAll(seguro.getListaSinistros());
        }
        return listaSinistros; // esse cliente não tem sinistros
    }

    public String toString() {
        return 
            "Nome: " + getNome() + "\n" +
            "Telefone: " + getTelefone() + "\n" +
            "Email:" + getEmail() + "\n" +
            "Endereco:" + getEndereco() + "\n";
    }
}