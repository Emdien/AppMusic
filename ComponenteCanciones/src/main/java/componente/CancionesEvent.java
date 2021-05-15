package componente;

import java.util.EventObject;

public class CancionesEvent extends EventObject {
	
	private Canciones cancionesNuevas;
	private Canciones cancionesAnteriores;
	
	public CancionesEvent(Object source, Canciones nuevas, Canciones anteriores) {
		super(source);
		// TODO Auto-generated constructor stub
		this.cancionesAnteriores = anteriores;
		this.cancionesNuevas = nuevas;
	}

	public Canciones getCancionesNuevas() {
		return cancionesNuevas;
	}

	public Canciones getCancionesAnteriores() {
		return cancionesAnteriores;
	}

	
	
	
	
}
