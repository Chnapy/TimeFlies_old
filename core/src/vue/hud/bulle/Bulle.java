/*
 * 
 * 
 * 
 */
package vue.hud.bulle;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip.TextTooltipStyle;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import static test.MainTest.MAX_HEIGHT;
import static test.MainTest.MAX_WIDTH;
import static vue.hud.vHud.defaultSkin;
import static vue.Vue.mouse_position;

/**
 * Bulle.java
 *
 */
public class Bulle extends Container {

	private static final int MAXI_WIDTH = 500;
	private static final String MESSAGE_DEFAULT = "Garder la touche CTRL appuyée et glissez votre souris sur un element pour decouvrir sa fonction.";

	private final Label label;

	public Bulle() {
		TextTooltipStyle style = defaultSkin.get(TextTooltipStyle.class);
		label = new Label(null, style.label);
		label.setWrap(true);

		setActor(label);
		setBackground(style.background);
		setVisible(false);
		setTouchable(Touchable.disabled);
		hover(MESSAGE_DEFAULT);
//		debugAll();
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (UIUtils.ctrl()) {
			setPosition(
					mouse_position.x + getWidth() > MAX_WIDTH ? MAX_WIDTH - getWidth() : (mouse_position.x >= 0 ? mouse_position.x : 0),
					mouse_position.y + getHeight() > MAX_HEIGHT ? MAX_HEIGHT - getHeight() : (mouse_position.y >= 0 ? mouse_position.y : 0));
			toFront();
			if (!isVisible()) {
				setVisible(true);
			}
		} else if (isVisible()) {
			setVisible(false);
		}
	}

	public void hover(String text) {
		if (!text.equals(label.getText().toString())) {
			label.setText(text);
			label.pack();
			if (label.getGlyphLayout().width == 0 || label.getGlyphLayout().width > MAXI_WIDTH) {
				width(MAXI_WIDTH);
			} else {
				width(label.getGlyphLayout().width);
			}
			pack();
			pack(); // Second pack is needed to recompute the label pref height in case the label wrapped on the first pack.
		}
	}

	public void out() {
		hover(MESSAGE_DEFAULT);
	}

}
