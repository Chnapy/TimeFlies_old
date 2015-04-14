package main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.core.Combat;
import gameplay.core.Jeu;
import gameplay.core.Joueur;
import gameplay.entite.NiveauSymbolique;
import gameplay.entite.Personnage;
import gameplay.map.Map;

/**
 * Main.java
 * CLASSE DE TEST
 *
 */
public class Main extends ApplicationAdapter {

	/**
	 *
	 */
	@Override
	public void create() {
		Personnage[] persosJ1 = {
//			new Personnage("perso1", 5, 7, Orientation.N, new CaracteristiquePhysique(500, 500, 30, 30, 5, 5, 0, 100, 100, 190), new NiveauSymbolique()),
//			new Personnage("perso2", 9, 4, Orientation.E, new CaracteristiquePhysique(500, 500, 30, 30, 5, 5, 0, 100, 100, 190), new NiveauSymbolique())
		};
		Personnage[] persosJ2 = {
//			new Personnage("perso3", 9, 1, Orientation.S, new CaracteristiquePhysique(500, 500, 30, 30, 5, 5, 0, 100, 100, 190), new NiveauSymbolique()),
//			new Personnage("perso4", 12, 10, Orientation.O, new CaracteristiquePhysique(500, 500, 30, 30, 5, 5, 0, 100, 100, 190), new NiveauSymbolique())
		};
		Joueur[] joueurs = {
			new Joueur(5, "J1", persosJ1),
			new Joueur(6, "J2", persosJ2)
		};
		Map map = new Map(15, 15);
		Combat combat = new Combat(map, joueurs);
		Jeu jeu = new Jeu();
		jeu.addCombat(combat);

	}

	/**
	 *
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 1);	//Fond blanc
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	//Efface
	}
}
