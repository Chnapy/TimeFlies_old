/*
 * 
 * 
 * 
 */
package vue.jeu.sorts;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import java.awt.Point;
import vue.jeu.map.vTuile;
import vue.jeu.vJeu;

/**
 * vSorts.java
 *
 */
public class vSorts extends Group {

	public final vSort[] VSORTS;

	public vSorts(AssetManager manager) {
		VSORTS = new vSort[]{
			null,
			new vSortQuiFaitMal(manager)
		};
	}

	public void addSort(int index, int tempsAction, Point start, Point end) {
		VSORTS[index].lancer(tempsAction, vTuile.getPosition(start.x, start.y), vTuile.getPosition(end.x, end.y));
		addActor(VSORTS[index]);
	}

	public void removeSort(vSort sort) {
		removeActor(sort);
		((vJeu) getStage()).removeSort();
	}

}
