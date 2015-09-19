/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import controleur.cCombat;
import gameplay.entite.EntiteActive;
import general.Tourable;
import vue.hud.Bloc;
import vue.hud.bulle.BulleListener;
import vue.hud.chatbox.chattext.vChatText;
import static vue.hud.vHud.defaultSkin;

/**
 * vChatBox.java
 *
 */
public class vChatBox extends Bloc implements Tourable {

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
		super("ChatBox", WIDTH, HEIGHT);
		setPosition(X, Y);

		vchatCombat = new vChatCombat(WIDTH, HEIGHT_COMBAT);
		vchatGeneral = new vChatGeneral(WIDTH, HEIGHT_GENERAL);

		splitpane = new SplitPane(vchatCombat, vchatGeneral, true, defaultSkin);

		defaults().fill();
		add(splitpane).expand().row();
		add(vchatGeneral.getTextfield());

		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "La chatbox est separee en deux : le chat combat, et le chat general.\n"
						+ "Le premier est entierement dedie aux combats.\n"
						+ "Le second est dedie aux discussions entre joueurs.";
			}
		});

//		debugAll();
	}

	@Override
	protected void render(Batch batch, float parentAlpha) {

	}

	@Override
	public void nouveauTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs) {
		vchatCombat.addText("Nouveau tour de " + entiteEnCours.getNom() + " ! Duree : " + entiteEnCours.getTempsAction().getTotal() / 1000 + "s.", vChatText.ChatTextType.COMBAT);
	}

	@Override
	public void finTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs) {
		vchatCombat.addText("Fin du tour de " + entiteEnCours.getNom() + " !", vChatText.ChatTextType.COMBAT);
	}

	@Override
	public void enTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs) {
	}

}
