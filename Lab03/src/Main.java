import java.time.LocalDate;
import java.text.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String entrada;
        Seguradora seguradora = new Seguradora("teste", "teste", "teste", "teste");
        Veiculo v1 = new Veiculo("teste", "teste", "teste");
        Veiculo v2 = new Veiculo("teste", "teste", "teste");
        Veiculo v3 = new Veiculo("teste", "teste", "teste");
        Veiculo v4 = new Veiculo("teste", "teste", "teste");
        ClientePF clientepf_1 = new ClientePF("teste1", null, null, null, null, null, "97885998002", null, v1);
        ClientePJ clientepj_1 = new ClientePJ("teste1", null, "90.387.818/0001-00", null, v2);
        ClientePF clientepf_2 = new ClientePF("teste2", null, null, null, null, null, "97885998002", null, v3);
        ClientePJ clientepj_2 = new ClientePJ("teste2", null, "28.745.587/0001-87", null, v4);
        
        if (clientepf_1.validarCPF(clientepf_1.getCadastro())) {
            seguradora.cadastrarCliente(clientepf_1);
        }
        if (clientepf_2.validarCPF(clientepf_2.getCadastro())) {
            seguradora.cadastrarCliente(clientepf_1);
        }
        if (clientepj_1.validarCNPJ(clientepj_1.getCadastro())) {
            seguradora.cadastrarCliente(clientepj_1);
        }
        if (clientepj_2.validarCNPJ(clientepj_2.getCadastro())) {
            seguradora.cadastrarCliente(clientepj_2);
        }

        seguradora.gerarSinistro(null, null, null, clientepf_1);
        seguradora.gerarSinistro(null, null, null, clientepf_2);
        seguradora.gerarSinistro(null, null, null, clientepj_1);
        seguradora.gerarSinistro(null, null, null, clientepj_2);

        System.out.println("Acessando Seguradora Fachada...");
        System.out.println("Atualizando e validando clientes vinculados...");
        System.out.println("Imprimindo situação atual da Seguradora"); // to String

        System.out.println("------------------------------------------------");
        System.out.println(seguradora.toString());
        System.out.println("------------------------------------------------");

        System.out.println("Quais informações você deseja ver?");
        System.out.println("Digite PF para listar os clientes Pessoa Física"); // usar toString de clientes e/ou veiculos
        System.out.println("Digite PJ para listar os clientes Pessoa Jurídica"); // usar toString de clientes e/ou veiculos
        
        entrada = lerEntrada();
        System.out.println("------------------------------------------------");
        List<Cliente> clientes = seguradora.listarClientes(entrada);
        for (Cliente cList : clientes) {
            System.out.println(cList.toString()+"\n");
        }
        System.out.println("------------------------------------------------");

        System.out.println("Digite o CPF ou CNPJ do cliente para buscar os sinistros associados");
        entrada = lerEntrada();

        System.out.println("------------------------------------------------");
        seguradora.visualizarSinistro(entrada);
        System.out.println("------------------------------------------------");

        System.out.println("Escolha um cliente para remover e digite o CPF/CNPJ dele");
        for (Cliente cList : seguradora.getListaClientes()) {
            System.out.println(cList.getNome()+" :"+cList.getCadastro()+"\n"); // coloquei só o nome e o cadastro, e não toString, pra evitar poluição na tela
        }

        System.out.println();
        entrada = lerEntrada();
        System.out.println();

        System.out.println("Removido com sucesso!");
        System.out.println("Digite SINISTRO para listar os sinistros cadastrados");
        if (lerEntrada().equals("SINISTRO")) {
            for (Sinistro sList : seguradora.listarSinistros()) {
                System.out.println(sList.toString());
            }
        }

        System.out.println("Finalizando...");
        System.out.println("------------------------------------------------");     
    }

    public static String lerEntrada() {
        Scanner scan = new Scanner(System.in);
        String entrada = scan.next();
        scan.close();
        return entrada;
    }
}