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
    
    public static boolean validarCPF(String cpf) {
        char[] v_cpf = cpf.toCharArray();
        int n = cpf.length();
        int n_semdigito = n-2;
        if (n != 11) {
            return false;
        }
        int iguais = 0;
        for (int i=1; i<n_semdigito; i++) {
            if (v_cpf[i]==v_cpf[i-1]) { // se um digito é igual ao seu antecessor
                iguais++;
            }
        }
        if (iguais == n_semdigito) { // verifica se todos os digitos são iguais
            return false;
        }

        /* Primeiro digito verificador */
        int primeiro = 0;
        for (int i=0, j=10; i<n_semdigito && j>=(n-n_semdigito); i++, j--) { // n - n_semdigito é 2
            primeiro += (Character.digit(v_cpf[i],10))*j;
        }
        primeiro = primeiro % n;
        primeiro = n - primeiro;
        if (primeiro != Character.digit(v_cpf[n-2], 10)) {
            return false;
        }

        /* Segundo digito verificador */
        int segundo = 0;
        for (int i=0, j=11; i<n-1 && j>=(n-n_semdigito); i++, j--) { // i<n-1 porque agora contamos o primeiro digito verificador
            segundo += (Character.digit(v_cpf[i],10))*j;
        }
        segundo = segundo % n;
        segundo = n - segundo;
        if (segundo>=10) {
            segundo = 0;
        }
        if (segundo != Character.digit(v_cpf[n-1], 10)) {
            return false;
        }

        return true; // Se não falhou até aqui, então é válido
    }
}

