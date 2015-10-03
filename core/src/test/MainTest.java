package test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import controleur.ControleurPrincipal;
import gameplay.core.Joueur;
import gameplay.entite.Personnage;
import gameplay.map.Map;
import gameplay.map.MapSerializable;
import java.text.DecimalFormat;
import java.util.Arrays;

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

	private final String mapPath;

	/**
	 *
	 * @param args
	 *             args[0] = path vers la map
	 *
	 */
	public MainTest(String[] args) {
		System.out.println("args : " + Arrays.toString(args));
		int length = args.length;
		mapPath = (length > 0) ? args[0] : "test.tfmap";
	}

	/**
	 * Au lancement de l'application.
	 *
	 */
	@Override
	public void create() {
		long loadStart = System.currentTimeMillis();
		System.out.println("Initialisation de la partie");

		camera.setToOrtho(false, MAX_WIDTH, MAX_HEIGHT);

		long debut = System.currentTimeMillis();
		System.out.print("-Chargement de la map [" + mapPath + "]...");
		MapSerializable smap = Map.getMapSerializable(Gdx.files.internal(mapPath));
		Map map = new Map(smap);
		System.out.println(" terminé [" + (System.currentTimeMillis() - debut) + "ms]");

		debut = System.currentTimeMillis();
		System.out.print("-Chargement des joueurs et leurs persos...");
		Personnage[] persosJ1 = {
			new Guerrier()
		};
		Personnage[] persosJ2 = {
			new Guerrier2()
		};
		Joueur[] joueurs = {
			new Joueur(5, "J1", persosJ1),
			new Joueur(6, "J2", persosJ2)
		};
		System.out.println(" terminé [" + (System.currentTimeMillis() - debut) + "ms]");

		debut = System.currentTimeMillis();
		System.out.println("-Création du controleur, de la vue et lancement de la partie...");
		ControleurPrincipal contPrincipal = new ControleurPrincipal(map, joueurs);
		this.setScreen(contPrincipal.getVue());
		contPrincipal.lancer();
		System.out.println("-Partie lancée [" + (System.currentTimeMillis() - debut) + "ms]");

		System.out.println("Partie initialisée [" + (System.currentTimeMillis() - loadStart) + "ms]");
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
}
