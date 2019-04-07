package br.pes.danton.validacao;

public enum EErrosAnotacoes implements IPadraoEnum {
	CLASSE_NAO_VALIDADA("\"ERRO: A classe não possui validação.\""),
	ATRIBUTO_NAO_VALIDADO("\"ERRO: O Atributo não possui validação.\""),
	ATRIBUTO_SEM_INDICE("\"ERRO: Índice do atributo não é válido.\"");

	private final String mensagem;

	private EErrosAnotacoes(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String getMensagem() {
		return mensagem;
	}
}
