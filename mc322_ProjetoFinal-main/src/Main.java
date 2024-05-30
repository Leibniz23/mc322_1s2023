public class Main {
    public static void main(String[] args) {
        Entidade entity = new Entidade("Fachada's Cinema", "Rua Santa Menefreda", "(11) 91102-0413",
                "fachada@gmail.com");
        Unidade u1 = new Unidade("07.305.383/0001-55", "Avenida Hill Valley", "fachadinha@gmail.com",entity);
        Compra compra = new Compra();
        compra.imprimir();
        u1.imprimir();
        entity.imprimir();
        Gerente admin = new Gerente(u1, "admin", "admin", 2500, "admin", "admin");
        u1.getListaFuncionarios().add(admin);
        u1.inicializarExemplo();
        entity.getListaUnidades().add(u1);
        admin.inicializarConsumiveis();


        new Janela(entity,"Teste");
    }
}