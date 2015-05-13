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
import java.awt.Point;
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
	protected EtatEntite etat;

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
	 * @param caracPhysique
	 */
	public Entite(String n,
			int posX, int posY, Orientation orient,
			SortPassif[] sortsPassifs, CaracteristiquePhysique caracPhysique) {
		nom = n;
		caracSpatiale = new CaracteristiqueSpatiale(posX, posY, orient);
		tabSortPassif = sortsPassifs;
		this.caracPhysique = caracPhysique;
		etat = EtatEntite.DEPLACEMENT;
	}

	public void setCaracSpatiale(CaracteristiqueSpatiale caracSpatiale) {
		this.caracSpatiale = caracSpatiale;
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

	public abstract void jouerTour();

	/**
	 * lance les effets sur la victime en prenant en compte les passif
	 * si le lanceur != null
	 *
	 * @param effets
	 * @param lanceur
	 */
	public void recoitSort(Effet[] effets, Entite lanceur) {
		int pourcentageSupp = 0;
		Orientation oriAttaque = getOrientation(this.getCaracSpatiale().getPosition(), lanceur.getCaracSpatiale().getPosition());
		if (!oriAttaque.equals(this.getCaracSpatiale().getOrientation())) {
			if (oriAttaque.invert().equals(this.getCaracSpatiale().getOrientation())) {
				//TODO pourcentage de Cous critique a définir
				pourcentageSupp = 30;
			}
			this.getCaracSpatiale().setOrientation(oriAttaque);
		}
		if (lanceur != null) {
			for (int i = 0; i < tabSortPassif.length; i++) {
				if (tabSortPassif[i] instanceof SortPassifEffets) {
					((SortPassifEffets) tabSortPassif[i]).applyEffect(effets, lanceur, this, true, pourcentageSupp);
				};
			}
		}
		for (int i = 0; i < effets.length; i++) {
			effets[i].lancerEffet(this, pourcentageSupp);
		}
		if (lanceur != null) {
			for (int i = 0; i < tabSortPassif.length; i++) {
				if (tabSortPassif[i] instanceof SortPassifEffets) {
					((SortPassifEffets) tabSortPassif[i]).applyEffect(effets, lanceur, this, false, pourcentageSupp);
				};
			}
		}
	}

	/**
	 *
	 * @param origine
	 * @param point
	 * @return l'orientation de l'origine qui regarde vers le point
	 */
	private Orientation getOrientation(Point origine, Point point) {
		double vecX = point.getX() - origine.getX();
		double vecY = point.getY() - origine.getY();

		if (vecX > 0) {
			if (vecY > 0) {
				if (vecX < vecY) {
					return Orientation.S;
				} else {
					return Orientation.E;
				}
			} else {
				if (vecX < vecY) {
					return Orientation.N;
				} else {
					return Orientation.E;
				}
			}
		} else {
			if (vecY > 0) {
				if (vecX < vecY) {
					return Orientation.S;
				} else {
					return Orientation.O;
				}
			} else {
				if (vecX < vecY) {
					return Orientation.N;
				} else {
					return Orientation.O;
				}
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

	/**
	 * 
	 * @return l'état de l'entité vue du joueur
	 */
	public EtatEntite getEtat() {
		return etat;
	}
	
	/**
	 * 
	 * @return l'état de l'entité en se moment
	 */
	public abstract EtatEntite getEtatNow();
	
	public void setEtat(EtatEntite etat) {
		this.etat = etat;
	}

}
