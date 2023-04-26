package Classes_principais;
import java.util.*;

public class Cliente {
    protected String nome;
    protected String endereco;
    protected List <Veiculo> listaVeiculos;

    public Cliente(String nome, String endereco, Veiculo ... listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Veiculo> getListVeiculos() {
        return listaVeiculos;
    }

    public String getCadastro() { // apenas para o @Override de ClientePF e ClientePJ funcionar
        return null;
    }

    public String toString() {
        return "Nome: " + getNome() + "\n" +
                "Endereco: " + getEndereco() + "\n";

    }

}