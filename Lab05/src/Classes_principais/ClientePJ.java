package Classes_principais;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClientePJ extends Cliente {
    final String CNPJ ;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;
    private List<Frota> listaFrota;

    public ClientePJ(String nome, String endereco, String cnpj, LocalDate dataFundacao, int funcionarios, Veiculo ... lista_Veiculos) {
        super(nome,endereco);
        this.CNPJ = cnpj.replaceAll("[^0-9]", "");
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = funcionarios;
        for (Veiculo veiculo : listaVeiculos) {
            this.adicionarVeiculo(veiculo);
        }
    }

    @Override
    public String getCadastro() {
        return this.CNPJ;
    }

    public String getDataFundacao() {
        String data = this.dataFundacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return this.qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public boolean atualizarFrota(String code, String acao, String placa) {
        /*
         * Adiciona ou remove, da frota indicada, um veículo
         */

        // apagar dps: vai ter que buscar o veículo por placa e a frota tbm, talvez criar um metodo pra isso
        if (acao.equals("rem")) {

        } else if (acao.equals("add")) {

        }
    }

    public boolean atualizarFrota(String code) {
        if (code.equals("delete")) {

        }
    }

    public 

    @Override
    public double calculaScore() {
        double score = CalcSeguro.VALOR_BASE.getFator() * (1+ (getQtdeFuncionarios()/100) * getListaVeiculos().size());
        return score;
    }

    @Override
    public String toString() {
        return
            "CNPJ: " + getCadastro() + "\n" +
            "DataFundacao: " + getDataFundacao() + "\n" +
            "Quantidade de Funcionários: " + Integer.toString(getQtdeFuncionarios()) + "\n" +
            "Valor do Seguro: " + Double.toString(getValorSeguro()) + "\n";
    }
}