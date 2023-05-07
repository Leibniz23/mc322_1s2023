package Classes_principais;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientePF extends Cliente {
    final String CPF;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String genero;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, String cpf, String educacao, LocalDate dataNascimento,
                    String genero, String classeEconomica, Veiculo ... listaVeiculos) {
    super(nome, endereco);
    this.CPF = cpf.replaceAll("[^0-9]", ""); // só os números do CPF
    this.dataLicenca = LocalDate.now(); // LocalDate.get para pegar dia mes e ano
    this.educacao = educacao;
    this.dataNascimento = dataNascimento;
    this.genero = genero;
    this.classeEconomica = classeEconomica;
    for (Veiculo veiculo : listaVeiculos) {
        this.listaVeiculos.add(veiculo);
    }
    }

    @Override
    public String getCadastro() {
        return this.CPF;
    }

    public void setdataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getDataLicenca() {
        String data = this.dataLicenca.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public String getEducacao() {
        return this.educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return this.classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public String getDataNascimento() { // não tem set porque ninguém muda de data de nascimento
        String data = this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    private int calcIdade() {
        int ano_nascimento = this.dataNascimento.getYear();
        int idade = LocalDate.now().getYear() - ano_nascimento;
        return idade;
    }

    @Override
    public double calculaScore() {
        int idade = calcIdade();
        if (18<=idade || idade < 30) {
            int carros = this.getListVeiculos().size();
            double score = CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_18_30.getFator() * carros;
            return score;

        } else if (30<=idade || idade<60) {
            int carros = this.getListVeiculos().size();
            double score = CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_30_60.getFator() * carros;
            return score;

        } else if (60<=idade || idade<90) {
            int carros = this.getListVeiculos().size();
            double score = CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_60_90.getFator() * carros;
            return score;

        } else {
            return -1.0; // Score inválido
        }
    }

    @Override
    public String toString() {
        return
            "CPF: " + getCadastro() + "\n" +
            "DataLicenca: " + getDataLicenca() + "\n" +
            "DataNascimento: " + getDataNascimento() + "\n" +
            "Educacao: " + getEducacao() + "\n" +
            "Genero: " + getGenero() + "\n" +
            "Classe Economica: " + getClasseEconomica() + "\n";
    }
}

