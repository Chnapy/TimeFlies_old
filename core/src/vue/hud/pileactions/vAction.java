/*
 * 
 * 
 * 
 */
package vue.hud.pileactions;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * vAction.java
 * 
 */
public class vAction {
	
	private final vTempsAction tempsAction;
	private final vIconeAction iconeAction;
	
	public vAction(ShapeRenderer shapeRender, int indexTexture, int tempsAction) {
		this.tempsAction = new vTempsAction(shapeRender, indexTexture, tempsAction);
		this.iconeAction = new vIconeAction(indexTexture);
	}

	public vTempsAction getTempsAction() {
		return tempsAction;
	}

	public vIconeAction getIconeAction() {
		return iconeAction;
	}

}
