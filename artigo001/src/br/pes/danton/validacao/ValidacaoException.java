package br.pes.danton.validacao;

public class ValidacaoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ValidacaoException(IPadraoEnum erroValidacao, String... mensagens) {
		super(Formatacao.daMensagemErro(erroValidacao, mensagens));
	}
}
