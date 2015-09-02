/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * vChat.java
 * 
 */
public abstract class vChat extends TextArea {
	
//	private final ScrollPane scroll;
	
	private float initY;
	private float initHeight;
	
	public vChat(int _width, int _height) {
		super("Blablabla chat lecture seule", new Skin(Gdx.files.internal("skin/default/uiskin.json")));
//		scroll = new ScrollPane(this);
		setTextFieldFilter((TextField textField, char c) -> false);
		setSize(_width, _height);
//		scroll.setForceScroll(true, true);
	}

	@Override
	public float getPrefHeight() {
		System.out.println(getHeight());
		return getHeight();
	}

//	public ScrollPane getScroll() {
//		return scroll;
//	}

	public float getInitY() {
		return initY;
	}

	public void setInitY(float initY) {
		this.initY = initY;
	}

	public float getInitHeight() {
		return initHeight;
	}

	public void setInitHeight(float initHeight) {
		this.initHeight = initHeight;
	}

}
