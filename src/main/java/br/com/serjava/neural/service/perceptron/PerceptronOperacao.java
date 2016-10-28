package br.com.serjava.neural.service.perceptron;

import java.util.ArrayList;
import java.util.List;

import br.com.serjava.neural.dao.AmostraDAO;
import br.com.serjava.neural.dao.impl.AmostraOperacaoDAO;
import br.com.serjava.neural.dto.Amostra;
import br.com.serjava.neural.service.util.NeuralUtils;

public class PerceptronOperacao {
	
	private AmostraDAO amostrasDAO = new AmostraOperacaoDAO();
	
	private static final String OPERACAO_RESOURCE_LOCATION = "perceptron/operacao.csv";

	public List<Double> classificarAmostra(Double[] pesosSinapticosAjustados, Integer sinaisPorAmostra) {
		
		List<Amostra> amostras = amostrasDAO.obterAmostras(OPERACAO_RESOURCE_LOCATION, sinaisPorAmostra);
		
		List<Double> resultadoClassificacao = new ArrayList<Double>();
		
		for(Amostra amostra : amostras) {
			
			Double u = 0D;
			
			for(int i = 0; i < sinaisPorAmostra; i++) {
			
				u += pesosSinapticosAjustados[i + 1] * amostra.obterAmostra(i);
			}
			
			//posicao 0 dos pesos sinapticos é o limiar de ativacao
			u = u - pesosSinapticosAjustados[0];
			
			resultadoClassificacao.add(NeuralUtils.funcaoDegrauBipolar(u));
		}
		
		return resultadoClassificacao;
	}
	
}
