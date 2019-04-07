package br.pes.danton.validacao;

public enum EErrosValidacaoAtributoString implements IPadraoEnum {
	VALOR_NULO("O valor do campo: %0 - n�o pode se nulo."),
	TAMANHO_INVALIDO("O campo: %0 - dever ter um tamanho m�nimo de: %1."),
	CARACTERES_INVALIDOS("O campo: %0 n�o pode conter s�mbolos.");

	private final String mensagem;

	private EErrosValidacaoAtributoString(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String getMensagem() {
		return mensagem;
	}
}
