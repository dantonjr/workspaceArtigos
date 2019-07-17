package br.pes.danton.loteria;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Principal {
	private static final int NUMERO_THREADS = 8;
	private static final long TOTAL_JOGOS = 6_250_000;
	private static final String NOME_ARQUIVO = System.getProperty("user.dir") + "\\processamento.txt";
	
	public static ConcurrentHashMap<String, Long> todosJogos = new ConcurrentHashMap<String, Long>();

	public static void main(String[] args) throws InterruptedException, IOException {
		long tempo = System.nanoTime();
		DecimalFormat df = new DecimalFormat("###,###,###,###");
		System.out.printf("Gerando %s de combinações.\n", df.format(Principal.TOTAL_JOGOS * Principal.NUMERO_THREADS));

		ArrayList<Thread> listaThreads = new ArrayList<Thread>();
		for (int i = 0; i < Principal.NUMERO_THREADS; i++) {
			Thread t = new Thread(new Processa(Principal.TOTAL_JOGOS, String.format("T%03d", i + 1)));
			t.start();
			listaThreads.add(t);
		}

		for (int i = 0; i < listaThreads.size(); i++) {
			listaThreads.get(i).join();
		}
		
		System.out.printf("Processamento encerrado...\nTempo total de processamento: %.6fs.\nGravando arquivo...\n", ((System.nanoTime() - tempo) / (double) 1000000000));
		tempo = System.nanoTime();

		PrintWriter gravarArq = new PrintWriter(new FileWriter(Principal.NOME_ARQUIVO));
		Principal.todosJogos.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(gravarArq::println);
		gravarArq.close();

		System.out.printf("Tempo total de gravação do arquivo: %.6fs.\n", ((System.nanoTime() - tempo) / (double) 1000000000));
		System.out.printf("Total de combinações geradas: %s.\n", df.format(Principal.NUMERO_THREADS * Principal.TOTAL_JOGOS));
		System.out.printf("Total de combinações no Hash: %s.\n", df.format(todosJogos.size()));
	}
}