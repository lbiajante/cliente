package dispositivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DispositivoClienteSocket {
	public static void main(String[] args) {
		try {
			final Socket dispositivoCliente = new Socket("localhost", 6666);		
			System.out.println("Dispositivo conectado");

			new Thread() {
				@Override
				public void run() {
					try {
																	
						BufferedReader leitor = new BufferedReader(
								new InputStreamReader(dispositivoCliente.getInputStream()));
						String mensagem = leitor.readLine();
						while (true) {								
							mensagem = leitor.readLine();
							if (mensagem == null || mensagem.isEmpty())
								continue;
							System.out.println(mensagem);
						}
					} catch (IOException e) {
						System.out
								.println("Impossivel ler a mensagem do servidor");
						e.printStackTrace();
					}
				}
			}.start();

			PrintWriter escritor = new PrintWriter(dispositivoCliente.getOutputStream(),
					true);
			BufferedReader leitorTerminal = new BufferedReader(
					new InputStreamReader(System.in));
			String enviarMensagem = "";
			while (true) {
				enviarMensagem = leitorTerminal.readLine();
				if (enviarMensagem == null || enviarMensagem.length() == 0) {
					continue;
				}
				escritor.println(enviarMensagem);
				escritor.flush();
				if (enviarMensagem.equalsIgnoreCase("sair")) {
					System.out.println("Cliente encerrado");
					System.exit(0);
				}
			}
		} catch (UnknownHostException e) {
			System.out.println("o endereço passado é inválido");

			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("o servidor pode estar fora ar");
			e.printStackTrace();
		}
	}

}
