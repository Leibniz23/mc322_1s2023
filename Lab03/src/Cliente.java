import java.util.*;

public class Cliente {
    private String nome;
    private String endereco;
    private List <Veiculo> listaVeiculos;

    public Cliente(String nome, String endereco, List <Veiculo> listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
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

    public String toString() {
        return "Nome: " + getNome() + "\n" +
            "CPF: " + getCpf() + "\n" +
            "Data de nascimento: " + getDataNascimento() + "\n" +
            "Idade: " + getIdade() + "\n" +
            "Endereco: " + getEndereco() + "\n";
    }

}