package test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import controleur.ControleurPrincipal;
import gameplay.caracteristique.Carac;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.core.Joueur;
import gameplay.effet.Balus;
import gameplay.effet.Declencheur;
import gameplay.effet.Effet;
import gameplay.entite.Personnage;
import gameplay.map.Map;
import gameplay.map.Type;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import static general.Orientation.EST;
import java.text.DecimalFormat;

/**
 * MainTest.java CLASSE DE TEST
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
	public static final DecimalFormat DF = new DecimalFormat();

	static {
		DF.setMinimumFractionDigits(1);
		DF.setMaximumFractionDigits(1);
		DF.setDecimalSeparatorAlwaysShown(true);
	}

	private static boolean loading;

	/**
	 * Au lancement de l'application.
	 *
	 */
	@Override
	public void create() {
		loading = true;

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
		Array<Declencheur> declencheur = new Array<Declencheur>();
		declencheur.add(new Balus(Carac.VITALITE, -30));
		Personnage[] persosJ1 = {
			new Guerrier(
			"bite", 1, 2, EST,
			new CaracteristiquePhysique(100, 15000, 1200, 10, 105),
			new SortPassif[]{},
			new SortActif[]{
				new SortQuiFaitMal(new Effet[]{new Effet(declencheur)})
			})
		};
		Personnage[] persosJ2 = {
			new Guerrier2(
			"bite", 0, 0, EST,
			new CaracteristiquePhysique(110, 12000, 1500, 0, 100),
			new SortPassif[]{},
			new SortActif[]{
				new SortQuiFaitMal(new Effet[]{new Effet(declencheur)})
			})
		};
		Joueur[] joueurs = {
			new Joueur(5, "J1", persosJ1),
			new Joueur(6, "J2", persosJ2)
		};

		ControleurPrincipal contCombat = new ControleurPrincipal(map, joueurs);
		this.setScreen(contCombat.getVue());
		contCombat.lancer();

		loading = false;
	}

	/**
	 * Se lance à chaque nouvel frame.
	 *
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);	//Fond gris
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	//Efface
		super.render(); //Important ! Lance le render() du Screen
	}

	/**
	 * Se lance à chaque redimensionnement de la fenêtre. Se lance également au
	 * moment du create().
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
	 * Se lance au moment de la fermeture de l'application. Est précédé par
	 * pause().
	 *
	 */
	@Override
	public void dispose() {
	}

	public static boolean isLoading() {
		return loading;
	}
}
