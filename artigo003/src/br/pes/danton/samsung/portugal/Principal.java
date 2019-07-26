package br.pes.danton.samsung.portugal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Principal {
	public static void main(String[] args) {
		String nomeArquivoJson = args.length > 0 ? args[0] : "dadosFotos.json";
		String caminhoPadrao = System.getProperty("user.dir");
		new File(caminhoPadrao + "\\imagens\\png").mkdirs();
		new File(caminhoPadrao + "\\imagens\\jpg").mkdirs();
		System.out.printf("Criada pasta para salvar as imagens:\n\t\"[%s]\\imagens\"\n", caminhoPadrao);
		System.out.printf("Assumindo arquivo [%s] como origem dos dados (Json).\n", nomeArquivoJson);
		try {
			System.out.println("Lendo arquivo Json...");
			JSONParser parser = new JSONParser();
			JSONArray jsons = (JSONArray) parser.parse(new FileReader(caminhoPadrao + "\\" + nomeArquivoJson));
			System.out.printf("Json lido com %d elementos.\n", jsons.size());
			System.out.println("Processando download das imagens...");
			DadosImagem dados = null;
			BufferedImage imagem = null;
			String titulo = "Início";
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject json = (JSONObject) jsons.get(i);
				dados = new DadosImagem();
				dados.setId(json.get("id").toString());
				dados.setTitle(json.get("title").toString());
				dados.setWidth(json.get("width").toString());
				dados.setHeight(json.get("height").toString());
				dados.setPath(json.get("path").toString());
				System.out.printf("Id: %4s\tTítulo \"%s\"\tWidth: %s\tHeight: %s\n", dados.getId(), dados.getTitle(), dados.getWidth(), dados.getHeight());
				titulo = dados.getTitle().isEmpty() ? titulo : dados.getTitle();
				System.out.printf("\tCapturando a imagem da URL [%s].\n", dados.getPath());
				URL url = new URL(dados.getPath());
				imagem = ImageIO.read(url);
				String nomeLocal = String.format("%04d - %s [%s]", (i + 1), titulo, dados.getId());
				ImageIO.write(imagem, "jpg", new File(caminhoPadrao + "\\imagens\\jpg\\" + nomeLocal + ".jpg"));
				ImageIO.write(imagem, "png", new File(caminhoPadrao + "\\imagens\\png\\" + nomeLocal + ".png"));
				System.out.printf("\tArquivo local salvo:\n\t\t[%s].\n", nomeLocal);
			}
		} catch (IOException | ParseException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Processo finalizado!");
	}
}
