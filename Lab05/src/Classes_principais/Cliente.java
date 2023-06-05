package Classes_principais;

import java.util.*;

public abstract class Cliente {
    protected String nome;
    protected String telefone;
    protected String endereco;

    /* Considerei mais fácil que cada cliente soubesse quais seguros possui,
     * para isso serve essa lista
     */
    protected List<Seguro> listaSeguros;
    protected String email;
    
    /* Construtor */
    public Cliente(String nome, String telefone, String endereco, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.listaSeguros = new ArrayList<Seguro>();
    }
    
    /* Getters e Setters */
    public abstract String getCadastro();

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /* Método abstrato que adiciona o seguro passado a lista de seguros do cliente */
    public abstract boolean adicionarSeguro(Seguro seguro);

    /* Método abstrato que remove o seguro com o ID passado da lista de seguros do cliente */
    public abstract boolean removerSeguro(int id);

    /* Método abstrato que torna a lista de seguros de um cliente uma 
     * lista vazia
    */
    public abstract void limparSeguros();

    /* Método abstrato que retorna a lista de seguros do cliente */
    public abstract List<Seguro> getListaSeguros();

    /* Método abstrato que redefine a lista de seguros do cliente */
    public abstract void setListaSeguros(List<Seguro> seguro);

    /* Método abstrato que, no clientePF calculará a idade da pessoa e
     * no clientePJ calculará os anos pós fundação da empresa
    */
    public abstract int calcIdade();


    /* Método abstrato que, no clientePF mostrará todos os veículos cadastrados
     * (não necessariamente segurados) e no clientePj mostrará cada frota e os 
     * veículos pertencentes a ela
     */
    public abstract void visualizarVeiculos();

    /* Métodos apenas para eu poder chama-los sem saber qual tipo de cliente é
     * e quando o método não faz sentido com aquele cliente ele retorna um valor inválido
     */
    public abstract List<Veiculo> getVeiculosPorFrota(int code);

    public abstract boolean cadastrarFrota(Frota frota);
    
    public abstract boolean atualizarFrota(int code, String placa);
    
    public abstract boolean atualizarFrota(int code, Veiculo veiculo);
    
    public abstract boolean atualizarFrota(int code);
    
    public abstract boolean cadastrarVeiculo(Veiculo veiculo);

    public abstract boolean removerVeiculo(String placa);

    public String toString() {
        return "Nome: " + getNome() + "\n" +
                "Telefone: " + getTelefone() + "\n" +
                "Endereço: " + getEndereco() + "\n" +
                "Email: " + getEmail() + "\n";

    }

}