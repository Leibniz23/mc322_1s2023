import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PainelCompra extends JPanel implements ActionListener, atualizar{
    JanelaVendedor janela;
    JPanel secaoListarIngressos;
    JButton botaoCancelar;
    JButton botaoEditarConsumiveis;
    JButton botaoEditarIngressos;
    JButton botaoConfirmarCompra;
    JLabel totalCompra;
    JLabel totalIngressos;
    JLabel totalConsumiveis;
    JTextArea areaTextoConsumiveis;
    Double[] precosConsumiveis = {10.00, 15.20, 30.00, 49.99, 6.00, 3.00, 4.99, 9.00, 4.00, 50.00};
    double precoBase = 1.0;
    ArrayList<PainelEditarIngresso> listaBotoesAssentosSelecionados = new ArrayList<>();

    PainelCompra(JanelaVendedor janela) {
        this.janela = janela;

        this.setBackground(Color.yellow);
        this.setBounds(0, 0, 800, 450);
        this.setLayout(new BorderLayout());
        this.setVisible(false);

        JPanel secaoSuperior = new JPanel();
        secaoSuperior.setBackground(Color.black);
        secaoSuperior.setVisible(true);
        secaoSuperior.setLayout(null);
        secaoSuperior.setPreferredSize(new Dimension(100, 50));
        this.add(secaoSuperior, BorderLayout.NORTH);

        botaoCancelar = new JButton();
        botaoCancelar.setBounds(10, 10, 100, 30);
        botaoCancelar.addActionListener(this);
        botaoCancelar.setVisible(true);
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setFocusable(false);
        secaoSuperior.add(botaoCancelar);

        JPanel secaoCentral = new JPanel();
        secaoCentral.setBackground(Color.blue);
        secaoCentral.setLayout(null);
        secaoCentral.setVisible(true);
        secaoCentral.setPreferredSize(null);
        this.add(secaoCentral, BorderLayout.CENTER);

        JPanel secaoInferior = new JPanel();
        secaoInferior.setBackground(new Color(0, 0, 100));
        secaoInferior.setLayout(null);
        secaoInferior.setVisible(true);
        secaoInferior.setPreferredSize(new Dimension(1600, 100));
        this.add(secaoInferior, BorderLayout.SOUTH);

        totalCompra = new JLabel("Total: R$" + 0.0);
        totalCompra.setVisible(true);
        totalCompra.setFont(new Font("Calibri", Font.BOLD, 20));
        totalCompra.setBounds(490, 27, 200, 20);
        totalCompra.setForeground(Color.lightGray);
        secaoInferior.add(totalCompra);

        botaoConfirmarCompra = new JButton();
        botaoConfirmarCompra.setBounds(630, 15, 150, 40);
        botaoConfirmarCompra.addActionListener(this);
        botaoConfirmarCompra.setVisible(true);
        botaoConfirmarCompra.setText("Confirmar compra");
        botaoConfirmarCompra.setFocusable(false);
        secaoInferior.add(botaoConfirmarCompra);


        JPanel secaoEditarConsumiveis = new JPanel();
        secaoEditarConsumiveis.setBackground(Color.orange);
        secaoEditarConsumiveis.setLayout(null);
        secaoEditarConsumiveis.setBounds(400, 0, 400, 330);
        secaoEditarConsumiveis.setVisible(true);
        secaoCentral.add(secaoEditarConsumiveis);

        totalConsumiveis = new JLabel("Consumíveis: R$" + 0.0);
        totalConsumiveis.setVisible(true);
        totalConsumiveis.setFont(new Font("Calibri", Font.BOLD, 15));
        totalConsumiveis.setBounds(140, 20, 200, 20);
        secaoEditarConsumiveis.add(totalConsumiveis);

        botaoEditarConsumiveis = new JButton();
        botaoEditarConsumiveis.setBounds(100, 230, 200, 50);
        botaoEditarConsumiveis.addActionListener(this);
        botaoEditarConsumiveis.setVisible(true);
        botaoEditarConsumiveis.setText("Editar consumíveis");
        botaoEditarConsumiveis.setFocusable(false);
        secaoEditarConsumiveis.add(botaoEditarConsumiveis);

        JPanel secaoEditarIngressos = new JPanel();
        secaoEditarIngressos.setBackground(Color.red);
        secaoEditarIngressos.setLayout(null);
        secaoEditarIngressos.setBounds(0, 0, 400, 330);
        secaoEditarIngressos.setVisible(true);
        secaoCentral.add(secaoEditarIngressos);

        secaoListarIngressos = new JPanel();
        secaoListarIngressos.setVisible(true);
        secaoListarIngressos.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 5));
        secaoListarIngressos.setBounds(75, 50, 250, 150);
        secaoListarIngressos.setBackground(Color.white);
        secaoEditarIngressos.add(secaoListarIngressos);

        areaTextoConsumiveis = new JTextArea();
        areaTextoConsumiveis.setVisible(true);
        areaTextoConsumiveis.setBounds(125, 50, 150, 150);
        areaTextoConsumiveis.setFont(new Font("Calibri", Font.PLAIN, 15));
        areaTextoConsumiveis.setForeground(Color.darkGray);
        areaTextoConsumiveis.setEditable(false);
        secaoEditarConsumiveis.add(areaTextoConsumiveis);

        totalIngressos = new JLabel("Ingressos: R$" + 0.0);
        totalIngressos.setVisible(true);
        totalIngressos.setFont(new Font("Calibri", Font.BOLD, 15));
        totalIngressos.setBounds(140, 20, 200, 20);
        secaoEditarIngressos.add(totalIngressos);

        botaoEditarIngressos = new JButton();
        botaoEditarIngressos.setBounds(100, 230, 200, 50);
        botaoEditarIngressos.addActionListener(this);
        botaoEditarIngressos.setVisible(true);
        botaoEditarIngressos.setText("Editar ingressos");
        botaoEditarIngressos.setFocusable(false);
        secaoEditarIngressos.add(botaoEditarIngressos);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCancelar) {
            this.setVisible(false);
            //janela.getPainelEscolherAssentos().atualizarPainel(0);
            janela.getPainelVendedor().setVisible(true);
            janela.getPainelCategorias().secaoListarMovel.removeAll();
            janela.getPainelVendedor().zerarQuantidadeConsumiveis();
            janela.getPainelEscolherAssentos().assentosSelecionados = new ArrayList<>();
            secaoListarIngressos.removeAll();
            this.atualizarPainel(1);
        }
        else if (e.getSource() == botaoConfirmarCompra) {
            int meias = 0;
            for (PainelEditarIngresso aux : listaBotoesAssentosSelecionados) {
                if (aux.botaoMeia.isSelected()) {
                    meias += 1;
                }
            }

            janela.getPainelVendedor().comissaoAtual += 0.5 * janela.getPainelVendedor().totalConsumiveis;
            janela.getPainelVendedor().atualizarPainel(0);

            janela.getPainelVendedor().efetivarCompra(janela.getPainelEscolherAssentos().assentosSelecionados, meias);
            this.setVisible(false);
            if (janela.getPainelEscolherAssentos().assentosSelecionados.size() != 0) {
                janela.getPainelEscolherAssentos().atualizarPainel(0);
            }
            janela.getPainelCategorias().atualizarPainel(0);
            janela.getPainelCompra().atualizarPainel(0);
            janela.getPainelVendedor().setVisible(true);
            janela.getPainelVendedor().zerarQuantidadeConsumiveis();
            janela.getPainelEscolherAssentos().assentosSelecionados = new ArrayList<>();
            listaBotoesAssentosSelecionados = new ArrayList<>();
            secaoListarIngressos.removeAll();
            this.atualizarPainel(1);

            //FAÇA COISA (COMPRA)
        }
        else if (e.getSource() == botaoEditarConsumiveis) {
            this.setVisible(false);
            janela.getPainelCategorias().setVisible(true);
        }
        else if (e.getSource() == botaoEditarIngressos) {
            if (janela.getPainelVendedor().filmeSelecionado == 0) {
                this.setVisible(false);
                janela.getPainelEscolherFilme().setVisible(true);
            }
            else {
                listaBotoesAssentosSelecionados = new ArrayList<>();
                janela.getPainelEscolherAssentos().botaoVoltar.setVisible(false);
                janela.getPainelEscolherAssentos().atualizarPainel(0);
                janela.getPainelEscolherAssentos().assentosSelecionados = new ArrayList<>();
                secaoListarIngressos.removeAll();
                this.setVisible(false);
                janela.getPainelEscolherAssentos().setVisible(true);
            }
        }
        else {
            precoBase = 30.0;
            Sessao sessao = janela.getPainelVendedor().vendedorLogado.getUnidade().getListaSessoes().get(((janela.getPainelVendedor().sessaoSelecionada + 5*(janela.getPainelVendedor().filmeSelecionado - 1)))-1);
            if (sessao.getSala().getEh3D()) {
                precoBase = (precoBase * 1.5);
            }
            if (sessao.getSala().getEhVIP()) {
                precoBase = (precoBase * 2);
            }
            double totalAux = 0.0;
            for (PainelEditarIngresso listaBotoesAssentosSelecionado : listaBotoesAssentosSelecionados) {
                if (listaBotoesAssentosSelecionado.botaoMeia.isSelected()) {
                    totalAux += 0.5 * (precoBase);
                } else {
                    totalAux += precoBase;
                }
            }
            janela.getPainelVendedor().setTotalCompra(totalAux + janela.getPainelVendedor().totalConsumiveis);
            janela.getPainelVendedor().setTotalIngressos(totalAux);
            totalIngressos.setText("Ingressos: R$" + totalAux);
            totalCompra.setText("Total: R$" + janela.getPainelVendedor().totalCompra);
            this.atualizarPainel(3);        }
    }

    @Override
    public void atualizarPainel(int codigo) {
        if (codigo == 0) {
            janela.getPainelVendedor().setTotalCompra(0.0);
            janela.getPainelVendedor().setTotalIngressos(0.0);
            janela.getPainelVendedor().setTotalConsumiveis(0.0);
            totalCompra.setText("Total: R$" + 0.0);
            totalIngressos.setText("Ingressos: R$" + 0.0);
            totalConsumiveis.setText("Consumíveis: R$" + 0.0);

        }
        else if (codigo == 1) {
            String textoTotal = "";
            double consumiveisTotal = 0.0;
            
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[0] != 0) {
                textoTotal += "Pipoca pequena:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[0] + "x\n";
            }
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[1] != 0) {
                textoTotal += "Pipoca média:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[1] + "x\n";
            }
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[2] != 0) {
                textoTotal += "Pipoca grande:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[2] + "x\n";
            }
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[3] != 0) {
                textoTotal += "Pipoca balde:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[3] + "x\n";
            }
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[4] != 0) {
                textoTotal += "Refrigerante:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[4] + "x\n";
            }
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[5] != 0) {
                textoTotal += "Água:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[5] + "x\n";
            }
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[6] != 0) {
                textoTotal += "Suco:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[6] + "x\n";
            }
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[7] != 0) {
                textoTotal += "Chocolate:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[7] + "x\n";
            }
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[8] != 0) {
                textoTotal += "Bala:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[8] + "x\n";
            }
            if (janela.getPainelVendedor().getQuantidadeConsumiveis()[9] != 0) {
                textoTotal += "Amendoim:        " + janela.getPainelVendedor().getQuantidadeConsumiveis()[9] + "x\n";
            }

            for (int i = 0; i < 10; i += 1) {
                consumiveisTotal += precosConsumiveis[i]*janela.getPainelVendedor().getQuantidadeConsumiveis()[i];
            }

            totalConsumiveis.setText("Consumíveis: R$" + consumiveisTotal);
            totalConsumiveis.setVisible(false);
            totalConsumiveis.setVisible(true);
            janela.getPainelVendedor().setTotalConsumiveis(consumiveisTotal);

            totalCompra.setText("Total: R$" + (janela.getPainelVendedor().totalIngressos + consumiveisTotal));
            totalConsumiveis.setVisible(false);
            totalConsumiveis.setVisible(true);
            janela.getPainelVendedor().setTotalCompra(consumiveisTotal +  janela.getPainelVendedor().totalIngressos);

            areaTextoConsumiveis.setText(textoTotal);
        }
        else if (codigo == 2) {
            for (int i = 0; i < janela.getPainelEscolherAssentos().assentosSelecionados.size(); i += 1) {
                PainelEditarIngresso aux = new PainelEditarIngresso(janela.getPainelVendedor().vendedorLogado.getUnidade().getListaSessoes().get((janela.getPainelVendedor().filmeSelecionado-1)*5).getFilme(), janela.getPainelEscolherAssentos().assentosSelecionados.get(i), this);
                secaoListarIngressos.add(aux);
                listaBotoesAssentosSelecionados.add(aux);
            }
        }
        else if (codigo == 3) {
            totalIngressos.setVisible(false);
            totalIngressos.setVisible(true);

            totalCompra.setVisible(false);
            totalCompra.setVisible(true);
        }
        else if (codigo == 4) {
            precoBase = 30.0;
            Sessao sessao = janela.getPainelVendedor().vendedorLogado.getUnidade().getListaSessoes().get(((janela.getPainelVendedor().sessaoSelecionada + 5*(janela.getPainelVendedor().filmeSelecionado - 1)))-1);
            // janela.getPainelEscolherAssentos().assentosSelecionados = new ArrayList<Integer>();
            if (sessao.getSala().getEh3D()) {
                precoBase = (precoBase * 1.5);
            }
            if (sessao.getSala().getEhVIP()) {
                precoBase = (precoBase * 2);
            }
            double totalAux = 0.0;
            for (PainelEditarIngresso listaBotoesAssentosSelecionado : listaBotoesAssentosSelecionados) {
                if (listaBotoesAssentosSelecionado.botaoMeia.isSelected()) {
                    totalAux += 0.5 * (precoBase);
                } else {
                    totalAux += precoBase;
                }
            }
            janela.getPainelVendedor().setTotalCompra(totalAux + janela.getPainelVendedor().totalConsumiveis);
            janela.getPainelVendedor().setTotalIngressos(totalAux);
            totalIngressos.setText("Ingressos: R$" + totalAux);
            totalCompra.setText("Total: R$" + janela.getPainelVendedor().totalCompra);
            this.atualizarPainel(3);
        }
    }
}
