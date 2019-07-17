package br.pes.danton.loteria.ferramenta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ComparaArquivo {
	private static final String NOME_ARQUIVO_TODOS = System.getProperty("user.dir") + "\\tudo.txt";
	private static final String NOME_ARQUIVO_COMPARA = System.getProperty("user.dir") + "\\processamento.txt";

	public static void main(String[] args) {
		Map<String, String> entrada = new HashMap<String, String>();
		DecimalFormat df = new DecimalFormat("###,###,###,###");
		long soma = 0;

		try {
			System.out.println("Lendo arquivo com todas as combinações...");
			BufferedReader arquivoLeitura = new BufferedReader(new FileReader(ComparaArquivo.NOME_ARQUIVO_TODOS));
			String conteudo = null;
			while ((conteudo = arquivoLeitura.readLine()) != null) {
				entrada.put(conteudo, conteudo);
			}
			arquivoLeitura.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.printf("Total de combinações lidas no arquivo com todas as combinações: %s.\n", df.format(entrada.size()));
		
		int contaComparacao = 0;
		try {
			System.out.println("Lendo arquivo com as combinações geradas e processando...");
			BufferedReader arquivoLeitura = new BufferedReader(new FileReader(ComparaArquivo.NOME_ARQUIVO_COMPARA));
			String conteudo = null;
			while ((conteudo = arquivoLeitura.readLine()) != null) {
				contaComparacao++;
				String [] dados = conteudo.split("=");
				String resposta = entrada.get(dados[0]);
				if (resposta instanceof String) {
					entrada.remove(dados[0]);
				}
				soma += Long.parseLong(dados[1]);
			}
			arquivoLeitura.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.printf("Total de combinações lidas no arquivo gerado: %s.\n", df.format(contaComparacao));
		
		System.out.println("Lista dos inexistentes...");
		for (String chave : entrada.keySet()) {
			System.out.println(chave);
		}
		System.out.printf("Total de combinações inexistentes: %s.\n", df.format(entrada.size()));
		System.out.printf("Total de combinações processadas a partir do arquivo de processamento: %s.\n", df.format(soma));
	}
}
