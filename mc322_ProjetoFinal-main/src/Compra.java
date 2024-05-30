import java.io.*;
import java.util.*;

public class Compra implements Imprimivel{
    /*
    Registra os itens que serão comprados por um cliente
     */
    private final int registroCompra;
    private final List<Ingresso> listaIngressos;
    private final List<Consumivel> listaConsumo;
    private double total;
    private static int cont = 1;

    public Compra() {
        this.registroCompra = cont;
        cont++;
        this.listaConsumo = new ArrayList<>();
        this.listaIngressos = new ArrayList<>();
        this.total = 0;
    }

    public int getRegistroCompra() {
        return registroCompra;
    }

    public List<Ingresso> getListaIngressos() {
        return listaIngressos;
    }

    public double getTotal() {
        return total;
    }

    /*
    Cria um arquivo .txt no diretório fileOut com as principais informações sobre a
    compra em questão
     */
    public void imprimir() {
        String registroCompra = Integer.toString(this.registroCompra);
        String diretorioAtual = System.getProperty("user.dir");
        String caminho = diretorioAtual + "/fileOut/Compra" + registroCompra + ".txt";
        try {
            BufferedWriter bufferEscrita = new BufferedWriter(new FileWriter(caminho));
            // Escrevendo o recibo da compra
            bufferEscrita.write("Código da compra: " + getRegistroCompra() + "\n\n");
            bufferEscrita.write("Quantidade de ingressos comprados: " + this.listaIngressos.size() + "\n");
            bufferEscrita.write("#############################\n");
            for (Ingresso i : listaIngressos) {
                bufferEscrita.write("Horário: "+i.getSessao().getHorario()+"\n");
                bufferEscrita.write("Assento: "+i.getAssento()+"\n");
                bufferEscrita.write("Filme: "+ i.getSessao().getFilme()+"\n");
                bufferEscrita.write("Preço: " + i.getPreco() +"\n");
                bufferEscrita.write("----------------------------------\n");
            }
            bufferEscrita.write("Quantidade de consumíveis comprados: "+this.listaConsumo.size()+"\n");
            bufferEscrita.write("#############################\n");
            for (Consumivel c : listaConsumo) {
                bufferEscrita.write("Comida: "+ c.getNome()+"\n");
                bufferEscrita.write("Valor: " + c.getPreco()+"\n");
                bufferEscrita.write("----------------------------------\n");
            }
            bufferEscrita.close();
        } catch (IOException e) {
            System.err.println((e.getMessage()));
        }
    }

    /*
    Recalcula o valor total da compra
     */
    public void atualizarTotal() {
        for (Ingresso i : listaIngressos) {
            total += i.getPreco();
        }
        for (Consumivel c : listaConsumo) {
            total += c.getPreco();
        }
    }

    /*
    Adiciona um ingresso, já criado, na lista de ingressos da compra
     */
    public void adicionarIngresso(Ingresso ingresso) {
        listaIngressos.add(ingresso);
    }

    /*
    Cria um consumivel
     */
    public void criarConsumivel(String nome, double preco) {
        Consumivel c = new Consumivel(nome, preco);
        adicionarConsumivel(c);
    }

    /*
    Adiciona um consumivel na lista de consumiveis da compra
     */
    public void adicionarConsumivel(Consumivel consumivel) {
        listaConsumo.add(consumivel);
    }

}
