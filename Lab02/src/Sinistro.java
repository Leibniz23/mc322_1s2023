public class Sinistro {
    private int id;
    private String data;
    private String endereco;
    private static int cont = 1;

    public Sinistro(String data, String endereco) {
        this.id = cont++;
        this.data = data;
        this.endereco = endereco;
    }

    public Sinistro(){
        this.id = cont++;
    }

    public int getId() {
        return this.id;
    }

    public String getData() {
        return this.data;
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
    
}