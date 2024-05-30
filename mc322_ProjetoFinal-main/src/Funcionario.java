

public abstract class Funcionario {
    /*
    Superclasse dos tipos Vendedor e Gerente
     */
    private final Unidade unidade;
    private final String nome;
    private final String cpf;
    private double salario;
    private final String login;
    private final String senha;

    public Funcionario(Unidade unidade, String nome, String cpf, double salario, String login, String senha) {
        this.unidade = unidade;
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.login = login;
        this.senha = senha;
    }

    public double getSalario() {
        return salario;
    }

    public String getNome() {
        return nome;
    }
    
    public Unidade getUnidade() {
        return unidade;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Unidade: " + getUnidade().getCnpj() + "\n" +
                "Nome: " + getNome() + "\n" +
                "CPF: " + getCpf() + "\n" +
                "Salário: R$ " + getSalario() + "\n" +
                "Login: " + getLogin() + "\n" +
                "Senha: " + getSenha();
    }
}
