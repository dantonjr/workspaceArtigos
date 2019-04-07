package br.pes.danton.validacao;

public enum EErrosValidacaoAtributoString implements IPadraoEnum {
	VALOR_NULO("O valor do campo: %0 - não pode se nulo."),
	TAMANHO_INVALIDO("O campo: %0 - dever ter um tamanho mínimo de: %1."),
	CARACTERES_INVALIDOS("O campo: %0 não pode conter símbolos.");

	private final String mensagem;

	private EErrosValidacaoAtributoString(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String getMensagem() {
		return mensagem;
	}
}
