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

	@Override
	public void create() {
		Personnage[] persosJ1 = {
			new Personnage("perso1", 5, 7, Orientation.NO, new CaracteristiquePhysique(500, 30, 5, 0, 100), new NiveauSymbolique()),
			new Personnage("perso2", 9, 4, Orientation.SE, new CaracteristiquePhysique(600, 25, 5, 0, 100), new NiveauSymbolique())
		};
		Personnage[] persosJ2 = {
			new Personnage("perso3", 9, 1, Orientation.SE, new CaracteristiquePhysique(450, 35, 5, 5, 120), new NiveauSymbolique()),
			new Personnage("perso4", 12, 10, Orientation.NE, new CaracteristiquePhysique(700, 28, 5, 20, 90), new NiveauSymbolique())
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

	@Override
	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
