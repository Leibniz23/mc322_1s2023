package Classes_principais;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClientePF extends Cliente {
    final String CPF;
    private String genero;
    private String educacao;
    private LocalDate dataNascimento;
    private List<Veiculo> listaVeiculos;

    public ClientePF(String nome, String telefone, String endereco, String email, String cpf,
                    String genero, String educacao, LocalDate dataNascimento){
        super(nome, telefone, endereco, email);
        this.CPF = cpf.replaceAll("[^0-9]", ""); // só os números do CPF
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    public boolean addVeiculo(Veiculo v) {
        /*
         * Adiciona o objeto veiculo passado a lista de
         * veículos do cliente
         */
        boolean existe = false;
        for (Veiculo iV : listaVeiculos) {
            if (iV.getPlaca().equals(v.getPlaca())) {
                existe = true;
            }
        }
        if (!existe) {
            this.listaVeiculos.add(v);
            return true;
        } else {
            return false;
        }
    }

    public boolean remVeiculo(String placa) {
        /*
         * Remove o veículo que tem a placa passada
         * da lista de veículos do cliente
         */
        Iterator<Veiculo> itVeiculo = this.listaVeiculos.iterator();
        while (itVeiculo.hasNext()) { // percorre a lista usando Iterator
            Veiculo vList = itVeiculo.next();
            if (vList.getPlaca().equals(placa)) {
                itVeiculo.remove();
                return true;
            }
        }
        return false; // o veículo não existe
    }

    @Override
    public String getCadastro() {
        return this.CPF;
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

    public String getDataNascimento() { // não tem set porque ninguém muda de data de nascimento
        String data = this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }


///////// por hr manter essa pt, possivelmente mandar pra seguro dps
    public int calcIdade() {
        int ano_nascimento = this.dataNascimento.getYear();
        int idade = LocalDate.now().getYear() - ano_nascimento;
        return idade;
    }

    @Override
    public double calculaScore() {
        int carros, idade = calcIdade();
        double score;
        if (18<=idade || idade < 30) {
            carros = this.getListaVeiculos().size();
            score = CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_18_30.getFator() * carros;
            return score;

        } else if (30<=idade || idade<60) {
            carros = this.getListaVeiculos().size();
            score = CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_30_60.getFator() * carros;
            return score;

        } else if (60<=idade || idade<90) {
            carros = this.getListaVeiculos().size();
            score = CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_60_90.getFator() * carros;
            return score;

        } else {
            return -1.0; // Score inválido
        }
    }

/////// até aqui

    @Override
    public String toString() {
        /*
         * Não coloquei todas as informações, como a maioria dos atributos herdados,
         * para facilitar a visualização
         */
        return        
            "Nome: " + getNome() + "\n" +
            "CPF: " + getCadastro() + "\n" +
            "DataNascimento: " + getDataNascimento() + "\n" +
            "Educacao: " + getEducacao() + "\n" +
            "Genero: " + getGenero() + "\n";
    }
}
