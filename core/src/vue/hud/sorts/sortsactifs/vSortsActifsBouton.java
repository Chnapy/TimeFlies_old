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
import gameplay.entite.EntiteActive;
import gameplay.sort.SortActif;
import general.TypeDonnee;
import java.util.Observable;
import java.util.Observer;
import static test.MainTest.DF;
import vue.hud.bulle.BulleListener;
import vue.hud.sorts.vSortsBouton;
import vue.hud.tabcarac.Donnee;

/**
 * vSortsActifsBouton.java
 *
 */
public class vSortsActifsBouton extends vSortsBouton implements Observer {

	//Tableau de textures des sorts
	private static final String[] TEXTURES = {
		"sort/sort_fond.png",
		"sort/sort_fond.png",
		"sort/sort_fond.png",
		"sort/sort_fond.png",
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
				poolDonnees.obtain().init(TypeDonnee.TEMPSACTION, manager, DF.format((float) sort.getTempsAction() / 1000)),
				poolDonnees.obtain().init(TypeDonnee.ZONEPORTEE, manager),
				poolDonnees.obtain().init(TypeDonnee.ZONEACTION, manager),
				poolDonnees.obtain().init(TypeDonnee.COOLDOWN, manager, sort.getCooldownActu() + ""),
				poolDonnees.obtain().init(TypeDonnee.DEGATS, manager)
		);

		float posXbase = getWidth() / 2 * OFFSET_ICONES, posYbase = getHeight() / 2 * OFFSET_ICONES, coeff, posX, posY;
		for (int i = 0; i < donnees.size; i++) {
			coeff = (float) i / (float) (donnees.size);
			coeff = coeff * 2 * (float) Math.PI + (float) Math.PI / 2;
			posX = (float) Math.cos(coeff);
			posY = (float) Math.sin(coeff);
			addActor(donnees.get(i));
			donnees.get(i).setPosition(posXbase * posX + getWidth() / 2 - Donnee.TEXTURE_SIZE / 2,
					posYbase * posY + getHeight() / 2 - Donnee.TEXTURE_SIZE / 2);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		TypeDonnee type = (TypeDonnee) ((Object[]) arg)[0];
		int d = (int) ((Object[]) arg)[1];
		for (Donnee donnee : donnees) {
			if (donnee.getType().equals(type)) {
				donnee.setText(d + "");
			}
		}
	}

	public void delete() {
		sort.deleteObservers();
		poolDonnees.freeAll(donnees);
	}

	public void enTour(EntiteActive entite) {
		for (Donnee donnee : donnees) {
			if (donnee.getType().equals(TypeDonnee.TEMPSACTION)) {
				donnee.setText(DF.format(
						entite.getSortFinalTempsAction((SortActif) sort) / 1000f
				));
				break;
			}
		}
	}

}
