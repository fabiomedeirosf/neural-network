package br.com.serjava.neural.service.util;

import java.math.BigDecimal;

public class NeuralUtils {

	/**
	 * Método que gera valores aleatórios para inicialização dos pesos sinápticos.
	 * @param quantidade - quantidade de pesos a serem gerados
	 * @param precisao precisão decimal. Geralmente utiliza-se quantidade análoga as amostras.
	 * @return Double[] vetor de pesos sinápticos com valores aleatórios.
	 */
	public static Double[] inicializarPesosSinapticos(Integer quantidade, Integer precisao) {
		
		Double[] pesos = new Double[quantidade];
		
		for(int i = 0; i < quantidade; i++) {
			pesos[i] = new BigDecimal(Math.random()).setScale(precisao, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		
		return pesos;
	}
	
	public static Double funcaoDegrau(Double valor) {
		
		return (valor >=0) ? 1D : 0D;
	}
	
	public static Double funcaoDegrauBipolar(Double valor) {
		
		return (valor >=0) ? 1D : -1D;
	}
}
