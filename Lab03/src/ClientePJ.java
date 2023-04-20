import java.util.Date;

public class ClientePJ extends Cliente {
    final String CNPJ ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, Veiculo ... lista_Veiculos) {
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

    public Date getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }


    @Override
    public String toString() {
        return "{" +
            " CNPJ='" + getCadastro() + "'" +
            ", dataFundacao='" + getDataFundacao() + "'" +
            "}";
    }
    
    public boolean validarCNPJ(String cnpj){
        return true;
    // ...
    }
}
