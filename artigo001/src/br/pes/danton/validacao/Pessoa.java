package br.pes.danton.validacao;

@ClasseValidada(nomeClasse = "Dados da Pessoa")
public class Pessoa {
	private Integer id;
	@AtributoValidado(nomeAtributo = "Nome da Pessoa")
	private String nome;
	@AtributoValidado(nomeAtributo = "Endereço de Residência")
	private String endereco;

	public Pessoa() throws ValidacaoException {
		throw new ValidacaoException(EErrosClasse.CONSTRUTOR_INVALIDO, RetornaNome.daClasse(this.getClass()));
	}

	public Pessoa(Integer id, String nome, String endereco) throws ValidacaoException {
		setId(id);
		setNome(nome);
		setEndereco(endereco);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws ValidacaoException {
		String nomeAtributo = RetornaNome.doAtributo(this.getClass(), 2);
		ValidaString.nula(nome, nomeAtributo);
		nome = nome.trim();
		ValidaString.tamanhoMinimo(nome, nomeAtributo, 5);
		ValidaString.simbolos(nome, nomeAtributo);
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d - Nome: %s - Endereço: %d%n", getId(), getNome(), getEndereco());
	}
}
