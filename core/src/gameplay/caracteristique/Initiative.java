package gameplay.caracteristique;

/**
 * Initiative.java
 * Gère l'initiative l'entité.
 * Défini au début du combat, l'initiative permet d'établir l'ordre
 * de jeu. Ne peut être modifié que par des sorts.
 * Une initiative élevée augmente les chances de commencer son tour parmi les
 * premiers.
 *
 */
public class Initiative extends Caracteristique {

	/**
	 *
	 * @param actu
	 */
	public Initiative(int actu) {
		super(actu, actu);
	}

	/**
	 * equals en fontion du type de la classe (initiative)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof Initiative;
	}

}
