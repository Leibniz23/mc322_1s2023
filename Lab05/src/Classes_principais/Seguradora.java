package Classes_principais;
import java.time.LocalDate;
import java.util.*;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Seguro> listaSeguro;
    private List<Cliente> listaClientes;

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<Seguro>();
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
    
    public List<Sinistro> listarSinistros() {
        return this.listaSinistros;
    }
    
    public boolean cadastrarCliente(Cliente newClient) { // Supõe que o cliente passado é válido
        for (Cliente client : this.listaClientes) {
            if(newClient.getCadastro().equals(client.getCadastro())) {
                return false;
            }
        }
        calculaPrecoSeguroCliente(newClient);
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

        Iterator<Sinistro> itSinistros = this.listaSinistros.iterator();
        while (itSinistros.hasNext()) { // percorre a lista usando Iterator
            Sinistro sList = itSinistros.next();
            if (sList.getCliente().getCadastro().equals(cliente)) {
                itSinistros.remove();
            }
        }
        return true;
    }

    public boolean cadastrarVeiculo(Veiculo v, String cadastro) {
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cadastro)) {
                c.adicionarVeiculo(v);
                calculaPrecoSeguroCliente(c);
                return true;
            }
        }
        return false; // o cliente que você tentou adicionar um veiculo não existe
    }

    public boolean excluirVeiculo(String placa) {
        for (Cliente c : listaClientes) {
            if (c.removerVeiculo(placa)) {
                calculaPrecoSeguroCliente(c);
                return true;
            }
        }
        return false; // veículo que você tentou remover não existe
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

    public boolean gerarSinistro(LocalDate data, String endereco, Veiculo veiculo, Cliente cliente) {
        Sinistro novoSinistro = new Sinistro(data, endereco, this, veiculo, cliente);
        boolean result = this.listaSinistros.add(novoSinistro); // não precisa verificar se está na lista porque cada sinistro tem um ID diferente
        calculaPrecoSeguroCliente(cliente);
        return result;
    }

    public boolean removerSinistro(int id) {
        Iterator<Sinistro> itSinistros = this.listaSinistros.iterator();
        while (itSinistros.hasNext()) { // percorre a lista usando Iterator
            Sinistro sList = itSinistros.next();
            if (sList.getID() == id) {
                itSinistros.remove();
                calculaPrecoSeguroCliente(sList.getCliente());
                return true;
            }
        }
        return false;
    }

    public boolean visualizarSinistro(String cliente) {
        boolean result = false;
        for (Sinistro sList : this.listaSinistros) {
            if (sList.getCliente().getCadastro().equals(cliente)) {
                System.out.println(sList.toString());
            }
        }
        return result;
    }

    public double calculaPrecoSeguroCliente(Cliente client) {
        int qtdeSinistros = 0;
        for (Sinistro sin : listaSinistros) {
            if ((sin.getCliente().getCadastro()).equals(client.getCadastro())) {
                qtdeSinistros++;
            }
        }
        double PrecoSeguroCliente = client.calculaScore() * (1+ qtdeSinistros);
        client.setValorSeguro(PrecoSeguroCliente);
        return PrecoSeguroCliente;
    }

    public double calculaReceita() {
        double receita = 0;
        for (Cliente client : listaClientes) {
            receita+=client.getValorSeguro();
        }
        return receita;
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

    public void transferirSeguro(Cliente c1, Cliente c2) {
        /*
         * Transfere todos os veículos do cliente c1 para o cliente c2, modificando o
         * valor do seguro de ambos
         */
        for (int i=0; i<c2.getListaVeiculos().size(); i++) {
            c1.adicionarVeiculo(c2.getListaVeiculos().get(i));
        }
        c2.limparVeiculos();
        calculaPrecoSeguroCliente(c1);
        calculaPrecoSeguroCliente(c2);
    }

    public String toString() {
        return 
            "Nome: " + getNome() + "\n" +
            "Telefone: " + getTelefone() + "\n" +
            "Email:" + getEmail() + "\n" +
            "Endereco:" + getEndereco() + "\n";
    }
}