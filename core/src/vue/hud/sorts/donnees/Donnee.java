/*
 * 
 * 
 * 
 */
package vue.hud.sorts.donnees;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import vue.hud.bulle.BulleListener;
import static vue.hud.vHud.defaultSkin;

/**
 * Donnee.java
 *
 */
public class Donnee extends HorizontalGroup {

	private static final String[] ICONES = {
		"tempsaction.png",
		"degats.png",
		"zoneportee.png",
		"zoneaction.png",
		"cooldown.png"
	};
	
	private static final String[] BULLES = {
		"Temps d'action requis",
		"Degats occasionnes",
		"Zone de portee",
		"Zone d'action",
		"Nombre de tours de relance"
	};

	public static enum TypeDonnee {

		TEMPSACTION(ICONES[0], BULLES[0]),
		DEGATS(ICONES[1], BULLES[1]),
		ZONEPORTEE(ICONES[2], BULLES[2]),
		ZONEACTION(ICONES[3], BULLES[3]),
		COOLDOWN(ICONES[4], BULLES[4]);

		private final String path;
		private Texture texture;
		private final String bulle;

		TypeDonnee(String _path, String _bulle) {
			path = _path;
			texture = null;
			bulle = _bulle;
		}

		final void createTexture(AssetManager manager) {
			if (texture == null) {
				texture = manager.get("icons/" + path, Texture.class);
			}
		}

		private Texture getTexture() {
			return texture;
		}

		private String getBulle() {
			return bulle;
		}
	}

	private final TypeDonnee type;
	private int valeur;
	private final Label label;

	public Donnee(TypeDonnee _type, int _valeur, AssetManager manager) {
		type = _type;
		type.createTexture(manager);
		valeur = _valeur;
		label = new Label(Integer.toString(valeur), defaultSkin);
		addActor(new Image(type.getTexture()));
		addActor(label);
		pack();
		
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return type.getBulle();
			}
		});
	}

	public TypeDonnee getType() {
		return type;
	}

	public void setValeur(int _valeur) {
		valeur = _valeur;
		label.setText(Integer.toString(valeur));
	}
	
	public int getTextureSize() {
		return type.getTexture().getWidth();
	}

}
