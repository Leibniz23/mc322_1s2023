import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelVendedor extends JPanel implements ActionListener, atualizar{
    JanelaVendedor janela;
    double comissaoAtual = 0.0;
    Vendedor vendedorLogado;
    int filmeSelecionado = 1;
    int sessaoSelecionada = 1;
    double totalCompra = 0.0;
    double totalIngressos = 0.0;
    double totalConsumiveis = 0.0;
    Integer[] quantidadesConsumiveis = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    JButton botaoVoltar;
    JButton boatoCompra;
    JButton botaoFilme1;
    JButton botaoFilme2;
    JButton botaoFilme3;
    JButton botaoFilme4;
    JLabel comissao;
    JPanel secaoCentral;
    Janela janelaLogin;

    PainelVendedor(Janela janelaLogin, JanelaVendedor janela, Vendedor vendedor) {
        this.janela = janela;
        this.janelaLogin = janelaLogin;
        this.vendedorLogado = vendedor;

        this.setBackground(Color.blue);
        this.setBounds(0, 0, 800, 450);
        this.setLayout(new BorderLayout());
        
        JPanel secaoSuperior = new JPanel();
        secaoSuperior.setBackground(Color.black);
        secaoSuperior.setVisible(true);
        secaoSuperior.setLayout(null);
        secaoSuperior.setPreferredSize(new Dimension(800, 50));
        this.add(secaoSuperior, BorderLayout.NORTH);

        JPanel secaoInferior = new JPanel();
        secaoInferior.setBackground(Color.black);
        secaoInferior.setVisible(true);
        secaoInferior.setLayout(null);
        secaoInferior.setPreferredSize(new Dimension(800, 100));
        this.add(secaoInferior, BorderLayout.SOUTH);

        secaoCentral = new JPanel();
        secaoCentral.setBackground(Color.gray);
        secaoCentral.setVisible(true);
        secaoCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 24, 30));
        secaoCentral.setPreferredSize(new Dimension(800, 283));
        this.add(secaoCentral, BorderLayout.CENTER);
        

        botaoFilme1 = new JButton();
        botaoFilme1.setPreferredSize(new Dimension(170, 240));
        botaoFilme1.addActionListener(this);
        botaoFilme1.setVisible(true);
        botaoFilme1.setText("1");
        botaoFilme1.setFocusable(false);
        secaoCentral.add(botaoFilme1);

        ImageIcon cartaz1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("posterFilmesAntigos/1titanic.png")));
        botaoFilme1.setIcon(cartaz1);

        botaoFilme2 = new JButton();
        botaoFilme2.setPreferredSize(new Dimension(170, 240));
        botaoFilme2.addActionListener(this);
        botaoFilme2.setVisible(true);
        botaoFilme2.setText("2");
        botaoFilme2.setFocusable(false);
        secaoCentral.add(botaoFilme2);

        ImageIcon cartaz2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("posterFilmesAntigos/2bacurau.png")));
        botaoFilme2.setIcon(cartaz2);

        botaoFilme3 = new JButton();
        botaoFilme3.setPreferredSize(new Dimension(170, 240));
        botaoFilme3.addActionListener(this);
        botaoFilme3.setVisible(true);
        botaoFilme3.setText("3");
        botaoFilme3.setFocusable(false);
        secaoCentral.add(botaoFilme3);

        ImageIcon cartaz3 = new ImageIcon(Objects.requireNonNull(getClass().getResource("posterFilmesAntigos/3vingadores.png")));
        botaoFilme3.setIcon(cartaz3);

        botaoFilme4 = new JButton();
        botaoFilme4.setPreferredSize(new Dimension(170, 240));
        botaoFilme4.addActionListener(this);
        botaoFilme4.setVisible(true);
        botaoFilme4.setText("4");
        botaoFilme4.setFocusable(false);
        secaoCentral.add(botaoFilme4);

        ImageIcon cartaz4 = new ImageIcon(Objects.requireNonNull(getClass().getResource("posterFilmesAntigos/42012.png")));
        botaoFilme4.setIcon(cartaz4);

        comissao = new JLabel("(R$" + 0 + ")");
        comissao.setVisible(true);
        comissao.setForeground(Color.lightGray);
        comissao.setBounds(720, 25, 100, 20);
        secaoInferior.add(comissao);
        
        botaoVoltar = new JButton();
        botaoVoltar.setBounds(10, 10, 60, 30);
        botaoVoltar.addActionListener(this);
        botaoVoltar.setVisible(true);
        botaoVoltar.setText("Sair");
        botaoVoltar.setFocusable(false);
        secaoSuperior.add(botaoVoltar);

        boatoCompra = new JButton();
        boatoCompra.setBounds(300, 10, 200, 50);
        boatoCompra.addActionListener(this);
        boatoCompra.setVisible(true);
        boatoCompra.setText("Realizar compra");
        boatoCompra.setFocusable(false);
        secaoInferior.add(boatoCompra);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoVoltar) {
            //this.setVendedorLogado(null);
            janelaLogin.getFrame().setVisible(true);
            janela.dispose();
        }
        else if (e.getSource() == boatoCompra) {
            //janela.getPainelCompra().atualizarPainel(0);
            filmeSelecionado = 0;
            sessaoSelecionada = 1;
            this.setVisible(false);
            janela.getPainelCompra().setVisible(true);
        }
        else {
            filmeSelecionado = Integer.parseInt(((JButton) e.getSource()).getText());
            janela.getPainelSessoesDisponiveis().atualizarPainel(filmeSelecionado);
            //this.filmeSelecionado = Integer.parseInt(((JButton) e.getSource()).getText());
            this.setVisible(false);
            janela.getPainelSessoesDisponiveis().setVisible(true);
        }
    }
    
    public void atualizarPainel(int codigo) {
        if (codigo == 0) {
            comissao.setText("(R$" + comissaoAtual + ")");
            comissao.setVisible(false);
            comissao.setVisible(true);
        }
    }

    public Integer[] getQuantidadeConsumiveis() {
        return quantidadesConsumiveis;
    }

    public void zerarQuantidadeConsumiveis() {
        for (int i = 0; i < 10; i += 1) {  
            quantidadesConsumiveis[i] = 0;
        }
    }

    public void setFilmeSelecionado(int codigo) {
        this.filmeSelecionado = codigo;
    }

    public void setSessaoSelecionado(int codigo) {
        this.sessaoSelecionada = codigo;
    }

    public void setTotalCompra(Double valor) {
        this.totalCompra = valor;
    }

    public void setTotalConsumiveis(Double valor) {
        this.totalConsumiveis = valor;
    }

    public void setTotalIngressos(Double valor) {
        this.totalIngressos = valor;
    }


    public void efetivarCompra(ArrayList<Integer> listaAssentos, int ingressosMeia) {
        Compra novaCompra = new Compra();
        if (listaAssentos.size() != 0) {
            Sessao sessao = vendedorLogado.getUnidade().getListaSessoes().get((sessaoSelecionada + 5*(filmeSelecionado-1))-1);


            for (Integer listaAssento : listaAssentos) {
                Ingresso novoIngresso = new Ingresso(listaAssento, sessao, true);
                novaCompra.adicionarIngresso(novoIngresso);
            }
            
            
            for (int j = 0; j < ingressosMeia; j += 1) {
                novaCompra.getListaIngressos().get(j).setEhInteiro(false);
                novaCompra.getListaIngressos().get(j).setPreco(novaCompra.getListaIngressos().get(j).getPreco()/2);
            }
            for (Integer assento : listaAssentos) {
                    sessao.getListaAssentos().remove(assento - 1);
                    sessao.getListaAssentos().add(assento - 1, false);
            }
            
        }


        for (int h = 0; h < quantidadesConsumiveis[0]; h += 1) {
            novaCompra.criarConsumivel("Pipoca pequena", janela.getPainelCompra().precosConsumiveis[0]);
        }
        for (int h = 0; h < quantidadesConsumiveis[1]; h += 1) {
            novaCompra.criarConsumivel("Pipoca média", janela.getPainelCompra().precosConsumiveis[1]);
        }
        for (int h = 0; h < quantidadesConsumiveis[2]; h += 1) {
            novaCompra.criarConsumivel("Pipoca grande", janela.getPainelCompra().precosConsumiveis[2]);
        }
        for (int h = 0; h < quantidadesConsumiveis[3]; h += 1) {
            novaCompra.criarConsumivel("Pipoca balde", janela.getPainelCompra().precosConsumiveis[3]);
        }
        for (int h = 0; h < quantidadesConsumiveis[4]; h += 1) {
            novaCompra.criarConsumivel("Refrigerante", janela.getPainelCompra().precosConsumiveis[4]);
        }
        for (int h = 0; h < quantidadesConsumiveis[5]; h += 1) {
            novaCompra.criarConsumivel("Água", janela.getPainelCompra().precosConsumiveis[5]);
        }
        for (int h = 0; h < quantidadesConsumiveis[6]; h += 1) {
            novaCompra.criarConsumivel("Suco", janela.getPainelCompra().precosConsumiveis[6]);
        }
        for (int h = 0; h < quantidadesConsumiveis[7]; h += 1) {
            novaCompra.criarConsumivel("Chocolate", janela.getPainelCompra().precosConsumiveis[7]);
        }
        for (int h = 0; h < quantidadesConsumiveis[8]; h += 1) {
            novaCompra.criarConsumivel("Bala", janela.getPainelCompra().precosConsumiveis[8]);
        }
        for (int h = 0; h < quantidadesConsumiveis[9]; h += 1) {
            novaCompra.criarConsumivel("Amendoim", janela.getPainelCompra().precosConsumiveis[9]);
        }
        

        //System.out.println(sessao.getListaAssentos());

        novaCompra.atualizarTotal();

        for (Ingresso ingresso : novaCompra.getListaIngressos()) {
            ingresso.imprimir();
        }

        vendedorLogado.getUnidade().getListaCompras().add(novaCompra);

        novaCompra.imprimir();
        
    }
} 
