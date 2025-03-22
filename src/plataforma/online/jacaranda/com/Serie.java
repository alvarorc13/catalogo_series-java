package plataforma.online.jacaranda.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Serie {

	private static final int ANNO_MINIMO = 1895;
	private String nombreSerie;
	private int anno;
	private Tema tema;
	private List<Temporada> temporadas;

	public Serie(String nombreSerie, int anno, Tema tema) throws SerieException {
		super();
		if (anno < ANNO_MINIMO) {
			throw new SerieException("El año de la serie no puede ser inferior a " + ANNO_MINIMO);
		} else {
			this.nombreSerie = nombreSerie;
			this.anno = anno;
			this.tema = tema;
			this.temporadas = new ArrayList<>();
		}
	}

	public void addTemporada(String nombreTemporada) throws SerieException {
		Temporada temporadaAniadir = new Temporada(nombreTemporada);
		if (!temporadas.contains(temporadaAniadir)) {
			temporadas.add(temporadaAniadir);
		} else {
			throw new SerieException("La temporada ya existe");
		}
	}

	public void addCapituloTemporada(String nombreTemporada, String nombreCapitulo) {
		if (nombreTemporada != null && nombreCapitulo != null) {
			Temporada temporadaAnnadirCapitulo = new Temporada(nombreTemporada);
			temporadas.forEach(temp -> {
				if (temp.equals(temporadaAnnadirCapitulo)) {
					try {
						temp.annadirCapitulo(nombreCapitulo);
					} catch (SerieException e) {
						System.out.println(e.getLocalizedMessage());
					}
				}
			});
		}
	}

	public void valorarTemporada(String nombreTemporada, int valoracion) throws SerieException {
		if (nombreTemporada != null) {
			Temporada temporadaValorar = new Temporada(nombreTemporada);
			temporadas.forEach(temp -> {
				if (temp.equals(temporadaValorar)) {
					try {
						temp.valorar(valoracion);
					} catch (SerieException e) {
						System.out.println(e.getLocalizedMessage());
					}
				}
			});
		}
	}

	public String listarTemporadasPorNotaMedia() {
//		Utilizo StringBuilder porque cuando uso String no puedo moficarlo con lambdas
		StringBuilder temporadasPorNota = new StringBuilder();
		temporadas.sort((temp1, temp2) -> -Double.compare(temp1.getNotaMedia(), temp2.getNotaMedia()));
		temporadas.forEach(temp -> {
			temporadasPorNota.append(temp.toString()).append("\n");
		});
		return temporadasPorNota.toString();
	}

//	No podemos ordenar el ArrayList de temporadas como el metodo anterior, porque no tenemos acceso al numero de capitulos desde esta clase. 
//	Por lo que he optado por hacer overriding de compareTo en la clase Temporada.
	public String listarTemporadaPorNumeroCapitulos() {
//		Utilizo StringBuilder porque cuando uso String no puedo moficarlo con lambdas
		StringBuilder temporadaPorNumeroCap = new StringBuilder();
		Collections.sort(temporadas);
		temporadas.forEach(temp -> {
			temporadaPorNumeroCap.append(temp).append("\n");
		});
		return temporadaPorNumeroCap.toString();
	}

	public int numeroTemporadas() {
		return this.temporadas.size();
	}

	public String getNombreSerie() {
		return nombreSerie;
	}

	public void setNombreSerie(String nombreSerie) {
		this.nombreSerie = nombreSerie;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreSerie);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj != null && obj instanceof Serie && this.hashCode() == obj.hashCode();
	}

	@Override
	public String toString() {
		return String.format("Nombre serie: %s, año: %s, tema: %s, numero de temporadas: %s", nombreSerie, anno, tema,
				numeroTemporadas());
	}

}
