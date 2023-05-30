package Menus;

public enum SubmenuOpcoes {
	CADASTRAR_CLIENTE("Cadastrar cliente PF/PJ"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_FROTA("Cadastrar frota"),
	CADASTRAR_CONDUTOR("Cadastrar condutor"),
	CADASTRAR_SEGURO("Cadastrar seguro"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES_SEG("Listar clientes PF/PJ por seguradora"),
	LISTAR_SEGUROS_SEG("Listar seguros por seguradora"),
	LISTAR_SEGUROS_CLIENTE("Listar seguros por cliente"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculos por cliente"),
	LISTAR_VEICULOS_SEG("Listar veiculos por seguradora"),
	LISTAR_CONDUTORES_SEGURO("Listar condutores por seguro"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros por cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_FROTA("Excluir frota"),
	EXCLUIR_SEGURO("Excluir seguro"),
	VOLTAR("Voltar");
	
	private final String descricao;
	
	SubmenuOpcoes(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}