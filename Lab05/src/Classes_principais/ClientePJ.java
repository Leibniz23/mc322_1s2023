package Classes_principais;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClientePJ extends Cliente {
    final String CNPJ ;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;
    protected List<SeguroPJ> listaSeguros;
    private List<Frota> listaFrota;

    public ClientePJ(String nome, String telefone, String endereco, String email,
                        String cnpj, LocalDate dataFundacao, int funcionarios) {
        super(nome, telefone, endereco, email);
        this.CNPJ = cnpj.replaceAll("[^0-9]", "");
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = funcionarios;
        this.listaFrota = new ArrayList<Frota>();
    }

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
    
    public int calcIdade() {
        int ano_fundacao = this.dataFundacao.getYear();
        int idade = LocalDate.now().getYear() - ano_fundacao;
        return idade;
    }

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

    public boolean atualizarFrota(int code, String placa) { // remove
        /*
         * Adiciona ou remove, da frota indicada, um veículo
         */

        // apagar dps: vai ter que buscar o veículo por placa e a frota tbm, talvez criar um metodo pra isso
        for (Frota frota : this.listaFrota) {
            if (frota.getCode() == code) {
                frota.remVeiculo(placa);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarFrota(int code, Veiculo veiculo) { // adiciona
        for (Frota frota : this.listaFrota) {
            if (frota.getCode() == code) {
                frota.addVeiculo(veiculo);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarFrota(int code) { // apaga tudo
        Iterator<Frota> itFrota = this.listaFrota.iterator();
        while (itFrota.hasNext()) { // percorre a lista usando Iterator
            Frota fList = itFrota.next();
            if (fList.getCode() == code) {
                itFrota.remove();
                return true;
            }
        }
        return false; // frota não encontrada
    }

    public List<Veiculo> getVeiculosPorFrota(int code) { // não fez muito sentido pra mim ter um retorno boolean
        for (Frota frota : this.listaFrota) {
            if (frota.getCode() == code) {
                return frota.getListaVeiculos();
            }
        }
        return null; // essa frota não existe
    }

    public List<SeguroPJ> getListaSeguros() {
        return this.listaSeguros;
    }

    public void setListaSeguros(List<SeguroPJ> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    public boolean adicionarSeguro(SeguroPJ seguro) {
        return this.listaSeguros.add(seguro);
    }

    public boolean removerSeguro(int id) {
        Iterator<SeguroPJ> itSeguro = this.listaSeguros.iterator();
        while (itSeguro.hasNext()) { // percorre a lista usando Iterator
            Seguro sList = itSeguro.next();
            if (sList.getID() == id) {
                itSeguro.remove();
                return true;
            }
        }
        return false;
    }

    public void limparSeguros() {
        this.listaSeguros.clear();
    }

    @Override
    public String toString() {
        return
            "CNPJ: " + getCadastro() + "\n" +
            "DataFundacao: " + getDataFundacao() + "\n" +
            "Quantidade de Funcionários: " + Integer.toString(getQtdeFuncionarios()) + "\n";
    }
}