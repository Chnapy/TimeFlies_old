package test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import controleur.cCombat;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.core.Joueur;
import gameplay.entite.Personnage;
import gameplay.map.Etat;
import gameplay.map.Map;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;

/**
 * MainTest.java
 * CLASSE DE TEST
 *
 * voir :
 * https://github.com/libgdx/libgdx/wiki/images/70efff32-dd28-11e3-9fc4-1eb57143aee6.png
 *
 */
public class MainTest extends Game {

	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 600;

	public static final OrthographicCamera camera = new OrthographicCamera();

	/**
	 * Au lancement de l'application.
	 *
	 */
	@Override
	public void create() {
		
		Personnage[] persosJ1 = {
			new Guerrier(
			"bite", 1, 2, Orientation.E,
			new CaracteristiquePhysique(100, 100, 100, 100, 100, 100, 100, 100, 100, 100),
			new SortPassif[]{},
			new SortActif[]{})
		};
		Personnage[] persosJ2 = {
			new Guerrier(
			"bite", 2, 0, Orientation.E,
			new CaracteristiquePhysique(100, 100, 100, 100, 100, 100, 100, 100, 100, 100),
			new SortPassif[]{},
			new SortActif[]{})
		};
		Joueur[] joueurs = {
			new Joueur(5, "J1", persosJ1),
			new Joueur(6, "J2", persosJ2)
		};
		Map map = new Map(new Etat[][]{
			{Etat.SIMPLE, Etat.SIMPLE, Etat.OBSTACLE},
			{Etat.SIMPLE, Etat.TROU, Etat.OBSTACLE},
			{Etat.SIMPLE, Etat.SIMPLE, Etat.SIMPLE},
			{Etat.SIMPLE, Etat.SIMPLE, Etat.SIMPLE},
			{Etat.ECRAN, Etat.ECRAN, Etat.SIMPLE},
			{Etat.ECRAN, Etat.ECRAN, Etat.SIMPLE}
		});
//		Combat combat = new Combat(map, joueurs);
//		Jeu jeu = new Jeu();
//		jeu.addCombat(combat);
//		combat.lancer();

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
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();
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
