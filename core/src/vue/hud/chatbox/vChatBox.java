/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

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

	//Position et taille du chat
	private static int X = 20;
	private static int Y = 12;
	private static int WIDTH = 500;
	private static int HEIGHT = 300;

	private final vChatCombat vchatCombat;
	private final vSeparateur vseparateur;
	private final vChatGeneral vchatGeneral;

	private final VerticalGroup vgroup;

	private boolean onResize;

	public vChatBox() {
		super("Chat", WIDTH, HEIGHT);
		setPosition(X, Y);

		vchatCombat = new vChatCombat(WIDTH, HEIGHT / 3 * 2 - 12);
		vseparateur = new vSeparateur(WIDTH, 10);
		vchatGeneral = new vChatGeneral(WIDTH, HEIGHT / 3);

		vgroup = new VerticalGroup();

		add(vgroup).fill().expand();
		vgroup.align(Align.top);
		vgroup.fill();
		vgroup.pad(0);
		vgroup.space(0);
		vgroup.addActor(vchatCombat);
		vgroup.addActor(vseparateur);
		vgroup.addActor(vchatGeneral);

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
//				vseparateur.setY(vchatCombat.getInitY() + y);
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

	}

	public void nouveauTour() {

	}

	public void finTour() {

	}

}
