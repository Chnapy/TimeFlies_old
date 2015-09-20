/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import controleur.Controleur;
import gameplay.entite.EntiteActive;
import general.Tourable;
import vue.hud.vHud;
import static vue.Vue.mouse_position;

/**
 * Curseur.java
 *
 */
public class Curseur extends Label implements Tourable {

	private int tempsActu;

	public Curseur() {
		super(null, vHud.defaultSkin);
		Pixmap cursor = new Pixmap(Gdx.files.internal("cursor_1.png"));
		
		Gdx.input.setCursorImage(cursor, 0, 0);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		setPosition(mouse_position.x + 12, mouse_position.y - 17);
	}

	@Override
	public void nouveauTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
		tempsActu = (int) (entiteEnCours.getTempsAction().getActu() / 1000);
		setText(tempsActu + "");
	}

	@Override
	public void finTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
		setText("");
	}

	@Override
	public void enTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
		if (tempsActu != (int) (entiteEnCours.getTempsAction().getActu() / 1000)) {
			tempsActu = (int) (entiteEnCours.getTempsAction().getActu() / 1000);
			setText(tempsActu + "");
		}
	}
}
