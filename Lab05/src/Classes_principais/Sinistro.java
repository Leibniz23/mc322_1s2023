package Classes_principais;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sinistro {
    final int ID;
    private LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    private static int cont = 1;

    public Sinistro(LocalDate data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = cont++;
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public Sinistro(){
        this.ID = cont++;
    }

    public int getID() {
        return this.ID;
    }

    public LocalDate getData() {
        return this.data;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String toString() {
        return "ID: " + getID() + "\n" +
            "Data: " + getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" + // só transforma a data em stirng
            "Endereco: " + getEndereco() + "\n" +
            "Seguradora: " + getSeguradora().getNome() + "\n" + // só o nome para facilitar a visualização
            "Veiculo:\n" + getVeiculo().toString() + "\n" +
            "Cadastro do cliente: " + getCliente().getCadastro() + "\n";
    }
}