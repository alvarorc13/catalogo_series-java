package plataforma.online.jacaranda;

import java.util.HashMap;
import java.util.Map;

import plataforma.online.jacaranda.com.Catalogo;
import plataforma.online.jacaranda.com.Serie;
import plataforma.online.jacaranda.com.SerieException;
import plataforma.online.jacaranda.com.Tema;

public class Main {

	public static void main(String[] args) {
		
		Map<String, Serie> ej = new HashMap<>();
		ej.put("adffd", null);
		System.out.println(ej.get(null));
		
//		Pruebas para el constructor de Serie
		Serie s1;
		try {
			s1 = new Serie("Serie1", 1800, Tema.DRAMA);
		} catch (SerieException e) {
			System.out.println(e.getLocalizedMessage());
		}


		try {
			s1  = new Serie("Serie1", 2025, Tema.DRAMA);
			try {
				s1.addTemporada("Temp1Serie1");
				s1.addTemporada("Temp2Serie1");
				s1.valorarTemporada("Temp2Serie1", 7);
				s1.addTemporada("Temp3Serie1");
				System.out.println(s1.listarTemporadasPorNotaMedia());
				s1.addCapituloTemporada("Temp3Serie1", "Cap1Temp3Serie1");
				System.out.println(s1.listarTemporadaPorNumeroCapitulos());
			} catch (SerieException e) {
				System.out.println(e.getLocalizedMessage());
			}
		} catch (SerieException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			s1  = new Serie("Serie1", 2025, Tema.DRAMA);
			try {
				s1.addTemporada("Temp1Serie1");
				s1.addTemporada("Temp1Serie1");
			} catch (SerieException e) {
				System.out.println(e.getLocalizedMessage());
			}
		} catch (SerieException e) {
			System.out.println(e.getLocalizedMessage());
		}

		
//		Pruebas para el metodo 'numeroDeTemporadasDeUnaSerie' de la clase Catalogo
		Catalogo c1 = new Catalogo();
		try {
			c1.addSerie("Serie1", 2025, Tema.DRAMA);
			c1.addSerie("Serie2", 2025, Tema.DRAMA);
			c1.addSerie("Serie3", 2025, Tema.DRAMA);
			c1.addSerie("Serie4", 2025, Tema.DRAMA);
			
			c1.addSerie("Serie5", 2025, Tema.COMEDIA);
			c1.addSerie("Serie6", 2025, Tema.INTRIGA);
			System.out.println(c1.numeroDeTemporadasDeUnaSerie("Serie1"));
			System.out.println(c1.numeroDeTemporadasDeUnaSerie("Serie500"));
		} catch (SerieException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			System.out.println(c1.numeroDeTemporadasDeUnaSerie(null));
		} catch (SerieException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			c1.addSerie("Serie1", 2025, Tema.DRAMA);
			c1.addSerie("Serie2", 2000, Tema.DRAMA);
			c1.addSerie("Serie3", 2030, Tema.DRAMA);
			c1.addSerie("Serie4", 2018, Tema.DRAMA);
			
			c1.addSerie("Serie5", 2025, Tema.COMEDIA);
			c1.addSerie("Serie6", 2025, Tema.INTRIGA);
			System.out.println(c1.listadoOrdenadoSeriesDeUnTema(Tema.DRAMA));
//			System.out.println(c1.listadoOrdenadoSeriesDeUnTema(Tema.CIENCIAFICCION)); Da excepcion
		} catch (SerieException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

}
