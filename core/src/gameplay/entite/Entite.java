/*
 * 
 * 
 * 
 */
package gameplay.entite;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import general.Mode;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.CaracteristiqueSpatiale;
import gameplay.effet.Effet;
import gameplay.envoutement.Envoutement;
import gameplay.envoutement.EnvoutementEffets;
import gameplay.sort.SortPassif;
import gameplay.sort.SortPassifBonus;
import gameplay.sort.SortPassifEffets;
import general.Orientation;
import java.util.Observable;

/**
 * Entite.java
 * Représente une entité, visible en combat.
 *
 */
public abstract class Entite extends Observable {

	//Caractéristiques physiques de l'entité
	protected final CaracteristiquePhysique caracPhysique;

	//Nom de l'entité
	protected final String nom;

	//Index pour la vue
	private final int index;

	//Etat de l'entité (déplacement, sort, ...) sans prendre en compte les 
	//actions prévues de la pile d'actions
	protected Mode etat;

	//Niveau symbolique
	protected NiveauSymbolique niveauSymbol;

	//Caractéristiques spatiales de l'entité
	protected CaracteristiqueSpatiale caracSpatiale;

	//Sorts passifs de l'entité
	protected SortPassif[] tabSortPassif;

	//Liste des envoutements
	protected final Array<Envoutement> listEnvoutements;

	/**
	 *
	 * @param n
	 * @param posX
	 * @param posY
	 * @param orient
	 * @param sortsPassifs
	 * @param caracPhysique
	 * @param index
	 */
	public Entite(String n,
			int posX, int posY, Orientation orient,
			SortPassif[] sortsPassifs, CaracteristiquePhysique caracPhysique,
			int index) {
		nom = n;
		caracSpatiale = new CaracteristiqueSpatiale(posX, posY, orient);
		tabSortPassif = sortsPassifs;
		this.caracPhysique = caracPhysique;
		etat = Mode.DEPLACEMENT;
		this.index = index;
		listEnvoutements = new Array<Envoutement>();
	}

	//Jeu du tour de l'entité
	public abstract void jouerTour(long time);

	/**
	 * Recoit un sort d'une entité autre.
	 * Lance les sorts passifs si possibilité.
	 *
	 * @param effets
	 * @param lanceur
	 * @param oriAttaque
	 * @param critique
	 */
	public void recoitSort(Effet[] effets, Entite lanceur, Orientation oriAttaque, boolean critique) {
		if (lanceur != null) {
			for (SortPassif sortPassif : tabSortPassif) {
				if (sortPassif instanceof SortPassifEffets) {
					((SortPassifEffets) sortPassif).applyEffect(effets, lanceur, this, true, critique);
				}
			}
			for (Envoutement envoutement : listEnvoutements) {
				if (envoutement instanceof EnvoutementEffets) {
					((EnvoutementEffets) envoutement).applyEffect(effets, lanceur, true, critique);
				}
			}
		}
		for (Effet effet : effets) {
			effet.lancerEffetEntite(this, oriAttaque, critique);
		}
		if (lanceur != null) {
			for (SortPassif tabSortPassif1 : tabSortPassif) {
				if (tabSortPassif1 instanceof SortPassifEffets) {
					((SortPassifEffets) tabSortPassif1).applyEffect(effets, lanceur, this, false, critique);
				}
			}
			for (Envoutement envoutement : listEnvoutements) {
				if (envoutement instanceof EnvoutementEffets) {
					((EnvoutementEffets) envoutement).applyEffect(effets, lanceur, false, critique);
				}
			}
		}
	}

	/**
	 * Change la position
	 *
	 * @param pt
	 */
	public void setPosition(GridPoint2 pt) {
		caracSpatiale.getPosition().x = pt.x;
		caracSpatiale.getPosition().y = pt.y;
	}

	public void move(int x, int y) {
		caracSpatiale.move(x, y);
	}

	public void premiereAction() {
		for (SortPassif sort : tabSortPassif) {
			if (sort instanceof SortPassifBonus) {
				sort.lancerSort(this, null, null, Orientation.NORD, false);
			}
		}
	}

	public void notifierObserveurs(Object[] envois) {
		setChanged();
		notifyObservers(envois);
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
	public Mode getEtat() {
		return etat;
	}

	/**
	 *
	 * @return l'état de l'entité en se moment
	 */
	public abstract Mode getEtatNow();

	public void setEtat(Mode etat) {
		this.etat = etat;
	}

	public int getIndex() {
		return index;
	}

}
