/*
 * 
 * 
 * 
 */
package vue.hud.chatbox.chattext;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import vue.Couleur;
import static vue.hud.vHud.defaultSkin;

/**
 * vChatText.java
 *
 */
public class vChatText extends Label {

	public vChatText(CharSequence text, ChatTextType type) {
		super(text, defaultSkin);
		setColor(type.getColor());
		setWrap(true);
	}

	public enum ChatTextType {

		IMPORTANT(Couleur.get("important", "hud", "chat")),
		GENERAL(Couleur.get("general", "hud", "chat")),
		MP(Couleur.get("mp", "hud", "chat")),
		COMBAT(Couleur.get("combat", "hud", "chat"));

		private Color color;

		ChatTextType(Color _color) {
			color = _color;
		}

		public Color getColor() {
			return color;
		}
	}

}
