package br.com.serjava.neural.service.adaline;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import br.com.serjava.neural.dao.AmostraDAO;
import br.com.serjava.neural.dao.impl.AmostraTreinamentoDAO;
import br.com.serjava.neural.dto.Amostra;
import br.com.serjava.neural.service.util.NeuralUtils;

/**
 * Classe para treinamento da rede Adaline
 * @author fabiomf
 *
 */
public class AdalineTreinamento {
	
	private static final String TREINAMENTO_RESOURCE_LOCATION = "adaline/treinamento.csv";
	
	private AmostraDAO amostraDAO = new AmostraTreinamentoDAO();

	/**
	 * Método responsáve pela fase de treinamento da rede Adaline até a convergência.
	 * É utilizado o algoritmo da Regra Dealta, ou o Erro Quadrático Médio - EQM;
	 * @param eta taxa de aprendizagem da rede
	 * @param epson precisão requerida pela rede
	 * @param sinaisPorAmostra quantidade de sinais contidos em uma amostra
	 * @param plotarGraficoEqm indica se deverá ser gerado o gráfico do EQM calculado: eqm x epocas
	 * @return Double[] vetor com os pesos sinápticos ajustados.
	 */
	public Double[] efetuarTreinamento(Double eta, Double epson, Integer sinaisPorAmostra, boolean plotarGraficoEqm ) {
		
		sinaisPorAmostra++;
		
		List<Amostra> listaAmostras = amostraDAO.obterAmostras(TREINAMENTO_RESOURCE_LOCATION, sinaisPorAmostra);
		Double[] pesosSinapticos = NeuralUtils.inicializarPesosSinapticos(sinaisPorAmostra, 4);
		
		System.out.println("w inicial: " + Arrays.toString(pesosSinapticos));
		
		Double eqmAnterior = null;
		Double eqmAtual = null;
		Integer epocas = 0;
		Double diferencaEqm = null;
		
		Map<Integer, Double> serieEqm = new HashMap<>();
		
		do {
			eqmAnterior = this.calcularErroQuadratico(listaAmostras, pesosSinapticos);
			
			for (Amostra  amostra : listaAmostras) {
				
				Double u = 0D;
				
				for (int i = 0; i < sinaisPorAmostra; i++) {
					u += amostra.obterAmostra(i) * pesosSinapticos[i];					
				}
				
				for (int i = 0; i < sinaisPorAmostra; i++) {
					pesosSinapticos[i] = pesosSinapticos[i] + (eta * (amostra.getValorDesejado() - u) * amostra.obterAmostra(i));
				}
			}
			
			epocas++;
			eqmAtual = this.calcularErroQuadratico(listaAmostras, pesosSinapticos);
			
			serieEqm.put(epocas, eqmAtual);
			diferencaEqm = Math.abs( (eqmAtual - eqmAnterior) );
		
		} while(diferencaEqm.compareTo(epson) > 0);
		
		System.out.println("w final: " + Arrays.toString(pesosSinapticos));
		System.out.println("Epocas: " + epocas);
		
		if (plotarGraficoEqm)
			this.gerarGraficoEqm(serieEqm);
		
		return pesosSinapticos;
	}
	
	/**
	 * Método que calcula o erro quadrático médio utilizando o algoritmo da regra Delta,
	 * também conhecido como:
	 * - Regra de aprendizado de Widrow-Hoff;
	 * - Algoritmo LMS (least mean square) - mínimos quadrados;
	 * - Método do gradiente descendente.
	 * @param listaAmostras amostras obtidas
	 * @param pesosSinapticos vetor de pesos sinápticos
	 * @return Double valor do erro quadrático médio
	 */
	private Double calcularErroQuadratico(List<Amostra> listaAmostras, Double[] pesosSinapticos) {
		
		Integer padroes = listaAmostras.size();
		
		Double eqm = 0D;
		
		for(Amostra amostra : listaAmostras) {
			
			Double u = 0D;
			
			for(int i = 0; i < pesosSinapticos.length; i++) {
				
				u += amostra.obterAmostra(i) * pesosSinapticos[i];
			}
			
			eqm = eqm + Math.pow( (amostra.getValorDesejado() - u) , 2);
		}
		
		eqm = eqm / padroes;

		return eqm;
	}
	
	private void gerarGraficoEqm(Map<Integer, Double> serieEqm) {
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("RNA - Adaline");

                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                XYDataset ds = createDataset(serieEqm);
                JFreeChart chart = ChartFactory.createXYLineChart("Erro Quadratico Medio",
                        "Epocas", "Eqm", ds, PlotOrientation.VERTICAL, true, true,
                        false);

                ChartPanel cp = new ChartPanel(chart);

                frame.getContentPane().add(cp);
            }
        });
	}
	
	private XYDataset createDataset(Map<Integer, Double> serieEqm) {

        DefaultXYDataset ds = new DefaultXYDataset();
        
        double[][] data = new double[2][serieEqm.size()];
        
        int lineIndex = 0;
        for (Map.Entry<Integer, Double> point : serieEqm.entrySet()) {
        	
        	data[0][lineIndex] = point.getKey();
        	data[1][lineIndex] = point.getValue();
        	
        	lineIndex++;
        }        

        ds.addSeries("Eqm", data);

        return ds;
    }

}
