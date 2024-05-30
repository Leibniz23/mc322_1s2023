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

    /* Sempre que um novo condutor é criado ou alterado, essa modificação é
     * registrada nessa lista estática para evitar conflitos de condutores iguais com
     * características diferentes
     */
    private static List<Condutor> listaCondutores = new ArrayList<Condutor> ();

    /* Construtor */
    public Condutor(String CPF, String nome, String telefone, String endereco, String email, LocalDate dataNascimento) {
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaSinistros = new ArrayList<Sinistro> ();
        listaCondutores.add(this); // o condutor é adicionado na lista no momento que é criado
    }

    /* Getters e Setters */
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

    public static List<Condutor> getListaCondutores() {
        return Condutor.listaCondutores;
    }

    /* Adiciona um sinistro na lista do condutor */
    public boolean addSinistro(Sinistro sinistro) {
        this.listaSinistros.add(sinistro);
        return true;
    }
    
    /* Retorna o condutor dono desse CPF da lista de condutores da classe */
    public static Condutor encontrarCondutor(String cpf) {
        for (Condutor c : Condutor.listaCondutores) {
            if (c.getCPF().equals(cpf)) {
                return c;
            }
        }
        return null; // condutor não está na lista
    }

    public String toString() { // Sucinto para não poluir o terminal
        return "Nome: "+ getNome() +"\n" +
                "CPF: "+getCPF() + "\n";
    }
    
}
