package umu.tds.apps.persistencia;

public abstract class FactoriaDAO {

	private static FactoriaDAO unicaInstancia;	// Singleton
	public static final String DAO_TDS = "umu.tds.apps.persistencia.TDSFactoriaDAO";
	
	
	// (Gonzalo) Este m�todo de obtener la instancia de la factoria para un tipo concreto
	// en verdad no se usar�, ya que solo disponemos de una factoria
	// (Gonzalo) he modificado Class.forName().newInstance a .getDeclaredConstructor().newInstance()
	// Ya que dicho metodo est� deprecado. Hay que testear.
	
	public static FactoriaDAO getInstancia(String tipo) throws DAOException {
		if (unicaInstancia == null) 
			try {
				unicaInstancia = (FactoriaDAO) Class.forName(tipo).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		return unicaInstancia;
	}
	
	// (Gonzalo) Este m�todo en vez de llamar al anterior, podr�a hacer como arriba
	// Es decir, hacer el casting a FactoriaDAO del objeto devuelto
	// por Class.forName(FactoriaDAO.DAO_TDS).newInstance()
	
	public static FactoriaDAO getInstancia() throws DAOException {
		if (unicaInstancia == null) return getInstancia(FactoriaDAO.DAO_TDS);
		else return unicaInstancia;
	}
	
	
	// M�todos factoria que devuelven adaptadores
	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();
	public abstract IAdaptadorCancionDAO getCancionDAO();
	public abstract IAdaptadorListaReproduccionDAO getListaReproduccionDAO();
	
}
