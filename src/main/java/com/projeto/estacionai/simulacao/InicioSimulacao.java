package com.projeto.estacionai.simulacao;


public class InicioSimulacao {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Thread simulacao = new Thread(new Simulacao());
			simulacao.start();	
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}
	


}
