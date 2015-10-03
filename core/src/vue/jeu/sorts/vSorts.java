/*
 * 
 * 
 * 
 */
package vue.jeu.sorts;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import vue.jeu.map.vMap;
import vue.jeu.map.vTuile;
import vue.jeu.vJeu;

/**
 * vSorts.java
 *
 */
public class vSorts extends Group {

	public final vSort[] VSORTS;

	public vSorts(AssetManager manager, int width, int height, int x, int y) {
		setSize(width, height);
		setPosition(x, y);
		VSORTS = new vSort[]{
			null,
			new vSortQuiFaitMal(manager),
			new vSortQuiFaitMal(manager),
			new vSortQuiFaitMal(manager),
			new vSortQuiFaitMal(manager),
			new vSortQuiFaitMal(manager)
		};
		setTouchable(Touchable.disabled);
	}

	public void addSort(int index, int tempsAction, GridPoint2 start, GridPoint2 end) {
		VSORTS[index].lancer(tempsAction, vMap.getTuilePosition(start.x, start.y), vMap.getTuilePosition(end.x, end.y));
		addActor(VSORTS[index]);
	}

	public void removeSort(vSort sort) {
		removeActor(sort);
		((vJeu) getStage()).removeSort();
	}

}
