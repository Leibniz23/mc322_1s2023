import java.util.*;

public class ClientePJ extends Cliente {
    final String CNPJ ;
    private Date dataNascimento ;

    public ClientePJ(String cpf, Date dataNascimento) {
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }


    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString () {
    // ...
    }
    public boolean validarCPF ( String cpf ){
    // ...
    }
}
