package Classes_principais;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientePJ extends Cliente {
    final String CNPJ ;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;

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