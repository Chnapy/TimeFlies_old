/*
 * 
 * 
 * 
 */
package vue.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import gameplay.sort.pileaction.Action;
import gameplay.sort.pileaction.ActionDeplacement;
import gameplay.sort.pileaction.ActionLancerSort;
import test.MainTest;
import vue.hud.minimap.vMinimap;
import vue.hud.pileactions.vPileActions;
import vue.hud.sorts.vSorts;
import vue.hud.timeline.vTimeline;

/**
 * vHud.java
 *
 */
public final class vHud extends Stage {

	//Générateur de font
	private static final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/kenvector_future_thin.ttf"));
	private static final FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

	//Font pour l'affichage des FPS
	public static BitmapFont FONT;

	//Batch pour l'affichage des FPS
	private final Batch batch = new SpriteBatch();

	//Vue des sorts
	private final vSorts vsorts;

	//Vue de la timeline
	private final vTimeline vtimeline;

	//Vue de la minimap
	private final vMinimap vminimap;

	//Vue de la pile d'actions
	private final vPileActions vpileactions;

	public vHud(cCombat controleur, Tuile[][] tabTuiles, Array<? extends EntiteActive> personnages) {
		parameter.size = 18;
		FONT = generator.generateFont(parameter);
		vsorts = new vSorts();
		vtimeline = new vTimeline(personnages);
		vminimap = new vMinimap(controleur, tabTuiles);
		vpileactions = new vPileActions();

		addActor(vsorts);
		addActor(vtimeline);
		addActor(vminimap);
		addActor(vpileactions);
	}

	/**
	 * Début du tour d'une entité
	 *
	 * @param ccombat
	 * @param entite
	 */
	public void nouveauTour(cCombat ccombat, EntiteActive entite) {
		vsorts.nouveauTour(ccombat, entite.getTabSortActif(), entite.getTabSortPassif());
		vtimeline.nouveauTour();
		vpileactions.nouveauTour(entite.getTempsAction().getActu());
	}

	/**
	 * Fin du tour d'une entité
	 */
	public void finTour() {
		vsorts.finTour();
		vminimap.finTour();
	}

	public void tourEnCours(EntiteActive entiteEnCours) {
		vtimeline.tourEnCours(entiteEnCours);
		vpileactions.tourEnCours(entiteEnCours);
	}

	public void addAction(Action action) {
		if (action instanceof ActionDeplacement) {
			vpileactions.addAction(0, 1234);
		} else {
			vpileactions.addAction(((ActionLancerSort)action).getSort().getIndex(), 
					((ActionLancerSort)action).getSort().getTempsAction());
		}
	}

	public void render() {
		setViewport(MainTest.viewport);
		act();
		draw();

		//Affichage FPS
		FONT.setColor(Color.RED);
		batch.begin();
		FONT.draw(batch, "fps: " + String.valueOf(Math.round(1 / Gdx.graphics.getRawDeltaTime())), 10, 50);
		batch.end();
		FONT.setColor(Color.BLACK);
	}

	public vSorts getVsorts() {
		return vsorts;
	}

	public vTimeline getVtimeline() {
		return vtimeline;
	}

	public vMinimap getVminimap() {
		return vminimap;
	}

}
