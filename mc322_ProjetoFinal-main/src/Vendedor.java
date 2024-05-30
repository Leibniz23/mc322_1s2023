import java.util.*;

public class Vendedor extends Funcionario{
    /*
    Subclasse de Funcionário, não tem permissão para criar novos funcionários,
    mas pode realizar Compras
     */
    private final List<Compra> listaCompras;

    /*

    Construtor
     */
    public Vendedor(Unidade unidade, String nome, String cpf, double salario, String login, String senha) {
        super(unidade, nome, cpf, salario, login, senha);
        this.listaCompras = new ArrayList<>();
    }

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

}
