package gameplay.sort.pileaction;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author ydardot
 *
 * la pile d'action permet de contenir toutes les
 * actions qui vont être réaliser par le joueur
 * et celui si peu les annulé
 */
public class PileAction {

	/**
	 * la pile qui vas contenir toutes les actions
	 */
	public Array<Action> pile;

	/**
	 * contructeur initialise la pile
	 */
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
	public Action getFirst() {
		if (pile.size > 0) {
			System.out.println(pile);
			return pile.removeIndex(0);
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
