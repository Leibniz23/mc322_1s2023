package Classes_principais;

import java.time.LocalDate;
import java.util.*;

public class Condutor {
    private final String CPF;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNascimento;
    private List<Sinistro> listaSinistros;
    private static List<Condutor> listaCondutores = new ArrayList<Condutor> ();

    // static map que armazena os condutores existentes, pra buscar um basta buscar pelo cpf (em o(1))
    // quando for criar ou alterar um condutor, adicionar nesse map


    public Condutor(String CPF, String nome, String telefone, String endereco, String email, LocalDate dataNascimento) {
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaSinistros = new ArrayList<Sinistro> ();
        listaCondutores.add(this);
    }

    public String getCPF() {
        return this.CPF;
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

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public boolean addSinistro(Sinistro sinistro) {
        this.listaSinistros.add(sinistro);
        return true;
    }
    
}
