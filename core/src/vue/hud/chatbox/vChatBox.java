/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import controleur.ControleurPrincipal;
import gameplay.entite.EntiteActive;
import general.Tourable;
import vue.hud.Bloc;
import vue.hud.bulle.BulleListener;
import vue.hud.chatbox.chattext.vChatText;
import vue.hud.chatbox.chattext.vChatText.ChatTextType;
import static vue.hud.vHud.defaultSkin;

/**
 * vChatBox.java
 *
 */
public class vChatBox extends Bloc implements Tourable {

	//Position et taille du chatbox
	private static final int X = 20;
	private static final int Y = 12;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 300;

	//Positions et taille des chats
	private static final int HEIGHT_COMBAT = HEIGHT / 3 * 2;
	private static final int HEIGHT_GENERAL = HEIGHT / 3;

	public static final vChatCombat vchatCombat = new vChatCombat(WIDTH, HEIGHT_COMBAT);
	public static final vChatGeneral vchatGeneral = new vChatGeneral(WIDTH, HEIGHT_GENERAL);

	private final SplitPane splitpane;

	public vChatBox(AssetManager manager) {
		super("ChatBox", WIDTH, HEIGHT, manager);
		setPosition(X, Y);

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
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		vchatCombat.addText("Nouveau tour de " + entiteEnCours.getNom() + " ! Duree : " + entiteEnCours.getTempsAction().getTotal() / 1000 + "s.", vChatText.ChatTextType.COMBAT);
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		vchatCombat.addText("Fin du tour de " + entiteEnCours.getNom() + " !", vChatText.ChatTextType.COMBAT);
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	public static void chatCombatPrint(String text, ChatTextType type) {
		vchatCombat.addText(text, type);
	}

	public static void chatGeneralPrint(String text, ChatTextType type) {
		vchatGeneral.addText(text, type);
	}

}
