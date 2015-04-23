/*
 * 
 * 
 * 
 */
package vue;

import vue.timeline.vTimeline;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.core.Timeline;
import gameplay.entite.Personnage;
import gameplay.map.Tuile;
import vue.map.vMap;
import vue.sorts.vSortsActifs;

/**
 * vCombat.java
 *
 */
public class vCombat implements Screen {

	private final cCombat combat;
	private final vMap vmap;
	private final vEntites ventites;
	private final vTimeline vtimeline;
	private final vSortsActifs vsortspassifs;

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
		vsortspassifs = new vSortsActifs();

		//Accepter les input
		InputMultiplexer inputM = new InputMultiplexer(vmap, ventites, vsortspassifs, vtimeline);
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
		vsortspassifs.render();
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

	public vTimeline getVtimeline() {
		return vtimeline;
	}

	public vMap getVmap() {
		return vmap;
	}

	public vEntites getVentites() {
		return ventites;
	}

}
