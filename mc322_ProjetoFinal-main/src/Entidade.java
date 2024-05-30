import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Entidade {
    /*
    Classe principal da corporação, representa a rede do cinema
     */
    private final String nomeRede;
    private final String enderecoRede;
    private final String telefoneRede;
    private final String emailRede;
    private final List<Unidade> listaUnidades;
    private double valorArrecadado;

    public Entidade(String nomeRede, String enderecoRede, String telefoneRede, String emailRede) {
        this.nomeRede = nomeRede;
        this.enderecoRede = enderecoRede;
        this.telefoneRede = telefoneRede;
        this.emailRede = emailRede;
        this.listaUnidades = new ArrayList<>();
        this.valorArrecadado = 0;
    }

    public String getNomeRede() {
        return nomeRede;
    }

    public String getEnderecoRede() {
        return enderecoRede;
    }

    public String getTelefoneRede() {
        return telefoneRede;
    }

    public String getEmailRede() {
        return emailRede;
    }

    public List<Unidade> getListaUnidades() {
        return listaUnidades;
    }

    public double getValorArrecadado() {
        return valorArrecadado;
    }

    /*
    Calcula o valor arrecadado total da entidade, sendo a soma dos valores arrecadados
    por todas as unidades.
     */
    public void calcValorArrecadado() {
        this.valorArrecadado = 0;
        for (Unidade u : listaUnidades) {
            u.calcSaldo();
            valorArrecadado += u.getSaldo();
        }
    }

    /*
    Cria um arquivo .txt no diretório fileOut com as principais informações sobre a
    entidade em questão
     */
    public void imprimir() {
        String diretorioAtual = System.getProperty("user.dir");
        String caminho = diretorioAtual + "/fileOut/" + getNomeRede() + "Corp.txt";
        try {
            BufferedWriter bufferEscrita = new BufferedWriter(new FileWriter(caminho));
            // Escrevendo as informações sobre a Entidade
            bufferEscrita.write("Todos os direitos a " + getNomeRede() + "©\n\n");
            bufferEscrita.write("Endereço da Sede: " + getEnderecoRede() + "\n");
            bufferEscrita.write("Email da Sede: " + getEmailRede() + "\n");
            String qntdeFiliais = Integer.toString(getListaUnidades().size());
            bufferEscrita.write("Quantidade de filiais:" + qntdeFiliais + "\n");
            bufferEscrita.write("Patrimônio da coorporação: " + getValorArrecadado() + "\n");
            bufferEscrita.write("#############################\n\n");
            bufferEscrita.write("Venha conhecer!!");
            bufferEscrita.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Funcionario> getListaFuncionarios() {
        ArrayList<Funcionario> lista = new ArrayList<>();
        for (Unidade unidade : listaUnidades) {
            lista.addAll(unidade.getListaFuncionarios());
        }

        return lista;
    }

    public String toString() {
        calcValorArrecadado();
        return "Rede: " + getNomeRede() + "\n" +
                "Endereço: " + getEnderecoRede() + "\n" +
                "Telefone: " + getTelefoneRede() + "\n" +
                "Email: " + getEmailRede() + "\n" +
                "Valor Arrecadado: R$ " + getValorArrecadado();
    }
}
