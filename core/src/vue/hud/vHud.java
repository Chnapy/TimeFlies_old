/*
 * 
 * 
 * 
 */
package vue.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.utils.Array;
import controleur.Controleur;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import gameplay.sort.pileaction.Action;
import general.Tourable;
import test.MainTest;
import vue.Couleur;
import vue.hud.bulle.Bulle;
import vue.hud.chatbox.vChatBox;
import vue.hud.minimap.vMinimap;
import vue.hud.pileactions.vPileActions;
import vue.hud.sorts.vSortsBout;
import vue.hud.tabcarac.TableauCarac;
import vue.hud.timeline.vTimeline;

/**
 * vHud.java
 *
 */
public final class vHud extends Stage implements Tourable {

	public static final ShapeRenderer shapeRenderer = new ShapeRenderer();

	static {
		shapeRenderer.setProjectionMatrix(MainTest.camera.combined);
	}

	public static final Skin defaultSkin = new Skin(Gdx.files.internal("skin/default/uiskin.json"));

	//Font pour l'affichage des FPS
	public static BitmapFont FONT;

	//Couleur par défaut de la font
	public static final Color FONT_COLOR = Couleur.get("font");

	//Batch pour l'affichage des FPS
	private final Batch batch = new SpriteBatch();

	//Vue des sorts
	private final vSortsBout vsorts;

	//Vue de la timeline
	private final vTimeline vtimeline;

	//Vue de la minimap
	private final vMinimap vminimap;

	//Vue de la pile d'actions
	private final vPileActions vpileactions;

	//Vue du chat
	private final vChatBox vchatbox;
	
	//Vue des carac's
	private final TableauCarac vtabcarac;

	//Bulle
	public static final Bulle bulle = new Bulle();

	public vHud(Controleur controleur, Tuile[][] tabTuiles, Array<? extends EntiteActive> personnages, AssetManager manager) {
		FONT = defaultSkin.get(WindowStyle.class).titleFont;

		vsorts = new vSortsBout(manager);
		vtimeline = new vTimeline(personnages);
		vminimap = new vMinimap(controleur, tabTuiles);
		vpileactions = new vPileActions();
		vchatbox = new vChatBox();
		vtabcarac = new TableauCarac();

		addActor(vsorts);
		addActor(vtimeline);
		addActor(vpileactions);
		addActor(vtabcarac);
		addActor(vminimap);
		addActor(vchatbox);
		addActor(bulle);
	}

	@Override
	public void nouveauTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
		vsorts.nouveauTour(controleur, entiteEnCours, objs);
		vtimeline.nouveauTour(controleur, entiteEnCours, objs);
		vpileactions.nouveauTour(controleur, entiteEnCours, objs);
		vtabcarac.nouveauTour(controleur, entiteEnCours, objs);
		vminimap.nouveauTour(controleur, entiteEnCours, objs);
		vchatbox.nouveauTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void finTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
		vsorts.finTour(controleur, entiteEnCours, objs);
		vtimeline.finTour(controleur, entiteEnCours, objs);
		vpileactions.finTour(controleur, entiteEnCours, objs);
		vtabcarac.finTour(controleur, entiteEnCours, objs);
		vminimap.finTour(controleur, entiteEnCours, objs);
		vchatbox.finTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void enTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
		vsorts.enTour(controleur, entiteEnCours, objs);
		vtimeline.enTour(controleur, entiteEnCours, objs);
		vpileactions.enTour(controleur, entiteEnCours, objs);
		vtabcarac.enTour(controleur, entiteEnCours, objs);
		vminimap.enTour(controleur, entiteEnCours, objs);
		vchatbox.enTour(controleur, entiteEnCours, objs);
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
		FONT.draw(batch, "fps: " + String.valueOf(Math.round(1 / Gdx.graphics.getRawDeltaTime())), 500, 20);
		batch.end();
		FONT.setColor(FONT_COLOR);
	}

	public static void drawBackground(float x, float y, float width, float height, Color fond_couleur, Color fond_contour_couleur) {

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(fond_couleur);
		shapeRenderer.rect(x + 2, y + 2, width - 3, height - 3);
		shapeRenderer.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(fond_contour_couleur);
		shapeRenderer.rect(x + 1, y + 1, width - 2, height - 2);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);

	}

	public vSortsBout getVsorts() {
		return vsorts;
	}

	public vTimeline getVtimeline() {
		return vtimeline;
	}

	public vMinimap getVminimap() {
		return vminimap;
	}

	public vChatBox getVchatbox() {
		return vchatbox;
	}

}
