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

        Veiculo v1 = new Veiculo("MZF-7260", "Chevrolet", "Novo", 2021);
        Veiculo v2 = new Veiculo("MRB-5329", "Honda", "Semi novo", 2010);
        Veiculo v3 = new Veiculo("HQV-7549", "Toyota", "Velho", 1989);
        Veiculo v4 = new Veiculo("BVL-9056", "Ford", "Novo",2020);

        ClientePF clientepf_1 = new ClientePF("Flavin do Pneu", "Rua dos Carijós", "43213068845",
                    "Ensino Superior", LocalDate.of(2000, 05, 25), "Feminino", "Classe Média", v1);
        ClientePJ clientepj_1 = new ClientePJ("Gasparzinho Lanches", "Avenida Almirante Maximiano Fonseca",
                            "46.068.425/0001-33", LocalDate.of(2018, 07, 22), 32);
        ClientePF clientepf_2 = new ClientePF("Shaolin Matador de Porco", "Rua da Imprensa", "78543216630",
                    "Ensino Médio", LocalDate.of(2003, 07, 02), "Masculino", "Classe Alta", v3);
        ClientePJ clientepj_2 = new ClientePJ("Planet Hemp", "Rua Serra de Bragança",
                            "28.745.587/0001-87", LocalDate.of(2017, 06, 21), 42);
        clientepj_1.adicionarVeiculo(v2);
        clientepj_2.adicionarVeiculo(v4);

        /* calculaPrecoSeguroCliente() é chamado sempre que algum cliente é cadastrado,
         * ou tem seus veículos/sinistros atualizados
         */
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

        seguradora.gerarSinistro(LocalDate.of(2023, 04, 24), "Rua dos Carijós", v1, clientepf_1);
        seguradora.gerarSinistro(LocalDate.of(2023, 04, 23), "Avenida Almirante Maximiano Fonseca", v3, clientepf_2);
        seguradora.gerarSinistro(LocalDate.of(2023, 04, 22), "Rua Serra de Bragança", v2, clientepj_1);
        seguradora.gerarSinistro(LocalDate.of(2023, 04, 21), "Rua Serra de Bragança", v4, clientepj_2);

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