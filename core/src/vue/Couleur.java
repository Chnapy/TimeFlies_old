/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Couleur.java
 *
 */
public class Couleur {

	private static final XmlReader reader = new XmlReader();
	public static Element couleurs;

	static {
		try {
			couleurs = reader.parse(Gdx.files.internal("xml/couleur.xml"));
		} catch (IOException ex) {
			Logger.getLogger(Couleur.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static Color get(String color, String... child) {
		Element elem = couleurs;
		for (String enf : child) {
			if (!enf.isEmpty()) {
				elem = elem.getChildByName(enf);
			}
		}
		return Color.valueOf(elem.get(color));
	}

	public static Color get(String color) {
		return get(color, "");
	}

}
