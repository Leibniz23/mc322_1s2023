package Classes_principais;

import java.time.LocalDate;
import java.util.*;

public class SeguroPF extends Seguro{
    private Veiculo veiculo;
    private ClientePF cliente;


    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, 
                    Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
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

    public boolean gerarSinistro(LocalDate data, String endereco, Condutor condutor) {
        Sinistro novoSinistro = new Sinistro(data, endereco, this, condutor);
        boolean result = this.listaSinistros.add(novoSinistro);
        if (result) { // só para garantir que foi adicionado na lista
            calcularValor();
            return true;
        } else {
            return false;
        }
    }

    public double calcularValor() {
        // qtdSinistrosCliente = iterar pelos seguros do cliente e somar os listaSinistros.size
        // qtdSinistrosCondutor = iterar lista de condutores, cada condutor tem uma lista de sinistros, somar todos
        int idade = cliente.calcIdade();
        int qntdSinistrosCondutor = 0, qntdSinistrosCliente = 0;
        for (Seguro seguro : cliente.getListaSeguros()) {  
            qntdSinistrosCliente += seguro.getListaSinistros().size(); // soma de todos os sinsitros de todos os seguros desse cliente
        }
        for (Condutor condutor : this.listaCondutores) {
            qntdSinistrosCondutor += condutor.getListaSinistros().size(); // soma de todos os sinsitros de todos os condutores desse seguro
        }
        double score = CalcSeguro.VALOR_BASE.getFator() * (1+ 1/(cliente.getListaSeguros().size()+2)) *
                        (2+ (qntdSinistrosCliente/10)) *
                        (5+ (qntdSinistrosCondutor/10)); // a lista de sinistros desse seguro é inteira do cliente, mesmo tendo outros condutores autorizados?
        if (idade < 30) {
            double valorMensal = score*CalcSeguro.FATOR_ATE_30.getFator();
            this.valorMensal = valorMensal;
            return this.valorMensal;

        } else if (30 <= idade || idade <= 60) {
            double valorMensal = score*CalcSeguro.FATOR_30_60.getFator();
            this.valorMensal = valorMensal;
            return this.valorMensal;
        } else if (idade > 60) {
            double valorMensal = score*CalcSeguro.FATOR_ACIMA_60.getFator();
            this.valorMensal = valorMensal;
            return this.valorMensal;
        } else {
            return -1.0; // Score inválido
        }
    }
}
