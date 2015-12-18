package br.ufc.pds;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Receiver implements Runnable {

	private Socket cliente;

	public Receiver(Socket cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		Scanner entrada;
		try {
			entrada = new Scanner(cliente.getInputStream());
			while (entrada.hasNextLine()) {
				System.out.println(entrada.nextLine());
			}
			entrada.close();
		} catch (IOException e) {
			System.out.println("Não foi possivel receber uma mensagem!");
		}
	}

	public Socket getCliente() {
		return cliente;
	}

}
