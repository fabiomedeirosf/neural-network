package br.com.serjava.neural.service.adaline;

import java.util.ArrayList;
import java.util.List;

import br.com.serjava.neural.dao.AmostraDAO;
import br.com.serjava.neural.dao.impl.AmostraOperacaoDAO;
import br.com.serjava.neural.dto.Amostra;
import br.com.serjava.neural.service.util.NeuralUtils;

/**
 * Classe para operação da rede Adaline
 * @author fabiomf
 *
 */
public class AdalineOperacao {

	private AmostraDAO amostrasDAO = new AmostraOperacaoDAO();
	
	private static final String OPERACAO_RESOURCE_LOCATION = "adaline/operacao.csv";
	
	public List<Double> classificarAmostra(Double[] pesosSinapticosAjustados, Integer sinaisPorAmostra) {
		
		List<Amostra> listaAmostras = this.amostrasDAO.obterAmostras(OPERACAO_RESOURCE_LOCATION, sinaisPorAmostra);
		
		List<Double> listaResultado = new ArrayList<Double>();
		
		for (Amostra amostra : listaAmostras) {
			
			Double u = 0D;
			
			for(int i = 0; i < sinaisPorAmostra; i++) {
				u += amostra.obterAmostra(i) * pesosSinapticosAjustados[i + 1];
			}
		
			u = u - pesosSinapticosAjustados[0];
			
			listaResultado.add(NeuralUtils.funcaoDegrauBipolar(u));
		}
		
		return listaResultado;
	}
		
}
