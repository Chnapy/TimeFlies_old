/*
 * 
 * 
 * 
 */
package vue.jeu.sorts;

import com.badlogic.gdx.assets.AssetManager;
import vue.jeu.sorts.effets.vCercle0;
import vue.jeu.sorts.effets.vCercle1;
import vue.jeu.sorts.effets.vEffet;

/**
 * vSortQuiFaitMal.java
 *
 */
public class vSortQuiFaitMal extends vSort {

	public vSortQuiFaitMal(AssetManager manager) {
		super(new vEffet[]{
			new vCercle0(manager),
			new vCercle1(manager, 30, 60, 128),
			new vCercle1(manager, 35, 60, 128),
			new vCercle1(manager, 40, 65, 128)
		});
	}

}
