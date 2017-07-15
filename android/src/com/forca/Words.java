package com.forca;

import java.util.Random;

public class Words {

	private String[] palavras = new String[] { "TATU", "CALANGO", "PREGUIÇA",
			"ARARA", "MACACO", "PERERECA", "JACARÉ", "ONÇA", "ÁGUIA", "GAMBÁ",
			"CARCARÁ", "CUTIA", "JIBOIA" };

	public Words() {
	}

	public String sorteio() {
		String palavraSorteada = palavras[(int) (random() * palavras.length)];

		return palavraSorteada;
	}

	public static double random() {
		Random r = new Random();

		return r.nextDouble();
	}
}
