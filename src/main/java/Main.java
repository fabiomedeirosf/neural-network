import java.util.Arrays;
import java.util.List;

import br.com.serjava.neural.service.adaline.AdalineOperacao;
import br.com.serjava.neural.service.adaline.AdalineTreinamento;
import br.com.serjava.neural.service.perceptron.PerceptronOperacao;
import br.com.serjava.neural.service.perceptron.PerceptronTreinamento;


public class Main {

	public static void main(String[] args) {

		System.out.println("--------> Rede Perceptron");
		redePerceptron();
		
		System.out.println("--------> Rede Adaline");
		redeAdaline();
		
	}

	private static void redePerceptron() {
		
		PerceptronTreinamento t = new PerceptronTreinamento();
		Double[] pesosSinapticos = t.efetuarTreinamento(0.01D, 3);
		
		PerceptronOperacao operacao = new PerceptronOperacao();
		List<Double> classificacao = operacao.classificarAmostra(pesosSinapticos, 3);
		
		System.out.println("W Final: " + Arrays.toString(pesosSinapticos));
		
		System.out.println("Classificação resultante:");
		for (Double d : classificacao) {
			System.out.printf("%3s", (d > 0) ? "C2" : "C1");
		}
		System.out.println();
	}
	
	private static void redeAdaline() {
		
		AdalineTreinamento a = new AdalineTreinamento();
		Double[] pesosSinapticos = a.efetuarTreinamento(0.0025, Math.pow(10, -6), 4, true);
		
		AdalineOperacao adalineOperacao = new AdalineOperacao();
		List<Double> listaResultadoAdaline = adalineOperacao.classificarAmostra(pesosSinapticos, 4);
		
		System.out.println("Classificação resultante:");
		for (Double d : listaResultadoAdaline) {
			System.out.printf("%3s", (d > 0) ? "B" : "A");
		}
		System.out.println();
	}
}
