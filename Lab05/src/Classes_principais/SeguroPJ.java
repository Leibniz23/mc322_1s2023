package Classes_principais;
import java.time.LocalDate;
import java.util.*;

public class SeguroPJ extends Seguro{
    private Frota frota;
    private ClientePJ cliente;

    /* Construtor */
    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
                        Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
        this.valorMensal = calcularValor();
    }

    /* Getters e Setters */
    public Frota getFrota() {
        return this.frota;
    }

    public boolean setFrota(Frota frota) {
        this.frota = frota;
        return true;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public boolean setCliente(ClientePJ cliente) {
        this.cliente = cliente;
        return true;
    }

    /* Apenas porque tive que declarar isso no seguro pai,
     * mas essa versão do método não será usada de fato
     */
    public boolean setCliente(ClientePF cliente) {
        return false;
    }
    
    public Veiculo getVeiculo() {
        return null;
    }

    public boolean setVeiculo(Veiculo veiculo) {
        return false;
    }

    public boolean autorizarCondutor(Condutor c) {
        /*
         * Como explicado no seguro pai
         */
        boolean existe = false;
        for (Condutor iC : listaCondutores) {
            if (iC.getCPF().equals(c.getCPF())) {
                existe = true;
            }
        }
        if (!existe) {
            this.listaCondutores.add(c);
            calcularValor();
            return true;
        } else {
            return false;
        }

    }

    public boolean desautorizarCondutor(String cpf) {
        /*
         * Como explicado no seguro pai
         */
        Iterator<Condutor> itCondutor = this.listaCondutores.iterator();
        while (itCondutor.hasNext()) { // percorre a lista usando Iterator
            Condutor cList = itCondutor.next();
            if (cList.getCPF().equals(cpf)) {
                itCondutor.remove();
                calcularValor();
                return true;
            }
        }
        return false;
    }

    public double calcularValor() {
        /*
         * Como explicado no seguro pai
         */
        double AnosPosFundacao = cliente.calcIdade();
        double qntdVeiculos = this.frota.getListaVeiculos().size();
        double qntdSinistrosCondutor = 0;
        double qntdSinistrosCliente = 0;
        for (Seguro seguro : cliente.getListaSeguros()) {  
            qntdSinistrosCliente += seguro.getListaSinistros().size(); // soma de todos os sinsitros de todos os seguros desse cliente
        }
        for (Condutor condutor : this.listaCondutores) {
            qntdSinistrosCondutor += condutor.getListaSinistros().size(); // soma de todos os sinsitros de todos os condutores desse seguro
        }
        double valorMensal = CalcSeguro.VALOR_BASE.getFator() * (10.0+ (cliente.getQtdeFuncionarios()/10.0)) *
                        (1.0+ 1.0/(qntdVeiculos+2.0)) *
                        (1.0+ 1.0/(AnosPosFundacao+2.0)) *
                        (2.0+ (qntdSinistrosCliente/10.0)) *
                        (5.0+ (qntdSinistrosCondutor/10.0));
        this.valorMensal = valorMensal;
        return this.valorMensal;
    }

    public boolean gerarSinistro(LocalDate data, String endereco,Condutor condutor) {
        /*
         * Como explicado no seguro pai
         */
        Sinistro novoSinistro = new Sinistro(data, endereco, this, condutor);
        boolean result = this.listaSinistros.add(novoSinistro);
        if (result) { // só para garantir que foi adicionado na lista
            calcularValor();
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return        
            "   ID: " + getID() + "\n" +
            "   Data de Inicio: "+ getDataInicio() +"\n"+
            "   Data de Fim: "+ getDataFim() +"\n"+
            "   Seguradora: "+ getSeguradora().getNome() +"\n"+
            "   Código da frota: " + getFrota().getCode() + "\n" +
            "   Cliente responsável: "+ getCliente().getNome() +"\n"+
            "   Valor mensal: "+ Double.toString(getValorMensal()) +"\n";
    }
    
}
