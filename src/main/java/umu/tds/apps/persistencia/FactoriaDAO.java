package umu.tds.apps.persistencia;

public abstract class FactoriaDAO {

	private static FactoriaDAO unicaInstancia;	// Singleton
	public static final String DAO_TDS = "umu.tds.apps.persistencia.TDSFactoriaDAO";
	
	
	// (Gonzalo) Este método de obtener la instancia de la factoria para un tipo concreto
	// en verdad no se usará, ya que solo disponemos de una factoria
	
	public static FactoriaDAO getInstancia(String tipo) throws DAOException {
		if (unicaInstancia == null) 
			try {
				unicaInstancia = (FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		return unicaInstancia;
	}
	
	// (Gonzalo) Este método en vez de llamar al anterior, podría hacer como arriba
	// Es decir, hacer el casting a FactoriaDAO del objeto devuelto
	// por Class.forName(FactoriaDAO.DAO_TDS).newInstance()
	
	public static FactoriaDAO getInstancia() throws DAOException {
		if (unicaInstancia == null) return getInstancia(FactoriaDAO.DAO_TDS);
		else return unicaInstancia;
	}
	
	
	//TODO: Métodos factoria que devuelven adaptadores
	
	
}
