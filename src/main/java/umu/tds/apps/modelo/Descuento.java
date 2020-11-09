package umu.tds.apps.modelo;


// Patron Estrategia


public interface Descuento {
	
	// Calcula el precio con el descuento aplicado

	public Double calcDescuento();
	
	// Genera el descuento tras comprobar si el usuario es valido
	
	public Descuento generarDescuento(Usuario user);
	
	// Valida - comprueba si el usuario es valido para este descuento
	
	public boolean validar(Usuario user);
	
	// Devuelve el valor del descuento en concreto
	
	public Double getValorDescuento();
}
