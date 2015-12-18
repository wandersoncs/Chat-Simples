package br.ufc.pds;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

@SuppressWarnings("deprecation")
public class Send implements Runnable {

	private Socket cliente;
	private String apelido;

	public Send(Socket cliente, String apelido) {
		this.cliente = cliente;
		this.apelido = apelido;
	}

	@Override
	public void run() {
		PrintStream saida;
		try {
			saida = new PrintStream(cliente.getOutputStream());
			Scanner tec = new Scanner(System.in);
			
			if (!apelido.equals("Servidor"))
				saida.println("Cliente " + apelido + " se conectou!");
			else {
				saida.println("Bem Vindo!");
				saida.println("\tDigite suas mensagens!");
			}
			
			while (tec.hasNextLine()) {
				
				String mensagem = tec.nextLine();
			
				if (isSaindo(mensagem)) {
					saida.println(apelido + " desconectou!");
					tec.close();
					saida.close();
					cliente.close();
					break;
				}
				
				Date data = new Date();	
				saida.println("[" + apelido + "][" + data.toLocaleString()
						+ "] " + mensagem);
			}
			tec.close();
		} catch (IOException e) {
			System.out.println("Nao foi possivel enviar uma mensagem!");
		}
	}

	private boolean isSaindo(String mensagem) {
		return !apelido.equals("Servidor") && mensagem.equals("tchau");
	}

	public Socket getCliente() {
		return cliente;
	}

}
