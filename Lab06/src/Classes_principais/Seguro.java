package Classes_principais;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    /* Construtor */
    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.ID = cont++;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaCondutores = new ArrayList<Condutor>();
    }

    /* Getters e Setters */
    public int getID() {
        return this.ID;
    }

    public String getDataInicio() {
        String data = this.dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        String data = this.dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
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

    /* 
     * Método abstrato que calculará o valor do seguro conforme o tipo do seguro
     */
    public abstract double calcularValor();

    /* 
     * Método abstrato que irá gerar um sinistro no seguro em questão, no nome do condutor passado
     */
    public abstract boolean gerarSinistro(LocalDate data, String endereco, Condutor condutor);

    /* 
     * Método abstrato que irá autorizar um condutor, ou seja,
     * colocar ele na lista de condutores do seguro
     */
    public abstract boolean autorizarCondutor(Condutor c); // adiciona esse condutor na lista de condutores
    
    /* 
     * Método abstrato que irá desautorizar um condutor, ou seja,
     * remover ele da lista de condutores do seguro
     */
    public abstract boolean desautorizarCondutor(String cpf);

    /*
    * Esses getters e setters abaixo servem apenas pra eu poder chamar eles em
    * qualquer instância de Seguro, mesmo sem saber se trata-se de um
     * seguroPF ou PJ
     */
    public abstract Cliente getCliente();
    
    public abstract Frota getFrota();

    public abstract boolean setFrota(Frota f);
    
    public abstract Veiculo getVeiculo();
    
    public abstract boolean setVeiculo(Veiculo v);
    
    public abstract boolean setCliente(ClientePF c);
    
    public abstract boolean setCliente(ClientePJ c);

    public String toString() {
        return
            "   ID: " + getID() + "\n" +
            "   Data de Inicio: "+ getDataInicio() +"\n"+
            "   Data de Fim: "+ getDataFim() +"\n"+
            "   Seguradora: "+ getSeguradora().getNome() +"\n";
    }
}
