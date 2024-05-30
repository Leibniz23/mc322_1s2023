import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelLogin extends JPanel implements ActionListener{
    JanelaVendedor janela;
    Unidade unidade;
    JButton botaoLogin;
    JTextField campoTextoNome;
    JTextField campoTextoSenha;

    PainelLogin(JanelaVendedor janela, Unidade unidade) {
        this.unidade = unidade;
        this.janela = janela;

        this.setBackground(Color.darkGray);
        this.setBounds(0, 0, 800, 450);
        this.setLayout(null);
        
        botaoLogin = new JButton();
        botaoLogin.setBounds(300, 300, 200, 50);
        botaoLogin.addActionListener(this);
        this.add(botaoLogin);

        campoTextoNome = new JTextField();
        campoTextoNome.setBounds(250, 100, 300, 20);
        this.add(campoTextoNome);

        campoTextoSenha = new JTextField();
        campoTextoSenha.setBounds(250, 200, 300, 20);
        this.add(campoTextoSenha);

        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == botaoLogin)) {

            for (Funcionario funcionario : unidade.getListaFuncionarios()) {
                if (funcionario.getLogin().equals(campoTextoNome.getText()) && (funcionario.getSenha().equals(campoTextoSenha.getText()))) {
                    this.setVisible(false);
                    janela.getPainelVendedor().setVisible(true);
                    janela.getPainelVendedor().vendedorLogado = (Vendedor )funcionario;
                }
            }

            // //MÉTODO DUMMY//
            // if (campoTextoNome.getText().equals("vendedor")) {
            //     campoTextoNome.setText("");
            //     this.setVisible(false);
            //     janela.getPainelVendedor().setVisible(true);
            //     janela.getPainelVendedor().vendedorLogado
            // }
        }
    }
} 
