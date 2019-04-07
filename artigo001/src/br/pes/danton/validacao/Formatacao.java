package br.pes.danton.validacao;

public class Formatacao {
	public static String daMensagemErro(IPadraoEnum enumErro, String... mensagens) {
		String erro = enumErro.getMensagem();
		int contador = 0;
		for (String mensagem : mensagens) {
			erro = erro.replaceAll("%" + contador, mensagem);
			contador++;
		}
		return erro;
	}
}
