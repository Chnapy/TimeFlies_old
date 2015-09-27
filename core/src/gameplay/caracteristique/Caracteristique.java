/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

import static test.MainTest.DF;

/**
 * Caracteristique.java
 * Représente une caractéristique.
 *
 */
public abstract class Caracteristique {

	//Valeur totale de la caractéristique
	private int total;

	//Valeur actuelle de la caractéristique. Ne peut pas dépasser total
	private int actu;

	/**
	 * Représente une caractéristique avec une valeur totale et une valeur
	 * actuelle
	 *
	 * @param total
	 * @param actu
	 */
	public Caracteristique(int total, int actu) {
		this.total = total;
		this.actu = actu;
	}

	/**
	 * Ajoute proprement une valeur à la valeur actu
	 *
	 * @param gain
	 */
	public void add(int gain) {
		actu += gain;
//		if (actu > total) {
//			actu = total;
//		}
	}

	/**
	 * Retire proprement une valeur à la valeur actu
	 *
	 * @param perte
	 */
	public void supp(int perte) {
		actu -= perte;
		if (actu < 0) {
			actu = 0;
		}
	}

	/**
	 *
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 *
	 * @param actu
	 */
	public void setActu(int actu) {
		this.actu = actu;
	}

	/**
	 *
	 * @return
	 */
	public int getTotal() {
		return total;
	}

	/**
	 *
	 * @return
	 */
	public int getActu() {
		return actu;
	}

	public abstract boolean equals(Object o);

}
