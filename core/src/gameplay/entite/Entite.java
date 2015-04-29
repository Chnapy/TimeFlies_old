/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.CaracteristiqueSpatiale;
import gameplay.caracteristique.Orientation;
import gameplay.effet.Effet;
import gameplay.sort.SortPassif;
import gameplay.sort.SortPassifEffets;

import java.util.Observable;

/**
 * Entite.java
 * Représente une entité, visible en combat.
 *
 */
public abstract class Entite extends Observable {

	protected final CaracteristiquePhysique caracPhysique;
	/**
	 *
	 */
	protected final String nom;
	
	public void setCaracSpatiale(CaracteristiqueSpatiale caracSpatiale) {
		this.caracSpatiale = caracSpatiale;
	}

	/**
	 * 
	 */
	protected NiveauSymbolique niveauSymbol;
	/**
	 *
	 */
	protected CaracteristiqueSpatiale caracSpatiale;

	/**
	 * Que l'entité soit active ou passive, elle peut posséder des sorts passifs
	 */
	protected SortPassif[] tabSortPassif;

	/**
	 *
	 * @param n
	 * @param posX
	 * @param posY
	 * @param orient
	 * @param sortsPassifs
	 */
	public Entite(String n,
			int posX, int posY, Orientation orient,
			SortPassif[] sortsPassifs,CaracteristiquePhysique caracPhysique) {
		nom = n;
		caracSpatiale = new CaracteristiqueSpatiale(posX, posY, orient);
		tabSortPassif = sortsPassifs;
		this.caracPhysique = caracPhysique;
	}
	public CaracteristiquePhysique getCaracPhysique() {
		return caracPhysique;
	}
	/**
	 * 
	 * @return niveau symbolique
	 */
	public NiveauSymbolique getNiveauSymbol() {
		return niveauSymbol;
	}

	public void jouerTour() {
		//TODO
	}

	/**
	 * lance les effets sur la victime en prenant en compte les passif
	 * si le lanceur != null
	 * @param effets
	 * @param lanceur
	 */
	public void recoitSort(Effet[] effets, Entite lanceur) {
		if(lanceur!=null){
			for (int i = 0; i < tabSortPassif.length; i++) {
				if(tabSortPassif[i] instanceof SortPassifEffets)
					((SortPassifEffets)tabSortPassif[i]).applyEffect(effets, lanceur, this, true);;
			}
		}
		for (int i = 0; i < effets.length; i++) {
			effets[i].lancerEffet(this);
		}
		if(lanceur!=null){
			for (int i = 0; i < tabSortPassif.length; i++) {
				if(tabSortPassif[i] instanceof SortPassifEffets)
					((SortPassifEffets)tabSortPassif[i]).applyEffect(effets, lanceur, this, false);;
			}
		}
	}

	/**
	 * 
	 * @return le nom de l'entité
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @return les caractéristique spatial de l'entité
	 */
	public CaracteristiqueSpatiale getCaracSpatiale() {
		return caracSpatiale;
	}

	/**
	 * 
	 * @return les sort passif de l'entitée
	 */
	public SortPassif[] getTabSortPassif() {
		return tabSortPassif;
	}

}
