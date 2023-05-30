package Classes_principais;

import java.time.LocalDate;
import java.util.*;

public class SeguroPF extends Seguro{
    private Veiculo veiculo;
    private ClientePF cliente;

    /* Construtor */
    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, 
                    Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.valorMensal = calcularValor();
    }

    /* Getters e Setters */
    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public boolean setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        return true;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public boolean setCliente(ClientePF cliente) {
        this.cliente = cliente;
        return true;
    }

    /* Apenas porque tive que declarar isso no seguro pai,
     * mas essa versão do método não será usada de fato
     */
    public boolean setCliente(ClientePJ cliente) {
        return false;
    }

    public Frota getFrota() {
        return null;
    }

    public boolean setFrota(Frota f) {
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

    public boolean gerarSinistro(LocalDate data, String endereco, Condutor condutor) {
        /*
         * Como explicado no seguro pai
         */
        Sinistro novoSinistro = new Sinistro(data, endereco, this, condutor);
        boolean result = this.listaSinistros.add(novoSinistro);
        condutor.addSinistro(novoSinistro);
        if (result) { // só para garantir que foi adicionado na lista
            calcularValor();
            return true;
        } else {
            return false;
        }
    }

    public double calcularValor() {
        /*
         * Como explicado no seguro pai
         */        
        double idade = cliente.calcIdade();
        double qntdSinistrosCondutor = 0.0, qntdSinistrosCliente = 0.0;
        for (Seguro seguro : cliente.getListaSeguros()) {  
            qntdSinistrosCliente += seguro.getListaSinistros().size(); // soma de todos os sinsitros de todos os seguros desse cliente
        }
        for (Condutor condutor : this.listaCondutores) {
            qntdSinistrosCondutor += condutor.getListaSinistros().size(); // soma de todos os sinsitros de todos os condutores desse seguro
        }
        double score = CalcSeguro.VALOR_BASE.getFator() * (1.0+ 1.0/(cliente.getListaSeguros().size()+2.0)) *
                        (2.0+ (qntdSinistrosCliente/10.0)) *
                        (5.0+ (qntdSinistrosCondutor/10.0)); // a lista de sinistros desse seguro é inteira do cliente, mesmo tendo outros condutores autorizados?
        if (idade < 30.0) {
            double valorMensal = score*CalcSeguro.FATOR_ATE_30.getFator();
            this.valorMensal = valorMensal;
            return this.valorMensal;

        } else if (30.0 <= idade || idade <= 60.0) {
            double valorMensal = score*CalcSeguro.FATOR_30_60.getFator();
            this.valorMensal = valorMensal;
            return this.valorMensal;
        } else if (idade > 60.0) {
            double valorMensal = score*CalcSeguro.FATOR_ACIMA_60.getFator();
            this.valorMensal = valorMensal;
            return this.valorMensal;
        } else {
            return -1.0; // Score inválido
        }
    }

    public String toString() {
        return        
            "   ID: " + getID() + "\n" +
            "   Data de Inicio: "+ getDataInicio() +"\n"+
            "   Data de Fim: "+ getDataFim() +"\n"+
            "   Seguradora: "+ getSeguradora().getNome() +"\n"+
            "   Placa do Veículo: "+ getVeiculo().getPlaca() +"\n"+
            "   Cliente responsável: "+ getCliente().getNome() +"\n" +
            "   Valor mensal: "+ Double.toString(getValorMensal()) +"\n";
    }
}
