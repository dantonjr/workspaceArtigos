package br.pes.danton.loteria;

import java.security.SecureRandom;
import java.util.Arrays;

public class Processa implements Runnable {

	private long limite;
	private String nome;

	public Processa(long limite, String nome) {
		this.limite = limite;
		this.nome = nome;
	}

	@Override
	public void run() {
		long tempo = System.nanoTime();
		SecureRandom sorteio = new SecureRandom();
//		Random sorteio = new Random();
		System.out.printf("Iniciando Thread: %s.\n", this.nome);
		int[] jogo = new int[5];
		for (long i = 0; i < limite; i++) {
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
			Principal.todosJogos.merge(chave, (long) 1, Long::sum);
		}
		System.out.printf("Thread: %s encerrada -> %.6fs.\n", this.nome, ((System.nanoTime() - tempo) / (double) 1000000000));
	}
}