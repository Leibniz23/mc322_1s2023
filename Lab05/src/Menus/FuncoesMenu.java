package Menus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import Classes_principais.*;

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
	
	public static MenuOpcoes lerOpcaoMenuExterno(Scanner scanner) {
    /* Recebe a variável de leitura, le o indice digitado e
     * retorna o menu externo correspondente
     */
		int opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite o código de uma opcao: ");
			opUsuario = scanner.nextInt();
			scanner.nextLine(); // só para limpar o buffer
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
			scanner.nextLine(); // para limpar o buffer
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	public static void executarOpcaoMenuExterno(MenuOpcoes op, Scanner scanner, List<Seguradora> seguradora) {
    /* Recebe o menu externo escolhido e a variável de leitura e a lista de seguradoras,
     * executando a função pedida ou chamando o submenu correspondente
     */

        switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op, scanner, seguradora);
				break;
			case GERAR_SINISTRO:
				System.out.println("Digite o nome da seguradora que registrará o sinistro:");
				String nomeSeguradora = scanner.nextLine();
				Seguradora segEscolhida=null;
				for (Seguradora seg : seguradora) {
					if (seg.getNome().equals(nomeSeguradora)) {
						segEscolhida = seg;
						break;
					}
				}
				System.out.println("Digite o CPF/CNPJ (só os números) do cliente responsável pelo sinistro:");
				String cadastro = scanner.nextLine();
				Cliente client = segEscolhida.encontrar_cliente(cadastro);
				System.out.println("Digite o endereço do sinistro:");
				String endereco = scanner.nextLine();
				System.out.println("Digite a placa do veículo do sinistro:");
				String placa = scanner.nextLine();
				Veiculo v = null;
				for (Veiculo Iv : client.getListaVeiculos()) { // o veículo com a placa digitada deve ja estar cadastrado no cliente
					if (Iv.getPlaca().equals(placa)) {
						v = Iv;
					}
				}

				if (v==null) {
					System.out.println("Placa não pertence a nenhum veículo do cliente");
					return;
				}
				segEscolhida.gerarSinistro(LocalDate.now(), endereco, v, client); // gerado com a data atual
				break;
			case TRANSFERIR_SEGURO:
				System.out.println("Digite o CPF/CNPJ do cliente que irá transferir o seguro (só os números):");
				String entrada1 = scanner.nextLine();
				Cliente c1=null, c2=null;
				int i;
				for (i=0; i<seguradora.size(); i++) {
					if (!seguradora.get(i).encontrar_cliente(entrada1).equals(null)) { // entra quando encontrar o cliente certo
						c1 = seguradora.get(i).encontrar_cliente(entrada1);
					}
				}
				System.out.println("Digite o CPF/CNPJ do cliente que irá receber o seguro (só os números e deve ser da mesma seguradora do anterior):");
				entrada1= scanner.nextLine();
				c2 = seguradora.get(i-1).encontrar_cliente(entrada1);
				seguradora.get(i-1).transferirSeguro(c1, c2); // i-1 porque o i é atualizado no for
				System.out.println("Transferência realizada!");
				break;
			case CALCULAR_RECEITA:
				System.out.println("Digite o nome da seguradora que deseja saber a receita: ");
				String entrada2 = scanner.nextLine();
				
				for (Seguradora seg : seguradora) {
					if (seg.getNome().equals(entrada2)) {
						System.out.println("\nA seguradora "+seg.getNome()+" tem receita líquida de "+
						Double.toString(seg.calculaReceita())+" reais.\n");
					}
				}
				break;
			case SAIR:
				break;
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, List<Seguradora> seguradora, Scanner scanner) {
    /* Recebe o submenu escolhido e a variável de leitura e a lista de seguradoras,
     * executando a função pedida
     */
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			System.out.println("Digite PF para cadastrar uma pessoa física ou PJ para cadastrar uma pessoa jurídica:");
			String subtipo = scanner.nextLine();
			if (subtipo.equals("PF")) {
				System.out.println("Digite o nome que deseja cadastrar:");
				String nome = scanner.nextLine();
				if (!Validacao.validarNome(nome)) {
					System.out.println("Nome inválido");
					return;
				}

				System.out.println("Digite o endereço que deseja cadastrar:");
				String endereco = scanner.nextLine();

				System.out.println("Digite o CPF que deseja cadastrar:");
				String cpf = scanner.nextLine().replaceAll("[^0-9]", ""); // só os números
				if (!Validacao.validarCPF(cpf)) {
					System.out.println("CPF inválido");
					return;
				}

				System.out.println("Digite o grau de educação que deseja cadastrar:");
				String educacao = scanner.nextLine();

				System.out.println("Digite a data de nascimento (no formato dd/mm/yy) que deseja cadastrar:");
				String nascimento = scanner.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
				LocalDate dataNascimento = LocalDate.parse(nascimento, formatter);

				System.out.println("Digite o gênero que deseja cadastrar:");
				String genero = scanner.nextLine();

				System.out.println("Digite a classe econômica que deseja cadastrar:");
				String classe = scanner.nextLine();

				ClientePF client = new ClientePF(nome, endereco, cpf, educacao, dataNascimento,
									genero, classe); // ele é criado sem veículos
				System.out.println("Digite o nome da seguradora que será cadastrado:");
				String segura = scanner.nextLine();
				for (Seguradora seg : seguradora) {
					if (seg.getNome().equals(segura)) {
						seg.cadastrarCliente(client);
						System.out.println("Cliente cadastrado com sucesso");
						return;
					}
				}

			} else if (subtipo.equals("PJ")) {
				System.out.println("Digite o nome que deseja cadastrar:");
				String nome = scanner.nextLine();
				if (!Validacao.validarNome(nome)) {
					System.out.println("Nome inválido");
					return;
				}
				System.out.println("Digite o endereço que deseja cadastrar:");
				String endereco = scanner.nextLine();

				System.out.println("Digite o CNPJ que deseja cadastrar:");
				String cnpj = scanner.nextLine().replaceAll("[^0-9]", ""); // só os números
				if (!Validacao.validarCNPJ(cnpj)) {
					System.out.println("CNPJ inválido");
					return;
				}

				System.out.println("Digite a data de fundação (no formato dd/mm/yy) que deseja cadastrar:");
				String fundacao = scanner.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
				LocalDate dataFundacao = LocalDate.parse(fundacao, formatter);
				
				System.out.println("Digite a quantidade de funcionários que deseja cadastrar:");
				int qtdeFuncionarios = scanner.nextInt();
				scanner.nextLine();

				ClientePJ pj = new ClientePJ(nome, endereco, cnpj, dataFundacao, qtdeFuncionarios); // ele é criado sem veículos
				System.out.println("Digite o nome da seguradora que será cadastrado:");
				String segura = scanner.nextLine();
				for (Seguradora seg : seguradora) {
					if (seg.getNome().equals(segura)) {
						seg.cadastrarCliente(pj);
						System.out.println("Cliente cadastrado com sucesso");
						return;
					}
				}
			}
			break;
		case CADASTRAR_VEICULO:
			System.out.println("Digite o CPF/CNPJ ao qual você deseja vincular o veículo:");
			String cadastro = scanner.nextLine();
			
			System.out.println("Digite a placa do veículo que deseja cadastrar:");
			String placa = scanner.nextLine();
			
			System.out.println("Digite a marca do veículo que deseja cadastrar:");
			String marca = scanner.nextLine();
			
			System.out.println("Digite o modelo do veículo que deseja cadastrar:");
			String modelo = scanner.nextLine();
			
			System.out.println("Digite o ano de fabricação do veículo que deseja cadastrar:");
			int anoFabricacao = scanner.nextInt();
			scanner.nextLine();

			Veiculo v = new Veiculo(placa, marca, modelo, anoFabricacao);

			for (Seguradora seg : seguradora) {
				if(!seg.encontrar_cliente(cadastro).equals(null)) { // se encontrar um cliente com esse cadastro
					seg.cadastrarVeiculo(v, cadastro);
					System.out.println("Veículo cadastrado com sucesso");
					return;
				}
			}
			break;
		case CADASTRAR_SEGURADORA:
			System.out.println("Digite o nome da seguradora deseja cadastrar:");
			String nome = scanner.nextLine();

			System.out.println("Digite o telefone da seguradora deseja cadastrar:");
			String telefone = scanner.nextLine();

			System.out.println("Digite o email da seguradora deseja cadastrar:");
			String email = scanner.nextLine();

			System.out.println("Digite o endereço da seguradora deseja cadastrar:");
			String endereco = scanner.nextLine();

			Seguradora newSeg = new Seguradora(nome, telefone, email, endereco);
			seguradora.add(newSeg);
			System.out.println("Seguradora cadastrada com sucesso");
			break;

		case LISTAR_CLIENTES_SEG:
			System.out.println("Listando clientes...\n");
			for (Seguradora seg : seguradora) {
				System.out.println("Seguradora: "+seg.getNome());
				System.out.println("###################################");
				for (Cliente c : seg.getListaClientes()) {
					System.out.println(c.toString());
				}
				System.out.println("###################################\n");
			}
			break;
		case LISTAR_SINISTROS_SEG:
			System.out.println("Listando sinistros...\n");
			for (Seguradora seg : seguradora) {
				System.out.println("Seguradora: "+seg.getNome());
				System.out.println("###################################");
				for (Sinistro s : seg.listarSinistros()) {
					System.out.println(s.toString());
				}
				System.out.println("###################################\n");
			}
			break;
		case LISTAR_SINISTROS_CLIENTE:
			System.out.println("Listando sinistros...\n");
			for (Seguradora seg : seguradora) {
				for (Cliente c : seg.getListaClientes()) {
					System.out.println("Cliente: "+c.getNome());
					System.out.println("###################################");
					seg.visualizarSinistro(c.getCadastro());
				}
				System.out.println("###################################\n");
			}
			break;
		case LISTAR_VEICULOS_CLIENTE:
			System.out.println("Listando veículos...\n");
			for (Seguradora seg : seguradora) {
				for (Cliente c : seg.getListaClientes()) {
					System.out.println("Cliente: "+c.getNome());
					System.out.println("###################################");
					c.visualizarVeiculos();
					System.out.println("###################################\n");
				}
			}
			break;
		case LISTAR_VEICULOS_SEG:
			System.out.println("Listando veículos...\n");
			for (Seguradora seg : seguradora) {
				System.out.println("Seguradora: "+seg.getNome());
				System.out.println("###################################");
				for (Cliente c : seg.getListaClientes()) {
					c.visualizarVeiculos();
					System.out.println();
				}
				System.out.println("###################################\n");
			}
			break;
		case EXCLUIR_CLIENTE:
			System.out.println("Digite o CPF/CNPJ do cliente que deseja excluir:");
			String cadastroExcluir = scanner.nextLine();
			for (Seguradora seg : seguradora) {
				if (seg.removerCliente(cadastroExcluir)) {
					break;
				}
			}
			System.out.println("Excluído com sucesso!");
			break;
		case EXCLUIR_VEICULO:
			System.out.println("Digite a placa do veículo que deseja excluir:");
			String placaExcluir = scanner.nextLine();
			for (Seguradora seg : seguradora) {
				if (seg.excluirVeiculo(placaExcluir)) {
					break;
				}
			}
			System.out.println("Excluído com sucesso!");
			break;
		case EXCLUIR_SINISTRO:
			System.out.println("Digite o ID do sinistro que deseja excluir");
			int id = scanner.nextInt();
			scanner.nextLine(); // limpando o buffer
			for (Seguradora seg : seguradora) {
				for (Sinistro sin : seg.listarSinistros()) {
					if (sin.getID()==id) {
						seg.removerSinistro(id);
						return;
					}
				}
			}
			System.out.println("Excluído com sucesso!");
			break;
		case VOLTAR:
			break;
		}
	}
	
	public static void executarSubmenu(MenuOpcoes op, Scanner scanner, List<Seguradora> seguradora) {
    /* Exibe o submenu, lê o indice escolhido e chama executarOpcaoSubMenu para
     * executar o comando
     */
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op, scanner);
			executarOpcaoSubMenu(opSubmenu, seguradora, scanner);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
}