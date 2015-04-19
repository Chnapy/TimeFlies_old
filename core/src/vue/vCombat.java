/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import vue.map.vMap;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.core.Timeline;
import gameplay.entite.Personnage;
import gameplay.map.Tuile;

/**
 * vCombat.java
 *
 */
public class vCombat implements Screen {

	private cCombat combat;
	private vMap vmap;
	private vEntites ventites;
	private vTimeline vtimeline;

	/**
	 *
	 * @param ccombat
	 * @param tabTuiles
	 * @param personnages
	 * @param timel
	 */
	public vCombat(final cCombat ccombat, final Tuile[][] tabTuiles, final Array<Personnage> personnages, final Timeline timel) {
		combat = ccombat;
		vmap = new vMap(ccombat, tabTuiles);
		ventites = new vEntites(personnages);
		vtimeline = new vTimeline(timel);
		
		//Accepter les input
		InputMultiplexer inputM = new InputMultiplexer(vmap, ventites, vtimeline);
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
		vmap.render();
		ventites.render();

//		//HUD
		vtimeline.render();
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

	public vTimeline getVTimeline() {
		return vtimeline;
	}

}
