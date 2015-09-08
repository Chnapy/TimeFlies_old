/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import vue.hud.chatbox.chattext.vChatText;
import vue.hud.chatbox.chattext.vChatTextBox;
import static vue.hud.vHud.defaultSkin;

/**
 * vChat.java
 *
 */
public abstract class vChat extends ScrollPane {

	private final vChatTextBox textbox;

	private boolean edited;

	public vChat(int _width) {
		super(new vChatTextBox(), defaultSkin);
		textbox = (vChatTextBox) this.getWidget();
		textbox.setWidth(_width);
		edited = false;

		setForceScroll(false, true);
		setFlickScroll(false);
		setOverscroll(false, true);
	}

	public void addText(String text, vChatText.ChatTextType type) {
		textbox.addText(text, type);
		edited = true;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (edited) {
			edited = false;
			layout();
			setScrollPercentY(1);
		}
	}

	public vChatTextBox getTextbox() {
		return textbox;
	}

}
