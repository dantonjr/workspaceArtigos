package br.pes.danton.validacao;

public class ValidaString {
	public static void nula(String string, String campo) throws ValidacaoException {
		if (!(string instanceof String))
			throw new ValidacaoException(EErrosValidacaoAtributoString.VALOR_NULO, campo);
	}

	public static void tamanhoMinimo(String string, String campo, int tamanho) throws ValidacaoException {
		if (string.length() < tamanho)
			throw new ValidacaoException(EErrosValidacaoAtributoString.TAMANHO_INVALIDO, campo, String.valueOf(tamanho));
	}

	public static void simbolos(String string, String campo) throws ValidacaoException {
		if (!string.matches("[\\s\\dA-Za-z]*"))
			throw new ValidacaoException(EErrosValidacaoAtributoString.CARACTERES_INVALIDOS, campo);
	}
}
