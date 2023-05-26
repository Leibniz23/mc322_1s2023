package Classes_principais;
import java.time.LocalDate;
import java.util.*;

public class SeguroPJ extends Seguro{
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
                        Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    public Frota getFrota() {
        return this.frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return this.cliente;
    }

    public boolean setCliente(ClientePJ cliente) {
        this.cliente = cliente;
        return true;
    }

    public boolean setCliente(ClientePF cliente) {
        return false;
    }

    public boolean autorizarCondutor(Condutor c) {
        // o condutor deve ser criado na main ou no menu, e deve ser verificado se ele
        // já não existe na lista static da classe condutor
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
         * Remove o veículo que tem a placa passada
         * da lista de veículos do cliente
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
        int AnosPosFundacao = cliente.calcIdade();
        int qntdVeiculos = this.frota.getListaVeiculos().size();
        int qntdSinistrosCondutor = 0;
        int qntdSinistrosCliente = 0;
        for (Seguro seguro : cliente.getListaSeguros()) {  
            qntdSinistrosCliente += seguro.getListaSinistros().size(); // soma de todos os sinsitros de todos os seguros desse cliente
        }
        for (Condutor condutor : this.listaCondutores) {
            qntdSinistrosCondutor += condutor.getListaSinistros().size(); // soma de todos os sinsitros de todos os condutores desse seguro
        }
        double valorMensal = CalcSeguro.VALOR_BASE.getFator() * (10+ (cliente.getQtdeFuncionarios()/10)) *
                        (1+ 1/(qntdVeiculos+2)) *
                        (1+ 1/(AnosPosFundacao+2)) *
                        (2+ (qntdSinistrosCliente/10)) *
                        (5+ (qntdSinistrosCondutor/10));
        this.valorMensal = valorMensal;
        return this.valorMensal;
    }

    public boolean gerarSinistro(LocalDate data, String endereco,Condutor condutor) {
        Sinistro novoSinistro = new Sinistro(data, endereco, this, condutor);
        boolean result = this.listaSinistros.add(novoSinistro);
        if (result) { // só para garantir que foi adicionado na lista
            calcularValor();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return
            "Frota: " + getFrota() + " " +
            "Cliente: " + getCliente() + " ";
    }
    
}
