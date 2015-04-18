/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import gameplay.entite.Personnage;
import gameplay.map.Tuile;

/**
 * vCombat.java
 *
 */
public class vCombat implements Screen {
	
	private vMap vmap;
	private vEntites vperso;

	/**
	 *
	 * @param tabTuiles
	 * @param personnages
	 */
	public vCombat(final Tuile[][] tabTuiles, final Array<Personnage> personnages) {
		
		vmap = new vMap(tabTuiles);
		vperso = new vEntites(personnages);
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

}
