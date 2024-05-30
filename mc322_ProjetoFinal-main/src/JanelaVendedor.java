import javax.swing.JFrame;

public class JanelaVendedor extends JFrame{
    PainelLogin painelLogin;
    PainelVendedor painelVendedor;
    PainelCompra painelCompra;
    PainelCategorias painelCategorias;
    PainelConsumiveis painelConsumiveis;
    PainelSessoesDisponiveis painelSessoesDisponiveis;
    PainelAssentosDisponiveis painelAssentosDisponiveis;
    PainelEscolherFilme painelEscolherFilme;
    PainelEscolherSessao painelEscolherSessao;
    PainelEscolherAssentos painelEscolherAssentos;
    Unidade unidade;

    JanelaVendedor(Janela janelaLogin, Unidade unidade, Vendedor vendedor) {
        this.setTitle("Janela");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(810, 460);
        this.setResizable(false);
        this.unidade = unidade;
        
        painelLogin = new PainelLogin(this, unidade);
        painelVendedor = new PainelVendedor(janelaLogin, this, vendedor);
        painelCompra = new PainelCompra(this);
        painelCategorias = new PainelCategorias(this);
        painelConsumiveis = new PainelConsumiveis(this);
        painelSessoesDisponiveis = new PainelSessoesDisponiveis(this);
        painelAssentosDisponiveis = new PainelAssentosDisponiveis(this);
        painelEscolherFilme = new PainelEscolherFilme(this);
        painelEscolherSessao = new PainelEscolherSessao(this);
        painelEscolherAssentos = new PainelEscolherAssentos(this);

        this.add(painelLogin);
        this.add(painelVendedor);
        this.add(painelCompra);
        this.add(painelCategorias);
        this.add(painelConsumiveis);
        this.add(painelSessoesDisponiveis);
        this.add(painelAssentosDisponiveis);
        this.add(painelEscolherFilme);
        this.add(painelEscolherSessao);
        this.add(painelEscolherAssentos);
        
        this.setVisible(true);
    }

    public PainelVendedor getPainelVendedor() {
        return painelVendedor;
    }
    public PainelCompra getPainelCompra() {
        return painelCompra;
    }

    public PainelCategorias getPainelCategorias() {
        return painelCategorias;
    }

    public PainelConsumiveis getPainelConsumiveis() {
        return painelConsumiveis;
    }

    public PainelSessoesDisponiveis getPainelSessoesDisponiveis() {
        return painelSessoesDisponiveis;
    }

    public PainelAssentosDisponiveis getPainelAssentosDisponiveis() {
        return painelAssentosDisponiveis;
    }

    public PainelEscolherFilme getPainelEscolherFilme() {
        return painelEscolherFilme;
    }

    public PainelEscolherSessao getPainelEscolherSessao() {
        return painelEscolherSessao;
    }

    public PainelEscolherAssentos getPainelEscolherAssentos() {
        return painelEscolherAssentos;
    }
}