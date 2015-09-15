/*
 * 
 * 
 * 
 */
package vue.jeu.sorts;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import vue.jeu.sorts.effets.vEffet;

/**
 * vSort.java
 *
 */
public class vSort extends Group {

	private final vEffet[] effets;

	private long tempsStart;
	private int tempsAction;

	public vSort(vEffet[] effets) {
		this.effets = effets;
		for (vEffet e : effets) {
			addActor(e);
		}
	}

	public void lancer(int tempsAction, float[] posStart, float[] posEnd) {
		tempsStart = System.currentTimeMillis();
		this.tempsAction = tempsAction;
		for (vEffet e : effets) {
			e.lancer(tempsAction, posStart, posEnd);
		}
	}

	@Override
	public void act(float delta) {
		if (System.currentTimeMillis() - tempsStart <= tempsAction) {
			super.act(delta);
		} else {
			((vSorts)getParent()).removeSort(this);
		}
	}

}
