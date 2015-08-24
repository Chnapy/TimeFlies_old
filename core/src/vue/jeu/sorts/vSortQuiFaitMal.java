/*
 * 
 * 
 * 
 */
package vue.jeu.sorts;

import vue.jeu.sorts.effets.vCercle0;
import vue.jeu.sorts.effets.vCercle1;
import vue.jeu.sorts.effets.vEffet;

/**
 * vSortQuiFaitMal.java
 *
 */
public class vSortQuiFaitMal extends vSort {

	private static final vEffet[] veffets = {
		new vCercle0(),
		new vCercle1(30, 60, 128),
		new vCercle1(35, 60, 128),
		new vCercle1(40, 65, 128)
	};

	public vSortQuiFaitMal() {
		super(veffets);
	}

}
