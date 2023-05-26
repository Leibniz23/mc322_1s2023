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

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSeguros = new ArrayList<Seguro>();
        this.listaClientes = new ArrayList<Cliente>();
    }

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
                                ClientePF clientepf, Veiculo veiculo) { // do PF
        Seguro novoSeguro = new SeguroPF(dataInicio, dataFim, this, veiculo, clientepf);
        clientepf.adicionarSeguro(novoSeguro);

    }

    public void gerarSeguro(LocalDate dataInicio, LocalDate dataFim,
                                ClientePJ clientepj, Frota frota) { // do PJ
        Seguro novoSeguro = new SeguroPJ(dataInicio, dataFim, this, frota, clientepj);
        clientepj.adicionarSeguro(novoSeguro);
    }

    public boolean cancelarSeguro(int id) {
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
    
    public boolean cadastrarCliente(Cliente newClient) { // Supõe que o cliente passado é válido
        for (Cliente client : this.listaClientes) {
            if(newClient.getCadastro().equals(client.getCadastro())) {
                return false;
            }
        }
        boolean result = this.listaClientes.add(newClient);
        return result;
    }

    public boolean removerCliente(String cliente) { // Supõe que o cliente passado é válido
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

    // public boolean cadastrarVeiculo(Veiculo v, String cadastro) {
    //     for (Cliente c : listaClientes) {
    //         if (c.getCadastro().equals(cadastro)) {
    //             c.adicionarVeiculo(v);
    //             return true;
    //         }
    //     }
    //     return false; // o cliente que você tentou adicionar um veiculo não existe
    // }

    // public boolean excluirVeiculo(String placa) {
    //     for (Cliente c : listaClientes) {
    //         if (c.removerVeiculo(placa)) {
    //             return true;
    //         }
    //     }
    //     return false; // veículo que você tentou remover não existe
    // }

    // public boolean gerarSinistro(LocalDate data, String endereco, Veiculo veiculo, Cliente cliente) {
    //     Sinistro novoSinistro = new Sinistro(data, endereco, this, veiculo, cliente);
    //     boolean result = this.listaSinistros.add(novoSinistro); // não precisa verificar se está na lista porque cada sinistro tem um ID diferente
    //     calculaPrecoSeguroCliente(cliente);
    //     return result;
    // }

    // public boolean removerSinistro(int id) {
    //     Iterator<Sinistro> itSinistros = this.listaSinistros.iterator();
    //     while (itSinistros.hasNext()) { // percorre a lista usando Iterator
    //         Sinistro sList = itSinistros.next();
    //         if (sList.getID() == id) {
    //             itSinistros.remove();
    //             calculaPrecoSeguroCliente(sList.getCliente());
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public void visualizarSinistro(String cadastro) {
        for (Seguro seguro : this.listaSeguros) {
            if (seguro.getCliente().getCadastro().equals(cadastro)) {
                for (Sinistro s : seguro.getListaSinistros()) {
                    System.out.println(s.toString());
                }
            }
        }
    }
    // public double calculaPrecoSeguroCliente(Cliente client) {
    //     int qtdeSinistros = 0;
    //     for (Sinistro sin : listaSinistros) {
    //         if ((sin.getCliente().getCadastro()).equals(client.getCadastro())) {
    //             qtdeSinistros++;
    //         }
    //     }
    //     double PrecoSeguroCliente = client.calculaScore() * (1+ qtdeSinistros);
    //     client.setValorSeguro(PrecoSeguroCliente);
    //     return PrecoSeguroCliente;
    // }

    public double calculaReceita() {
        double receita = 0;
        for (Seguro seguro : listaSeguros) {
            receita += seguro.getValorMensal();
        }
        return receita;
    }

    public void cadastrarSinistro() {
        
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

    public void transferirSeguro(ClientePF c1, ClientePF c2) {
        /*
         * Transfere todos os veículos do cliente c1 para o cliente c2, modificando o
         * valor do seguro de ambos
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
         * Transfere todos os veículos do cliente c1 para o cliente c2, modificando o
         * valor do seguro de ambos
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

    public List <Seguro> getSegurosPorCliente(Cliente cliente) {
        /*
         * Retorna um lista com todos os seguros de um cliente PJ
         */
        return cliente.getListaSeguros();
    }

    public List <Sinistro> getSinistrosPorCliente(ClientePF cliente) {
        for (Seguro seguro : cliente.getListaSeguros()) {
            if (seguro.getCliente().equals(cliente)) {
                return seguro.getListaSinistros();
            }
        }
        return null; // esse cliente não tem sinistros
    }

    public List <Sinistro> getSinistrosPorCliente(ClientePJ cliente) {
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