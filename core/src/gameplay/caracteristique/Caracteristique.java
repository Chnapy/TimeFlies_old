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
	
	public Caracteristique(int total, int actu) {
		this.total = total;
		this.actu = actu;
	}
	
	public void add(int gain) {
		actu += gain;
		if(actu > total) {
			actu = total;
		}
	}
	
	public void supp(int perte) {
		actu -= perte;
		if(actu < 0) {
			actu = 0;
		}
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setActu(int actu) {
		this.actu = actu;
	}
	
	public int getTotal() {
		return total;
	}

	public int getActu() {
		return actu;
	}

}
