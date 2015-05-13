/*
 * 
 * 
 * 
 */
package gameplay.entite;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

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
 *
 */
public abstract class EntiteActive extends Entite {

	private SortActif[] tabSortActif;
	private Array<Envoutement> listEnvoutements;

	//Est en train de se déplacer
	private boolean enDeplacement;
	private EtatEntite etatNow;

	private SortActif sortEnCours = null;
	private long tempsFinSort =-1;
	private final int indexTextureTimeline;

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
	 * @param iTextureTimeline
	 */
	public EntiteActive(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs,
			int iTextureTimeline) {

		super(nom, posX, posY, orientation, sortsPassifs, cPhysique);

		tabSortActif = sortsActifs;
		listEnvoutements = new Array<Envoutement>();
		niveauSymbol = new NiveauSymbolique(Stream.concat(Arrays.stream(sortsPassifs), Arrays.stream(sortsActifs)).toArray(Sort[]::new));
		indexTextureTimeline = iTextureTimeline;
	}

	@Override
	public void jouerTour() {
		long debutTour = TimeUtils.millis();
		long tempsAction = caracPhysique.getCaracteristique(Carac.TEMPSACTION).getActu();
		long palier = debutTour;
		long time = TimeUtils.millis();

		System.out.println("DEBUT Tour actif pendant " + caracPhysique.getCaracteristique(Carac.TEMPSACTION).getActu() + "ms : " + nom);

		while (time < debutTour + tempsAction || enDeplacement) {
			if (time >= palier + 10) {
				palier = time;
				caracPhysique.supp(Carac.TEMPSACTION, 10);
				setChanged();
				notifyObservers(-1);
			}
			if (!actionIsRunning() && pileAction.pile.size > 0) {
				System.out.println("Action lancée");
				if(pileAction.pile.get(0) instanceof ActionDeplacement){
					etatNow = EtatEntite.DEPLACEMENT;
				}else{
					etatNow = EtatEntite.SORT;
					tempsFinSort=((ActionLancerSort)pileAction.pile.get(0)).getSort().getTempsAction()+time;
				}
				setChanged();
				notifyObservers(pileAction.getFirst());
			}
			if(tempsFinSort!=-1 && tempsFinSort<=time){
				tempsFinSort = -1;
			}
			/*else if (sortEnCours != null && TempsFinSort == -1) {
				TempsFinSort = time + sortEnCours.getTempsAction();
			} else if (sortEnCours != null && TempsFinSort <= time) {
				sortEnCours = null;
				TempsFinSort = -1;
			}*/
			
			time = TimeUtils.millis();
		}
		pileAction.pile.clear();
		System.out.println("FIN Tour actif : " + nom);
	}

	/**
	 *
	 * @return true si une action se déroule (déplacement ou sort) false sinon
	 */
	public boolean actionIsRunning() {
		return enDeplacement || tempsFinSort != -1;
	}

	/**
	 * ajoute une action dans la pile d'action
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
		for (Envoutement envout : listEnvoutements) {
			envout.actionFinTour();
		}
		caracPhysique.setActu(Carac.TEMPSACTION, caracPhysique.getCaracteristique(Carac.TEMPSACTION).getTotal());
	}

	public EtatEntite getEtatNow(){
		if(actionIsRunning()){
			return etatNow;
		}
		else{
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

	public int getIndexTextureTimeline() {
		return indexTextureTimeline;
	}

	public Caracteristique getTempsAction() {
		return getCaracPhysique().getCaracteristique(Carac.TEMPSACTION);
	}

}
