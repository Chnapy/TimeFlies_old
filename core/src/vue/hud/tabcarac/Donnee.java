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
import com.badlogic.gdx.utils.Pool.Poolable;
import general.TypeDonnee;
import vue.hud.bulle.BulleListener;
import static vue.hud.vHud.defaultSkin;

/**
 * Donnee.java
 *
 */
public class Donnee extends HorizontalGroup implements Poolable {

	public static final int TEXTURE_SIZE = 32;

	private TypeDonnee type;
	private Texture texture;
	private Label label;

	public Donnee(TypeDonnee _type, AssetManager manager) {
		this(_type, manager, "-");
	}

	public Donnee(TypeDonnee _type, AssetManager manager, String text) {
		this();
		type = _type;
		texture = manager.get(type.getPath());
		label.setText(text);
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

	public Donnee() {
		type = null;
		texture = null;
		label = new Label("", defaultSkin);
	}

	public final Donnee init(TypeDonnee _type, AssetManager manager) {
		return init(_type, manager, "-");
	}

	public final Donnee init(TypeDonnee _type, AssetManager manager, String text) {
		if (!_type.equals(type)) {
			type = _type;
		}
		if (!manager.get(type.getPath()).equals(texture)) {
			texture = manager.get(type.getPath());
		}
		label.setText(text);
		addActor(new Image(texture));
		addActor(label);
		try {
			setSize(getPrefWidth(), getPrefHeight());
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Donnee.java - " + e.getMessage());
		}

		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return type.getBulle();
			}
		});

		return this;
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

	@Override
	public void reset() {
		setText("-");
		clear();
	}

}
