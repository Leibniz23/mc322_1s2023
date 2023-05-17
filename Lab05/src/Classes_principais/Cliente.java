package Classes_principais;
import java.util.*;

public abstract class Cliente {
    protected String nome;
    protected String endereco;
    protected List <Veiculo> listaVeiculos;
    protected double valorSeguro;

    public Cliente(String nome, String endereco, Veiculo ... listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
        this.valorSeguro = -1.0; // valor inválido, ao ser cadastrado em uma seguradora ele recebe o valor real
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
        /*
         * Apenas atualiza a lista de veículos do Cliente, não se preocupa em 
         * recalcular seu valor de seguro
         */
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

    public boolean removerVeiculo(String placa) {
        /*
         * Apenas atualiza a lista de veículos do Cliente, não se preocupa em 
         * recalcular seu valor de seguro
         */
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

    public void visualizarVeiculos() {
        for (Veiculo v : this.listaVeiculos) {
            System.out.println(v.toString());
            System.out.println();
        }
    }

    public void limparVeiculos(){
        this.listaVeiculos.clear();
    }
    
    public double calculaScore() {
        return -1.0; // inválido porque o Cliente pai não deve ser usado
    }

    public String toString() {
        return "Nome: " + getNome() + "\n" +
                "Endereco: " + getEndereco() + "\n";

    }

}