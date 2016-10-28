package br.com.serjava.neural.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.serjava.neural.dao.AmostraDAO;
import br.com.serjava.neural.dto.Amostra;

/**
 * Classe de acesso a dados para as amostras na fase de operação.
 * @author fabiomf
 *
 */
public class AmostraOperacaoDAO implements AmostraDAO {

	@Override
	public List<Amostra> obterAmostras(String resourceName, Integer sinaisPorAmostra) {
		File file = null;
		FileReader reader = null;
		BufferedReader br = null;
		
		List<Amostra> listaAmostras = new ArrayList<Amostra>();
		
		Amostra amostra = null;
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			file = new File(classLoader.getResource(resourceName).getFile());
			
			reader = new FileReader(file);
			
			br = new BufferedReader(reader);
			
			String linha = br.readLine();
			
			while( (linha = br.readLine()) != null) {
				
				String[] dados = linha.split(";");
		
				amostra = new Amostra(sinaisPorAmostra);
				
				for (int i = 0; i <= amostra.getAmostrasLength(); i++) {
					amostra.adicionar(i, Double.valueOf(dados[i + 1]));
				}
				
				listaAmostras.add(amostra);
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				br.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaAmostras;
	}

}
