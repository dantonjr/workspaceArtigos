package br.pes.danton.validacao;

public class Testa {
	public static void main(String[] args) {
		Pessoa pessoa = null;
		try {
			 pessoa = new Pessoa();
		} catch (ValidacaoException e) {
			System.out.println(e.getMessage());
		}
		try {
			pessoa = new Pessoa(27, "Jr", "Blumenau/SC");
			System.out.println(pessoa.toString());
		} catch (ValidacaoException e) {
			System.out.println(e.getMessage());
		}
	}
}
