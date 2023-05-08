package Classes_principais;
import java.util.*;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Sinistro> listaSinistros;
    private List<Cliente> listaClientes;

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<Sinistro>();
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

        boolean result = this.listaClientes.add(newClient);
        return result;
    }

    public boolean removerCliente(String cliente) { // Supõe que o cliente passado é válido
        Iterator<Cliente> itClientes = this.listaClientes.iterator();
        while (itClientes.hasNext()) { // percorre a lista usando Iterator
            Cliente cList = itClientes.next();
            if (cList.getCadastro().equals(cliente)) {
                itClientes.remove();
            }
        }

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
                return true;
            }
        }
        return false; // o cliente que você tentou adicionar um veiculo não existe
    }

    public boolean excluirVeiculo(Veiculo v, String cadastro) {
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cadastro)) {
                c.removerVeiculo(v);
                return true;
            }
        }
        return false; // cliente que você tentou remover o veículo não existe
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

    public boolean gerarSinistro(String data, String endereco, Veiculo veiculo, Cliente cliente) {
        Sinistro novoSinistro = new Sinistro(data, endereco, this, veiculo, cliente);
        boolean result = this.listaSinistros.add(novoSinistro); // não precisa verificar se está na lista porque cada sinistro tem um ID diferente
        calculaPrecoSeguroCliente(cliente);
        return result;
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

    public String toString() {
        return 
            "Nome: " + getNome() + "\n" +
            "Telefone: " + getTelefone() + "\n" +
            "Email:" + getEmail() + "\n" +
            "Endereco:" + getEndereco() + "\n";
    }
}
