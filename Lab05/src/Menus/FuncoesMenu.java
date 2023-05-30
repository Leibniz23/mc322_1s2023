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
				/* Recebe os dados necessários para gerar o sinistro e o gera,
				 * já cadastrando ele no seguro indicado na seguradora indicada
				*/
				System.out.println("Digite o nome da seguradora que registrará o sinistro:");
				String nomeSeguradora = scanner.nextLine();
				Seguradora segEscolhida=null;
				for (Seguradora seg : seguradora) {
					if (seg.getNome().equals(nomeSeguradora)) {
						segEscolhida = seg;
						break;
					}
				}
				System.out.println("Digite o ID do seguro que registrará o sinistro:");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Digite a data (dd/MM/yy) do sinistro:");
				String dataString = scanner.nextLine();
				LocalDate data = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yy"));
				System.out.println("Digite o endereço do sinistro:");
				String endereco = scanner.nextLine();
				System.out.println("Digite a CPF do condutor responsável pelo sinistro (já deve estar cadastrado no sistema e o CPF são apenas número):");
				String cpf = scanner.nextLine();
				Condutor c = null;
				for (Condutor Ic : Condutor.getListaCondutores()) {
					if (Ic.getCPF().equals(cpf)) {
						c = Ic;
					}
				}

				if (c==null) {
					System.out.println("Condutor não cadastrado!");
					return;
				}
				segEscolhida.cadastrarSinistro(id, data, endereco, c); // gerado com a data atual
				System.out.println("Sinistro gerado");
				break;
			case ATUALIZAR_FROTA:
				/* Pergunta qual função, entre adicionar e remover veículo, vai usar,
				 * pede o necessário e faz a alteração (deletar uma frota usa o mesmo método,
				 * como foi pedido, mas no submenu Excluir frota)
				 */
				System.out.println("Digite o code da frota que você deseja atualizar:");
				int code = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Digite o CNPJ do cliente dono dessa frota:");
				String cnpj = scanner.nextLine().replaceAll("[^0-9]", "");
				ClientePJ cliente = null;
				for (Seguradora seg : seguradora) {
					cliente = seg.encontrar_clientePJ(cnpj);
					if (!Validacao.validarExistencia(cliente)) {
						break;
					}
				}
				System.out.println("Digite ADD para adicionar um veículo na frota, ou REM para remover um veículo da frota");
				String acao = scanner.nextLine();
				if (acao.equals("ADD")) {
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
					cliente.atualizarFrota(code, v);
					System.out.println("Atualização realizada");
				} else if (acao.equals("REM")) {
					System.out.println("Digite a placa do veículo que deseja cadastrar:");
					String placa = scanner.nextLine();
					cliente.atualizarFrota(code, placa);
					System.out.println("Atualização realizada");
				}
				break;
			case AUTORIZAR_CONDUTOR:
			/* Pede o necessário para encontrar o condutor e o seguro e
			 * autoriza o condutor naquele seguro
			 */
				System.out.println("Digite o CPF do condutor (já cadastrado) que deseja autorizar:");
				String cpfCond = scanner.nextLine();
				Condutor cond = Condutor.encontrarCondutor(cpfCond);
				System.out.println("Digite o ID do seguro que deseja autorizar esse condutor:");
				int idSeguro  = scanner.nextInt();
				Seguro seguro=null;
				for (Seguradora seg : seguradora) {
					seguro = seg.encontrar_seguro(idSeguro);
					if (!Validacao.validarExistencia(seguro)) {
						break;
					}
				}
				seguro.autorizarCondutor(cond);
				System.out.println("Autorizado com sucesso");
				break;
			case DESAUTORIZAR_CONDUTOR:
			/* Pede o necessário para encontrar o condutor e o seguro e
			 * autoriza o condutor naquele seguro
			 */
				System.out.println("Digite o CPF do condutor (já cadastrado) que deseja desautorizar:");
				String cpfCondRemove= scanner.nextLine();
				System.out.println("Digite o ID do seguro que deseja desautorizar esse condutor:");
				int idSeguroRemove = scanner.nextInt();
				Seguro seguroRemove = null;
				for (Seguradora seg : seguradora) {
					seguroRemove = seg.encontrar_seguro(idSeguroRemove);
					if (!Validacao.validarExistencia(seguroRemove)) {
						break;
					}
				}
				seguroRemove.desautorizarCondutor(cpfCondRemove);
				System.out.println("Desautorizado com sucesso");
				break;
			case CALCULAR_RECEITA: // Soma todos os valores de seguro
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
			/* Pede o necessário para criar um cliente, cria ele e cadastra em alguma seguradora */
			System.out.println("Digite PF para cadastrar uma pessoa física ou PJ para cadastrar uma pessoa jurídica:");
			String subtipo = scanner.nextLine();
			if (subtipo.equals("PF")) {
				/* Caso de cadastro de clientePF */
				System.out.println("Digite o nome que deseja cadastrar:");
				String nome = scanner.nextLine();
				if (!Validacao.validarNome(nome)) {
					System.out.println("Nome inválido");
					return;
				}

				System.out.println("Digite o telefone que deseja cadastrar:");
				String telefone = scanner.nextLine();

				System.out.println("Digite o endereço que deseja cadastrar:");
				String endereco = scanner.nextLine();

				System.out.println("Digite o email que deseja cadastrar:");
				String email = scanner.nextLine();

				System.out.println("Digite o CPF que deseja cadastrar:");
				String cpf = scanner.nextLine().replaceAll("[^0-9]", ""); // só os números
				if (!Validacao.validarCPF(cpf)) {
					System.out.println("CPF inválido");
					return;
				}

				System.out.println("Digite a data de nascimento (no formato dd/mm/yy) que deseja cadastrar:");
				String nascimento = scanner.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
				LocalDate dataNascimento = LocalDate.parse(nascimento, formatter);

				System.out.println("Digite o gênero que deseja cadastrar:");
				String genero = scanner.nextLine();

				System.out.println("Digite o grau de educação que deseja cadastrar:");
				String educacao = scanner.nextLine();

				ClientePF client = new ClientePF(nome, telefone, endereco, email, cpf, genero,
								educacao, dataNascimento); // ele é criado sem veículos nem seguros
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
				/* Caso de cadastro de clientePJ */
				System.out.println("Digite o nome que deseja cadastrar:");
				String nome = scanner.nextLine();
				if (!Validacao.validarNome(nome)) {
					System.out.println("Nome inválido");
					return;
				}

				System.out.println("Digite o telefone que deseja cadastrar:");
				String telefone = scanner.nextLine();

				System.out.println("Digite o endereço que deseja cadastrar:");
				String endereco = scanner.nextLine();

				System.out.println("Digite o email que deseja cadastrar:");
				String email = scanner.nextLine();

				System.out.println("Digite o CNPJ que deseja cadastrar:");
				String cnpj = scanner.nextLine().replaceAll("[^0-9]", ""); // só os números por segurança
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

				ClientePJ pj = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, qtdeFuncionarios); // ele é criado sem frotas
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
			/* Pede o necessário para criar um veículo e o cadastra em um cliente */
			System.out.println("Digite o CPF ao qual você deseja vincular o veículo:");
			String cadastroCpf = scanner.nextLine();
			
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
				if(!seg.encontrar_cliente(cadastroCpf).equals(null)) { // se encontrar um cliente com esse cadastro
					seg.encontrar_cliente(cadastroCpf).cadastrarVeiculo(v);
					System.out.println("Veículo cadastrado com sucesso");
					return;
				}
			}
			break;
		case CADASTRAR_FROTA:
		/* Pede o necessário para criar um veículo e o cadastra em um cliente */
			System.out.println("Digite o CNPJ ao qual você deseja vincular essa frota:");
			String cadastroCnpj = scanner.nextLine().replaceAll("[^0-9]", "");
			ClientePJ clientePJ = null;
			for (Seguradora seg : seguradora) {
				clientePJ = seg.encontrar_clientePJ(cadastroCnpj);
				if (!Validacao.validarExistencia(clientePJ)) {
					break;
				}
			}
			if (!Validacao.validarExistencia(clientePJ)) { // se não acho dentro do for então não existe esse cliente
				System.out.println("CNPJ não cadastrado");
				return;
			}
			Frota novaFrota = new Frota();
			clientePJ.cadastrarFrota(novaFrota);
			break;
		case CADASTRAR_CONDUTOR:
		/* Pede o necessário para criar um veículo e o cadastra em um cliente */
			System.out.println("Digite o CPF do condutor que deseja cadastrar:");
			String cpf = scanner.nextLine().replaceAll("[^0-9]", ""); // só os números
			if (!Validacao.validarCPF(cpf) || Validacao.CondutorCadastrado(cpf)) {
				System.out.println("CPF inválido ou já cadastrado");
				return;
			}
			
			System.out.println("Digite o nome do condutor que deseja cadastrar:");
			String nome = scanner.nextLine();
			if (!Validacao.validarNome(nome)) {
				System.out.println("Nome inválido");
				return;
			}
			
			System.out.println("Digite o telefone do condutor que deseja cadastrar:");
			String telefone = scanner.nextLine();

			System.out.println("Digite o endereço do condutor que deseja cadastrar:");
			String endereco = scanner.nextLine();

			System.out.println("Digite o email do condutor que deseja cadastrar:");
			String email = scanner.nextLine();

			System.out.println("Digite a data de nascimento (no formato dd/mm/yy) que deseja cadastrar:");
			String nascimento = scanner.nextLine();
			LocalDate dataNascimento = LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd/MM/yy"));

			new Condutor(cpf, nome, telefone, endereco, email, dataNascimento);
			System.out.println("Condutor registrado com sucesso");
			break;
		case CADASTRAR_SEGURO:
			/* Pede o necessário para cadastrar algum seguro, seja PF ou PJ */
			System.out.println("Digite PF para cadastrar um seguro de pessoa física ou PJ para cadastrar um seguro de pessoa jurídica:");
			String tipo = scanner.nextLine();

			System.out.println("Digite a data (dd/MM/yy) de inicio do seguro:");
			String inicio = scanner.nextLine();
			LocalDate dataInicio= LocalDate.parse(inicio, DateTimeFormatter.ofPattern("dd/MM/yy"));
			
			System.out.println("Digite a data (dd/MM/yyyy) de término do seguro:");
			String fim = scanner.nextLine();
			LocalDate dataFim= LocalDate.parse(fim, DateTimeFormatter.ofPattern("dd/MM/yy"));
			
			System.out.println("Digite o nome da seguradora em que você irá registrar esse seguro:");
			String nomeSeg = scanner.nextLine();
			Seguradora segAtual = null;
			for (Seguradora s : seguradora) {
				if (s.getNome().equals(nomeSeg)) {
					segAtual = s;
				}
			}

			if (tipo.equals("PF")) {
				/* Caso de um seguroPF */
				System.out.println("Digite o CPF do cliente responsável por esse seguro:");
				String cpfSeguro = scanner.nextLine();
				ClientePF clientepf = segAtual.encontrar_clientePF(cpfSeguro);
				if (!Validacao.validarExistencia(clientepf)) {
					System.out.println("Cliente não encontrado");
					return;
				}
				System.out.println("Digite a placa do veículo desse cliente que você deseja segurar:");
				String placaSeguro = scanner.nextLine();
				Veiculo veiculoSeguro = null;
				for (Veiculo vSeguro : clientepf.getListaVeiculos()) {
					if (vSeguro.getPlaca().equals(placaSeguro)) {
						veiculoSeguro = vSeguro;
					}
				}
				segAtual.gerarSeguro(dataInicio, dataFim, clientepf, veiculoSeguro);
				
			} else if (tipo.equals("PJ")) {
				/* Caso de um seguroPJ */
				System.out.println("Digite o CNPJ do cliente responsável por esse seguro:");
				String cnpjSeguro = scanner.nextLine();
				ClientePJ clientepj = segAtual.encontrar_clientePJ(cnpjSeguro);
				if (!Validacao.validarExistencia(clientepj)) {
					System.out.println("Cliente não encontrado");
					return;
				}
				System.out.println("Digite o code da frota desse cliente que você deseja segurar:");
				int codeSeguro = scanner.nextInt();
				scanner.nextLine();
				Frota frotaSeguro=null;
				for (Frota f : clientepj.getListaFrota()) {
					if (f.getCode() == codeSeguro) {
						frotaSeguro = f;
					}
				}
				segAtual.gerarSeguro(dataInicio, dataFim, clientepj, frotaSeguro);
			}
			break;
		case CADASTRAR_SEGURADORA:
			/* Pede o necessário e cadastra uma seguradora na lista de seguradoras da main*/
			System.out.println("Digite o nome da seguradora deseja cadastrar:");
			String segNome = scanner.nextLine();

			System.out.println("Digite o telefone da seguradora deseja cadastrar:");
			String segTelefone = scanner.nextLine();

			System.out.println("Digite o email da seguradora deseja cadastrar:");
			String segEmail = scanner.nextLine();

			System.out.println("Digite o endereço da seguradora deseja cadastrar:");
			String segEndereco = scanner.nextLine();

			Seguradora newSeg = new Seguradora(segNome, segTelefone, segEmail, segEndereco);
			seguradora.add(newSeg);
			System.out.println("Seguradora cadastrada com sucesso");
			break;

		case LISTAR_CLIENTES_SEG:
		/* Mostra todos os clientes de cada seguradora */
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
		case LISTAR_SEGUROS_SEG:
		/* Mostra todos os seguros da lista de seguros de cada seguradora */
			System.out.println("Listando seguros...\n");
			for (Seguradora seg : seguradora) {
				System.out.println("Seguradora: "+seg.getNome());
				System.out.println("###################################");
				for (Seguro s : seg.getlistarSeguros()) {
					System.out.println(s.toString());
				}
				System.out.println("###################################\n");
			}
			break;
		case LISTAR_SEGUROS_CLIENTE:
		/* Mostra todos os seguros de cada cliente, de cada seguradora */
			System.out.println("Listando seguros...\n");
			for (Seguradora seg : seguradora) {
				for (Cliente c : seg.getListaClientes()) {
					System.out.println("Cliente: "+c.getNome());
					System.out.println("###################################");
					seg.visualizarSeguros(c.getCadastro());
				}
				System.out.println("###################################\n");
			}
			break;
		case LISTAR_VEICULOS_CLIENTE:
		/* Mostra todos os veículos de cada cliente, de cada seguradora*/
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
		/* Mostra todos os veículos de cada seguradora */
			System.out.println("Listando veículos...\n");
			for (Seguradora seg : seguradora) {
				System.out.println("Seguradora: "+seg.getNome());
				System.out.println("###################################");
				for (Cliente c : seg.getListaClientes()) {
					c.visualizarVeiculos();
				}
				System.out.println("###################################\n");
			}
			break;
		case LISTAR_CONDUTORES_SEGURO:
			/* Mostra todos os condutores autorizados de cada seguro */
			for (Seguradora seg : seguradora) {
				for (Seguro seguro : seg.getlistarSeguros()) {
					System.out.println(seguro.toString());
					System.out.println("###################################\n");
					for (Condutor cond : seguro.getListaCondutores()) {
						System.out.println(cond.toString());
					}
					System.out.println("###################################\n");
				}
			}
			break;
		case LISTAR_SINISTROS_CLIENTE:
			/* Mostra todos os sinistros de cada cliente */
			for (Seguradora seg : seguradora) {
				for (Cliente client : seg.getListaClientes()) {
					System.out.println(client.toString());
					System.out.println("###################################");
					seg.visualizarSinistro(client.getCadastro());
				System.out.println("###################################\n");
				}
			}
			break;
		case EXCLUIR_CLIENTE:
		/* Pede o necessário e exclui o cliente passado */
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
		/* Pede o necessário e exclui o veiculo passado
		 * (de clientePF, para remover um veículo de uma frota deve-se usar
		 * o atualizar frota)
		*/
			System.out.println("Digite o CPF do cliente dono do veículo que deseja excluir:");
			String cpfDono = scanner.nextLine();
			Cliente clientepfDono = null;
			for (Seguradora seg : seguradora) {
				clientepfDono = seg.encontrar_clientePF(cpfDono);
				if (!Validacao.validarExistencia(clientepfDono)) {
					break;
				}
			}
			
			System.out.println("Digite a placa do veículo que deseja excluir:");
			String placaExcluir = scanner.nextLine();
			clientepfDono.removerVeiculo(placaExcluir);
			System.out.println("Excluído com sucesso!");
			break;
		case EXCLUIR_FROTA:
		/* Pede o necessário e exclui a frota passada */
			System.out.println("Digite o CPF do cliente dono do veículo que deseja excluir:");
			String cnpjDono = scanner.nextLine();
			Cliente clientepjDono = null;
			for (Seguradora seg : seguradora) {
				clientepjDono = seg.encontrar_clientePJ(cnpjDono);
				if (!Validacao.validarExistencia(clientepjDono)) {
					break;
				}
			}

			System.out.println("Digite o code da frota que deseja excluir:");
			int codeExcluir = scanner.nextInt();
			scanner.nextLine();
			clientepjDono.atualizarFrota(codeExcluir);
			System.out.println("Excluído com sucesso!");
			break;
		case EXCLUIR_SEGURO:
		/* Pede o necessário e exclui a frota passada */
			System.out.println("Digite o ID do seguro que deseja excluir");
			int id = scanner.nextInt();
			scanner.nextLine(); // limpando o buffer, por isso o nextLine() sempre depois do nextInt()
			for (Seguradora seg : seguradora) {
				for (Seguro s : seg.getlistarSeguros()) {
					if (s.getID()==id) {
						seg.cancelarSeguro(id);
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