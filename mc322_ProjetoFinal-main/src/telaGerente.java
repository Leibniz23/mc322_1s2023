import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class telaGerente {
    private JPanel painelGerente;
    private JLabel labelBemVindo;
    private JButton buttonSair;
    private JList<String> listaFuncionarios;
    private JTextArea labelFuncionarioInfo;
    private JButton removerButton;
    private JButton alterarButton;
    private JButton cadastrarButton;
    private JList<String> listaSessoes;
    private JButton modificarFilmeButton;
    private JLabel labelImagem;
    private JList<String> listaConsumiveis;
    private JButton alterarPrecoButton;
    private JTextArea textFilme;
    private JButton adicionarItemButton;

    public telaGerente(Janela janela, Gerente gerente) {
        /* Aba Funcionarios */
        /* Popula JList com Funcionarios */
        DefaultListModel<String> listaFuncionariosModelo = new DefaultListModel<>();
        int i = 0;
        for (Funcionario f : gerente.getUnidade().getListaFuncionarios()) {
            listaFuncionariosModelo.add(i, f.getCpf());
            i++;
        }
        listaFuncionarios.setModel(listaFuncionariosModelo);

        /* Configuração Inicial */
        labelBemVindo.setText("Bem-vindo(a), " + gerente.getNome());
        alterarButton.setEnabled(false);

        /* Eventos */
        buttonSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                janela.getCards().add(Janela.LOGIN, new telaLogin(janela).getPainelLogin());
                janela.trocarCard(Janela.LOGIN);
                janela.getCards().remove(painelGerente);
            }
        });

        listaFuncionarios.addListSelectionListener(e -> {
            if (listaFuncionarios.getSelectedIndex() == -1) {
                labelFuncionarioInfo.setText("");
                return;
            }
            List<Funcionario> funcionariosUnidade = gerente.getUnidade().getListaFuncionarios();
            Funcionario selecionado = funcionariosUnidade.get(listaFuncionarios.getSelectedIndex());
            labelFuncionarioInfo.setText(selecionado.toString());
            removerButton.setEnabled(selecionado != gerente);
            alterarButton.setEnabled(true);
        });

        cadastrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                janela.adicionarCard(Janela.INSERIR_FUNCIONARIO, new telaCadastrarFuncionario(janela, gerente).getPainelInserir());
                janela.trocarCard(Janela.INSERIR_FUNCIONARIO);
                janela.getCards().remove(painelGerente);
            }
        });

        removerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Funcionario> funcionariosUnidade = gerente.getUnidade().getListaFuncionarios();
                Funcionario selecionado = funcionariosUnidade.get(listaFuncionarios.getSelectedIndex());
                listaFuncionariosModelo.removeElementAt(listaFuncionarios.getSelectedIndex());
                gerente.demitirFuncionario(selecionado.getCpf());
            }
        });

        alterarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Funcionario> funcionariosUnidade = gerente.getUnidade().getListaFuncionarios();
                Funcionario selecionado = funcionariosUnidade.get(listaFuncionarios.getSelectedIndex());
                try {
                    String salario = JOptionPane.showInputDialog("Digite o novo salário:");
                    gerente.modificarSalario(selecionado.getCpf(), Double.parseDouble(salario));
                    labelFuncionarioInfo.setText(selecionado.toString());
                } catch (NullPointerException ignored) {
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Valor não numérico!");
                }
            }
        });

        /* Aba Sessões */
        /* Populando JList com Sessões */
        DefaultListModel<String> listaSessoesModelo = new DefaultListModel<>();
        i = 0;
        for (Sessao s : gerente.getUnidade().getListaSessoes()) {
            listaSessoesModelo.add(i, "Sala " + s.getSala().getId() + " " + s.getHorario());
            i++;
        }
        listaSessoes.setModel(listaSessoesModelo);

        /* Configurações Iniciais */
        modificarFilmeButton.setEnabled(false);

        /* Eventos */
        listaSessoes.addListSelectionListener(e -> {
            if (listaSessoes.getSelectedIndex() == -1) {
                return;
            }
            List<Sessao> sessoesUnidade = gerente.getUnidade().getListaSessoes();
            Sessao selecionada = sessoesUnidade.get(listaSessoes.getSelectedIndex());
            atualizarSessaoInfo(selecionada);
            modificarFilmeButton.setEnabled(true);
        });

        modificarFilmeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Sessao> sessoesUnidade = gerente.getUnidade().getListaSessoes();
                Sessao selecionada = sessoesUnidade.get(listaSessoes.getSelectedIndex());
                try {
                    String filme = JOptionPane.showInputDialog("Digite o nome do Filme:");
                    JFileChooser escolher = new JFileChooser("src/posterFilmesAntigos/");
                    escolher.setDialogTitle("Escolher cartaz");
                    escolher.addChoosableFileFilter(new FileNameExtensionFilter("Imagens",
                                                                      "jpg", "jpeg", "png", "gif"));
                    escolher.setAcceptAllFileFilterUsed(false);
                    if (escolher.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        gerente.modificarFilme(filme, escolher.getSelectedFile().getPath(), selecionada);
                        atualizarSessaoInfo(selecionada);
                    }
                } catch (NullPointerException ignored) {
                }
            }
        });

        /* Aba Consumíveis */
        /* Populando JList com Consumiveis */
        DefaultListModel<String> listaConsumiveisModelo = new DefaultListModel<>();
        i = 0;
        for (Consumivel c : gerente.getUnidade().getListaConsumivel()) {
            listaConsumiveisModelo.add(i, c.getNome() + "(R$ " + c.getPreco() + ")");
            i++;
        }
        listaConsumiveis.setModel(listaConsumiveisModelo);

        /* Configurações Iniciais */
        alterarPrecoButton.setEnabled(false);

        /* Eventos */
        alterarPrecoButton.addActionListener(e -> {
            try {
                List<Consumivel> consumiveisUnidade = gerente.getUnidade().getListaConsumivel();
                Consumivel selecionado = consumiveisUnidade.get(listaConsumiveis.getSelectedIndex());
                String preco = JOptionPane.showInputDialog("Digite o preço:");
                    gerente.alterarPreco(selecionado.getNome(), Double.parseDouble(preco));
                listaConsumiveisModelo.set(listaConsumiveis.getSelectedIndex(),
                                           selecionado.getNome() + "(R$ " + selecionado.getPreco() + ")");
            } catch (NullPointerException ignored) {
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Valor não numérico!");
            }
        });

        listaConsumiveis.addListSelectionListener(e -> {
            if (listaConsumiveis.getSelectedIndex() == -1) {
                alterarPrecoButton.setEnabled(false);
                return;
            }
            alterarPrecoButton.setEnabled(true);
        });

        adicionarItemButton.addActionListener(e -> {
            try {
                String nome = JOptionPane.showInputDialog("Digite o nome:");
                String preco = JOptionPane.showInputDialog("Digite o preço:");
                    gerente.adicionarConsumivel(nome, Double.parseDouble(preco));
                listaConsumiveisModelo.addElement(nome + "(R$ " + preco + ")");
            } catch (NullPointerException ignored) {
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Valor não numérico!");
            }
        });
    }

    public JPanel getPainelGerente() {
        return painelGerente;
    }

    public void atualizarSessaoInfo(Sessao selecionada) {
        textFilme.setText(selecionada.toString());
        labelImagem.setText("<html><img width=250 height=250 src=\"file:"+ selecionada.getCartaz() + "\"><html>");
    }
}
