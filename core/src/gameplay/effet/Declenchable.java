/*
 * 
 * 
 * 
 */
package gameplay.effet;

import com.badlogic.gdx.utils.Array;


/**
 * Declenchable.java
 * Représente la condition pour qu'un effet se déclenche.
 *
 */
public class Declenchable {

	private Effet effet;
	private int minimum;
	private int maximum;

	/**
	 *
	 * @param ef
	 * @param min
	 * @param max
	 */
	public Declenchable(Effet ef, int min, int max) {
		effet = ef;
		minimum = min;
		maximum = max;
	}
	/**
	 * permet de savoir si le déclancheur peu se déclanger avec les effets 
	 * 
	 * @param effets
	 * @return true si les effects contienne le déclancheur sinon false
	 */
	public boolean canDeclanche(Array<Effet> effets){
		for (int i = 0; i < effets.size; i++) {
			if(effet.equals(effets.get(i)))
				return true;
		}
		//TODO prendre en compte le min et le max
		return false;
	}

}
