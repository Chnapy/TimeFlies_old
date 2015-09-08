/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import vue.hud.chatbox.chattext.vChatText;
import static vue.hud.chatbox.chattext.vChatText.ChatTextType.*;
import static vue.hud.vHud.defaultSkin;

/**
 * vChatGeneral.java
 *
 */
public class vChatGeneral extends vChat {

	private final int HEIGHT_TEXTFIELD = 31;

	private final TextField textfield;

	public vChatGeneral(int width, int height) {
		super(width);
		setSize(width, height - HEIGHT_TEXTFIELD);
		textfield = new TextField("", defaultSkin);
		textfield.setHeight(HEIGHT_TEXTFIELD);
		textfield.addListener(new InputListener() {

			@Override
			public boolean keyTyped(InputEvent event, char character) {
				if (event.getKeyCode() == Input.Keys.ENTER) {
					addText(textfield.getText(), vChatText.ChatTextType.GENERAL);
					textfield.setText("");
				}
				return super.keyTyped(event, character);
			}

		});

		addText("Ce chat est reserve aux discussion entre les joueurs. C'est la ou le joueur peut discuter quoi.", GENERAL);
		addText("Voici un message de type \"General\".", GENERAL);
		addText("Voici un message de type \"Message prive\".", MP);
		addText("Ce message est de type \"Important\" !", IMPORTANT);
	}

	public TextField getTextfield() {
		return textfield;
	}

}
