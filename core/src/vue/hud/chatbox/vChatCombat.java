/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import static vue.hud.chatbox.chattext.vChatText.ChatTextType.*;

/**
 * vChatCombat.java
 *
 */
public class vChatCombat extends vChat {

	public vChatCombat(int width, int height) {
		super(width);
		setSize(width, height);

		addText("Ce chat est reserve aux messages du combat. Le joueur ne peut pas y ajouter des messages. Ca sert de log pour les combats.", GENERAL);
		addText("Ce message est de type \"Combat\" ! (machin tape truc)", COMBAT);
		addText("Ce message est de type \"Important\" !", IMPORTANT);
	}

}
