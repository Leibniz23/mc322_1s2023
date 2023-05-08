package Menus;

public enum SubmenuOpcoes {
	CADASTRAR_CLIENTE("Cadastrar cliente PF/PJ"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES("Listar clientes PF/PJ por seguradora"),
	LISTAR_SINISTROS_SEG("Listar sinistros por seguradora"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros por cliente"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculos por cliente"),
	LISTAR_VEICULOS_SEG("Listar veiculos por seguradora"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	VOLTAR("Voltar");
	
	private final String descricao;
	
	SubmenuOpcoes(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
