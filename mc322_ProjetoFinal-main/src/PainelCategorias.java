import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelCategorias extends JPanel implements ActionListener, atualizar{
    JanelaVendedor janela;
    JButton botaoVoltar;
    JButton botaoPipoca;
    JButton botaoBebidas;
    JButton botaoDiversos;
    JPanel secaoListarMovel;


    PainelCategorias(JanelaVendedor janela) {
        this.janela = janela;

        this.setBackground(Color.darkGray);
        this.setBounds(0, 0, 800, 450);
        this.setLayout(null);
        this.setVisible(false);

        JPanel secaoSuperior = new JPanel();
        secaoSuperior.setLayout(null);
        secaoSuperior.setBackground(Color.black);
        secaoSuperior.setVisible(true);
        secaoSuperior.setBounds(0, 0, 800, 50);
        this.add(secaoSuperior);

        botaoVoltar = new JButton();
        botaoVoltar.setBounds(10, 10, 100, 30);
        botaoVoltar.addActionListener(this);
        botaoVoltar.setVisible(true);
        botaoVoltar.setText("Voltar");
        botaoVoltar.setFocusable(false);
        secaoSuperior.add(botaoVoltar);

        JPanel secaoCentral = new JPanel();
        secaoCentral.setLayout(null);
        secaoCentral.setBackground(Color.blue);
        secaoCentral.setVisible(true);
        secaoCentral.setBounds(0, 50, 800, 400);
        this.add(secaoCentral);

        JPanel secaoCategorias = new JPanel();
        secaoCategorias.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 55));
        secaoCategorias.setBackground(Color.orange);
        secaoCategorias.setVisible(true);
        secaoCategorias.setBounds(0, 0, 200, 400);
        secaoCentral.add(secaoCategorias);

        botaoPipoca = new JButton();
        botaoPipoca.setPreferredSize(new Dimension(100, 50));
        botaoPipoca.addActionListener(this);
        botaoPipoca.setVisible(true);
        botaoPipoca.setText("Pipoca");
        botaoPipoca.setFocusable(false);
        secaoCategorias.add(botaoPipoca);

        botaoBebidas = new JButton();
        botaoBebidas.setPreferredSize(new Dimension(100, 50));
        botaoBebidas.addActionListener(this);
        botaoBebidas.setVisible(true);
        botaoBebidas.setText("Bebidas");
        botaoBebidas.setFocusable(false);
        secaoCategorias.add(botaoBebidas);

        botaoDiversos = new JButton();
        botaoDiversos.setPreferredSize(new Dimension(100, 50));
        botaoDiversos.addActionListener(this);
        botaoDiversos.setVisible(true);
        botaoDiversos.setText("Diversos");
        botaoDiversos.setFocusable(false);
        secaoCategorias.add(botaoDiversos);

        JPanel secaoListar = new JPanel();
        secaoListar.setLayout(null);
        secaoListar.setBackground(Color.white);
        secaoListar.setVisible(true);
        secaoListar.setBounds(250, 20, 500, 330);
        secaoCentral.add(secaoListar);

        secaoListarMovel = new JPanel();
        secaoListarMovel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));
        secaoListarMovel.setBackground(Color.lightGray);
        secaoListarMovel.setVisible(true);
        secaoListarMovel.setBounds(0, 0, 500, 330);
        secaoListar.add(secaoListarMovel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoVoltar) {
            this.setVisible(false);
            janela.getPainelCompra().setVisible(true);
            janela.getPainelCompra().atualizarPainel(1);
        }
        else if (e.getSource() == botaoPipoca) {
            this.setVisible(false);
            janela.getPainelConsumiveis().estadoPipoca();
            janela.getPainelConsumiveis().setVisible(true);
        }
        else if (e.getSource() == botaoBebidas) {
            this.setVisible(false);
            janela.getPainelConsumiveis().estadoBebida();
            janela.getPainelConsumiveis().setVisible(true);
        }
        else if (e.getSource() == botaoDiversos) {
            this.setVisible(false);
            janela.getPainelConsumiveis().estadoOutro();
            janela.getPainelConsumiveis().setVisible(true);
        }
    }

    @Override
    public void atualizarPainel(int codigo) {
        if (codigo == 0) {
            secaoListarMovel.removeAll();
        }
    }
    
}
