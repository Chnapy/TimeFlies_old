/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortspassifs;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import controleur.ControleurPrincipal;
import gameplay.sort.SortPassif;
import vue.hud.bulle.BulleListener;
import vue.hud.sorts.vSortsBouton;

/**
 * vSortsActifsBouton.java
 *
 */
public class vSortsPassifsBouton extends vSortsBouton {

	//Tableau de textures des sorts
	private static final String[] TEXTURES = {
		"sort/sort_fond.png",
		"sort/sort_fond.png",
		"sort/sort_fond.png",
		"sort/sort_fond.png"
	};

	public vSortsPassifsBouton(ControleurPrincipal ccombat, SortPassif sort, AssetManager manager) {
		super(sort, manager.get(TEXTURES[sort.getIndex()], Texture.class));
//		sort.addObserver(this);
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "Description : " + sort.getDescription();
			}
		});
	}

	public void delete() {
		sort.deleteObservers();
		poolDonnees.freeAll(donnees);
	}

}
