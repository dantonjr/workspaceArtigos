package br.pes.danton.loteria.ferramenta;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeraTodasCombinacoes {

	private static ConcurrentHashMap<String, String> todosJogos = new ConcurrentHashMap<String, String>();
	private static AtomicInteger contador = new AtomicInteger(1);
	private static final String NOME_ARQUIVO = System.getProperty("user.dir") + "\\tudo.txt";
	private static final int TOTAL_THREADS = 8;
	private static final boolean PROCESSA_POR_THREAD = true;
	private static final int TOTAL_COMBINACOES = 24_040_016;

	public void processaPorThread() throws InterruptedException {
		System.out.println("Processamento por THREAD...");
		Thread[] threads = new Thread[GeraTodasCombinacoes.TOTAL_THREADS];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					int atual = GeraTodasCombinacoes.contador.getAndAdd(1);
					System.out.printf("Iniciando Thread: %2d.\n", atual);
					SecureRandom sorteio = new SecureRandom();
					int[] jogo = new int[5];
					while (GeraTodasCombinacoes.todosJogos.size() < GeraTodasCombinacoes.TOTAL_COMBINACOES) {
						Arrays.fill(jogo, Integer.MAX_VALUE);
						int j = 0;
						while (j < 5) {
							int numero = sorteio.nextInt(80) + 1;
							if (Arrays.binarySearch(jogo, numero) >= 0) {
								continue;
							}
							jogo[j++] = numero;
							Arrays.sort(jogo);
						}
						String chave = String.format("%s-%s-%s-%s-%s", jogo[0], jogo[1], jogo[2], jogo[3], jogo[4]);
						GeraTodasCombinacoes.todosJogos.putIfAbsent(chave, chave);
					}
					System.out.printf("Terminando Thread: %2d.\n", atual);
				}
			});
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}

	public void processaPorFor() {
		System.out.println("Processamento por FOR...");
		DecimalFormat df = new DecimalFormat("###,###,###,###");
		for (int x = 1; x <= 76; x++) {
			long tempoParcial = System.nanoTime();
			System.out.printf("%d: ", x);
			for (int y = 2; y <= 77; y++) {
				for (int z = 3; z <= 78; z++) {
					for (int a = 4; a <= 79; a++) {
						for (int b = 5; b <= 80; b++) {
							int[] vetor = new int[] { x, y, z, a, b };
							int[] duplicado = IntStream.of(vetor).distinct().toArray();
							if (vetor.length - duplicado.length == 0) {
								Arrays.sort(vetor);
								String chave = String.format("%s-%s-%s-%s-%s", vetor[0], vetor[1], vetor[2], vetor[3], vetor[4]);
								GeraTodasCombinacoes.todosJogos.putIfAbsent(chave, chave);
							}
						}
					}
				}
			}
			System.out.printf("Total no hash (parcial): %s/24.040.016 - %.6fs.\n", df.format(GeraTodasCombinacoes.todosJogos.size()), ((System.nanoTime() - tempoParcial) / (double) 1000000000));
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		long tempo = System.nanoTime();
		GeraTodasCombinacoes gera = new GeraTodasCombinacoes();
		if (GeraTodasCombinacoes.PROCESSA_POR_THREAD) {
			gera.processaPorThread();
		} else {
			gera.processaPorFor();
		}
		System.out.println("Gravando arquivo...");
		PrintWriter gravarArq = new PrintWriter(new FileWriter(GeraTodasCombinacoes.NOME_ARQUIVO));
		GeraTodasCombinacoes.todosJogos.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList()).forEach(gravarArq::println);
		gravarArq.close();
		System.out.println("Finalizado!");
		System.out.printf("Tempo total de processamento: %.6fs.\n", ((System.nanoTime() - tempo) / (double) 1000000000));
	}
}
