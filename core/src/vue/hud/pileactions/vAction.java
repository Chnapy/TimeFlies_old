/*
 * 
 * 
 * 
 */
package vue.hud.pileactions;

import com.badlogic.gdx.assets.AssetManager;

/**
 * vAction.java
 *
 */
public class vAction {

	private final vTempsAction tempsAction;
	private final vIconeAction iconeAction;

	public vAction(AssetManager manager, int index, int tempsAction) {
		this.tempsAction = new vTempsAction(index, tempsAction);
		this.iconeAction = new vIconeAction(manager, index);
	}

	public vTempsAction getTempsAction() {
		return tempsAction;
	}

	public vIconeAction getIconeAction() {
		return iconeAction;
	}

}
