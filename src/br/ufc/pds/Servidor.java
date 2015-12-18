package br.ufc.pds;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Servidor {

	@SuppressWarnings("resource")
	public static void main(String[] args){

		String apelido = "Servidor";
		int porta;
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite a porta do Servidor:");
		
		try {
			porta = entrada.nextInt();

			ServerSocket server = new ServerSocket(porta);
			Socket cliente;

			while (true) {
				cliente = server.accept();

				Send enviar = new Send(cliente, apelido);
				Thread threadSend = new Thread(enviar);
				threadSend.start();

				Receiver receber = new Receiver(cliente);
				Thread threadReceber = new Thread(receber);
				threadReceber.start();

				while (!cliente.isClosed());
			}
		} catch (IOException e) {
			System.out.println("O servidor nao pode entrar em execução");
		} catch (InputMismatchException e) {
			System.out.println("Numero de porta invalido!");
		}
	}

}
