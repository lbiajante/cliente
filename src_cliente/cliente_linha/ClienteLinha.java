package cliente_linha;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteLinha {

	public static void main(String[] args) {

		if (args.length > 0) {
			String local = args[0];
			String data = args[1];
			String hora = args[2];

			ClienteLinha cl = new ClienteLinha();
			cl.geraArquivo(local, data, hora);
			cl.conectaCliente();
		}
	}

	private static Socket cliente;

	public void conectaCliente() {
		try {
			cliente = new Socket("localhost", 6667);
			System.out.println("Dispositivo conectado");
			new Thread() {
				private FileInputStream file;

				@Override
				public void run() {
					try {
						BufferedReader leitor = new BufferedReader(
								new InputStreamReader(cliente.getInputStream()));
						String mensagem = leitor.readLine();

						if (mensagem.equals("::PRONTO")) {
							PrintWriter escritor = new PrintWriter(
									cliente.getOutputStream(), true);
							String enviarMensagem = "000001"; // login de
																// usuario
																// cadastrado
							escritor.println(enviarMensagem);
							escritor.flush();

							mensagem = leitor.readLine();
							if (mensagem.equals("::ERRO")) {
								System.out
										.println("Usuario de cliente_dispositivo nao cadastrado no servidor\n"
												+ "Conexao encerrada");
								System.exit(0);
							} else if (mensagem.equals("::OK")) {

								File filex = new File("arq");
								ObjectOutputStream out = new ObjectOutputStream(
										cliente.getOutputStream());
								file = new FileInputStream("arquivo_linha.txt");

								byte[] buffer = new byte[4096];

								while (true) {
									int lenght = file.read(buffer);
									if (lenght == -1)
										break;
									out.write(buffer, 0, lenght);
									out.flush();
								}
							}
							System.out.println("Envio concluido");
							System.exit(0);
						}
					} catch (IOException e) {
						System.out
								.println("Impossivel ler a mensagem do servidor");
						e.printStackTrace();
					}
				}
			}.start();

		} catch (UnknownHostException e) {
			System.out.println("o endereço passado é inválido");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("o servidor pode estar fora ar");
			e.printStackTrace();
		}
	}

	private void geraArquivo(String local, String data, String hora) {
		try {
			FileWriter fw = new FileWriter("arquivo_linha.txt");
			PrintWriter pw = new PrintWriter(fw);
			pw.println(local + ";" + data + ";" + hora);
			pw.flush();
			pw.close();

		} catch (IOException e) {
			System.out.println("Erro ao cadastrar!");
		}
	}
}
