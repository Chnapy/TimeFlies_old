/*
 * 
 * 
 * 
 */
package vue.sorts;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * vSortsActifsListBouton.java
 *
 */
public class vSortsActifsListBouton extends Table {

	public vSortsActifsListBouton() {
		for (int i = 0; i < 10; i++) {
			vSortsActifsBouton button = new vSortsActifsBouton(525 + i * 135);
			add(button).size(128, 128);
		}
	}

}
