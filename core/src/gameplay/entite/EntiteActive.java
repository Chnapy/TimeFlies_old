/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.Carac;
import gameplay.caracteristique.Caracteristique;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.envoutement.Envoutement;
import gameplay.sort.Sort;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import gameplay.sort.pileaction.Action;
import gameplay.sort.pileaction.pileAction;

import java.awt.Point;
import java.util.Arrays;
import java.util.stream.Stream;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

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

	private SortActif sortEnCours = null;
	private final int indexTextureTimeline;
	
	private pileAction pileAction = new pileAction();

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
		long TempsFinSort = -1;
		
		System.out.println("DEBUT Tour actif pendant " + caracPhysique.getCaracteristique(Carac.TEMPSACTION).getActu() + "s : " + nom);
		
		while (time < debutTour + tempsAction || enDeplacement) {
			if (time >= palier + 10) {
				palier = time;
				caracPhysique.supp(Carac.TEMPSACTION, 10);
				setChanged();
				notifyObservers(-1);
			}
			if(this.sortEnCours!=null && TempsFinSort==-1){
				TempsFinSort=time+this.sortEnCours.getTempsAction();
			}else if(this.sortEnCours!=null && TempsFinSort <=time){
				this.sortEnCours=null;
				TempsFinSort=-1;
			}
			if(!actionIsRunning() && this.pileAction.pile.size>=1){
				System.out.println("action de la pile");
				setChanged();
				notifyObservers(this.pileAction.getFirst());
			}
			time = TimeUtils.millis();
		}
		
		System.out.println("FIN Tour actif : " + nom);
	}
	
	/**
	 * 
	 * @return true si une action se déroule (déplacement ou sort) false sinon
	 */
	public boolean actionIsRunning(){
		return enDeplacement || this.sortEnCours!=null;		
	}
	
	/**
	 * ajoute une action dans la pile d'action
	 * @param action
	 */
	public void addAction(Action a){
		this.pileAction.add(a);
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
