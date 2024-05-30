import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelEditarIngresso extends JPanel{
    JCheckBox botaoMeia;
    JLabel informacoes;

    PainelEditarIngresso(String filme, int assento, PainelCompra painel) {
        this.setVisible(true);
        this.setPreferredSize(new Dimension(250, 20));
        this.setLayout(null);
        
        informacoes = new JLabel();
        informacoes.setVisible(true);
        informacoes.setText("Filme: " + filme + "            (Assento: " + assento + ")");
        informacoes.setFont(new Font("Calibri", Font.PLAIN, 15));
        informacoes.setBounds(0, 0, 250, 20);
        this.add(informacoes);

        botaoMeia = new JCheckBox();
        botaoMeia.setVisible(true);
        botaoMeia.addActionListener(painel);
        botaoMeia.setBounds(230, 0, 20, 20);
        this.add(botaoMeia);
    }
}
