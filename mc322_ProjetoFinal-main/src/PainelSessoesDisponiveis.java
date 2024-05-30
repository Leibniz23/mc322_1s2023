import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PainelSessoesDisponiveis extends JPanel implements ActionListener, atualizar{
    JanelaVendedor janela;
    JButton botaoVoltar;
    JButton botaoSessao1;
    JButton botaoSessao2;
    JButton botaoSessao3;
    JButton botaoSessao4;
    JButton botaoSessao5;

    PainelSessoesDisponiveis(JanelaVendedor janela) {
        this.janela = janela;

        this.setBackground(Color.red);
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

        JPanel secaoCentral = new JPanel();
        secaoCentral.setBackground(Color.darkGray);
        secaoCentral.setVisible(true);
        secaoCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 50));
        secaoCentral.setPreferredSize(new Dimension(100, 750));
        
        JScrollPane scrollSessoes = new JScrollPane(secaoCentral);
        this.add(scrollSessoes, BorderLayout.CENTER);

        botaoSessao1 = new JButton();
        botaoSessao1.setPreferredSize(new Dimension(650, 90));
        botaoSessao1.addActionListener(this);
        botaoSessao1.setVisible(true);
        botaoSessao1.setText("Sessão 10:00 - 12:00    (" + 0 + "/100)");
        botaoSessao1.setFont(new Font("Calibri", Font.PLAIN, 40));
        botaoSessao1.setFocusable(false);
        secaoCentral.add(botaoSessao1);

        botaoSessao2 = new JButton();
        botaoSessao2.setPreferredSize(new Dimension(650, 90));
        botaoSessao2.addActionListener(this);
        botaoSessao2.setVisible(true);
        botaoSessao2.setText("Sessão 12:30 - 14:30    (" + 0 + "/100)");
        botaoSessao2.setFont(new Font("Calibri", Font.PLAIN, 40));
        botaoSessao2.setFocusable(false);
        secaoCentral.add(botaoSessao2);

        botaoSessao3 = new JButton();
        botaoSessao3.setPreferredSize(new Dimension(650, 90));
        botaoSessao3.addActionListener(this);
        botaoSessao3.setVisible(true);
        botaoSessao3.setText("Sessão 15:00 - 17:00    (" + 0 + "/100)");
        botaoSessao3.setFont(new Font("Calibri", Font.PLAIN, 40));
        botaoSessao3.setFocusable(false);
        secaoCentral.add(botaoSessao3);

        botaoSessao4 = new JButton();
        botaoSessao4.setPreferredSize(new Dimension(650, 90));
        botaoSessao4.addActionListener(this);
        botaoSessao4.setVisible(true);
        botaoSessao4.setText("Sessão 17:30 - 19:30    (" + 0 + "/100)");
        botaoSessao4.setFont(new Font("Calibri", Font.PLAIN, 40));
        botaoSessao4.setFocusable(false);
        secaoCentral.add(botaoSessao4);

        botaoSessao5 = new JButton();
        botaoSessao5.setPreferredSize(new Dimension(650, 90));
        botaoSessao5.addActionListener(this);
        botaoSessao5.setVisible(true);
        botaoSessao5.setText("Sessão 20:00 - 22:00    (" + 0 + "/100)");
        botaoSessao5.setFont(new Font("Calibri", Font.PLAIN, 40));
        botaoSessao5.setFocusable(false);
        secaoCentral.add(botaoSessao5);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoVoltar) {
            //janela.getPainelVendedor().setFilmeSelecionado = 0;
            this.setVisible(false);
            janela.getPainelVendedor().setVisible(true);
        }
        else {
            if (e.getSource() == botaoSessao1) {
                janela.getPainelVendedor().setSessaoSelecionado(1);
            }
            else if (e.getSource() == botaoSessao2) {
                janela.getPainelVendedor().setSessaoSelecionado(2);   
            }
            else if (e.getSource() == botaoSessao3) {
                janela.getPainelVendedor().setSessaoSelecionado(3);   
            }
            else if (e.getSource() == botaoSessao4) {
                janela.getPainelVendedor().setSessaoSelecionado(4);   
            }
            else if (e.getSource() == botaoSessao5) {
                janela.getPainelVendedor().setSessaoSelecionado(5);   
            }


            janela.getPainelAssentosDisponiveis().atualizarPainel(0);
            this.setVisible(false);
            janela.getPainelAssentosDisponiveis().setVisible(true);
        }
    }

    @Override
    public void atualizarPainel(int codigo) {
        JButton[] listaBotoes = {botaoSessao1, botaoSessao2, botaoSessao3, botaoSessao4, botaoSessao5};

        int j = 0;

        for (int i = (5 * (janela.getPainelVendedor().filmeSelecionado - 1)); i < (janela.getPainelVendedor().filmeSelecionado)*5; i += 1) {
            //System.out.println(j + " " + i);
            Sessao sessao = janela.getPainelVendedor().vendedorLogado.getUnidade().getListaSessoes().get(i);

            listaBotoes[j].setText("Sessão" + sessao.getHorario() + "    (" + sessao.assentosOcupados() + "/100)");
            j += 1;
        }
    }
    
}
