package br.com.serjava.neural.service.perceptron;

import java.util.Arrays;
import java.util.List;

import br.com.serjava.neural.dao.AmostraDAO;
import br.com.serjava.neural.dao.impl.AmostraTreinamentoDAO;
import br.com.serjava.neural.dto.Amostra;
import br.com.serjava.neural.service.util.NeuralUtils;

/**
 * Classe responsável por implementar o treinamento supervisionado da rede Perceptron
 * @author fabiomf
 *
 */
public class PerceptronTreinamento {

	private static final String TREINAMENTO_RESOURCE_LOCATION = "perceptron/treinamento.csv";
	
	private AmostraDAO amostrasDAO = new AmostraTreinamentoDAO();
	
	/**
	 * Método que efetua o treinamento da rece perceptron.
	 * @param eta - taxa de variação da rede para a aproximação linear
	 * @param sinaisPorAmostra - quantidade de sinais contidos em cada amostra a ser lida
	 * @return Double[] vetor de pesos sinápticos ajustados aos parâmetros de treinamento
	 */
	public Double[] efetuarTreinamento(Double eta, Integer sinaisPorAmostra) {
		
		//é acrescido 1 a quantidade de sinais para ajuste do limiar de ativação no mesmo vetor de pesos.
		sinaisPorAmostra++;
		
		List<Amostra> amostras = amostrasDAO.obterAmostras(TREINAMENTO_RESOURCE_LOCATION, sinaisPorAmostra);
		
		Double[] pesosSinapticos = NeuralUtils.inicializarPesosSinapticos(sinaisPorAmostra, 4);
		
		System.out.println("W inicial: " + Arrays.toString(pesosSinapticos));
		
		boolean erro = true;
		
		int epocas = 0;
		while(erro) {
		
			erro = false;
		
			for(Amostra amostra : amostras) {
				
				Double u = 0D;
				Double y;
				
				for(int i = 0; i < sinaisPorAmostra; i++) {
					u += amostra.obterAmostra(i) * pesosSinapticos[i];
				}
				
				//funcao degrau
				y = NeuralUtils.funcaoDegrauBipolar(u);
								
				if ( !y.equals(amostra.getValorDesejado())) {
					
					erro = true;
					
					//aplica a regra de Hebb
					for (int i = 0; i < sinaisPorAmostra; i++) {
						pesosSinapticos[i] = pesosSinapticos[i] + (eta * (amostra.getValorDesejado() - y) * amostra.obterAmostra(i));
					}
				} 
			}
			
			epocas++;
		}
		
		System.out.println("Epocas: "+ epocas);

		return pesosSinapticos;
	}

}
