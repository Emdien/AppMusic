package umu.tds.apps.modelo;

public class Cancion {
	private String titulo;
	private String rutaFichero;
	private String interprete;
	private String estilo;
	private int numReproducciones;
	
	
	public Cancion(String titulo, String rutaFichero, String interprete, String estilo, int numReproducciones) {
		super();
		this.titulo = titulo;
		this.rutaFichero = rutaFichero;
		this.interprete = interprete;
		this.estilo = estilo;
		this.numReproducciones = numReproducciones;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getRutaFichero() {
		return rutaFichero;
	}

	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}

	public String getInterprete() {
		return interprete;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public void setNumReproducciones(int numReproducciones) {
		this.numReproducciones = numReproducciones;
	}
	
	
	
}
