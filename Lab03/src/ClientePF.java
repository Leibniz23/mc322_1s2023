import java.time.LocalDate;
import java.util.*;

public class ClientePF extends Cliente {
    private String CPF;
    private LocalDate dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;

    public ClientePF(String nome , String endereco , Date dataLicenca,
                        String educacao , String genero , String classeEconomica ,
                        List < Veiculo > listaVeiculos , String cpf , Date dataNascimento ) {
    super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
    this.cpf = cpf ;
    this.dataNascimento = dataNascimento ;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString () {
    // ...
    }
    public boolean validarCPF ( String cpf ){
    // ...
    }
    
    public boolean validarCPF(String cpf) {
        cpf = this.CPF.replaceAll("[^0-9]", "");
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
