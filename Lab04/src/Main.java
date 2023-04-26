import java.util.*;
import java.util.Scanner;

import Classes_principais.Cliente;
import Classes_principais.ClientePF;
import Classes_principais.ClientePJ;
import Classes_principais.Seguradora;
import Classes_principais.Sinistro;
import Classes_principais.Veiculo;

public class Main {
    public static void main(String[] args) {
        String entrada;
        Scanner scan = new Scanner(System.in);
        Seguradora seguradora = new Seguradora("Seguradora Fachada", "(19) 99999-9999",
                        "fachada@gmail.com", "Rua dos Alfeneiros");
        Veiculo v1 = new Veiculo("MZF-7260", "Chevrolet", "Novo", 2001);
        Veiculo v2 = new Veiculo("MRB-5329", "Honda", "Semi novo", 2002);
        Veiculo v3 = new Veiculo("HQV-7549", "Toyota", "Velho", 2003);
        Veiculo v4 = new Veiculo("BVL-9056", "Ford", "Novo", 2004);
        ClientePF clientepf_1 = new ClientePF("Flavin do Pneu", "Rua dos Carijós", null, "Ensino Superior",
                        "Feminino", "Classe Média", "43213068845", new Date(), v1);
        ClientePJ clientepj_1 = new ClientePJ("Gasparzinho Lanches", "Avenida Almirante Maximiano Fonseca", "46.068.425/0001-33", new Date(), v2);
        ClientePF clientepf_2 = new ClientePF("Shaolin Matador de Porco", "Rua da Imprensa", null, "Ensino Médio",
                        "Masculino", "Classe Alta", "78543216630", null, v3);
        ClientePJ clientepj_2 = new ClientePJ("Planet Hemp", "Rua Serra de Bragança", "28.745.587/0001-87", new Date(), v4);
        
        if (clientepf_1.validarCPF(clientepf_1.getCadastro())) {
            seguradora.cadastrarCliente(clientepf_1);
        }
        if (clientepf_2.validarCPF(clientepf_2.getCadastro())) {
            seguradora.cadastrarCliente(clientepf_2);
        }
        if (clientepj_1.validarCNPJ(clientepj_1.getCadastro())) {
            seguradora.cadastrarCliente(clientepj_1);
        }
        if (clientepj_2.validarCNPJ(clientepj_2.getCadastro())) {
            seguradora.cadastrarCliente(clientepj_2);
        } 

        seguradora.gerarSinistro("24/04/2023", "Rua dos Carijós", v1, clientepf_1);
        seguradora.gerarSinistro("23/04/2033", "Avenida Almirante Maximiano Fonseca", v2, clientepf_2);
        seguradora.gerarSinistro("22/04/2033", "Rua Serra de Bragança", v3, clientepj_1);
        seguradora.gerarSinistro("21/04/2023", "Rua Serra de Bragança", v4, clientepj_2);

        System.out.println("Acessando Seguradora Fachada...");
        System.out.println("Atualizando e validando clientes vinculados...");
        System.out.println("Imprimindo situação atual da Seguradora");

        System.out.println("------------------------------------------------");
        System.out.println(seguradora.toString());
        System.out.println("------------------------------------------------");

        System.out.println("Quais informações você deseja ver?");
        System.out.println("Digite PF para listar os clientes Pessoa Física"); // usar toString de clientes e/ou veiculos
        System.out.println("Digite PJ para listar os clientes Pessoa Jurídica"); // usar toString de clientes e/ou veiculos
        
        entrada = lerEntrada(scan);
        System.out.println("------------------------------------------------");
        List<Cliente> clientes = seguradora.listarClientes(entrada);
        for (Cliente cList : clientes) {
            System.out.println(cList.toString());
        }
        System.out.println("------------------------------------------------");

        System.out.println("Digite o CPF ou CNPJ do cliente para buscar os sinistros associados");
        entrada = lerEntrada(scan);

        System.out.println("------------------------------------------------");
        seguradora.visualizarSinistro(entrada);
        System.out.println("------------------------------------------------");

        System.out.println("Escolha um cliente para remover e digite o CPF/CNPJ dele");
        System.out.println();
        for (Cliente cList : seguradora.getListaClientes()) {
            System.out.println(cList.getNome()+": "+cList.getCadastro()); // coloquei só o nome e o cadastro (e não toString) pra evitar poluição na tela
        }

        System.out.println();
        entrada = lerEntrada(scan);
        System.out.println();

        System.out.println("Removido com sucesso!");
        System.out.println("Digite SINISTRO para listar os sinistros cadastrados");
        System.out.println("------------------------------------------------");
        if (lerEntrada(scan).equals("SINISTROS")) {
            for (Sinistro sList : seguradora.listarSinistros()) {
                System.out.println(sList.toString());
            }
        }
        System.out.println("------------------------------------------------");     

        System.out.println("Finalizando...");
    }

    public static String lerEntrada(Scanner scan) {
        String entrada = scan.nextLine();
        return entrada;
    }
}