public class Main {
    public static void main(String[] args) {
        Cliente client = new Cliente("Matheus", "4321.-65", "01/05/98", 24, "rua alan turing");
        // System.out.println(client.toString());
        Cliente client2 = new Cliente("Matheus", "1234.-65", "01/05/98", 24, "rua alan turing");


        System.out.println(client.validarCPF(client.getCpf()));
        System.out.println(client2.validarCPF(client2.getCpf()));

    }
}
