package br.pes.danton.validacao;

public enum EErrosAnotacoes implements IPadraoEnum {
	CLASSE_NAO_VALIDADA("\"ERRO: A classe n�o possui valida��o.\""),
	ATRIBUTO_NAO_VALIDADO("\"ERRO: O Atributo n�o possui valida��o.\""),
	ATRIBUTO_SEM_INDICE("\"ERRO: �ndice do atributo n�o � v�lido.\"");

	private final String mensagem;

	private EErrosAnotacoes(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String getMensagem() {
		return mensagem;
	}
}
