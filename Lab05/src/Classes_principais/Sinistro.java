package Classes_principais;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sinistro {
    final int ID;
    private LocalDate data;
    private String endereco;
    private Seguro seguro;
    private Condutor condutor;
    private static int cont = 1;

    public Sinistro(LocalDate data, String endereco, Seguro seguro,Condutor condutor) {
        this.ID = cont++;
        this.data = data;
        this.endereco = endereco;
        this.seguro = seguro;
        this.condutor = condutor;
    }
    
    public int getID() {
        return this.ID;
    }
    
    public LocalDate getData() {
        return this.data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro Seguro) {
        this.seguro = Seguro;
    }

    public Condutor getCondutor() {
        return this.condutor;
    }

    public void setCondutor(Condutor Condutor) {
        this.condutor = Condutor;
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
            "ID do seguro: " + getSeguro().getID() + "\n" + // só o ID para facilitar a visualização
            "Cadastro do Condutor: " + getCondutor().getCPF() + "\n";
    }
}