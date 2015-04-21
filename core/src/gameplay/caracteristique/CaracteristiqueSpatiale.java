/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

import java.awt.Point;

/**
 * CaracteristiqueSpatiale.java
 * Caractéristiques de positionnement et d'orientation de l'entité.
 */
public class CaracteristiqueSpatiale {

	private Point position;
	private Orientation orientation;

	/**
	 *
	 * @param posX
	 * @param posY
	 * @param orient
	 */
	public CaracteristiqueSpatiale(int posX, int posY, Orientation orient) {
		position = new Point(posX, posY);
		orientation = orient;
	}

	/**
	 *
	 * @param x
	 * @param y
	 */
	public void move(int x, int y) {
		position.x += x;
		position.y += y;
	}

	/**
	 *
	 * @return
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 *
	 * @return
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 *
	 * @param orientation
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

}
