import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Unidade {
    /*
    Filial da Entidade, será responsável por conter os Funcionários e as Compras
     */
    private final String cnpj;
    private final String endereco;
    private final List<Funcionario> listaFuncionarios;
    private final String email;
    private final List<Compra> listaCompras;
    private final List<Sessao> listaSessoes;
    private final List<Sala> listaSalas;
    private final List<Consumivel> listaConsumivel;
    private final Entidade entidade;
    private double saldo;

    public Unidade(String cnpj, String endereco, String email, Entidade entidade) {
        this.cnpj = cnpj.replaceAll("[^0-9]", ""); // para colocar apenas os números
        this.email = email;
        this.listaFuncionarios = new ArrayList<>();
        this.listaCompras = new ArrayList<>();
        this.listaSessoes = new ArrayList<>();
        this.listaSalas = new ArrayList<>();
        this.listaConsumivel = new ArrayList<>();
        this.entidade = entidade;
        this.endereco = endereco;
        this.saldo = 0;
    }

    public String getCnpj() {
        return cnpj;
    }
    
    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public String getEmail() {
        return email;
    }
    public String getEndereco() {
        return endereco;
    }

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

    public List<Sessao> getListaSessoes() {
        return listaSessoes;
    }

    public List<Sala> getListaSalas() {return listaSalas;}

    public double getSaldo() {
        return saldo;
    }

    public List<Consumivel> getListaConsumivel() {
        return listaConsumivel;
    }

    /*
            Calcula o salario de um funcionario (fornecido), com base na sua lista de compras.
             */
    public void calcSalario(Vendedor vendedor) {
        double soma = 0;
        for (Compra c : vendedor.getListaCompras()) {
            soma += (c.getTotal() * (0.5 / 100));
        }
        soma += vendedor.getSalario();
        vendedor.setSalario(soma);
    }

    /*
    Calcula quanto uma unidade ganha, sendo as vendas dela (lista de compras) um saldo positivo
    e os salarios dos funcionarios um saldo negativo.
     */
    public void calcSaldo() {
        saldo=0;
        for (Compra c : listaCompras) {
            saldo += c.getTotal();
        }
        for (Funcionario f : listaFuncionarios) {
            if (f.getClass() == Vendedor.class) {
                calcSalario((Vendedor) f);
            }
            saldo -= f.getSalario();
        }
    }

    /*
    Cria um arquivo .txt no diretório fileOut com as principais informações sobre a
    unidade em questão
     */
    public void imprimir() {
        String diretorioAtual = System.getProperty("user.dir");
        String caminho = diretorioAtual + "/fileOut/Unidade" + getCnpj() + ".txt";
        try {
            BufferedWriter bufferEscrita = new BufferedWriter(new FileWriter(caminho));
            // Escrevendo as informações da Unidade
            bufferEscrita.write("Esta unidade é uma filial de " + this.entidade.getNomeRede() + "©\n\n");
            bufferEscrita.write("CNPJ: " + getCnpj() + "\n");
            bufferEscrita.write("Email: " + getEmail() + "\n");
            bufferEscrita.write("Endereço: " + getEndereco() + "\n");
            String qntdeFuncionarios = Integer.toString(getListaFuncionarios().size());
            String qntdeSessoes = Integer.toString(getListaSessoes().size());
            bufferEscrita.write("Quantidade de funcionários:" + qntdeFuncionarios + "\n");
            bufferEscrita.write("Quantidade de sessões oferecidas: " + qntdeSessoes + "\n");
            bufferEscrita.write("Patrimônio Líquido: " + getSaldo() + "\n");
            bufferEscrita.write("#############################\n\n");
            bufferEscrita.write("Venha conhecer!!");
            bufferEscrita.close();
        } catch (IOException problema) {
            System.err.println(problema.getMessage());
        }
    }

    public void inicializarExemplo() {
        //Unidade ja construida para exemplificar na apresentação
        //Instanciando e adicionando as salas da unidade
        Sala sala1 = new Sala(1, 100, false, false);
        Sala sala2 = new Sala(2, 100, true, false);
        Sala sala3 = new Sala(3, 100, false, true);
        Sala sala4 = new Sala(4, 100, true, true);
        Sala[] listaSalas = {sala1, sala2, sala3, sala4};
        for (Sala sala : listaSalas) {
            this.getListaSalas().add(sala);
        }


        //Instanciando e adicionando as sessoes da unidade (4 filmes, 5 sessoes cada = 20 sessoes)
        Sessao sessao1 = new Sessao("10:00-12:00", sala1, "Titanic", "src/posterFilmesAntigos/1titanic.png");
        Sessao sessao2 = new Sessao("12:30-14:30", sala2, "Titanic", "src/posterFilmesAntigos/1titanic.png");
        Sessao sessao3 = new Sessao("15:00-17:00", sala3, "Titanic", "src/posterFilmesAntigos/1titanic.png");
        Sessao sessao4 = new Sessao("17:30-19:30", sala4, "Titanic", "src/posterFilmesAntigos/1titanic.png");
        Sessao sessao5 = new Sessao("20:00-22:00", sala1, "Titanic", "src/posterFilmesAntigos/1titanic.png");
        Sessao sessao6 = new Sessao("10:00-12:00", sala2, "Bacurau", "src/posterFilmesAntigos/2bacurau.png");
        Sessao sessao7 = new Sessao("12:30-14:30", sala3, "Bacurau", "src/posterFilmesAntigos/2bacurau.png");
        Sessao sessao8 = new Sessao("15:00-17:00", sala4, "Bacurau", "src/posterFilmesAntigos/2bacurau.png");
        Sessao sessao9 = new Sessao("17:30-19:30", sala1, "Bacurau", "src/posterFilmesAntigos/2bacurau.png");
        Sessao sessao10 = new Sessao("20:00-22:00", sala2, "Bacurau", "src/posterFilmesAntigos/2bacurau.png");
        Sessao sessao11 = new Sessao("10:00-12:00", sala3, "Vingadores", "src/posterFilmesAntigos/3vingadores.png");
        Sessao sessao12 = new Sessao("12:30-14:30", sala4, "Vingadores", "src/posterFilmesAntigos/3vingadores.png");
        Sessao sessao13 = new Sessao("15:00-17:00", sala1, "Vingadores", "src/posterFilmesAntigos/3vingadores.png");
        Sessao sessao14 = new Sessao("17:30-19:30", sala2, "Vingadores", "src/posterFilmesAntigos/3vingadores.png");
        Sessao sessao15 = new Sessao("20:00-22:00", sala3, "Vingadores", "src/posterFilmesAntigos/3vingadores.png");
        Sessao sessao16 = new Sessao("10:00-12:00", sala3, "2012", "src/posterFilmesAntigos/42012.png");
        Sessao sessao17 = new Sessao("12:30-14:30", sala4, "2012", "src/posterFilmesAntigos/42012.png");
        Sessao sessao18 = new Sessao("15:00-17:00", sala1, "2012", "src/posterFilmesAntigos/42012.png");
        Sessao sessao19 = new Sessao("17:30-19:30", sala2, "2012", "src/posterFilmesAntigos/42012.png");
        Sessao sessao20 = new Sessao("20:00-22:00", sala3, "2012", "src/posterFilmesAntigos/42012.png");
        Sessao[] listaSessoes = {sessao1, sessao2, sessao3, sessao4, sessao5, sessao6, sessao7, sessao8, sessao9, sessao10, sessao11, sessao12, sessao13, sessao14, sessao15, sessao16, sessao17, sessao18, sessao19, sessao20};
        for (Sessao sessao : listaSessoes) {
            this.getListaSessoes().add(sessao);
        }
    }
}
