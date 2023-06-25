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

    /* Construtor */
    public ClientePF(String nome, String telefone, String endereco, String email, String cpf,
                    String genero, String educacao, LocalDate dataNascimento){
        super(nome, telefone, endereco, email);
        this.CPF = cpf.replaceAll("[^0-9]", ""); // só os números do CPF
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }
    
    /*
     * Adiciona o objeto veiculo passado à lista de
     * veículos do cliente
     */
    public boolean cadastrarVeiculo(Veiculo v) {
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

    /*
     * Remove o veículo que tem a placa passada
     * da lista de veículos do cliente
     */
    public boolean removerVeiculo(String placa) {
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

    /* Getters e Setters */
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

    public LocalDate getDataNascimento() { // não tem set porque ninguém muda de data de nascimento
        return this.dataNascimento;
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

    /* Explicada no cliente pai */
    public int calcIdade() {
        int ano_nascimento = this.dataNascimento.getYear();
        int idade = LocalDate.now().getYear() - ano_nascimento;
        return idade;
    }

    /* Explicada no cliente pai */
    public List<Seguro> getListaSeguros() {
        return this.listaSeguros;
    }

    /* Explicada no cliente pai */
    public void setListaSeguros(List<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    /* Explicada no cliente pai */
    public boolean adicionarSeguro(Seguro seguro) {
        return this.listaSeguros.add(seguro);
    }

    /* Explicada no cliente pai */
    public boolean removerSeguro(int id) {
        Iterator<Seguro> itSeguro = this.listaSeguros.iterator();
        while (itSeguro.hasNext()) { // percorre a lista usando Iterator
            Seguro sList = itSeguro.next();
            if (sList.getID() == id) {
                itSeguro.remove();
                return true;
            }
        }
        return false;
    }

    /* Explicada no cliente pai */
    public void limparSeguros() {
        this.listaSeguros.clear();
    }

    /* Explicada no cliente pai */
    public void visualizarVeiculos() {
        for (Veiculo v : this.listaVeiculos) {
            System.out.println(v.toString());
        }
    }
    
    
    /* Explicação desses métodos está no cliente pai */
    public List<Veiculo> getVeiculosPorFrota(int code) {return null;}

    public boolean cadastrarFrota(Frota frota) {return false;}
    
    public boolean atualizarFrota(int code, String placa) {return false;}
    
    public boolean atualizarFrota(int code, Veiculo veiculo) {return false;}
    
    public boolean atualizarFrota(int code) {return false;}
    
    /*
    * Não coloquei todas as informações, como a maioria dos atributos herdados,
    * para facilitar a visualização
    */
    public String toString() {
        String data = this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return        
            "Nome: " + getNome() + "\n" +
            "CPF: " + getCadastro() + "\n" +
            "DataNascimento: " + data + "\n" +
            "Educacao: " + getEducacao() + "\n" +
            "Genero: " + getGenero() + "\n";
        }
}
    