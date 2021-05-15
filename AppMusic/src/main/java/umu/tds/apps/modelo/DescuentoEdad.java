package umu.tds.apps.modelo;

import java.time.LocalDate;
import java.time.ZoneId;


public class DescuentoEdad implements Descuento{
	
	private final Double valorDescuento = 0.50;
	private static final int VALOR_EDAD = 65;

	@Override
	public Double calcDescuento() {
		
		return Usuario.getPreciobase()*valorDescuento;
	}


	@Override
	public Descuento generarDescuento(Usuario user) {
		if (validar(user))
			return new DescuentoEdad();
		return null;
	}

	
	@Override
	public boolean validar(Usuario user) {
		LocalDate actual = LocalDate.now();
		
		LocalDate nacim = user.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		long years = java.time.temporal.ChronoUnit.YEARS.between(nacim, actual);
		
		if (years >= VALOR_EDAD)
			return true;
		return false;
	}


	@Override
	public Double getValorDescuento() {
		return valorDescuento;
	}

}