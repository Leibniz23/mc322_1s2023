package Classes_principais;
public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private Seguro seguro;
    private int anoFabricacao;

    public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    public Veiculo() {}

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnoFabricacao() {
        return Integer.toString(this.anoFabricacao);
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public String toString() {
        return
            "   Placa: " + getPlaca() + "\n" +
            "   Marca: " + getMarca() + "\n" +
            "   Modelo: " + getModelo() + "\n" +
            "   AnoFabricacao: " + getAnoFabricacao();
    }

}