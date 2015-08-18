/*
 * 
 * 
 * 
 */
package vue.hud.pileactions;

/**
 * vAction.java
 *
 */
public class vAction {

	private final vTempsAction tempsAction;
	private final vIconeAction iconeAction;

	public vAction(int indexTexture, int tempsAction) {
		this.tempsAction = new vTempsAction(indexTexture, tempsAction);
		this.iconeAction = new vIconeAction(indexTexture);
	}

	public vTempsAction getTempsAction() {
		return tempsAction;
	}

	public vIconeAction getIconeAction() {
		return iconeAction;
	}

}
