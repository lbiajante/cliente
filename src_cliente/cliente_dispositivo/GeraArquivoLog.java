package cliente_dispositivo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

public class GeraArquivoLog {
	String path = "arquivo_dispositivo.txt";	
	
	public void gerarArquivo() {

		try {			
			FileWriter criadorDeArquivo = new FileWriter(path, true);
			criadorDeArquivo.flush();
			criadorDeArquivo.close();
		} catch (IOException e) {
			System.out.println("Erro na criação do arquivo");
		}		
	} 

	public int contarLinhas() {
		int numeroLinhas = 0;
			try {
				
			File arq = new File(path);
			long tamanhoCad = arq.length();
			FileInputStream fs = new FileInputStream(arq);
			DataInputStream in = new DataInputStream(fs);

			@SuppressWarnings("resource")
			LineNumberReader lineRead = new LineNumberReader(
					new InputStreamReader(in));
			lineRead.skip(tamanhoCad);
			numeroLinhas = lineRead.getLineNumber() + 1;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return numeroLinhas;
	}
	
	public void escreverNoArquivo() {
		try {			
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println();
			pw.flush();
			pw.close();

		} catch (IOException e) {
			System.out.println("Erro ao cadastrar!");
		}
	}
			
		public void listarArquivo() {		
			try {
				FileInputStream arq = new FileInputStream(path);
				InputStreamReader input = new InputStreamReader(arq);
				BufferedReader lerCadastro = new BufferedReader(input);
				
				String linha = lerCadastro.readLine();
				if (linha == null) {
					System.out.println("Cadastro vazio");
				}
				while (linha != null) {
					
					linha = lerCadastro.readLine();
				}
				arq.close();
				lerCadastro.close();
			} catch (IOException e) {
				System.err.printf("Erro na abertura do arquivo: %s.",
						e.getMessage());
			}
		
	}
	
}