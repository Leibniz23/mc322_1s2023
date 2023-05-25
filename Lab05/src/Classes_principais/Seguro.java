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

    public boolean autorizarCondutor(Condutor c) { // adiciona esse condutor na lista de condutores
        boolean existe = false;
        for (Condutor iC : listaCondutores) {
            if (iC.getCPF().equals(c.getCPF())) {
                existe = true;
            }
        }
        if (!existe) {
            this.listaCondutores.add(c);
            return true;
        } else {
            return false;
        }
    }

    public boolean desautorizarCondutor(Condutor c) { // remove esse condutor na lista de condutores
        Iterator<Condutor> itCondutor = this.listaCondutores.iterator();
        while (itCondutor.hasNext()) { // percorre a lista usando Iterator
            Condutor cList = itCondutor.next();
            if (cList.getCPF().equals(c.getCPF())) {
                itCondutor.remove();
                return true;
            }
        }
        return false; // se chegou aqui o condutor não está cadastrado
    }

    public abstract Cliente getCliente();
}
