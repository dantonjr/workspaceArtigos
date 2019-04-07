package br.pes.danton.validacao;

public class RetornaNome {
	public static String daClasse(Class<?> classe) {
		return classe.isAnnotationPresent(ClasseValidada.class)
				? classe.getAnnotation(ClasseValidada.class).nomeClasse()
				: EErrosAnotacoes.CLASSE_NAO_VALIDADA.getMensagem();
	}

	public static String doAtributo(Class<?> classe, int indice) {
		String mensagem = null;
		try {
			mensagem = classe.getDeclaredFields()[indice].isAnnotationPresent(AtributoValidado.class)
					? classe.getDeclaredFields()[indice].getAnnotation(AtributoValidado.class).nomeAtributo()
					: EErrosAnotacoes.ATRIBUTO_NAO_VALIDADO.getMensagem();
		} catch (IndexOutOfBoundsException e) {
			mensagem = EErrosAnotacoes.ATRIBUTO_SEM_INDICE.getMensagem();
		}
		return mensagem;
	}
}
