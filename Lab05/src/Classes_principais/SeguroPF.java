package Classes_principais;

import java.time.LocalDate;

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

    public double calcularValor() {
        // qtdSinistrosCliente = iterar pelos seguros do cliente e somar os listaSinistros.size
        // qtdSinistrosCondutor = iterar pelos condutores e 
        int carros, idade = cliente.calcIdade();
        double score = CalcSeguro.VALOR_BASE.getFator() * (1+ 1/(cliente.getListaVeiculos().size())) *
                        (2+ ); // a lista de sinistros desse seguro é inteira do cliente, mesmo tendo outros condutores autorizados?
        if (idade < 30) {


        } else if (30 < idade || idade < 60) {
            

        } else if (idade > 60) {
            carros = this.getListaVeiculos().size();
            score = CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_60_90.getFator() * carros;
            return score;

        } else {
            return -1.0; // Score inválido
        }
    }

}
