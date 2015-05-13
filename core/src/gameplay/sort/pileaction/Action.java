package gameplay.sort.pileaction;

import java.awt.Point;

/**
 *
 * @author ydardot
 *
 * une action permet de sauvegarder les données lancé par le joueur
 * elle est utiliser dans la pile d'action pour sauvegarder les
 * lancement de sort et déplacement du personnage d'un joueur
 */
public abstract class Action {

	/**
	 * la position du lieu ou vas se déroulé l'action
	 */
	private Point position;

	public Action(Point p1) {
		this.position = p1;
	}

	/**
	 * permet de récupérer l'endroi ou se déroule l'action
	 *
	 * @return la position de l'action
	 */
	public Point getPoint() {
		return position;
	}
	
	public abstract String toString();
}
