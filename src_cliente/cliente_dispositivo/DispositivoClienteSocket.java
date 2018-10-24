package cliente_dispositivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DispositivoClienteSocket {

	private static Socket cliente;

	public void executaClienteDispositivo() {
		try {
			cliente = new Socket("localhost", 6666);
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
							String enviarMensagem = "000001"; // login de usuario cadastrado
							escritor.println(enviarMensagem);
							escritor.flush();

							mensagem = leitor.readLine();
							if (mensagem.equals("::ERRO")) {
								System.out
										.println("Usuario de cliente_dispositivo nao cadastrado no servidor\n"
												+ "Conexao encerrada");
								System.exit(0);
							} else if (mensagem.equals("::OK")) {

								ObjectOutputStream out = new ObjectOutputStream(
										cliente.getOutputStream());
								file = new FileInputStream(
										"arquivo_enviado_dispositivo.txt");
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
}
