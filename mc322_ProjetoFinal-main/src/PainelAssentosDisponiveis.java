import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelAssentosDisponiveis extends JPanel implements ActionListener, atualizar{
    JanelaVendedor janela;
    JButton botaoVoltar;
    JPanel secaoCentral;

    PainelAssentosDisponiveis(JanelaVendedor janela) {
        this.janela = janela;

        this.setBackground(Color.blue);
        this.setBounds(0, 0, 800, 450);
        this.setLayout(new BorderLayout());
        this.setVisible(false);

        JPanel secaoSuperior = new JPanel();
        secaoSuperior.setBackground(Color.black);
        secaoSuperior.setVisible(true);
        secaoSuperior.setLayout(null);
        secaoSuperior.setPreferredSize(new Dimension(100, 50));
        this.add(secaoSuperior, BorderLayout.NORTH);

        botaoVoltar = new JButton();
        botaoVoltar.setBounds(10, 10, 100, 30);
        botaoVoltar.addActionListener(this);
        botaoVoltar.setVisible(true);
        botaoVoltar.setText("Voltar");
        botaoVoltar.setFocusable(false);
        secaoSuperior.add(botaoVoltar);

        secaoCentral = new JPanel();
        secaoCentral.setBackground(Color.white);
        secaoCentral.setVisible(true);
        secaoCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 17, 15));
        secaoCentral.setPreferredSize(new Dimension(800, 330));
        this.add(secaoCentral, BorderLayout.CENTER);

        JPanel secaoLateralDireita = new JPanel();
        secaoLateralDireita.setBackground(Color.darkGray);
        secaoLateralDireita.setVisible(true);
        secaoLateralDireita.setLayout(new FlowLayout());
        secaoLateralDireita.setPreferredSize(new Dimension(100, 400));
        this.add(secaoLateralDireita, BorderLayout.EAST);

        JPanel secaoLateralEsquerda = new JPanel();
        secaoLateralEsquerda.setBackground(Color.darkGray);
        secaoLateralEsquerda.setVisible(true);
        secaoLateralEsquerda.setLayout(new FlowLayout());
        secaoLateralEsquerda.setPreferredSize(new Dimension(100, 400));
        this.add(secaoLateralEsquerda, BorderLayout.WEST);

        this.atualizarAssentosDisponiveis();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoVoltar) {
            //janela.getPainelVendedor().setSessaoSelecionada = 0;
            this.setVisible(false);
            janela.getPainelSessoesDisponiveis().setVisible(true);
        }
    }

    //MÉTODO DUMMY//
    public void atualizarAssentosDisponiveis() {
        secaoCentral.removeAll();

        ImageIcon imagemAssentoLivre = new ImageIcon("icones_GUI/green.png");

        for (int i = 0; i < 100; i += 1) {
            JLabel assentoLivre = new JLabel();
            assentoLivre.setIcon(imagemAssentoLivre);
            secaoCentral.add(assentoLivre);
        }
    }


    @Override
    public void atualizarPainel(int codigo) {
        secaoCentral.removeAll();


        ImageIcon imagemAssentoLivre = new ImageIcon(Objects.requireNonNull(getClass().getResource("icones_GUI/green_small.png")));
        ImageIcon imagemAssentoOcupado = new ImageIcon(Objects.requireNonNull(getClass().getResource("icones_GUI/red_small.png")));

        Sessao sessao = janela.getPainelVendedor().vendedorLogado.getUnidade().getListaSessoes().get(((janela.getPainelVendedor().sessaoSelecionada + 5*(janela.getPainelVendedor().filmeSelecionado - 1)))-1);


        for (int i = 0; i < 100; i += 1) {
            if (sessao.getListaAssentos().get(i)) {
                JLabel assentoLivre = new JLabel();
                assentoLivre.setIcon(imagemAssentoLivre);
                secaoCentral.add(assentoLivre);

            }
            else {
                JLabel assentoOcupado = new JLabel();
                assentoOcupado.setIcon(imagemAssentoOcupado);
                secaoCentral.add(assentoOcupado);

            }
        }

        this.setVisible(false);
        this.setVisible(true);

        JPanel tela = new JPanel();
        tela.setVisible(true);
        tela.setPreferredSize(new Dimension(500, 7));
        tela.setBackground(Color.lightGray);
        secaoCentral.add(tela);
    }
    
}
