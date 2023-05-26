package Classes_principais;

import java.time.LocalDate;
import java.util.*;

public abstract class Seguro {
    protected final int ID;
    protected LocalDate dataInicio;
    protected LocalDate dataFim;
    protected Seguradora seguradora;
    protected List<Sinistro> listaSinistros;
    protected List<Condutor> listaCondutores;
    protected double valorMensal;
    private static int cont = 1;

    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.ID = cont++;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaCondutores = new ArrayList<Condutor>();
        this.valorMensal = calcularValor();
    }

    public int getID() {
        return this.ID;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public List<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public List<Condutor> getListaCondutores() {
        return this.listaCondutores;
    }

    public void setListaCondutores(List<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal() {
        return this.valorMensal;
    }

    public void setValorMensal(int valorMensal) {
        this.valorMensal = valorMensal;
    }

    public abstract double calcularValor();

    public abstract boolean gerarSinistro(LocalDate data, String endereco,Condutor condutor);

    public abstract boolean autorizarCondutor(Condutor c); // adiciona esse condutor na lista de condutores
        
    public abstract boolean desautorizarCondutor(String cpf);

    public abstract Cliente getCliente();

    public void setCliente(Cliente c) { 
        /*
        Se eu colocasse como abstract não daria certo no metodo
        transferir seguro, pois eu não sei se o cliente será do
        tipo PF ou PJ, e definir abstract setCliente(Cliente c) também
        não da certo, então o jeito foi usar override mesmo
        */
    }
}
