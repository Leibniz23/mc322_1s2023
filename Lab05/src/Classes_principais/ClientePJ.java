package Classes_principais;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientePJ extends Cliente {
    final String CNPJ ;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;
    private List<Frota> listaFrota;

    public ClientePJ(String nome, String telefone, String endereco, String email,
                        String cnpj, LocalDate dataFundacao, int funcionarios) {
        super(nome, telefone, endereco, email);
        this.CNPJ = cnpj.replaceAll("[^0-9]", "");
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = funcionarios;
        this.listaFrota = new ArrayList<Frota>();
    }

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

    
    public List<Frota> getListaFrota() {
        return this.listaFrota;
    }
    
    public void setListaFrota(List<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }
    
    public int calcIdade() {
        int ano_fundacao = this.dataFundacao.getYear();
        int idade = LocalDate.now().getYear() - ano_fundacao;
        return idade;
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

    @Override
    public String toString() {
        return
            "CNPJ: " + getCadastro() + "\n" +
            "DataFundacao: " + getDataFundacao() + "\n" +
            "Quantidade de Funcionários: " + Integer.toString(getQtdeFuncionarios()) + "\n" +
            "Valor do Seguro: " + Double.toString(getValorSeguro()) + "\n";
    }
}