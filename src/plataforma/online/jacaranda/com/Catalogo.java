package plataforma.online.jacaranda.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Catalogo {
	private Map<String, Serie> mapSeries;

	public Catalogo() {
		super();
		this.mapSeries = new HashMap<>();
	}

	public void addSerie(String nombreSerie, int anio, Tema tema) throws SerieException {
		if (nombreSerie != null) {
			mapSeries.put(nombreSerie, new Serie(nombreSerie, anio, tema));
		}
	}

	public void addTemporada(String nombreSerie, String nombreTemporada) throws SerieException {
		if (nombreSerie != null && nombreTemporada != null) {
			mapSeries.get(nombreSerie).addTemporada(nombreTemporada);
		}
	}

	public void addCapituloTemporada(String nombreSerie, String nombreCapitulo, String nombreTemporada)
			throws SerieException {
		if (nombreSerie != null && nombreTemporada != null && nombreCapitulo != null) {
			mapSeries.get(nombreSerie).addCapituloTemporada(nombreTemporada, nombreCapitulo);
		}
	}

	public void valorarTemporada(String nombreSerie, String nombreTemporada, int valoracion) throws SerieException {
		if (nombreSerie != null && nombreTemporada != null) {
			mapSeries.get(nombreSerie).valorarTemporada(nombreTemporada, valoracion);
		}
	}

	public int numeroDeTemporadasDeUnaSerie(String nombreSerie) throws SerieException {
//		Opcion no vista en clase
//		AtomicInteger numeroTemporadas = new AtomicInteger(-1);
//		if (nombreSerie != null) {
//			mapSeries.forEach((String key, Serie value)->{if (key.equals(nombreSerie)) {numeroTemporadas.set(value.numeroTemporadas());}});
//			if (numeroTemporadas.equals(-1)) {
//				throw new SerieException("No hay ninguna temporada con ese nombre");
//			}
//		}
//		return numeroTemporadas.get();

		int numeroTemporadas = -1;
		if (mapSeries.keySet().contains(nombreSerie)) {
			numeroTemporadas = mapSeries.get(nombreSerie).numeroTemporadas();
		} else {
			throw new SerieException("No hay ninguna serie con ese nombre");
		}

		return numeroTemporadas;
	}

	public void modificarTema(String nombreSerie, Tema temaNuevo) throws SerieException {
		if (mapSeries.get(nombreSerie) != null) {
			if (mapSeries.get(nombreSerie).getTema().equals(temaNuevo)) {
				throw new SerieException("El tema debe ser distinto.");
			} else {
				mapSeries.get(nombreSerie).setTema(temaNuevo);				
			}
		} else {
			throw new SerieException("Nombre de la serie no encontrado");
		}
	}

	public String listadoOrdenadoSeriesDeUnTema(Tema tema) throws SerieException {
		List<Serie> catalogoPorTema = new ArrayList<>();
		mapSeries.forEach((String key, Serie value) -> {
			if (value.getTema().equals(tema)) {
				catalogoPorTema.add(value);
			}
		});
		if (catalogoPorTema.isEmpty()) {
			throw new SerieException("No hay ninguna serie con ese tema");
		} else {
			catalogoPorTema.sort((temp1, temp2) -> Integer.compare(temp1.getAnno(), temp2.getAnno()));
		}
		return catalogoPorTema.toString();
	}

}
