/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * Fatigue.java
 * Gère la fatigue de l'entité.
 * Varie entre 0 et 100 et se compte en pourcentage.
 * La fatigue réduit la valeur initiale du temps d'action.
 * Une fatigue de 0% n'influencera pas le temps d'action.
 * Une fatigue de 100% supprimera tout le temps d'action.
 *
 */
public class Fatigue extends Caracteristique {

	/**
	 *
	 * @param total
	 * @param actu
	 */
	public Fatigue(int total, int actu) {
		super(total, actu);
	}

	/**
	 * equals en fontion du type de la classe (fatique)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof Fatigue;
	}

}
