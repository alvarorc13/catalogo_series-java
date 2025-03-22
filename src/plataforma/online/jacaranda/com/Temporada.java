package plataforma.online.jacaranda.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Temporada implements Comparable<Temporada> {
	private String nombreTemporada;
	private List<String> capitulos;
	private int sumaOpiniones, numeroOpiniones;

	public Temporada(String nombreTemporada) {
		super();
		this.nombreTemporada = nombreTemporada;
		this.capitulos = new ArrayList<>();
		this.sumaOpiniones = 0;
		this.numeroOpiniones = 0;
	}

	public void annadirCapitulo(String capituloAnnadir) throws SerieException {
		if (capituloAnnadir != null) {
			if (capitulos.contains(capituloAnnadir)) {
				throw new SerieException("El capitulo ya existe");
			} else {
				capitulos.add(capituloAnnadir);
			}
		}
	}

	public boolean eliminarCapitulo(String capituloEliminar) throws SerieException {
		boolean eliminado = false;
		if (capituloEliminar != null) {
			if (capitulos.contains(capituloEliminar)) {
				capitulos.remove(capituloEliminar);
			} else {
				throw new SerieException("El capitulo no existe.");
			}
		}
		return eliminado;
	}

	public String primerCapituloQueContieneEstaPalabra(String palabra) throws SerieException {
		String nombreCapitulo = "";
		if (palabra != null) {
			for (int i = 0; i < capitulos.size() && nombreCapitulo.isEmpty(); i++) {
				if (capitulos.get(i) != null && capitulos.get(i).equals(palabra)) {
					nombreCapitulo = capitulos.get(i);
				}
			}
		}
		if (nombreCapitulo.isEmpty()) {
			throw new SerieException("Capitulo no encontrado");
		}
		return nombreCapitulo;
	}

	public void anadirCapituloDespues(String nombreCapituloAnnadir, String nombreCapituloAnterior)
			throws SerieException {
		if (capitulos.contains(nombreCapituloAnterior)) {
			int indiceCapituloAnterior = capitulos.indexOf(nombreCapituloAnterior);
			if (indiceCapituloAnterior == capitulos.size() - 1) {
				capitulos.add(nombreCapituloAnnadir);
			} else {
				capitulos.add(indiceCapituloAnterior + 1, nombreCapituloAnnadir);
			}
		} else {
			throw new SerieException("Nombre del capitulo no existente.");
		}
	}

	public void valorar(int valoracion) throws SerieException {
		if (valoracion >= 0 && valoracion <= 10) {
			this.sumaOpiniones += valoracion;
			this.numeroOpiniones++;
		} else {
			throw new SerieException("La valoracion debe estar entre 0 y 10");
		}
	}

	@Override
	public String toString() {
		return String.format("Nombre temporada: %s, numero capitulos: %s, valoracion: %s", nombreTemporada,
				capitulos.size(), getNotaMedia());
	}

	public double getNotaMedia() {
		return numeroOpiniones > 0 ? sumaOpiniones / numeroOpiniones : 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreTemporada);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj != null && obj instanceof Temporada && this.hashCode() == obj.hashCode();
	}

	@Override
	public int compareTo(Temporada otraTemp) {
		return this.capitulos.size() - otraTemp.capitulos.size();
	}

}
