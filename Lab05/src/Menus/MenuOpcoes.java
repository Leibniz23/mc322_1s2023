package Menus;

public enum MenuOpcoes {
	CADASTROS("Cadastros", new SubmenuOpcoes[] {
			SubmenuOpcoes.CADASTRAR_CLIENTE,
			SubmenuOpcoes.CADASTRAR_CONDUTOR,
			SubmenuOpcoes.CADASTRAR_VEICULO,
			SubmenuOpcoes.CADASTRAR_FROTA,
			SubmenuOpcoes.CADASTRAR_SEGURO,
			SubmenuOpcoes.CADASTRAR_SEGURADORA,
			SubmenuOpcoes.VOLTAR
	}),
	LISTAR("Listar", new SubmenuOpcoes[] {
			SubmenuOpcoes.LISTAR_CLIENTES_SEG,
			SubmenuOpcoes.LISTAR_SEGUROS_SEG,
			SubmenuOpcoes.LISTAR_SEGUROS_CLIENTE,
			SubmenuOpcoes.LISTAR_VEICULOS_CLIENTE,
			SubmenuOpcoes.LISTAR_VEICULOS_SEG,
			SubmenuOpcoes.LISTAR_CONDUTORES_SEGURO,
			SubmenuOpcoes.LISTAR_SINISTROS_CLIENTE,
			SubmenuOpcoes.VOLTAR
	}),
	EXCLUIR("Excluir", new SubmenuOpcoes[] {
			SubmenuOpcoes.EXCLUIR_CLIENTE,
			SubmenuOpcoes.EXCLUIR_VEICULO,
			SubmenuOpcoes.EXCLUIR_FROTA,
			SubmenuOpcoes.EXCLUIR_SEGURO,
			SubmenuOpcoes.VOLTAR}),
	GERAR_SINISTRO("Gerar Sinistro", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	ATUALIZAR_FROTA("Atualizar Frota", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	AUTORIZAR_CONDUTOR("Autorizar Condutor", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	DESAUTORIZAR_CONDUTOR("Desautorizar Condutor", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	CALCULAR_RECEITA("Calcular Receita", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	SAIR("Sair", new SubmenuOpcoes[] {});
	
	
	private final String descricao;
	private final SubmenuOpcoes[] submenu;
	
	MenuOpcoes(String descricao, SubmenuOpcoes[] submenu){
		this.descricao = descricao;
		this.submenu = submenu;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOpcoes[] getSubmenu() {
		return submenu;
	}
}