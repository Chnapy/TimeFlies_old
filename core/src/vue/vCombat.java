/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import gameplay.core.Timeline;
import gameplay.entite.Personnage;
import gameplay.map.Tuile;

/**
 * vCombat.java
 *
 */
public class vCombat implements Screen {
	
	private vMap vmap;
	private vEntites vperso;
	private vTimeline vtimeline;

	/**
	 *
	 * @param tabTuiles
	 * @param personnages
	 * @param timel
	 */
	public vCombat(final Tuile[][] tabTuiles, final Array<Personnage> personnages, final Timeline timel) {
		
		vmap = new vMap(tabTuiles);
		vperso = new vEntites(personnages);
		vtimeline = new vTimeline(timel);
	}

	@Override
	public void show() {
		
	}

	/**
	 *
	 * @param delta
	 */
	@Override
	public void render(float delta) {
//		System.out.println("DEBUT RENDER");
		vmap.render();
		vperso.render();
		
		//HUD
		vtimeline.render();
//		System.out.println("FIN RENDER");
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
