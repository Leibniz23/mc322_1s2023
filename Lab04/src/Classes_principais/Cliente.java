package Classes_principais;
import java.util.*;

public class Cliente {
    protected String nome;
    protected String endereco;
    protected List <Veiculo> listaVeiculos;
    protected double valorSeguro;

    public Cliente(String nome, String endereco, Veiculo ... listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
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

    public List<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public String getCadastro() { // apenas para o @Override de ClientePF e ClientePJ funcionar
        return null;
    }
    
    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    
    public double getValorSeguro() {
        return this.valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public boolean adicionarVeiculo(Veiculo v) {
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

    public boolean removerVeiculo(Veiculo v) {
        Iterator<Veiculo> itVeiculo = this.listaVeiculos.iterator();
        while (itVeiculo.hasNext()) { // percorre a lista usando Iterator
            Veiculo vList = itVeiculo.next();
            if (vList.getPlaca().equals(v.getPlaca())) {
                itVeiculo.remove();
                return true;
            }
        }
        return false; // o veículo não existe
    }
    
    public double calculaScore() {
        return -1.0; // inválido porque o Cliente pai não deve ser usado
    }

    public String toString() {
        return "Nome: " + getNome() + "\n" +
                "Endereco: " + getEndereco() + "\n";

    }

}