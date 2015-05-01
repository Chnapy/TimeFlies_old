/*
 * 
 * 
 * 
 */
package gameplay.entite;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import gameplay.caracteristique.Carac;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.envoutement.Envoutement;
import gameplay.sort.Sort;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import java.awt.Point;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * EntiteActive.java
 * Représente une entité active (controlable par un joueur).
 *
 */
public abstract class EntiteActive extends Entite {

	private SortActif[] tabSortActif;
	private Array<Envoutement> listEnvoutements;

	//Mode Deplacement (true) ou Sort (false)
	private boolean modeDeplacement = true;

	//Est en train de se déplacer
	private boolean enDeplacement;

	private SortActif sortEnCours = null;

	/**
	 *
	 * @param nom
	 * @param posX
	 * @param posY
	 * @param orientation
	 * @param cPhysique
	 * @param sortsPassifs
	 * @param sortsActifs
	 */
	public EntiteActive(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs) {

		super(nom, posX, posY, orientation, sortsPassifs, cPhysique);

		tabSortActif = sortsActifs;
		listEnvoutements = new Array<Envoutement>();
		super.niveauSymbol = new NiveauSymbolique(Stream.concat(Arrays.stream(sortsPassifs), Arrays.stream(sortsActifs)).toArray(Sort[]::new));
	}

	@Override
	public void jouerTour() {
		long debutTour = TimeUtils.millis();
		long tempsAction = caracPhysique.getCaracteristique(Carac.TEMPSACTION).getActu() * 1000;
		System.out.println("DEBUT Tour actif pendant " + caracPhysique.getCaracteristique(Carac.TEMPSACTION).getActu() + "s : " + nom);
		while (TimeUtils.millis() < debutTour + tempsAction);
		System.out.println("FIN Tour actif : " + nom);
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chaque tour
	 * global commencera.
	 */
	public void debutTourGlobal() {
		for (Envoutement envout : listEnvoutements) {
			envout.actionDebutTourGlobal();
		}
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chaque tour
	 * global se terminera.
	 */
	public void finTourGlobal() {
		for (Envoutement envout : listEnvoutements) {
			envout.actionFinTourGlobal();
		}
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chacun de ses
	 * tours commencera.
	 */
	public void debutTour() {
		for (Envoutement envout : listEnvoutements) {
			envout.actionDebutTour();
		}
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chacun de ses
	 * tours finira.
	 */
	public void finTour() {
		for (Envoutement envout : listEnvoutements) {
			envout.actionFinTour();
		}
	}

	/**
	 * Change la position, notifie la vue
	 *
	 * @param listeParcours
	 */
	public void setPosition(Array<Point> listeParcours) {
		caracSpatiale.getPosition().x = listeParcours.peek().x;
		caracSpatiale.getPosition().y = listeParcours.peek().y;
		setChanged();
		notifyObservers(listeParcours);
	}

	public CaracteristiquePhysique getCaracPhysique() {
		return caracPhysique;
	}

	/**
	 *
	 * @return true si l'entitée est en déplacement
	 */
	public boolean isEnDeplacement() {
		return enDeplacement;
	}

	/**
	 *
	 * @param enDeplacement
	 */
	public void setEnDeplacement(boolean enDeplacement) {
		this.enDeplacement = enDeplacement;
	}

	/**
	 *
	 * @param deplacer
	 */
	public void setModeDeplacement(boolean deplacer) {
		System.out.println("Mode deplacement : " + deplacer);
		this.modeDeplacement = deplacer;
	}

	/**
	 *
	 * @return true si le mode de déplacement est activé
	 */
	public boolean isModeDeplacement() {
		return modeDeplacement;
	}

	public SortActif[] getTabSortActif() {
		return tabSortActif;
	}

	public SortActif getSortEnCours() {
		return sortEnCours;
	}

	public SortActif setSortEnCours(int index) {
		sortEnCours = getSort(index);
		return sortEnCours;
	}

	private SortActif getSort(int index) {
		for (SortActif sort : tabSortActif) {
			if (sort.getIndex() == index) {
				return sort;
			}
		}
		return null;
	}

}
