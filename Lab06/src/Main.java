import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Classes_principais.*;
import Menus.*;

/* Alguns métodos criados são, eventualmente, pouco usados nas situações que deveriam ser, 
 * isso porque a utilização repetida deles me fez perceber que eram necessários, mas não
 * tive tempo de coloca-los onde já havia usado outra forma de obter o que queria, então
 * só segui e utilizei eles dali em diante. Também tomei algumas ideias que depois se provaram
 * problemáticas, mas ai já não havia mais tempo para mudar para a melhor forma possível, por isso
 * podem haver algumas "gambiarras", mas tentei deixar o mais explicado possível, no mais, peço compreensão...
 * Abraço!
 */

public class Main {
	public static void main(String[] args) {
        /* Instanciações, cadastros iniciais e validações */
		MenuOpcoes op;
		Scanner scanner = new Scanner(System.in);

		Seguradora seguradora = new Seguradora("Fachada Ltda", "(19) 99999-9999",
                        "fachada@gmail.com", "Rua dos Alfeneiros");

        Veiculo v0 = new Veiculo("MSF-7260", "Chevrolet", "Usado", 2012);
        Veiculo v1 = new Veiculo("MZF-7261", "Chevrolet", "Novo", 2021);
        Veiculo v2 = new Veiculo("MRB-5322", "Honda", "Semi novo", 2010);
        Veiculo v3 = new Veiculo("HQV-7543", "Toyota", "Velho", 1989);
        Veiculo v4 = new Veiculo("BVL-9054", "Ford", "Novo",2020);

        Condutor cond1 = new Condutor("98851098891", "Beatriz Pereira Lima" , "(11) 36042-7478", "Rua Tenente-Coronel Cardoso",
                                "wanli519@uorak.com", LocalDate.of(2000, 8, 25));
        Condutor cond2 = new Condutor("65697268890", "Tiago Barros Rocha", "(11) 80000-4816" , "Avenida Tocantins",
                                "elfidio7162@uorak.com", LocalDate.of(2001, 8, 23));

        ClientePF clientepf_0 = new ClientePF("Flavinha do Pneu", "(13) 55894-0026", "Rua dos Carijas", "ser1@gmail.com", "31245156250",
                            "Feminino", "Ensino Superior", LocalDate.of(1996, 05, 25));
                                
        ClientePF clientepf_1 = new ClientePF("Flavin do Pneu", "(11) 55677-0026", "Rua dos Carijós", "sergiy31@uorak.com", "43213068845",
                            "Feminino", "Ensino Superior", LocalDate.of(2000, 05, 25));
        ClientePJ clientepj_1 = new ClientePJ("Gasparzinho Lanches", "(11) 61964-8943", "Avenida Almirante Maximiano Fonseca", "wolfram5068@uorak.com",
                                "46.068.425/0001-33", LocalDate.of(2018, 07, 22), 32);
        ClientePF clientepf_2 = new ClientePF("Shaolin Matador de Porco", "(11) 35418-4448", "Rua da Imprensa", "acisclo4353@uorak.com", "78543216630",
                            "Masculino", "Ensino Médio", LocalDate.of(2003, 07, 02));
        ClientePJ clientepj_2 = new ClientePJ("Planet Hemp", "(11) 62086-2908", "Rua Serra de Bragança", "michal236@uorak.com",
                                "28.745.587/0001-87", LocalDate.of(2017, 06, 21), 42);
        
        clientepf_0.cadastrarVeiculo(v0); 
        clientepf_1.cadastrarVeiculo(v1);
        clientepf_2.cadastrarVeiculo(v2);

        Frota f1 = new Frota();
        f1.addVeiculo(v3);
        clientepj_1.cadastrarFrota(f1);
        Frota f2 = new Frota();
        f2.addVeiculo(v4);
        clientepj_2.cadastrarFrota(f2);
        

        if (Validacao.validarCPF(clientepf_0.getCadastro()) && 
        Validacao.validarNome(clientepf_0.getNome())) {
            seguradora.cadastrarCliente(clientepf_0);
        }
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
        
        int idSeguro0 = seguradora.gerarSeguro(LocalDate.of(2023, 04, 25), LocalDate.of(2024, 04, 25), clientepf_0, v0);
        int idSeguro1 = seguradora.gerarSeguro(LocalDate.of(2023, 04, 24), LocalDate.of(2024, 04, 24), clientepf_1, v1);
        int idSeguro2 = seguradora.gerarSeguro(LocalDate.of(2023, 04, 23), LocalDate.of(2024, 04, 23), clientepf_2, v2);
        int idSeguro3 = seguradora.gerarSeguro(LocalDate.of(2023, 04, 22), LocalDate.of(2024, 04, 22), clientepj_1, f1);
        int idSeguro4 = seguradora.gerarSeguro(LocalDate.of(2023, 04, 21), LocalDate.of(2024, 04, 21), clientepj_2, f2);

        /* Utilizando autorizarCondutor(*) */
        seguradora.encontrar_seguro(idSeguro1).autorizarCondutor(cond1);
        seguradora.encontrar_seguro(idSeguro2).autorizarCondutor(cond2);

        /*
         * O Sinistro de fato é criado no gerarSinistro() da classe Seguro,
         * cadastrarSinistro() é apenas um método auxiliar
         */
        seguradora.cadastrarSinistro(idSeguro0, LocalDate.of(2023, 03, 24), "Rua dos Carijas", cond1);
        seguradora.cadastrarSinistro(idSeguro1, LocalDate.of(2023, 04, 24), "Rua dos Carijós", cond1);
        seguradora.cadastrarSinistro(idSeguro2, LocalDate.of(2023, 05, 24), "Avenida Almirante Maximiano Fonseca", cond2);
        seguradora.cadastrarSinistro(idSeguro3, LocalDate.of(2023, 06, 24), "Rua da Imprensa", cond1);
        seguradora.cadastrarSinistro(idSeguro4, LocalDate.of(2023, 07, 24), "Rua Serra de Bragança", cond2);
        
        System.out.println("\nEntrando na seguradora " + seguradora.getNome() + "...\n");

        /* Utilizando getSegurosPorCliente(*) e listarClientes(*) */
        List<Cliente> ClientesPF = seguradora.listarClientes("PF");
        List<Cliente> ClientesPJ = seguradora.listarClientes("PJ");
        System.out.println("Imprimindo Clientes PF e Seguros associados...");
        for (Cliente c : ClientesPF) {
            System.out.println(c.toString());
            for (Seguro s : Seguradora.getSegurosPorCliente(c)) {
                System.out.println(s.toString());
            }
            System.out.println("###################################\n");
        }

        /* Utilizando getVeiculosPorFrota(*) */
        System.out.println("Imprimindo Clientes PJ e Seguros associados...");
        for (Cliente c : ClientesPJ) {
            System.out.println(c.toString());
            for (Seguro s : Seguradora.getSegurosPorCliente(c)) {
                System.out.println(s.toString());
                List<Veiculo> VeiculosDaFrota = c.getVeiculosPorFrota(s.getFrota().getCode());
                String id = Integer.toString(s.getFrota().getCode());
                System.out.println("A frota "+ id + " possui os seguintes veículos:\n");
                for(Veiculo v : VeiculosDaFrota) {
                    System.out.println(v.toString());  
                }
            }

            System.out.println("###################################\n");
        }

        /* Utilizando getSinistrosPorCliente(*) */
        System.out.println("Imprimindo Sinistros associados a cada cliente...");
        for (Cliente c : seguradora.getListaClientes()) {
            System.out.println(c.toString());
            for (Sinistro s : Seguradora.getSinistrosPorCliente(c)) {
                System.out.println(s.toString());
            }
            System.out.println("###################################\n");
        }

        /* Utilizando transferirSeguro(*) e calculaReceita() */
        seguradora.transferirSeguro(clientepf_1, clientepf_0);
        System.out.println("O cliente "+clientepf_0.getNome()+" transferiu seus seguros para "+clientepf_1.getNome()+"\n");
        System.out.println("Nova situação de ClientesPF:\n");
        for (Cliente c : ClientesPF) {
            System.out.println(c.toString());
            for (Seguro s : Seguradora.getSegurosPorCliente(c)) {
                System.out.println(s.toString());
            }
            System.out.println("###################################\n");
        }

        String receita = Double.toString(seguradora.calculaReceita());
        System.out.println("A receita atual da seguradora é "+receita+" reais.\n");

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