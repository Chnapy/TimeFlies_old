/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import vue.hud.Bloc;

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
	private static int HEIGHT_SEPARATEUR = 10;
	private static int HEIGHT_COMBAT = HEIGHT / 3 * 2 - HEIGHT_SEPARATEUR - 2;
	private static int HEIGHT_GENERAL = HEIGHT / 3;

	private final vChatCombat vchatCombat;
	private final vSeparateur vseparateur;
	private final vChatGeneral vchatGeneral;

	private final VerticalGroup vgroup;

	private boolean hasRender;

	public vChatBox() {
		super("Chat", WIDTH, HEIGHT);
		setPosition(X, Y);

		vchatCombat = new vChatCombat(WIDTH, HEIGHT_COMBAT);
		vseparateur = new vSeparateur(WIDTH, HEIGHT_SEPARATEUR);
		vchatGeneral = new vChatGeneral(WIDTH, HEIGHT_GENERAL);

		vgroup = new VerticalGroup();

		add(vgroup).fill().expand();
		vgroup.align(Align.top);
		vgroup.fill();
		vgroup.pad(0);
		vgroup.space(0);
		vgroup.addActor(vchatCombat);
		vgroup.addActor(vchatCombat.getTextfield());
		vgroup.addActor(vseparateur);
		vgroup.addActor(vchatGeneral);
		vgroup.addActor(vchatGeneral.getTextfield());

		vseparateur.addListener(new DragListener() {
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				super.dragStop(event, x, y, pointer);
				vseparateur.setY(vseparateur.getInitY() + y);
				vseparateur.setVisible(true);
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				super.drag(event, x, y, pointer);
//				System.out.println(y);
				vchatCombat.setY(vchatCombat.getInitY() + y);
				vchatCombat.setHeight(vchatCombat.getInitHeight() - y);
				vchatGeneral.setHeight(vchatGeneral.getInitHeight() + y);
			}

			@Override
			public void dragStart(InputEvent event, float x, float y, int pointer) {
				super.dragStart(event, x, y, pointer);
				vseparateur.setVisible(false);
				vchatCombat.setInitHeight(vchatCombat.getHeight());
				vchatGeneral.setInitHeight(vchatGeneral.getHeight());
				vchatCombat.setInitY(vchatCombat.getY());
				vseparateur.setInitY(vseparateur.getY());
			}
		});

//		debugAll();
	}

	@Override
	protected void render(Batch batch, float parentAlpha) {
		if(!hasRender) {
			hasRender = true;
			vchatCombat.actuHeight();
			vchatGeneral.actuHeight();
		}
	}

	public void nouveauTour() {
		vchatCombat.addText("Nouveau tour !");
	}

	public void finTour() {
		vchatCombat.addText("Fin du tour !");
	}

}
