package gameplay.effet;

public interface Declancher {
	
	/**
	 * 
	 * @param effet
	 * @param min
	 * @param max
	 * @return true si le declancheur est le bon et es dans l'interval min/max
	 */
	public boolean canDeclanche(Effet effet,int min, int max);
}
