
import java.time.LocalDate;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String entrada;
        Scanner scan = new Scanner(System.in);

        Seguradora seguradora = new Seguradora("Seguradora Fachada", "(19) 99999-9999",
                        "fachada@gmail.com", "Rua dos Alfeneiros");
        Veiculo v1 = new Veiculo("MZF-7260", "Chevrolet", "Novo");
        Veiculo v2 = new Veiculo("MRB-5329", "Honda", "Semi novo");
        Veiculo v3 = new Veiculo("HQV-7549", "Toyota", "Velho");
        Veiculo v4 = new Veiculo("BVL-9056", "Ford", "Novo");
        ClientePF clientepf_1 = new ClientePF("Flavin do Pneu", "Rua dos Carijós", "43213068845",
                    "Ensino Superior", LocalDate.of(2000, 05, 25), "Feminino", "Classe Média", v1);
        ClientePJ clientepj_1 = new ClientePJ("Gasparzinho Lanches", "Avenida Almirante Maximiano Fonseca",
                            "46.068.425/0001-33", LocalDate.of(2018, 07, 22), v2);
        ClientePF clientepf_2 = new ClientePF("Shaolin Matador de Porco", "Rua da Imprensa", "78543216630",
                    "Ensino Médio", LocalDate.of(2003, 07, 02), "Masculino", "Classe Alta", v3);
        ClientePJ clientepj_2 = new ClientePJ("Planet Hemp", "Rua Serra de Bragança",
                            "28.745.587/0001-87", LocalDate.of(2017, 06, 21), v4);
        
        if (ClientePF.validarCPF(clientepf_1.getCadastro())) {
            seguradora.cadastrarCliente(clientepf_1);
        }
        if (ClientePF.validarCPF(clientepf_2.getCadastro())) {
            seguradora.cadastrarCliente(clientepf_2);
        }
        if (ClientePJ.validarCNPJ(clientepj_1.getCadastro())) {
            seguradora.cadastrarCliente(clientepj_1);
        }
        if (ClientePJ.validarCNPJ(clientepj_2.getCadastro())) {
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
        System.out.println("Digite PF para listar os clientes Pessoa Física");
        System.out.println("Digite PJ para listar os clientes Pessoa Jurídica");
        
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
        if (ClientePF.validarCPF(entrada) || ClientePJ.validarCNPJ(entrada)) {
            seguradora.visualizarSinistro(entrada);
        } else {
            System.out.println("Cadastro inválido");
        }
        System.out.println("------------------------------------------------");

        System.out.println("Escolha um cliente para remover e digite o CPF/CNPJ dele");
        System.out.println();
        for (Cliente cList : seguradora.getListaClientes()) {
            System.out.println(cList.getNome()+": "+cList.getCadastro()); // coloquei só o nome e o cadastro (e não toString) pra evitar poluição na tela
        }
        System.out.println();
        
        entrada = lerEntrada(scan);
        System.out.println();
        boolean existe = seguradora.removerCliente(entrada);
        if (!existe) {
            System.out.println("Esse cliente não está cadastrado");
        } else {
            System.out.println("Removido com sucesso!");
        }

        System.out.println("Digite SINISTROS para listar os sinistros cadastrados");
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