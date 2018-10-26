package gera_arquivo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class GeraArquivoLog {

	private Lugares randomLugares() {
		int indice = new Random().nextInt(Lugares.values().length);
		return Lugares.values()[indice];
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy;HHmm");
		Date date = new Date();
		return dateFormat.format(date);
	}

	String timeToPath = getDateTime().replaceAll("[^a-zZ-Z1-9 ]", "");
	String path = "arquivo_dispositivo" + timeToPath + ".txt";
	private static Scanner entrada;

	public void escreverNoArquivo(String label) {
		try {
			FileWriter fw = new FileWriter(path, true);			
			PrintWriter pw = new PrintWriter(fw);
			pw.println(label);
			pw.flush();
			pw.close();

		} catch (IOException e) {
			System.out.println("Erro ao cadastrar!");
		}
	}

	public void log() {
		String linhaLog = randomLugares() + ";" + getDateTime();
		this.escreverNoArquivo(linhaLog);
		System.out.println(linhaLog);
	}

	public static void main( String args []) {
		GeraArquivoLog log = new GeraArquivoLog();
		
		System.out.println("Quantas entradas no arquivo deseja?");
		entrada = new Scanner(System.in);
		int x = entrada.nextInt();

		for (int i = 0; i <= x; i++) {
			log.log();
			

		}
	}

}