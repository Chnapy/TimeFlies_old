/*
 * 
 * 
 * 
 */
package vue.hud.chatbox.chattext;

import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import vue.hud.chatbox.chattext.vChatText.ChatTextType;

/**
 * vChatTextBox.java
 *
 */
public class vChatTextBox extends VerticalGroup {

	public vChatTextBox() {
		align(Align.left);
		fill();
	}

	public void addText(String text, ChatTextType type) {
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException();
		}

		vChatText label = new vChatText(text, type);
		this.addActor(label);
	}

}
