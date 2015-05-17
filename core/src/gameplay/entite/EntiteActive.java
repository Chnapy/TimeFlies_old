/*
 * 
 * 
 * 
 */
package gameplay.entite;

import com.badlogic.gdx.utils.Array;
import gameplay.caracteristique.Carac;
import gameplay.caracteristique.Caracteristique;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.envoutement.Envoutement;
import gameplay.sort.Sort;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import gameplay.sort.pileaction.Action;
import gameplay.sort.pileaction.ActionDeplacement;
import gameplay.sort.pileaction.ActionLancerSort;
import gameplay.sort.pileaction.PileAction;
import java.awt.Point;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * EntiteActive.java
 * Représente une entité active (controlable par un joueur).
 * Joue son tour.
 *
 */
public abstract class EntiteActive extends Entite {

	//Tableau des sorts actifs
	private SortActif[] tabSortActif;

	//Liste des envoutements
	private Array<Envoutement> listEnvoutements;

	//Est en train de se déplacer
	private boolean enDeplacement;

	//Etat de l'entité en prenant en compte les actions prévues de la pile 
	//d'actions
	private EtatEntite etatNow;

	//Sort actif en phase d'être lancé
	private SortActif sortEnCours = null;

	//Temps avant la fin du sort (en ms)
	private long tempsFinSort = -1;

	//Index de la texture de l'entité sur la timeline
	private final int indexTextureTimeline;

	//Pile des actions
	private PileAction pileAction = new PileAction();

	/**
	 *
	 * @param nom
	 * @param posX
	 * @param posY
	 * @param orientation
	 * @param cPhysique
	 * @param sortsPassifs
	 * @param sortsActifs
	 * @param indexTexture
	 * @param iTextureTimeline
	 */
	public EntiteActive(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs,
			int indexTexture,
			int iTextureTimeline) {

		super(nom, posX, posY, orientation, sortsPassifs, cPhysique, indexTexture);

		tabSortActif = sortsActifs;
		listEnvoutements = new Array<Envoutement>();
		niveauSymbol = new NiveauSymbolique(Stream.concat(Arrays.stream(sortsPassifs), Arrays.stream(sortsActifs)).toArray(Sort[]::new));
		indexTextureTimeline = iTextureTimeline;
	}

	/**
	 * Joue le tour sur l'ensemble du temps d'action de l'entité.
	 * Utilise les actions de la pile d'actions.
	 *
	 * @param time
	 */
	@Override
	public void jouerTour(long time) {
		if (!actionIsRunning() && pileAction.pile.size > 0) {
			System.out.println("Action lancée");
			if (pileAction.pile.get(0) instanceof ActionDeplacement) {
				etatNow = EtatEntite.DEPLACEMENT;
			} else {
				etatNow = EtatEntite.SORT;
				tempsFinSort = ((ActionLancerSort) pileAction.pile.get(0)).getSort().getTempsAction() + time;
			}
			setChanged();
			notifyObservers(pileAction.getFirst());
		}
		if (tempsFinSort != -1 && tempsFinSort <= time) {
			tempsFinSort = -1;
		}
	}

	/**
	 * Renvoie si une action est en court d'exécution
	 *
	 * @return true si une action se déroule (déplacement ou sort) false sinon
	 */
	public boolean actionIsRunning() {
		return enDeplacement || tempsFinSort != -1;
	}

	/**
	 * Ajoute une action dans la pile d'action
	 *
	 * @param a
	 */
	public void addAction(Action a) {
		pileAction.add(a);
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
		pileAction.pile.clear();
		System.out.println("FIN Tour actif : " + nom);
		for (Envoutement envout : listEnvoutements) {
			envout.actionFinTour();
		}
		caracPhysique.setActu(Carac.TEMPSACTION, caracPhysique.getCaracteristique(Carac.TEMPSACTION).getTotal());
	}

	/**
	 * Renvoie l'état de l'entité en prenant en compte la pile d'actions
	 *
	 * @return
	 */
	@Override
	public EtatEntite getEtatNow() {
		if (actionIsRunning()) {
			return etatNow;
		} else {
			return getEtat();
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
	 * Renvoie la dernière position d'arrivée de la pile d'actions
	 *
	 * @return la position de l'entité une fois que la liste d'action sera fini
	 */
	public Point getLastPosition() {
		Point ret = getCaracSpatiale().getPosition();
		for (int i = 0; i < pileAction.pile.size; i++) {
			if (pileAction.pile.get(i) instanceof ActionDeplacement) {
				ret = ((ActionDeplacement) pileAction.pile.get(i)).getPath().get(((ActionDeplacement) pileAction.pile.get(i)).getPath().size - 1);
			}
		}
		return ret;
	}

	public SortActif[] getTabSortActif() {
		return tabSortActif;
	}

	public SortActif getSortEnCours() {
		return sortEnCours;
	}

	/**
	 * Défini le sort en cours d'après son index, et le renvoie
	 *
	 * @param index
	 * @return
	 */
	public SortActif setSortEnCours(int index) {
		sortEnCours = getSort(index);
		return sortEnCours;
	}

	/**
	 * Récupère un sort depuis son index
	 *
	 * @param index
	 * @return
	 */
	private SortActif getSort(int index) {
		for (SortActif sort : tabSortActif) {
			if (sort.getIndex() == index) {
				return sort;
			}
		}
		return null;
	}

	public int getIndexTextureTimeline() {
		return indexTextureTimeline;
	}

	public Caracteristique getTempsAction() {
		return getCaracPhysique().getCaracteristique(Carac.TEMPSACTION);
	}

}
