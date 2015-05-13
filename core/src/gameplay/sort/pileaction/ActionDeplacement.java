package gameplay.sort.pileaction;

import java.awt.Point;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author ydardot
 * Action de déplacement passant par le chemin path
 */
public class ActionDeplacement extends Action {

	private Array<Point> path;

	public ActionDeplacement(Point p1, Array<Point> path) {
		super(p1);
		this.path = path;
	}

	/**
	 *
	 * @return le path a réaliser
	 */
	public Array<Point> getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "Déplacement de "+path.get(0)+" à "+path.get(path.size-1);
	}
}
