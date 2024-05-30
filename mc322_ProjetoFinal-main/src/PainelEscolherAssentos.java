import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.ImageIcon;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PainelEscolherAssentos extends JPanel implements ActionListener, atualizar{
    JanelaVendedor janela;
    JButton botaoVoltar;
    JButton botaoConfirmarAssentos;
    JPanel secaoCentral;
    ArrayList<JCheckBox> botoesAssentos;
    ArrayList<Integer> assentosSelecionados = new ArrayList<>();

    PainelEscolherAssentos(JanelaVendedor janela) {
        this.janela = janela;

        this.setBackground(Color.black);
        this.setBounds(0, 0, 800, 450);
        this.setLayout(new BorderLayout());
        this.setVisible(false);

        JPanel secaoSuperior = new JPanel();
        secaoSuperior.setBackground(Color.orange);
        secaoSuperior.setVisible(true);
        secaoSuperior.setLayout(null);
        secaoSuperior.setPreferredSize(new Dimension(800, 50));
        this.add(secaoSuperior, BorderLayout.NORTH);

        botaoVoltar = new JButton();
        botaoVoltar.setBounds(10, 10, 100, 30);
        botaoVoltar.addActionListener(this);
        botaoVoltar.setVisible(true);
        botaoVoltar.setText("Voltar");
        botaoVoltar.setFocusable(false);
        secaoSuperior.add(botaoVoltar);

        JPanel secaoInferior = new JPanel();
        secaoInferior.setBackground(Color.lightGray);
        secaoInferior.setLayout(null);
        secaoInferior.setVisible(true);
        secaoInferior.setPreferredSize(new Dimension(1600, 70));
        this.add(secaoInferior, BorderLayout.SOUTH);

        botaoConfirmarAssentos = new JButton();
        botaoConfirmarAssentos.setBounds(630, 15, 150, 40);
        botaoConfirmarAssentos.addActionListener(this);
        botaoConfirmarAssentos.setVisible(true);
        botaoConfirmarAssentos.setText("Confirmar");
        botaoConfirmarAssentos.setFocusable(false);
        secaoInferior.add(botaoConfirmarAssentos);

        secaoCentral = new JPanel();
        secaoCentral.setBackground(Color.white);
        secaoCentral.setVisible(true);
        secaoCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        secaoCentral.setPreferredSize(new Dimension(800, 330));
        this.atualizarPainel(0);
        this.add(secaoCentral, BorderLayout.CENTER);

        JPanel secaoLateralDireita = new JPanel();
        secaoLateralDireita.setBackground(Color.black);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoVoltar) {
            //this.atualizarPainel(0);
            this.setVisible(false);
            janela.getPainelEscolherSessao().setVisible(true);
        }
        if (e.getSource() == botaoConfirmarAssentos) {
            this.setVisible(false);
            janela.getPainelCompra().setVisible(true);
            for (int i = 0; i < 100; i += 1) {
                if (botoesAssentos.get(i).isSelected()) {
                    assentosSelecionados.add(Integer.parseInt(botoesAssentos.get(i).getText()) + 1);
                }
            }

            janela.getPainelCompra().atualizarPainel(2);
            janela.getPainelCompra().atualizarPainel(4);
        }
    }

    @Override
    public void atualizarPainel(int codigo) {
        secaoCentral.removeAll();

        ImageIcon imagemAssentoLivre = new ImageIcon(Objects.requireNonNull(getClass().getResource("icones_GUI/green.png")));
        ImageIcon imagemAssentoSelecionado = new ImageIcon(Objects.requireNonNull(getClass().getResource("icones_GUI/gray.png")));

        Sessao sessao = janela.getPainelVendedor().vendedorLogado.getUnidade().getListaSessoes().get((janela.getPainelVendedor().sessaoSelecionada + 5*(janela.getPainelVendedor().filmeSelecionado - 1))-1);

        botoesAssentos = new ArrayList<>();

        for (int i = 0; i < 100; i += 1) {
            JCheckBox assento = new JCheckBox();
            assento.setText(String.valueOf(i));
            assento.setPreferredSize(new Dimension(20, 20));
            assento.setIcon(imagemAssentoLivre);
            assento.setSelectedIcon(imagemAssentoSelecionado);
            assento.setHorizontalAlignment(SwingConstants.CENTER);
            botoesAssentos.add(assento);
        }

        for (int i = 0; i < 100; i += 1) {
            if (sessao.getListaAssentos().get(i)) {
                secaoCentral.add(botoesAssentos.get(i));
            }
            else {
                JPanel assentoOcupado = new JPanel();
                assentoOcupado.setPreferredSize(new Dimension(20, 20));
                assentoOcupado.setBackground(Color.red);
                secaoCentral.add(assentoOcupado);
            }
        }

        JPanel tela = new JPanel();
        tela.setVisible(true);
        tela.setPreferredSize(new Dimension(500, 7));
        tela.setBackground(Color.lightGray);
        secaoCentral.add(tela);
    }
    

    
}
