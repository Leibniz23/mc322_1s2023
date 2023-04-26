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
    
    public static boolean validarCNPJ(String cnpj) {
        if (cnpj.length() != 14) {
            return false;
        }
    
        /* Calcula o primeiro dígito verificador */ 
        int soma = 0;
        int peso = 5;
        for (int i = 0; i < 12; i++) {
            soma += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * peso;
            peso--;
            if (peso < 2) {
                peso = 9;
            }
        }
        int resto = soma % 11;
        int digito1;
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        /* Calcula o segundo dígito verificador */
        soma = 0;
        peso = 6;
        for (int i = 0; i < 13; i++) {
            soma += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * peso;
            peso--;
            if (peso < 2) {
                peso = 9;
            }
        }
        resto = soma % 11;
        int digito2;
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }
        if (Integer.parseInt(String.valueOf(cnpj.charAt(12))) == digito1 && 
            Integer.parseInt(String.valueOf(cnpj.charAt(13))) == digito2) {
            return true;
        } else {
            return false;
        }
    }
}
