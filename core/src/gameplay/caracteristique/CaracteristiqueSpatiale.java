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

	//Position de l'entité
	private Point position;

	//Orientation de l'entité (N S E O)
	private Orientation orientation;

	/**
	 * Représente les caractéristiques spatiales de l'entité : position,
	 * orientation
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
	 * Modifie la position via un mouvement
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

	/**
	 * hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	/**
	 * equals en fonction de la position uniquement
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CaracteristiqueSpatiale other = (CaracteristiqueSpatiale) obj;
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		return true;
	}

}
