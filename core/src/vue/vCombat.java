/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.core.Timeline;
import gameplay.entite.Personnage;
import gameplay.map.Tuile;
import vue.hud.timeline.vTimeline;
import vue.hud.vHud;
import vue.jeu.entites.vEntites;
import vue.jeu.map.vMap;
import vue.jeu.vJeu;

/**
 * vCombat.java
 *
 */
public class vCombat implements Screen {

	private final vJeu vjeu;
	private final vHud vhud;

	/**
	 *
	 * @param ccombat
	 * @param tabTuiles
	 * @param personnages
	 * @param timel
	 */
	public vCombat(final cCombat ccombat, final Tuile[][] tabTuiles, final Array<Personnage> personnages, final Timeline timel) {
		vjeu = new vJeu(ccombat, tabTuiles, personnages);
		vhud = new vHud(timel);

		//Accepter les input
		InputMultiplexer inputM = new InputMultiplexer(vjeu, vhud);
		Gdx.input.setInputProcessor(inputM);

	}

	@Override
	public void show() {

	}

	/**
	 *
	 * @param delta	espace de temps entre 2 frames (utilisé pour
	 *              l'interpolation)
	 */
	@Override
	public void render(float delta) {
		vjeu.render();
		vhud.render();
	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

	public vTimeline getVtimeline() {
		return vhud.getVtimeline();
	}

	public vMap getVmap() {
		return vjeu.getVmap();
	}

	public vEntites getVentites() {
		return vjeu.getVentites();
	}

	public vHud getVhud() {
		return vhud;
	}

}
