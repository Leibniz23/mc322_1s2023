import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Classes_principais.*;
import Menus.*;

/* Coloquei comentários nas coisas que considerei confusas e mais difíceis de entender,
 * mas não em métodos que eu julguei auto explicativos ou coisas que achei triviais. Caso
 * precise de mais comentários e/ou alguma melhoria na documentação, pode mandar no feedback
 * da tarefa e melhorarei no próximo lab. Abraço!
 */

public class Main {
	public static void main(String[] args) {
        
        /* Declarações e validações */
		MenuOpcoes op;
		Scanner scanner = new Scanner(System.in);

		Seguradora seguradora = new Seguradora("Fachada Ltda", "(19) 99999-9999",
                        "fachada@gmail.com", "Rua dos Alfeneiros");

        Veiculo v1 = new Veiculo("MZF-7261", "Chevrolet", "Novo", 2021);
        Veiculo v2 = new Veiculo("MRB-5322", "Honda", "Semi novo", 2010);
        Veiculo v3 = new Veiculo("HQV-7543", "Toyota", "Velho", 1989);
        Veiculo v4 = new Veiculo("BVL-9054", "Ford", "Novo",2020);

        Condutor cond1 = new Condutor("98851098891", "Beatriz Pereira Lima" , "(11) 36042-7478", "Rua Tenente-Coronel Cardoso",
                                "wanli519@uorak.com", LocalDate.of(2000, 8, 25));
        Condutor cond2 = new Condutor("65697268890", "Tiago Barros Rocha", "(11) 80000-4816" , "Avenida Tocantins",
                                "elfidio7162@uorak.com", LocalDate.of(2001, 8, 23));

        Frota f1 = new Frota();
        f1.addVeiculo(v3);
        Frota f2 = new Frota();
        f1.addVeiculo(v4);

        ClientePF clientepf_1 = new ClientePF("Flavin do Pneu", "(11) 55677-0026", "Rua dos Carijós", "sergiy31@uorak.com", "43213068845",
                            "Feminino", "Ensino Superior", LocalDate.of(2000, 05, 25));
        ClientePJ clientepj_1 = new ClientePJ("Gasparzinho Lanches", "(11) 61964-8943", "Avenida Almirante Maximiano Fonseca", "wolfram5068@uorak.com",
                            "46.068.425/0001-33", LocalDate.of(2018, 07, 22), 32);
        ClientePF clientepf_2 = new ClientePF("Shaolin Matador de Porco", "(11) 35418-4448", "Rua da Imprensa", "acisclo4353@uorak.com", "78543216630",
                            "Masculino", "Ensino Médio", LocalDate.of(2003, 07, 02));
        ClientePJ clientepj_2 = new ClientePJ("Planet Hemp", "(11) 62086-2908", "Rua Serra de Bragança", "michal236@uorak.com",
                            "28.745.587/0001-87", LocalDate.of(2017, 06, 21), 42);

        if (Validacao.validarCPF(clientepf_1.getCadastro()) && 
            Validacao.validarNome(clientepf_1.getNome())) {
            seguradora.cadastrarCliente(clientepf_1);
        }
        if (Validacao.validarCPF(clientepf_2.getCadastro()) &&
            Validacao.validarNome(clientepf_2.getNome())) {
            seguradora.cadastrarCliente(clientepf_2);
        }
        if (Validacao.validarCNPJ(clientepj_1.getCadastro()) &&
            Validacao.validarNome(clientepj_1.getNome())) {
            seguradora.cadastrarCliente(clientepj_1);
        }
        if (Validacao.validarCNPJ(clientepj_2.getCadastro()) &&
            Validacao.validarNome(clientepj_2.getNome())) {
            seguradora.cadastrarCliente(clientepj_2);
        }

        clientepf_1.cadastrarVeiculo(v1);
        clientepf_2.cadastrarVeiculo(v2);

        seguradora.gerarSeguro(LocalDate.of(2023, 04, 24), LocalDate.of(2024, 04, 24), clientepf_1, v1);
        seguradora.gerarSeguro(LocalDate.of(2023, 04, 23), LocalDate.of(2024, 04, 23), clientepf_2, v2);
        seguradora.gerarSeguro(LocalDate.of(2023, 04, 22), LocalDate.of(2024, 04, 22), clientepj_1, f1);
        seguradora.gerarSeguro(LocalDate.of(2023, 04, 21), LocalDate.of(2024, 04, 21), clientepj_2, f2);

        gerar

        List<Cliente> ClientesPF = seguradora.listarClientes("PF");
        List<Cliente> ClientesPJ = seguradora.listarClientes("PJ");
        System.out.println("Imprimindo Clientes PF e sinistros associados...");
        for (Cliente c : ClientesPF) {
            System.out.println(c.toString());
            seguradora.visualizarSinistro(c.getCadastro());
            System.out.println("###################################\n");
        }
        System.out.println("Imprimindo Clientes PJ e sinistros associados...");
        for (Cliente c : ClientesPJ) {
            System.out.println(c.toString());
            seguradora.visualizarSinistro(c.getCadastro());
            System.out.println("###################################\n");
        }

        System.out.println("Imprimindo Sinistros...");
        List<Sinistro> sinistros = seguradora.listarSinistros();
        for (Sinistro sin : sinistros) {
            System.out.println(sin.toString());
            System.out.println("###################################\n");
        }

        String receita = Double.toString(seguradora.calculaReceita());
        System.out.println("A receita atual da seguradora é "+receita+" reais");

        /*Execução do menu interativo */
        List<Seguradora> seguradoras = new ArrayList<Seguradora>();
        seguradoras.add(seguradora);
		do {
			FuncoesMenu.exibirMenuExterno();
			op = FuncoesMenu.lerOpcaoMenuExterno(scanner);
			FuncoesMenu.executarOpcaoMenuExterno(op, scanner, seguradoras);
		}while(op != MenuOpcoes.SAIR);
		scanner.close();
		System.out.println("Até mais!");
	}

}