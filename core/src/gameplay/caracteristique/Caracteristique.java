/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * Caracteristique.java
 * Représente une caractéristique.
 *
 */
public abstract class Caracteristique {

	private int total;
	private int actu;

	/**
	 *
	 * @param total
	 * @param actu
	 */
	public Caracteristique(int total, int actu) {
		this.total = total;
		this.actu = actu;
	}

	/**
	 *
	 * @param gain
	 */
	public void add(int gain) {
		actu += gain;
		if (actu > total) {
			actu = total;
		}
	}

	/**
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

}
