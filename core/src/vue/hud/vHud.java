/*
 * 
 * 
 * 
 */
package vue.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import gameplay.sort.pileaction.Action;
import test.MainTest;
import vue.Couleur;
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
	
	public static final ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	static {
		shapeRenderer.setProjectionMatrix(MainTest.camera.combined);
	}

	//Font pour l'affichage des FPS
	public static BitmapFont FONT;
	
	//Couleur par défaut de la font
	public static final Color FONT_COLOR = Couleur.get("font");

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
		vpileactions.addAction(action.getSort().getIndex(),
				action.getSort().getTempsAction());
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
		FONT.setColor(FONT_COLOR);
	}
	
	public static void drawBackground(float x, float y, float width, float height, Color fond_couleur, Color fond_contour_couleur) {

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(fond_couleur);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(fond_contour_couleur);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.rect(x - 1, y - 1, width + 2, height + 2);
		shapeRenderer.rect(x - 2, y - 2, width + 4, height + 4);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		
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
