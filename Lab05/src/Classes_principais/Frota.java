package Classes_principais;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Frota {
    private final int code;
    private List<Veiculo> listaVeiculos;
    private static int cont = 1;

    /* Construtor */
    public Frota() {
        this.code = cont++;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    /* Adiciona o veículo passado na lista da frota */
    public boolean addVeiculo(Veiculo v) {
        boolean existe = false;
        for (Veiculo iV : listaVeiculos) {
            if (iV.getPlaca().equals(v.getPlaca())) {
                existe = true;
            }
        }
        if (!existe) {
            this.listaVeiculos.add(v);
            return true;
        } else {
            return false;
        }
    }

    /* Remove o veículo dono da placa passada da lista da frota */
    public boolean remVeiculo(String placa) {
        Iterator<Veiculo> itVeiculo = this.listaVeiculos.iterator();
        while (itVeiculo.hasNext()) { // percorre a lista usando Iterator
            Veiculo vList = itVeiculo.next();
            if (vList.getPlaca().equals(placa)) {
                itVeiculo.remove();
                return true;
            }
        }
        return false; // o veículo não existe
    }

    /* Getters e Setters */
    public int getCode() {
        return this.code;
    }

    public List<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public String toString() {
        return
            "Código da frota: " + getCode() + "\n";
    }

}
