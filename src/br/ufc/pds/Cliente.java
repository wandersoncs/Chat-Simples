package br.ufc.pds;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		String apelido;
		String host;
		int port;
		
		Scanner entrada = new Scanner(System.in);
		try {
			System.out.println("Digite o ip do Servidor:");
			host = entrada.nextLine();
			System.out.println("Digite a porta do Servidor:");
			port = entrada.nextInt();
			Socket cliente = new Socket(host, port);
			
			System.out.println("Digite seu Apelido de ate 8 letras:");
			apelido = entrada.next();
			while (apelido.length() > 8 || apelido.toUpperCase().equals("SERVIDOR")) {
				System.out.println("Formato invalido! Digite novamente!");
				apelido = entrada.next();
			}
		
			Receiver Receber = new Receiver(cliente);
			Thread threadReceber = new Thread(Receber);
			threadReceber.start();
	
			Send enviar = new Send(cliente, apelido);
			Thread threadSend = new Thread(enviar);
			threadSend.start();
			
		} catch (IOException e) {
			System.out.println("O cliente nao pode se conectar ao servidor!");
		}

	}
		

}
