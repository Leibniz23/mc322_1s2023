package Classes_principais;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClientePJ extends Cliente {
    final String CNPJ ;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;
    private List<Frota> listaFrota;

    /* Construtor */
    public ClientePJ(String nome, String telefone, String endereco, String email,
                        String cnpj, LocalDate dataFundacao, int funcionarios) {
        super(nome, telefone, endereco, email);
        this.CNPJ = cnpj.replaceAll("[^0-9]", "");
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = funcionarios;
        this.listaFrota = new ArrayList<Frota>();
    }

    /* Getters e Setters */
    public String getCadastro() {
        return this.CNPJ;
    }

    public LocalDate getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return this.qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public List<Frota> getListaFrota() {
        return this.listaFrota;
    }
    
    public void setListaFrota(List<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }
    
    /* Explicada no cliente pai */
    public int calcIdade() {
        int ano_fundacao = this.dataFundacao.getYear();
        int idade = LocalDate.now().getYear() - ano_fundacao;
        return idade;
    }

    /* Insere na lista de frotas o objeto Frota passado, contanto que ele ainda
     * não tenha sido adicionado
     */
    public boolean cadastrarFrota(Frota frota) {
        boolean existe = false;
        for (Frota iF : listaFrota) {
            if (iF.getCode() == frota.getCode()) {
                existe = true;
            }
        }
        if (!existe) {
            this.listaFrota.add(frota);
            return true;
        } else {
            return false;
        }
    }

    /* Remove o veículo dono da placa dada da frota cujo código foi passado*/
    public boolean atualizarFrota(int code, String placa) {
        for (Frota frota : this.listaFrota) {
            if (frota.getCode() == code) {
                frota.remVeiculo(placa);
                return true;
            }
        }
        return false;
    }

    /* Adiciona o Veiculo passado na frota cujo código foi passado */
    public boolean atualizarFrota(int code, Veiculo veiculo) {
        for (Frota frota : this.listaFrota) {
            if (frota.getCode() == code) {
                frota.addVeiculo(veiculo);
                return true;
            }
        }
        return false;
    }

    /* Exclui da lista de frotas a frota cujo código foi passado */
    public boolean atualizarFrota(int code) {
        Iterator<Frota> itFrota = this.listaFrota.iterator();
        while (itFrota.hasNext()) {
            Frota fList = itFrota.next();
            if (fList.getCode() == code) {
                itFrota.remove();
                return true;
            }
        }
        return false; // frota não encontrada
    }

    /* Retorna um ArrayList contendo os veículos da frota cujo código foi passado */
    public List<Veiculo> getVeiculosPorFrota(int code) { // não fez muito sentido pra mim ter um retorno boolean, como indica o UML
        for (Frota frota : this.listaFrota) {
            if (frota.getCode() == code) {
                return frota.getListaVeiculos();
            }
        }
        return null; // essa frota não existe
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
        for (Frota f : this.listaFrota) {
            System.out.println(f.toString());
            for (Veiculo v : f.getListaVeiculos()) {
                System.out.println(v.toString());
            }
        }
    }

    
    /* Explicação desses métodos está no cliente pai */
    public boolean cadastrarVeiculo(Veiculo seguro) {
        return false;
    }

    public boolean removerVeiculo(String placa) {
        return false;
    }
    
    public String toString() {
        String data = this.dataFundacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String funcionarios = Integer.toString(getQtdeFuncionarios());
        return
            "CNPJ: " + getCadastro() + "\n" +
            "DataFundacao: " + data + "\n" +
            "Quantidade de Funcionários: " + funcionarios + "\n";
    }
}