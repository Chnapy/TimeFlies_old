/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import static vue.hud.vHud.defaultSkin;

/**
 * vChat.java
 *
 */
public abstract class vChat extends ScrollPane {
	
	private final int HEIGHT_TEXTFIELD = 31;

	private final TextArea textarea;
	private final TextField textfield;

	private float initY;
	private float initHeight;
	private boolean write;

	public vChat(int _width, int _height) {
		super(new TextArea("Blablabla chat lecture seule\nBlablabla chat lecture seule\nBlablabla chat lecture seule", defaultSkin), defaultSkin);
		textarea = (TextArea) this.getWidget();
		textfield = new TextField("", defaultSkin);
		textfield.addListener(new InputListener() {

			@Override
			public boolean keyTyped(InputEvent event, char character) {
				if(event.getKeyCode() == Keys.ENTER) {
					addText(textfield.getText());
					textfield.setText("");
				}
				return super.keyTyped(event, character);
			}
			
		});
		
		write = false;
		textarea.setTextFieldFilter((TextField textField, char c) -> write);
		
		setSize(_width, _height - HEIGHT_TEXTFIELD);
		setForceScroll(false, true);
		setFlickScroll(false);
		setOverscroll(false, true);
		addText("Test test test test");
		addText("Test test test test");
		addText("Test test test test");
		addText("Test test test test");
		addText("Test test test test");
		addText("Test test test test");
		addText("Test test test test");
		addText("Test test test test");
		addText("Test test test test");
		addText("Test test test test");
	}

	public void addText(String text) {
		if (text.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		int selectionStart = -1, selectionEnd = -1;
		if (!textarea.getSelection().isEmpty()) {
			selectionStart = textarea.getSelectionStart();
			selectionEnd = selectionStart + textarea.getSelection().length();
		}
		
		write = true;
		textarea.appendText("\n" + text);
		write = false;
		actuHeight();
		
		if(selectionStart != -1) {
			textarea.setSelection(selectionStart, selectionEnd);
		}
		
	}
	
	public void actuHeight() {
		textarea.setPrefRows(textarea.getLines());
		textarea.moveCursorLine(-1);

		layout();
	}

	@Override
	public float getPrefHeight() {
		return getHeight();
	}

	public TextArea getTextarea() {
		return textarea;
	}
	
	public TextField getTextfield() {
		return textfield;
	}

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
