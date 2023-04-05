public class Main {
    public static void main(String[] args) {
        Cliente c_certo = new Cliente("Matheus", "432.130.688-45",
        "01/08/2003", 19, "Rua Saturnino de Brito");

        Cliente c_errado = new Cliente("Matheus", "234.031.886-45",
        "01/08/2003", 19, "Rua Saturnino de Brito");

        Seguradora seg = new Seguradora();
        
        Veiculo v = new Veiculo();

        Sinistro sin1 = new Sinistro();

        Sinistro sin2 = new Sinistro();

        System.out.println(c_certo.toString()); // testa todos os gets de Cliente

        /**
         * Eu coloquei o if e o println do validarCpf() na main, e não
         * dentro do próprio método, porque nesse caso a informação de true ou false
         * seria inutilizada
         */
        if (c_certo.validarCPF(c_certo.getCpf())) { // valida o CPF do primeiro cliente, que é válido
            System.out.println(c_certo.getCpf()+" é um CPF válido\n");
        } else {
            System.out.println(c_certo.getCpf()+" não é um CPF válido\n");
        }

        if (c_errado.validarCPF(c_errado.getCpf())) { // valida o CPF do segundo cliente, que é inválido
            System.out.println(c_errado.getCpf()+" é um CPF válido\n");
        } else {
            System.out.println(c_errado.getCpf()+" não é um CPF válido\n");
        }

        c_certo.setNome("Leonardo");
        c_certo.setCpf("444.444.444-00");
        c_certo.setDataNascimento("31/08/2003");
        c_certo.setEndereco("Rua dos Alfeneiros");
        c_certo.setIdade(20);

        System.out.println(c_certo.toString()); // testa se os sets acima funcionaram

        if (c_certo.validarCPF(c_certo.getCpf())) { // valida o novamente o CPF do primeiro cliente, que agora é válido
            System.out.println(c_certo.getCpf()+" é um CPF válido\n");
        } else {
            System.out.println(c_certo.getCpf()+" não é um CPF válido\n");
        }

        /**
         * Utilizei o set para inicializar os objetos seg, v, sin1 e sin2 
         * para testar os métodos set, mesmo tendo criado um construtor que poderia inicializa-los
         */
        seg.setNome("Seguradora Fachada");
        seg.setTelefone("(19) 95427-2356");
        seg.setEmail("aNgElOfThEnIgHt@gmail.com");
        seg.setEndereco("Avenida Tenebris");
        System.out.println("Nome: " + seg.getNome() + "\n" +
                            "Telefone: " + seg.getTelefone() + "\n" +
                            "Email: " + seg.getEmail() + "\n" +
                            "Endereco: " + seg.getEndereco() + "\n");

        v.setPlaca("BRA2E19");
        v.setMarca("Honda");
        v.setModelo("Civic");
        System.out.println("Placa: " + v.getPlaca() + "\n" +
                            "Marca: " + v.getMarca() + "\n" +
                            "Modelo: " + v.getModelo() + "\n");

        /**
         * Como o ID é dado por um contador estático e comum a todos
         * os objetos da classe, ele será sempre diferente
         */
        sin1.setData("11/02/2009");
        sin1.setEndereco("Rua Santa Menefreda");
        System.out.println("ID: " + sin1.getId() + "\n" +
                            "Data: " + sin1.getData() + "\n" +
                            "Endereço: " + sin1.getEndereco() + "\n");

        sin2.setData("25/07/2020");
        sin2.setEndereco("Rua Café's Pizzaria");
        System.out.println("ID: " + sin2.getId() + "\n" +
                            "Data: " + sin2.getData() + "\n" +
                            "Endereço: " + sin2.getEndereco());
    }
}
