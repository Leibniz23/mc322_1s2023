package Classes_principais;

public class Sinistro {
    final int ID;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    private static int cont = 1;

    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = cont++;
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public Sinistro(){
        this.ID = cont++;
    }

    public int getID() {
        return this.ID;
    }

    public String getData() {
        return this.data;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String toString() {
        return "ID: " + getID() + "\n" +
            "Data: " + getData() + "\n" +
            "Endereco: " + getEndereco() + "\n" +
            "Seguradora: " + getSeguradora().getNome() + "\n" + // só o nome para facilitar a visualização
            "Veiculo:\n" + getVeiculo().toString() + "\n" +
            "Cliente: " + getCliente().getCadastro() + "\n";
    }
}