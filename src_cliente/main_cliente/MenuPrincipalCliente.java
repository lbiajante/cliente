package main_cliente;

import java.util.Scanner;

import cliente_dispositivo.DispositivoClienteSocket;
import cliente_manual.ClienteSocket;

public class MenuPrincipalCliente {
	private Scanner entrada = new Scanner(System.in);
	ClienteSocket clienteSocket = new ClienteSocket();
	DispositivoClienteSocket dispositivoCliente = new DispositivoClienteSocket();
	
	public MenuPrincipalCliente() {	
	}

	public void menuPrincipalCliente() {
		boolean confere = true;
		int i = 0;
		while (confere) {
			try {
				System.out.println("inicializando cliente, verifique se o servidor foi iniciado...\n"
						+ "Indique o tipo de conexao:\n"
						+ "1 - Cliente administrador do banco\n"
						+ "2 - Dispositivo de ronda\n"
						+ "3 - Sair");
				i = entrada.nextInt();

				if (i == 1) {									
					clienteSocket.executaClienteSocket();
					break;

				} else if (i == 2) {					
					dispositivoCliente.executaClienteDispositivo();
					break;
					
				} else if (i == 3) {
					System.out.println("Cliente desconectado");
					confere = false;
					
				} else {
					System.out
							.println("opcao nao existe, por favor digite novamente");
					confere = true;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("opcao invalida, tente novamente ");
				confere = true;
			}
		}
	}	
}
