package br.pes.danton.validacao;

public enum EErrosClasse implements IPadraoEnum {
	CONSTRUTOR_INVALIDO("Esse tipo de objeto (%0) não pode ser construído sem parâmetros.");

	private final String mensagem;

	private EErrosClasse(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String getMensagem() {
		return mensagem;
	}
}
