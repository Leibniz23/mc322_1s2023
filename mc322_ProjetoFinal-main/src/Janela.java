import javax.swing.*;
import java.awt.*;

public class Janela {
    private final JPanel cards;
    /* Criar nome dos cards */
    public static final String LOGIN = "Login";
    public static final String GERENTE = "Gerente";
    public static final String INSERIR_FUNCIONARIO = "Inserir Funcionario";
    private final Entidade entidade;
    private final JFrame frame = new JFrame();

    public Janela(Entidade entidade, String titulo) {
        this.entidade = entidade;
        frame.setTitle(titulo);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cards = new JPanel(new CardLayout());
        frame.add(cards);

        /* Adiciona cards (paineis) */
        cards.add(new telaLogin(this).getPainelLogin(), LOGIN);

        frame.setContentPane(cards);
    }

    public void adicionarCard(String card, JPanel painel) {
        cards.add(painel, card);
    }

    public void trocarCard(String card) {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, card);
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public JPanel getCards() {
        return cards;
    }
    public JFrame getFrame() {
        return frame;
    }
}
