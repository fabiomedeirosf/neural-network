package br.com.serjava.neural.dto;

import java.util.Arrays;

/**
 * Classe que representa o conteúdo das amostras.
 * @author fabiomf
 *
 */
public class Amostra {

	private Double[] amostras;
	
	private Double valorDesejado;
	
	public Amostra(Integer elementos) {
		
		amostras = new Double[elementos];
	}
	
	public void adicionar(int index, Double valor) {
		this.amostras[index] = valor;
	}
	
	public Integer getAmostrasLength() {
		return amostras.length - 1;
	}

	public Double getValorDesejado() {
		return valorDesejado;
	}

	public void setValorDesejado(Double valorDesejado) {
		this.valorDesejado = valorDesejado;
	}
	
	public Double obterAmostra(int index) {
		return this.amostras[index];
	}

	@Override
	public String toString() {
		return "Amostra [amostras=" + Arrays.toString(amostras)
				+ ", valorDesejado=" + valorDesejado + "]";
	}
	
	
}

