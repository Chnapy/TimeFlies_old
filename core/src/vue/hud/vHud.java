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
import test.MainTest;
import vue.hud.sorts.vSorts;
import vue.hud.timeline.vTimeline;

/**
 * vHud.java
 *
 */
public final class vHud extends Stage {

	private static final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/kenvector_future_thin.ttf"));
	private static final FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	public static BitmapFont FONT;

	private final vSorts vsorts;
	private final vTimeline vtimeline;
	private final Batch batch = new SpriteBatch();

	public vHud(Array<? extends EntiteActive> personnages) {
		parameter.size = 18;
		FONT = generator.generateFont(parameter);
		vsorts = new vSorts();
		vtimeline = new vTimeline(personnages);

		addActor(vsorts);
		addActor(vtimeline);
	}

	public void nouveauTour(cCombat ccombat, EntiteActive entite) {
		vsorts.nouveauTour(ccombat, entite.getTabSortActif(), entite.getTabSortPassif());
		vtimeline.nouveauTour();
	}

	public void finTour() {
		vsorts.finTour();
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

}
