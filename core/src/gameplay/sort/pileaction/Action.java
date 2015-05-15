package gameplay.sort.pileaction;

import java.awt.Point;

/**
 *
 * Une action permet de sauvegarder les données lancé par le joueur
 * elle est utiliser dans la pile d'action pour sauvegarder les
 * lancement de sort et déplacement du personnage d'un joueur
 */
public abstract class Action {

	/**
	 * La position du lieu ou vas se déroulé l'action
	 */
	private Point position;

	public Action(Point p1) {
		this.position = p1;
	}

	/**
	 * Permet de récupérer l'endroi ou se déroule l'action
	 *
	 * @return la position de l'action
	 */
	public Point getPoint() {
		return position;
	}

	@Override
	public abstract String toString();
}
