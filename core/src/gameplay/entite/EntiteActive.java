/*
 * 
 * 
 * 
 */
package gameplay.entite;

import com.badlogic.gdx.math.GridPoint2;
import general.Mode;
import gameplay.caracteristique.Carac;
import gameplay.caracteristique.Caracteristique;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.envoutement.Envoutement;
import gameplay.sort.Sort;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import gameplay.sort.base.Deplacer;
import gameplay.sort.base.Orienter;
import gameplay.sort.pileaction.Action;
import gameplay.sort.pileaction.PileAction;
import general.Orientation;
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
	private final SortActif[] tabSortActif;

	private final Orienter orienter;

	private final Deplacer deplacer;

	//Est en train de se déplacer
	private boolean enDeplacement;

	//Etat de l'entité en prenant en compte les actions prévues de la pile 
	//d'actions
	private Mode etatNow;

	//Sort actif en phase d'être lancé
	private SortActif sortEnCours = null;

	//Temps avant la fin du sort (en ms)
	private long tempsFinSort = -1;
	private long timeLeft = -1;
	private long oldTime = -1;

	//Index de la texture de l'entité sur la timeline
	private final int indexTextureTimeline;

	//Pile des actions
	private final PileAction pileAction = new PileAction();

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
		niveauSymbol = new NiveauSymbolique(Stream.concat(Arrays.stream(sortsPassifs), Arrays.stream(sortsActifs)).toArray(Sort[]::new));
		indexTextureTimeline = iTextureTimeline;
		orienter = new Orienter();
		deplacer = new Deplacer();
	}

	public Orienter getOrienter() {
		return orienter;
	}

	public Deplacer getDeplacer() {
		return deplacer;
	}

	/**
	 * Joue le tour sur l'ensemble du temps d'action de l'entité.
	 * Utilise les actions de la pile d'actions.
	 *
	 * @param time
	 */
	@Override
	public void jouerTour(long time) {
		if (!actionIsRunning() && pileAction.size > 0) {
			try {
				if (pileAction.get(0).getEtat() == Action.EtatAction.DEPLACEMENT) {
					etatNow = Mode.DEPLACEMENT;
				} else {
					etatNow = Mode.SORT;
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				System.out.println("pileAction = " + pileAction);
				System.out.println("pileAction.get(0) = " + pileAction.get(0));
				System.out.println("pileAction.get(0).getEtat() = " + pileAction.get(0).getEtat());
			}
			tempsFinSort = pileAction.get(0).getSort().getTempsAction() + time;
			setChanged();
			notifyObservers(pileAction.removeFirst());
		}
		if (tempsFinSort != -1 && tempsFinSort <= time) {
			tempsFinSort = -1;
		}
		if (oldTime != -1) {
			timeLeft -= time - oldTime;
			oldTime = time;
		} else {
			timeLeft = this.caracPhysique.get(Carac.TEMPSACTION).getActu();
		}

	}
	
	public int getSortFinalTempsAction(SortActif sort) {
		float sortTemps = sort.getTempsAction();
		float vitesse = caracPhysique.get(Carac.VITESSEACTION).getActu();
		return (int)((float)sortTemps / ((float)vitesse / 100));
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
	 * permet de changer le temps restant
	 *
	 * @param time
	 */
	public void subTime(int time) {
		this.timeLeft -= time;
	}

	/**
	 *
	 * @return le temps dispot total avec le temps supplémentaire
	 */
	public long tempsDispo() {
		return timeLeft + this.caracPhysique.get(Carac.TEMPSSUPP).getActu();
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
			envout.debutTourGlobal();
		}
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chaque tour
	 * global se terminera.
	 */
	public void finTourGlobal() {
		for (Envoutement envout : listEnvoutements) {
			envout.finTourGlobal();
		}
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chacun de ses
	 * tours commencera.
	 */
	public void debutTour() {
		for (Envoutement envout : listEnvoutements) {
			envout.debutTour();
		}
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chacun de ses
	 * tours finira.
	 */
	public void finTour() {
		pileAction.clear();
		for (Envoutement envout : listEnvoutements) {
			envout.finTour();
			if(envout.subDuree() <= 0) {
				envout.finEnvoutement();
				listEnvoutements.removeValue(envout, false);
			}
		}
		caracPhysique.setActu(Carac.TEMPSACTION, caracPhysique.get(Carac.TEMPSACTION).getTotal());
	}

	/**
	 * Renvoie l'état de l'entité en prenant en compte la pile d'actions
	 *
	 * @return
	 */
	@Override
	public Mode getEtatNow() {
		if (actionIsRunning()) {
			return etatNow;
		} else {
			return getEtat();
		}
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
	public GridPoint2 getLastPosition() {
		GridPoint2 ret = pileAction.getLastPosition();
		if (ret == null) {
			return getCaracSpatiale().getPosition();
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
	public SortActif getSort(int index) {
		for (SortActif sort : tabSortActif) {
			if (sort.getIndex() == index) {
				return sort;
			}
		}
		throw new IllegalArgumentException();
	}

	public int getIndexTextureTimeline() {
		return indexTextureTimeline;
	}

	public Caracteristique getTempsAction() {
		return getCaracPhysique().get(Carac.TEMPSACTION);
	}
	
	public void addEnvoutement(Envoutement envoutement) {
		listEnvoutements.add(envoutement);
	}

}
