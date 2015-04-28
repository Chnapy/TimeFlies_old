package gameplay.caracteristique;
/**
 * Initiative.java
 * Gère l'initiative l'entité.
 *
 */
public class Initiative extends Caracteristique{
	
	/**
	 * 
	 * @param actu
	 */
	public Initiative(int actu) {
		super(actu, actu);
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Initiative;
	}

}
