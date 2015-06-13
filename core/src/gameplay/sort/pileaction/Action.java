package gameplay.sort.pileaction;

import gameplay.caracteristique.Orientation;
import gameplay.sort.SortActif;
import gameplay.sort.base.Deplacer;
import gameplay.sort.base.Orienter;
import java.awt.Point;

/**
 *
 * Une action permet de sauvegarder les données lancé par le joueur
 * elle est utiliser dans la pile d'action pour sauvegarder les
 * lancement de sort et déplacement du personnage d'un joueur
 */
public class Action {

	/**
	 * La position du lieu ou vas se déroulé l'action
	 */
	private final Point position;
	private final SortActif sort;
	private final EtatAction etat;
	private final Orientation oriAttaque;
	private final Orientation precOriAttaque;
	private final boolean critique;

	public Action(Point p1, SortActif sort, Orientation oriAttaque, Orientation precOriAttaque, boolean critique) {
		this.position = p1;
		this.sort = sort;
		this.oriAttaque = oriAttaque;
		this.precOriAttaque = precOriAttaque;
		this.critique = critique;
		etat = definirEtat();
	}

	private EtatAction definirEtat() {
		if (sort instanceof Deplacer) {
			return EtatAction.DEPLACEMENT;
		}
		if (sort instanceof Orienter) {
			return EtatAction.ROTATION;
		}
		return EtatAction.SORT;
	}

	/**
	 * Permet de récupérer l'endroi ou se déroule l'action
	 *
	 * @return la position de l'action
	 */
	public Point getPoint() {
		return position;
	}

	public SortActif getSort() {
		return sort;
	}

	public EtatAction getEtat() {
		return etat;
	}

	public Orientation getOriAttaque() {
		return oriAttaque;
	}

	public boolean isCritique() {
		return critique;
	}

	public enum EtatAction {

		SORT,
		DEPLACEMENT,
		ROTATION
	}
}
