package umu.tds.apps.modelo;

import java.time.LocalDate;

public class DescuentoTemporal implements Descuento{
	
	private final Double valorDescuento = 0.20;
	private final static int duracionDescuento = 15;
	private final static LocalDate fechaInicio = LocalDate.parse("2020-11-9");

	
	
	@Override
	public Double calcDescuento() {
		
		return Usuario.getPreciobase()*valorDescuento;
	}
	
	@Override
	public boolean validar(Usuario user) {
		
		LocalDate actual = LocalDate.now();
		if (actual.isAfter(fechaInicio) && actual.isBefore(fechaInicio.plusDays(duracionDescuento)))
			return true;
		
		
		return false;
	}

	@Override
	public Descuento generarDescuento(Usuario user) {
		if (validar(user))
			return new DescuentoTemporal();
		return null;
	}
	
	
	public Double getValorDescuento() {
		return valorDescuento;
	}

}
