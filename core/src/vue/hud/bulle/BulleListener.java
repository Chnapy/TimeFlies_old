/*
 * 
 * 
 * 
 */
package vue.hud.bulle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import test.MainTest;
import static vue.hud.vHud.bulle;

/**
 * BulleListener
 *
 */
public abstract class BulleListener extends ClickListener {

	private final Actor actor;
	private final Rectangle bounds;
	private Vector3 mousePosition;

	public BulleListener(Actor _actor) {
		actor = _actor;
		bounds = new Rectangle();
	}

	@Override
	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		if (fromActor == null || !fromActor.isDescendantOf(actor)) {
			bulle.hover(getBulleContent());
		}
	}

	@Override
	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		mousePosition = MainTest.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f));
		bounds.set(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
		if (bounds.contains(mousePosition.x, mousePosition.y)) {
			return;
		}
		bulle.out();
	}

	public abstract String getBulleContent();

}
