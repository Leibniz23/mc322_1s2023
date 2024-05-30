public class Sala {
    private final int id;
    private final double preco;
    private final int numAssentos;
    private final boolean ehVIP;
    private final boolean eh3D;


    public Sala(int id, int numAssentos, boolean eh3D, boolean ehVIP) {
        this.id = id;
        this.preco = 30;
        this.numAssentos = numAssentos;
        this.eh3D = eh3D;
        this.ehVIP = ehVIP;
    }

    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public int getNumAssentos() {
        return numAssentos;
    }

    public boolean getEhVIP() {
        return ehVIP;
    }

    public boolean getEh3D() {
        return eh3D;
    }

}
