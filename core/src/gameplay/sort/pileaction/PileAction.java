package gameplay.sort.pileaction;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;

/**
 * La pile d'action permet de contenir toutes les
 * actions qui vont être réalisées par le joueur
 * et celui si peu les annulé
 */
public class PileAction extends Array<Action> {

	/**
	 * retourne le premier de la pile et le supprime
	 *
	 * @return le premier de la pile ou null si la pile est vide
	 */
	public Action removeFirst() throws ArrayIndexOutOfBoundsException {
		if (size > 0) {
			return removeIndex(0);
		}
		throw new ArrayIndexOutOfBoundsException("size = 0");
	}

	public GridPoint2 getLastPosition() {
		if (size > 0) {
			for (int i = size - 1; i >= 0; i--) {
				if (get(i).getEtat() == Action.EtatAction.DEPLACEMENT) {
					return get(i).getGridPoint2();
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
		if (size > 0) {
			pop();
		}
	}
}
