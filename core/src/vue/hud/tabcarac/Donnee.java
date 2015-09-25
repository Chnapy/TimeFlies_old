/*
 * 
 * 
 * 
 */
package vue.hud.tabcarac;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import general.TypeDonnee;
import vue.hud.bulle.BulleListener;
import static vue.hud.vHud.defaultSkin;

/**
 * Donnee.java
 * 
 */
public class Donnee extends HorizontalGroup {
	
	public static final int TEXTURE_SIZE = 32;
	
	private final TypeDonnee type;
	private final Texture texture;
	private final Label label;
	
	public Donnee(TypeDonnee _type, AssetManager manager, String text) {
		this(_type, manager);
		label.setText(text);
	}
	
	public Donnee(TypeDonnee _type, AssetManager manager) {
		type = _type;
		texture = manager.get(type.getPath());
		label = new Label("-", defaultSkin);
		addActor(new Image(texture));
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
	
	public void setText(String text) {
		label.setText(text);
	}
	
	public String getText() {
		return label.getText().toString();
	}

}
