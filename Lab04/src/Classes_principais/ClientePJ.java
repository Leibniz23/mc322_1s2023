package Classes_principais;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientePJ extends Cliente {
    final String CNPJ ;
    private LocalDate dataFundacao;

    public ClientePJ(String nome, String endereco, String cnpj, LocalDate dataFundacao, Veiculo ... lista_Veiculos) {
        super(nome,endereco);
        this.CNPJ = cnpj.replaceAll("[^0-9]", "");
        this.dataFundacao = dataFundacao;
        for (Veiculo veiculo : listaVeiculos) {
            this.listaVeiculos.add(veiculo); // colocar no cliente padrao
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

    @Override
    public String toString() {
        return
            "CNPJ: " + getCadastro() + "\n" +
            "DataFundacao: " + getDataFundacao() + "\n";
    }
}
