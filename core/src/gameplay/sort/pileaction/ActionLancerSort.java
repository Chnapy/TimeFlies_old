package gameplay.sort.pileaction;

import gameplay.sort.SortActif;
import java.awt.Point;

/**
 * Action de lancer un sort sur une position donné
 */
public class ActionLancerSort extends Action {

	//Sort lancé
	private SortActif sort;

	public ActionLancerSort(Point p1, SortActif sort) {
		super(p1);
		this.sort = sort;
	}

	/**
	 *
	 * @return le sort de l'action
	 */
	public SortActif getSort() {
		return this.sort;
	}

	@Override
	public String toString() {
		return "Lancer un sort: " + sort.getNom();
	}
}
