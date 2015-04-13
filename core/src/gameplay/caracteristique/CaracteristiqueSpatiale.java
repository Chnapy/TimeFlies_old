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

	public CaracteristiqueSpatiale(int posX, int posY, Orientation orient) {
		position = new Point(posX, posY);
		orientation = orient;
	}
	
	public void move(int x, int y) {
		position.x += x;
		position.y += y;
	}

	public Point getPosition() {
		return position;
	}

	public Orientation getOrientation() {
		return orientation;
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

}
