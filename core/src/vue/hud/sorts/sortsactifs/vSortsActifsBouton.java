/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortsactifs;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import controleur.ControleurPrincipal;
import gameplay.sort.SortActif;
import java.util.Observable;
import java.util.Observer;
import vue.hud.bulle.BulleListener;
import vue.hud.sorts.donnees.Donnee;
import vue.hud.sorts.donnees.Donnee.TypeDonnee;
import vue.hud.sorts.vSortsBouton;

/**
 * vSortsActifsBouton.java
 *
 */
public class vSortsActifsBouton extends vSortsBouton implements Observer {

	//Tableau de textures des sorts
	private static final String[] TEXTURES = {
		"sort/sort_fond.png",
		"sort/sort_fond.png"
	};
	private static final float OFFSET_ICONES = 1.2f;

	public vSortsActifsBouton(ControleurPrincipal ccombat, SortActif sort, AssetManager manager) {
		super(sort, manager.get(TEXTURES[sort.getIndex()], Texture.class));
		sort.addObserver(this);
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				ccombat.controleurSort.modeSort(sort.getIndex());
				return super.touchDown(event, x, y, pointer, button); //TODO
			}
		});
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "Description : " + sort.getDescription();
			}
		});
		donnees.addAll(
				new Donnee(TypeDonnee.TEMPSACTION, sort.getTempsAction(), manager),
				new Donnee(TypeDonnee.ZONEPORTEE, -1, manager),
				new Donnee(TypeDonnee.ZONEACTION, -1, manager),
				new Donnee(TypeDonnee.COOLDOWN, sort.getCooldownActu(), manager),
				new Donnee(TypeDonnee.DEGATS, -1, manager)
		);

		float posXbase = getWidth() / 2 * OFFSET_ICONES, posYbase = getHeight() / 2 * OFFSET_ICONES, coeff, posX, posY;
		for (int i = 0; i < donnees.size; i++) {
			coeff = (float) i / (float) (donnees.size);
			coeff = coeff * 2 * (float) Math.PI + (float) Math.PI / 2;
			posX = (float) Math.cos(coeff);
			posY = (float) Math.sin(coeff);
			addActor(donnees.get(i));
			donnees.get(i).setPosition(posXbase * posX + getWidth() / 2 - donnees.get(i).getTextureSize() / 2,
					posYbase * posY + getHeight() / 2 - donnees.get(i).getTextureSize() / 2);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		TypeDonnee type = (TypeDonnee) ((Object[]) arg)[0];
		int d = (int) ((Object[]) arg)[1];
		for(Donnee donnee : donnees) {
			if(donnee.getType().equals(type)) {
				donnee.setValeur(d);
			}
		}
	}
	
	public void clearSortObserver() {
		sort.deleteObservers();
	}

}
