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
    
    public boolean cadastrarCliente(Cliente newClient) {
        for (Cliente client : this.listaClientes) {
            if(newClient.getCadastro().equals(client.getCadastro())) {
                return false;
            }
        }
        boolean result = this.listaClientes.add(newClient);
        return result;
    }

    public boolean removerCliente(String cliente) { // assumindo que String cliente é o CPF/CNPJ
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


    public String toString() {
        return 
            "Nome: " + getNome() + "\n" +
            "Telefone: " + getTelefone() + "\n" +
            "Email:" + getEmail() + "\n" +
            "Endereco:" + getEndereco() + "\n";
    }
}
