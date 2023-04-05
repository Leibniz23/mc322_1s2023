public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean validarCPF(String cpf) {
        cpf = this.cpf.replaceAll("[^0-9]", "");
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

    public String toString() {
        return "Nome: " + getNome() + "\n" +
            "CPF: " + getCpf() + "\n" +
            "Data de nascimento: " + getDataNascimento() + "\n" +
            "Idade: " + getIdade() + "\n" +
            "Endereco: " + getEndereco() + "\n";
    }

}