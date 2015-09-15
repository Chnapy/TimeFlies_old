/*
 * 
 * 
 * 
 */
package vue.jeu.sorts;

import com.badlogic.gdx.scenes.scene2d.Group;
import java.awt.Point;
import vue.jeu.map.vTuile;
import vue.jeu.vJeu;

/**
 * vSorts.java
 *
 */
public class vSorts extends Group {

	public static final vSort[] VSORTS = {
		null,
		new vSortQuiFaitMal()
	};

	public void addSort(int index, int tempsAction, Point start, Point end) {
		VSORTS[index].lancer(tempsAction, vTuile.getPosition(start.x, start.y), vTuile.getPosition(end.x, end.y));
		addActor(VSORTS[index]);
	}

	public void removeSort(vSort sort) {
		removeActor(sort);
		((vJeu) getStage()).removeSort();
	}

}
