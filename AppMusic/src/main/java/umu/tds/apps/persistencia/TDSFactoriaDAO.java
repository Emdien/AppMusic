package umu.tds.apps.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO{

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		return AdaptadorCancionTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorListaReproduccionDAO getListaReproduccionDAO() {
		return AdaptadorListaReproduccionTDS.getUnicaInstancia();
	}

}
