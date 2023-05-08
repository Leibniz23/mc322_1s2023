package Menus;
import java.util.Scanner;

public class FuncoesMenu {
    public static void exibirMenuExterno() {
    /* Mostra as opções do menu principal */
		MenuOpcoes menuOpcoes[] = MenuOpcoes.values();
		System.out.println("Menu principal");
		for(MenuOpcoes op: menuOpcoes) {
			System.out.println(op.ordinal() + " - " + op.getDescricao());
		}
	}

	public static void exibirSubmenu(MenuOpcoes op) {
    /* Recebe o menu principal escolhido e mostra as opções
     * do submenu correspondente ao menu principal escolhido
     */
		SubmenuOpcoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i +" - " + submenu[i].getDescricao());
		}
	}
	
	//ler opções do menu externo
	public static MenuOpcoes lerOpcaoMenuExterno(Scanner scanner) {
    /* Recebe a variável de leitura, le o indice digitado e
     * retorna o menu externo correspondente
     */
		int opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > MenuOpcoes.values().length - 1);
		opUsuarioConst = MenuOpcoes.values()[opUsuario];
		return opUsuarioConst;
	}

	public static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op, Scanner scanner) {
    /*Le o indice digitado e retorna o submenu correspondente*/
        int opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	public static void executarOpcaoMenuExterno(MenuOpcoes op, Scanner scanner) {
    /* Recebe o menu externo escolhido e a variável de leitura,
     * executando a função pedida ou chamando o submenu correspondente
     */

        switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op, scanner);
				break;
			case GERAR_SINISTRO:
				System.out.println("Executar metodo gerar Sinistro");
				break;
			case TRANSFERIR_SEGURO:
				System.out.println("Executar metodo tranferir seguro");
				break;
			case CALCULAR_RECEITA:
				System.out.println("Executar metodo calcular receita");
				break;
			case SAIR:
				break;
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu) {
    /* Recebe o submenu escolhido e a variável de leitura e
     * executa a função pedida
     */
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			System.out.println("Chamar metodo cadastrar cliente");
			break;
		case CADASTRAR_VEICULO:
			System.out.println("Chamar metodo cadastrar veiculo");
			break;
		case CADASTRAR_SEGURADORA:
			System.out.println("Chamar metodo cadastrar seguradora");
			break;
		case LISTAR_CLIENTES:
			System.out.println("Chamar metodo listar clientes");
			break;
		case LISTAR_SINISTROS_SEG:
			System.out.println("Chamar metodo listar sinistros");
			break;
		case LISTAR_SINISTROS_CLIENTE:
			System.out.println("Chamar metodo listar sinistros");
			break;
		case LISTAR_VEICULOS_CLIENTE:
			System.out.println("Chamar metodo listar veiculos");
			break;
		case LISTAR_VEICULOS_SEG:
			System.out.println("Chamar metodo listar veiculos");
			break;
		case EXCLUIR_CLIENTE:
			System.out.println("Chamar metodo excluir cliente");
			break;
		case EXCLUIR_VEICULO:
			System.out.println("Chamar metodo excluir veiculo");
			break;
		case EXCLUIR_SINISTRO:
			System.out.println("Chamar metodo excluir sinistro");
			break;
		case VOLTAR:
			break;
		}
	}
	
	public static void executarSubmenu(MenuOpcoes op, Scanner scanner) {
    /* Exibe o submenu, lê o indice escolhido e chama executarOpcaoSubMenu para
     * executar o comando
     */
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op, scanner);
			executarOpcaoSubMenu(opSubmenu);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
}
