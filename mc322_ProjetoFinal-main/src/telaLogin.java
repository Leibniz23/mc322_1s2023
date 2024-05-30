import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class telaLogin {
    private JTextField campoLogin;
    private JButton buttonEntrar;
    private JPanel painelLogin;
    private JLabel labelErroLogin;
    private JTextField campoSenha;
    private JTextArea textTitulo;

    public telaLogin(Janela janela) {
        /* Configurações Iniciais */
        labelErroLogin.setVisible(false);
        labelErroLogin.setForeground(Color.RED);
        textTitulo.setText(janela.getEntidade().toString());

        /* Eventos */
        buttonEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String login = campoLogin.getText();
                String senha = campoSenha.getText();
                Entidade entidade = janela.getEntidade();

                Funcionario funcionario = entidade.getListaFuncionarios()
                        .stream()
                        .filter(f -> f.getLogin().equals(login))
                        .findAny()
                        .orElse(null);

                if (funcionario == null) {
                    labelErroLogin.setVisible(true);
                } else if (!funcionario.getSenha().equals(senha)) {
                    labelErroLogin.setVisible(true);
                    labelErroLogin.setText("Senha incorreta!");
                } else {
                    if (funcionario.getClass() == Gerente.class) {
                        janela.adicionarCard(Janela.GERENTE, new telaGerente(janela, (Gerente) funcionario).getPainelGerente());
                        janela.trocarCard(Janela.GERENTE);
                        janela.getCards().remove(painelLogin);
                    }
                    else {
                        funcionario.getUnidade().inicializarExemplo();
                        janela.getFrame().setVisible(false);
                        new JanelaVendedor(janela, entidade.getListaUnidades().get(0), (Vendedor) funcionario);
                    }
                }
            }
        });
    }

    public JPanel getPainelLogin() {
        return painelLogin;
    }
}
