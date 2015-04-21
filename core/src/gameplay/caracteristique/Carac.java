/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * Carac.java
 * Représente l'ensemble des caractéristiques.
 *
 */
public enum Carac {

	/**
	 * La vie d'une entité.
	 * Si la vie tombe à 0, l'entité meurt.
	 */
	VITALITE,
	/**
	 * Le temps d'action d'une entité active.
	 * Les sorts actifs utilisent le temps d'action. Ce temps d'action diminue
	 * pendant
	 * le tour de jeu de l'entité en fonction du temps.
	 */
	TEMPSACTION,
	/**
	 * Le temps supplémentaire d'une entité active.
	 * Ce temps est utilisé lorsqu'un sort actif est en cours d'exécution et
	 * qu'il
	 * dépasse le temps d'action.
	 */
	TEMPSSUP,
	/**
	 * La fatigue d'une entité active.
	 * La fatigue fait varier le niveau de temps d'action disponible.
	 */
	FATIGUE,
	/**
	 * La vitesse d'action d'une entité active.
	 * La vitesse d'action fait varier le niveau de temps d'action utilisé par
	 * les sorts actifs.
	 */
	VITESSEACTION
}
