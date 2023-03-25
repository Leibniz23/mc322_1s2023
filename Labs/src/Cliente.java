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

    public String toString() {
        return "Nome: " + getNome() + "\n" +
            "CPF: " + getCpf() + "\n" +
            "Data de nascimento: " + getDataNascimento() + "\n" +
            "Idade: " + getIdade() + "\n" +
            "Endereco: " + getEndereco();
    }
    
    public String validarCPF(String cpf) {
        cpf = this.cpf.replaceAll("[^0-9]", "");
        int primeiro = cpf.digito1(cpf);
        int segundo = cpf.digito2(cpf);
        if (cpf[9] == primeiro && cpf[10] == segundo) {
            
        }
    }

}

/*
 * como funciona o replaceAll
 * Como fazer a main
 * tem q fazer construtores?
 */