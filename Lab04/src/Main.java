import java.time.LocalDate;
import java.util.Scanner;
import Classes_principais.*;
import Menus.*;

public class Main {
	public static void main(String[] args) {
		Seguradora seguradora = new Seguradora("Seguradora Fachada", "(19) 99999-9999",
                        "fachada@gmail.com", "Rua dos Alfeneiros");
        Veiculo v1 = new Veiculo("MZF-7260", "Chevrolet", "Novo", 2021);
        Veiculo v2 = new Veiculo("MRB-5329", "Honda", "Semi novo", 2010);
        Veiculo v3 = new Veiculo("HQV-7549", "Toyota", "Velho", 1989);
        Veiculo v4 = new Veiculo("BVL-9056", "Ford", "Novo",2020);
        ClientePF clientepf_1 = new ClientePF("Flavin do Pneu", "Rua dos Carijós", "43213068845",
                    "Ensino Superior", LocalDate.of(2000, 05, 25), "Feminino", "Classe Média", v1);
        ClientePJ clientepj_1 = new ClientePJ("Gasparzinho Lanches", "Avenida Almirante Maximiano Fonseca",
                            "46.068.425/0001-33", LocalDate.of(2018, 07, 22), v2);
        ClientePF clientepf_2 = new ClientePF("Shaolin Matador de Porco", "Rua da Imprensa", "78543216630",
                    "Ensino Médio", LocalDate.of(2003, 07, 02), "Masculino", "Classe Alta", v3);
        ClientePJ clientepj_2 = new ClientePJ("Planet Hemp", "Rua Serra de Bragança",
                            "28.745.587/0001-87", LocalDate.of(2017, 06, 21), v4);
        
        if (Validacao.validarCPF(clientepf_1.getCadastro())) {
            seguradora.cadastrarCliente(clientepf_1);
        }
        if (Validacao.validarCPF(clientepf_2.getCadastro())) {
            seguradora.cadastrarCliente(clientepf_2);
        }
        if (Validacao.validarCNPJ(clientepj_1.getCadastro())) {
            seguradora.cadastrarCliente(clientepj_1);
        }
        if (Validacao.validarCNPJ(clientepj_2.getCadastro())) {
            seguradora.cadastrarCliente(clientepj_2);
        }
		MenuOpcoes op;
		Scanner scanner = new Scanner(System.in);
		do {
			FuncoesMenu.exibirMenuExterno();
			op = FuncoesMenu.lerOpcaoMenuExterno(scanner);
			FuncoesMenu.executarOpcaoMenuExterno(op, scanner);
		}while(op != MenuOpcoes.SAIR);
		scanner.close();
		System.out.println("Até mais!");
	}

}
