package componente;

import java.util.Vector;

public class CargadorCanciones implements IBuscarCanciones{

	private Canciones canciones;
	private Vector<CancionesListener> oyentes = new Vector<>();
	
	
	
	
	@Override
	public void setArchivoCanciones(String archivo) {
		Canciones cancionesAnterior = this.canciones; 
		Canciones cancionesNuevo = MapperCancionesXMLtoJava.cargarCanciones(archivo);
		
		this.canciones = cancionesNuevo;
		if (!cancionesNuevo.equals(cancionesAnterior)) {
			CancionesEvent event = new CancionesEvent(this, cancionesNuevo, cancionesAnterior);
			notificarCambio(event);
		}
		
	}
	
	public Canciones getArchivoCanciones() {
		return canciones;
	}
	
	public synchronized void addCancionesListener(CancionesListener listener) {
		oyentes.add(listener);
	}
	
	public synchronized void removeCancionesListener(CancionesListener listener) {
		oyentes.remove(listener);
	}

	@SuppressWarnings("unchecked")
	private void notificarCambio(CancionesEvent event) {
		Vector<CancionesListener> lista;
		synchronized (this) {
			lista=(Vector<CancionesListener>) oyentes.clone();
		}
		for (CancionesListener cl : lista) {
			cl.nuevasCanciones(event);
		}
	}
}
