package br.com.serjava.neural.dao;

import java.util.List;

import br.com.serjava.neural.dto.Amostra;

/**
 * Classe de acesso a fonte de dados de amostras.
 * @author fabiomf
 *
 */
public interface AmostraDAO {

	/**
	 * Método que obtém, de um arquivo csv, as amostras para treinamento e operação.
	 * @param sinaisPorAmostra - quantidade de sinais esperados em cada amostra.
	 * @param resourceName - nome do diretorio/arquivo a ser lido.
	 * @return List<Amostras> lista com um vetor de sinais das amostras.
	 */
	public List<Amostra> obterAmostras(String resourceName, Integer sinaisPorAmostra);
}
