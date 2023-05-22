package Classes_principais;

import java.util.Iterator;
import java.util.List;

public class Frota {
    private String code;
    private List<Veiculo> listaVeiculos;

    public Frota(String code, List<Veiculo> listaVeiculos) {
        this.code = code;
        this.listaVeiculos = listaVeiculos;
    }

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

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public String toString() {
        return
            "Code: " + getCode() + "\n" +
            "Veículos da frota: " + getListaVeiculos() + "\n";
    }

}
