package gameplay.sort.pileaction;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;

/**
 * La pile d'action permet de contenir toutes les
 * actions qui vont être réalisées par le joueur
 * et celui si peu les annulé
 */
public class PileAction {

	/**
	 * la pile qui vas contenir toutes les actions
	 */
	public final Array<Action> pile;

	public PileAction() {
		pile = new Array<Action>();
	}

	/**
	 * Ajoute une action dans la pile
	 *
	 * @param action
	 */
	public void add(Action action) {
		pile.add(action);
	}

	/**
	 * retourne le premier de la pile et le supprime
	 *
	 * @return le premier de la pile ou null si la pile est vide
	 */
	public Action removeFirst() {
		if (pile.size > 0) {
			return pile.removeIndex(0);
		}
		return null;
	}

	public GridPoint2 getLastPosition() {
		if (pile.size > 0) {
			for (int i = pile.size - 1; i >= 0; i--) {
				if (pile.get(i).getEtat() == Action.EtatAction.DEPLACEMENT) {
					return pile.get(i).getGridPoint2();
				}
			}
		}
		return null;
	}

	/**
	 * supprime le dernier élément entré dans la pile
	 * si celle si n'est pas vide
	 */
	public void undo() {
		if (pile.size > 0) {
			pile.pop();
		}
	}
}
