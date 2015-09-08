/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import vue.hud.Bloc;
import vue.hud.chatbox.chattext.vChatText;
import static vue.hud.vHud.defaultSkin;

/**
 * vChatBox.java
 *
 */
public class vChatBox extends Bloc {

	//Position et taille du chatbox
	private static int X = 20;
	private static int Y = 12;
	private static int WIDTH = 500;
	private static int HEIGHT = 300;

	//Positions et taille des chats
	private static int HEIGHT_COMBAT = HEIGHT / 3 * 2;
	private static int HEIGHT_GENERAL = HEIGHT / 3;

	public final vChatCombat vchatCombat;
	public final vChatGeneral vchatGeneral;

	private final SplitPane splitpane;

	public vChatBox() {
		super("Chat", WIDTH, HEIGHT);
		setPosition(X, Y);

		vchatCombat = new vChatCombat(WIDTH, HEIGHT_COMBAT);
		vchatGeneral = new vChatGeneral(WIDTH, HEIGHT_GENERAL);

		splitpane = new SplitPane(vchatCombat, vchatGeneral, true, defaultSkin);

		defaults().fill();
		add(splitpane).expand().row();
		add(vchatGeneral.getTextfield());

//		debugAll();
	}

	@Override
	protected void render(Batch batch, float parentAlpha) {

	}

	public void nouveauTour() {
		vchatCombat.addText("Nouveau tour !", vChatText.ChatTextType.COMBAT);
	}

	public void finTour() {
		vchatCombat.addText("Fin du tour !", vChatText.ChatTextType.COMBAT);
	}

}
