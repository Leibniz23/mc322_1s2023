import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PainelComplexo extends JPanel implements ActionListener{
    int consumivelAssociado;
    JanelaVendedor janela;
    JButton botaoSomar;
    JButton botaoSubtrair;
    JLabel nome;
    String rotulo;

    PainelComplexo(int codigo, JanelaVendedor janela, String rotulo) {
        this.rotulo = rotulo;
        this.consumivelAssociado = codigo;
        this.janela = janela;

        this.setVisible(true);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(500, 80));

        botaoSomar = new JButton();
        botaoSomar.setVisible(true);
        botaoSomar.addActionListener(this);
        botaoSomar.setFocusable(false);
        botaoSomar.setFont(new Font("Calibri", Font.BOLD, 13));
        botaoSomar.setText("+");
        botaoSomar.setBounds(460, 0, 40, 40);
        this.add(botaoSomar);

        botaoSubtrair = new JButton();
        botaoSubtrair.setVisible(true);
        botaoSubtrair.addActionListener(this);
        botaoSubtrair.setFocusable(false);
        botaoSubtrair.setFont(new Font("Calibri", Font.BOLD, 13));
        botaoSubtrair.setText("-");
        botaoSubtrair.setBounds(460, 40, 40, 40);
        this.add(botaoSubtrair);

        nome = new JLabel();
        nome.setVisible(true);
        nome.setFont(new Font("Calibri", Font.BOLD, 30));
        nome.setForeground(Color.darkGray);
        nome.setText(rotulo + janela.getPainelVendedor().getQuantidadeConsumiveis()[consumivelAssociado] + "x");
        nome.setBounds(20, 20, 400, 50);
        this.add(nome);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoSomar) {
            janela.getPainelVendedor().getQuantidadeConsumiveis()[consumivelAssociado] += 1;
            this.atualizarNome();
        }
        else if (janela.getPainelVendedor().getQuantidadeConsumiveis()[consumivelAssociado] != 0){
            janela.getPainelVendedor().getQuantidadeConsumiveis()[consumivelAssociado] -= 1;
            this.atualizarNome();
        }
        if (janela.getPainelVendedor().getQuantidadeConsumiveis()[consumivelAssociado] == 0){
            janela.getPainelCategorias().secaoListarMovel.remove(this);
            janela.getPainelCategorias().secaoListarMovel.setVisible(false);
            janela.getPainelCategorias().secaoListarMovel.setVisible(true);
        }

    }

    public void atualizarNome() {
        this.nome.setText(rotulo + " " + janela.getPainelVendedor().getQuantidadeConsumiveis()[consumivelAssociado] + "x");
        this.setVisible(false);
        this.setVisible(true);
    }
}
