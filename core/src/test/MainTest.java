package test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import controleur.cCombat;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.core.Joueur;
import gameplay.entite.Personnage;
import gameplay.map.Map;
import gameplay.map.Type;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MainTest.java
 * CLASSE DE TEST
 *
 * voir :
 * https://github.com/libgdx/libgdx/wiki/images/70efff32-dd28-11e3-9fc4-1eb57143aee6.png
 *
 */
public class MainTest extends Game {

	public static final int MAX_WIDTH = 1920;
	public static final int MAX_HEIGHT = 1080;

	public static final OrthographicCamera camera = new OrthographicCamera();
	public static final Viewport viewport = new FitViewport(MAX_WIDTH, MAX_HEIGHT, camera);

	/**
	 * Au lancement de l'application.
	 *
	 */
	@Override
	public void create() {
		camera.setToOrtho(false, MAX_WIDTH, MAX_HEIGHT);
		Map map = new Map(new Type[][]{
			{Type.SIMPLE, Type.SIMPLE, Type.OBSTACLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE},
			{Type.SIMPLE, Type.TROU, Type.OBSTACLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE},
			{Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE},
			{Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE},
			{Type.ECRAN, Type.ECRAN, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE},
			{Type.ECRAN, Type.ECRAN, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE},
			{Type.ECRAN, Type.ECRAN, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE, Type.SIMPLE}
		});
		Personnage[] persosJ1 = {
			new Guerrier(
			"bite", 1, 2, Orientation.E,
			new CaracteristiquePhysique(100, 100, 10, 10, 100, 100, 100, 100, 100, 100),
			new SortPassif[]{},
			new SortActif[]{
				new SortQuiFaitMal(map)
			})
		};
		Personnage[] persosJ2 = {
			new Guerrier(
			"bite", 0, 0, Orientation.E,
			new CaracteristiquePhysique(100, 100, 10, 10, 100, 100, 100, 100, 100, 100),
			new SortPassif[]{},
			new SortActif[]{
				new SortQuiFaitMal(map)
			})
		};
		Joueur[] joueurs = {
			new Joueur(5, "J1", persosJ1),
			new Joueur(6, "J2", persosJ2)
		};

		cCombat contCombat = new cCombat(map, joueurs);
		this.setScreen(contCombat.getVue());
		contCombat.lancer();
	}

	/**
	 * Se lance à chaque nouvel frame.
	 *
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 1);	//Fond blanc
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	//Efface
		super.render(); //Important ! Lance le render() du Screen
	}

	/**
	 * Se lance à chaque redimensionnement de la fenêtre.
	 * Se lance également au moment du create().
	 *
	 * @param width
	 * @param height
	 */
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height);
	}

	/**
	 * Se lance juste avant dispose().
	 *
	 */
	@Override
	public void pause() {
	}

	/**
	 * Se lance au moment de la fermeture de l'application.
	 * Est précédé par pause().
	 *
	 */
	@Override
	public void dispose() {
	}
}
